package com.example.allench.twitterclient.models;

import org.json.JSONException;
import org.json.JSONObject;

/*
{
  "created_at": "Wed Aug 12 16:54:19 +0000 2015",
  "id": 631509028821037000,
  "id_str": "631509028821037057",
  "text": "HAHA",
  "source": "<a href=\"http://codepath.com\" rel=\"nofollow\">aLleN's CodePath OAuth Demo</a>",
  "truncated": false,
  "in_reply_to_status_id": null,
  "in_reply_to_status_id_str": null,
  "in_reply_to_user_id": null,
  "in_reply_to_user_id_str": null,
  "in_reply_to_screen_name": null,
  "user": {
    "id": 15113708,
    "id_str": "15113708",
    "name": "黑輪醬",
    "screen_name": "allen0960",
    "location": "Taiwan",
    "description": "",
    "url": null,
    "entities": {
      "description": {
        "urls": []
      }
    },
    "protected": false,
    "followers_count": 117,
    "friends_count": 100,
    "listed_count": 0,
    "created_at": "Sat Jun 14 02:35:53 +0000 2008",
    "favourites_count": 1,
    "utc_offset": 28800,
    "time_zone": "Taipei",
    "geo_enabled": true,
    "verified": false,
    "statuses_count": 824,
    "lang": "en",
    "contributors_enabled": false,
    "is_translator": false,
    "is_translation_enabled": false,
    "profile_background_color": "1A1B1F",
    "profile_background_image_url": "http://pbs.twimg.com/profile_background_images/16624278/ice-cream-mice.jpg",
    "profile_background_image_url_https": "https://pbs.twimg.com/profile_background_images/16624278/ice-cream-mice.jpg",
    "profile_background_tile": false,
    "profile_image_url": "http://pbs.twimg.com/profile_images/57039617/cat_normal.jpg",
    "profile_image_url_https": "https://pbs.twimg.com/profile_images/57039617/cat_normal.jpg",
    "profile_link_color": "2FC2EF",
    "profile_sidebar_border_color": "181A1E",
    "profile_sidebar_fill_color": "252429",
    "profile_text_color": "666666",
    "profile_use_background_image": true,
    "has_extended_profile": false,
    "default_profile": false,
    "default_profile_image": false,
    "following": false,
    "follow_request_sent": false,
    "notifications": false
  },
  "geo": null,
  "coordinates": null,
  "place": null,
  "contributors": null,
  "is_quote_status": false,
  "retweet_count": 0,
  "favorite_count": 0,
  "entities": {
    "hashtags": [],
    "symbols": [],
    "user_mentions": [],
    "urls": []
  },
  "favorited": false,
  "retweeted": false,
  "lang": "tl"
}
*/

public class User {
    public String userid;
    public String username;
    public String profile_image_url;

    public static User fromJSON(JSONObject json) {
        User user = null;
        try {
            user = new User();
            user.userid = json.getString("screen_name");
            user.username = json.getString("name");
            user.profile_image_url = json.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
