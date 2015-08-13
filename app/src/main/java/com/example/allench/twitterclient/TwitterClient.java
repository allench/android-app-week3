package com.example.allench.twitterclient;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
    public static final String REST_URL = "https://api.twitter.com/1.1";
    public static final String REST_CONSUMER_KEY = "OFCFGzgU9UcXh5Bfuj8bFZGor";
    public static final String REST_CONSUMER_SECRET = "zgrlUWqZjzOvzyDhMOu23p17Y3GruRYGQYqRN2ISReNtVRGlqI";
    public static final String REST_CALLBACK_URL = "oauth://cptwitterclient";

    private int DEFAULT_COUNT = 50;

    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    // AccountInfo
    public void getAccountInfo(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("account/verify_credentials.json");
        RequestParams params = new RequestParams();
        getClient().get(apiUrl, params, handler);
    }

    // NewTweet
    public void postNewTweet(AsyncHttpResponseHandler handler, String description) {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", description);
        getClient().post(apiUrl, params, handler);
    }

    // HomeTimeline
    public void getHomeTimeline(AsyncHttpResponseHandler handler) {
        getHomeTimeline(handler, 0);
    }

    public void getHomeTimeline(AsyncHttpResponseHandler handler, long max_id) {
        getHomeTimeline(handler, max_id, 1);
    }

    public void getHomeTimeline(AsyncHttpResponseHandler handler, long max_id, long since_id) {
        String apiUrl = getApiUrl("statuses/home_timeline.json");
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        if (max_id > 0) {
            params.put("max_id", max_id - 1);
        }
        if (since_id > 0) {
            params.put("since_id", since_id);
        }
        params.put("count", DEFAULT_COUNT);
        getClient().get(apiUrl, params, handler);
    }

	/* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e RequestParams params = new RequestParams("foo", "bar");
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler);
	 *    i.e client.post(apiUrl, params, handler);
	 */
}