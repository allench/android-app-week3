package com.example.allench.twitterclient.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.allench.twitterclient.R;
import com.example.allench.twitterclient.TwitterApplication;
import com.example.allench.twitterclient.TwitterClient;
import com.example.allench.twitterclient.adapters.TweetsArrayAdapter;
import com.example.allench.twitterclient.fragments.ComposeDialogFragment;
import com.example.allench.twitterclient.models.Media;
import com.example.allench.twitterclient.models.Tweet;
import com.example.allench.twitterclient.models.User;
import com.example.allench.twitterclient.utils.EndlessScrollListener;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends ActionBarActivity {

    private int INFINITE_SCROLL_VISIBLE_THRESHOILD = 5;
    private SwipeRefreshLayout ptrContainer;
    private ArrayList<Tweet> mTweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;
    private TwitterClient client;
    private long mMaxId = 0;
    private User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        // action bar icon
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // init mTweets adapter
        mTweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(this, mTweets);
        // init mTweets listview & bind infinite scroll event
        lvTweets = (ListView) findViewById(R.id.lvTweets);
        lvTweets.setAdapter(aTweets);
        lvTweets.setOnScrollListener(new EndlessScrollListener(INFINITE_SCROLL_VISIBLE_THRESHOILD) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                fetchHomeTimeline();
            }
        });

        // init Pull-To-Refresh Container
        ptrContainer = (SwipeRefreshLayout) findViewById(R.id.ptrContainer);
        ptrContainer.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        // setup refresh listener which triggers new data loading
        ptrContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // reload mTweets
                reloadHomeTimeline();
            }
        });

        // init twitter client
        client = TwitterApplication.getRestClient();
        // fetch account info
        fetchAccountInfo();
        // fetch mTweets
        reloadHomeTimeline();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isNetOK = (activeNetworkInfo != null) && activeNetworkInfo.isConnectedOrConnecting();
        // if network is unavailable, show a Toast to user
        if (!isNetOK) {
            Toast.makeText(TimelineActivity.this, "Internet Connection is NOT available!", Toast.LENGTH_LONG).show();
        }
        return isNetOK;
    }

    private void fetchAccountInfo() {
        if (!isNetworkAvailable()) {
            loadStatusFromSQLite();
            return;
        }
        client.getAccountInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                mUser = User.fromJSON(json);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(TimelineActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void reloadHomeTimeline() {
        mMaxId = 0;
        fetchHomeTimeline();
    }

    private void fetchHomeTimeline() {
        if (!isNetworkAvailable()) {
            loadStatusFromSQLite();
            return;
        }
        // if end, do nothing, just exit
        if (mMaxId == -1) {
            return;
        }
        // do async http request
        client.getHomeTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // if first time, clear list
                if (mMaxId == 0) {
                    aTweets.clear();
                }
                // load mTweets into adapter
                aTweets.addAll(Tweet.fromJSONArray(response));
                // record max_id from last tweet
                mMaxId = mTweets.get(mTweets.size() - 1).id;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(TimelineActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                // mark as the end
                mMaxId = -1;
            }

            @Override
            public void onFinish() {
                super.onFinish();
                // show refreshing indicator
                ptrContainer.setRefreshing(false);
            }
        }, mMaxId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // compose a new tweet
        if (id == R.id.action_compose) {
            // init dialog fields
            ComposeDialogFragment dialog = ComposeDialogFragment.newInstance(mUser);
            // bind dialog button click
            dialog.setOnButtonClickListener(new ComposeDialogFragment.OnButtonClickListener() {
                @Override
                public void onButtonApplyClick(String description) {
                    client.postNewTweet(new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                            // directly insert the new tweet into adapter
                            aTweets.insert(Tweet.fromJSON(json), 0);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                            Toast.makeText(TimelineActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }, description);
                }
            });
            dialog.show(getSupportFragmentManager(), "fragment_compose_dialog");
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveStatusIntoSQLite();
    }

    private void saveStatusIntoSQLite() {
        // clean old data
        new Delete().from(User.class).execute();
        new Delete().from(Tweet.class).execute();
        new Delete().from(Media.class).execute();
        // save account info
        mUser.save();
        // save all mTweets
        for (int i = 0; i < mTweets.size(); i++) {
            Tweet tweet = mTweets.get(i);
            for (int k = 0; k < tweet.medias.size(); k++) {
                tweet.medias.get(k).save();
            }
            tweet.user.save();
            tweet.save();
        }
    }

    private void loadStatusFromSQLite() {
        // load account info
        User user = new Select().from(User.class).executeSingle();
        if (user != null) { mUser = user; }
        // load tweets
        List<Tweet> tweets = new Select().from(Tweet.class).execute();
        if (tweets != null) {
            mTweets.addAll(tweets);
        }
    }

}
