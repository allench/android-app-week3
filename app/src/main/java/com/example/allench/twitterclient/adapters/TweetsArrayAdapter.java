package com.example.allench.twitterclient.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.allench.twitterclient.fragments.TweetItemFragment;
import com.example.allench.twitterclient.models.Tweet;

import java.util.List;

public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate UI by using subclassing pattern
        // [DOC] https://www.bignerdranch.com/blog/customizing-android-listview-rows-subclassing/
        TweetItemFragment tweetView = (TweetItemFragment) convertView;
        if (tweetView == null) {
            tweetView = TweetItemFragment.inflate(parent);
        }
        tweetView.setItem(getItem(position));
        return tweetView;
    }

//    private class ViewHolder {
//        ImageView ivLogo;
//        TextView tvName;
//        TextView tvDescription;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // inflate UI by using ViewHolder pattern
//        ViewHolder vh;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tweet_item, parent, false);
//            vh  = new ViewHolder();
//            vh.ivLogo = (ImageView) convertView.findViewById(R.id.ivLogo);
//            vh.tvName = (TextView) convertView.findViewById(R.id.tvName);
//            vh.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
//            convertView.setTag(vh);
//        } else {
//            vh = (ViewHolder) convertView.getTag();
//        }
//        // load data
//        Tweet tweet = getItem(position);
//        vh.ivLogo.setImageResource(android.R.color.transparent);
//        Picasso.with(getContext()).load(tweet.user.profile_image_url).into(vh.ivLogo);
//        vh.tvName.setText(tweet.user.username);
//        vh.tvDescription.setText(tweet.text);
//        return convertView;
//    }
}
