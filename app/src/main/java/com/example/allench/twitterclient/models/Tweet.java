package com.example.allench.twitterclient.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ocpsoft.prettytime.PrettyTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/*
{
  "created_at": "Thu Aug 13 03:01:37 +0000 2015",
  "id": 631661859880370200,
  "id_str": "631661859880370176",
  "text": "RT @Flickr: 捕獲野生莉亞公主？ Princess Leia arrested. \n(Photo CC by piotr mamnaimie)\nhttp://t.co/GIjNkw0pIE http://t.co/6DRoPLfaiu",
  "source": "...",
  "truncated": false,
  "in_reply_to_status_id": null,
  "in_reply_to_status_id_str": null,
  "in_reply_to_user_id": null,
  "in_reply_to_user_id_str": null,
  "in_reply_to_screen_name": null,
  "user": {
    "id": 2608241,
    "id_str": "2608241",
    "name": "Tenz科科笑",
    "screen_name": "tenz",
    "location": "Taipei, Taiwan",
    "description": "你確定要follow這傢伙嗎？",
    "url": "http://t.co/kVcyzkY1ox",
    "entities": {
      "url": {
        "urls": [
          {
            "url": "http://t.co/kVcyzkY1ox",
            "expanded_url": "http://wp.tenz.net",
            "display_url": "wp.tenz.net",
            "indices": [
              0,
              22
            ]
          }
        ]
      },
      "description": {
        "urls": []
      }
    },
    "protected": false,
    "followers_count": 3474,
    "friends_count": 789,
    "listed_count": 117,
    "created_at": "Wed Mar 28 03:36:15 +0000 2007",
    "favourites_count": 3795,
    "utc_offset": 28800,
    "time_zone": "Taipei",
    "geo_enabled": true,
    "verified": false,
    "statuses_count": 23685,
    "lang": "en",
    "contributors_enabled": false,
    "is_translator": false,
    "is_translation_enabled": false,
    "profile_background_color": "022330",
    "profile_background_image_url": "http://abs.twimg.com/images/themes/theme15/bg.png",
    "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme15/bg.png",
    "profile_background_tile": false,
    "profile_image_url": "http://pbs.twimg.com/profile_images/565431343060746240/xqA8YU8D_normal.jpeg",
    "profile_image_url_https": "https://pbs.twimg.com/profile_images/565431343060746240/xqA8YU8D_normal.jpeg",
    "profile_banner_url": "https://pbs.twimg.com/profile_banners/2608241/1398224846",
    "profile_link_color": "0084B4",
    "profile_sidebar_border_color": "A8C7F7",
    "profile_sidebar_fill_color": "C0DFEC",
    "profile_text_color": "333333",
    "profile_use_background_image": true,
    "has_extended_profile": false,
    "default_profile": false,
    "default_profile_image": false,
    "following": true,
    "follow_request_sent": false,
    "notifications": false
  },
  "geo": null,
  "coordinates": null,
  "place": null,
  "contributors": null,
  "retweeted_status": {
    "created_at": "Tue Aug 11 07:00:01 +0000 2015",
    "id": 630997079737925600,
    "id_str": "630997079737925632",
    "text": "捕獲野生莉亞公主？ Princess Leia arrested. \n(Photo CC by piotr mamnaimie)\nhttp://t.co/GIjNkw0pIE http://t.co/6DRoPLfaiu",
    "source": "...",
    "truncated": false,
    "in_reply_to_status_id": null,
    "in_reply_to_status_id_str": null,
    "in_reply_to_user_id": null,
    "in_reply_to_user_id_str": null,
    "in_reply_to_screen_name": null,
    "user": {
      "id": 21237045,
      "id_str": "21237045",
      "name": "Flickr",
      "screen_name": "Flickr",
      "location": "San Francisco, CA",
      "description": "Stunning photos and stories, event announcements, latest news, and much more from within the Flickr community. Need support? Let us know at @FlickrHelp",
      "url": "http://t.co/c9twUy4cn8",
      "entities": {
        "url": {
          "urls": [
            {
              "url": "http://t.co/c9twUy4cn8",
              "expanded_url": "http://flickr.com",
              "display_url": "flickr.com",
              "indices": [
                0,
                22
              ]
            }
          ]
        },
        "description": {
          "urls": []
        }
      },
      "protected": false,
      "followers_count": 900138,
      "friends_count": 277,
      "listed_count": 11484,
      "created_at": "Wed Feb 18 20:27:45 +0000 2009",
      "favourites_count": 686,
      "utc_offset": -25200,
      "time_zone": "Pacific Time (US & Canada)",
      "geo_enabled": true,
      "verified": true,
      "statuses_count": 7843,
      "lang": "en",
      "contributors_enabled": false,
      "is_translator": false,
      "is_translation_enabled": false,
      "profile_background_color": "F3F5F6",
      "profile_background_image_url": "http://pbs.twimg.com/profile_background_images/880723066/df6ecc8d001b50aeb1dc15ac59d0b593.png",
      "profile_background_image_url_https": "https://pbs.twimg.com/profile_background_images/880723066/df6ecc8d001b50aeb1dc15ac59d0b593.png",
      "profile_background_tile": false,
      "profile_image_url": "http://pbs.twimg.com/profile_images/530137407572561920/hE5ieIjF_normal.jpeg",
      "profile_image_url_https": "https://pbs.twimg.com/profile_images/530137407572561920/hE5ieIjF_normal.jpeg",
      "profile_banner_url": "https://pbs.twimg.com/profile_banners/21237045/1439197177",
      "profile_link_color": "212124",
      "profile_sidebar_border_color": "FFFFFF",
      "profile_sidebar_fill_color": "FFFFFF",
      "profile_text_color": "333333",
      "profile_use_background_image": false,
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
    "retweet_count": 37,
    "favorite_count": 55,
    "entities": {
      "hashtags": [],
      "symbols": [],
      "user_mentions": [],
      "urls": [
        {
          "url": "http://t.co/GIjNkw0pIE",
          "expanded_url": "http://bit.ly/1KNeAo2",
          "display_url": "bit.ly/1KNeAo2",
          "indices": [
            65,
            87
          ]
        }
      ],
      "media": [
        {
          "id": 630997079586943000,
          "id_str": "630997079586942976",
          "indices": [
            88,
            110
          ],
          "media_url": "http://pbs.twimg.com/media/CMHAgC8WsAAcx4G.jpg",
          "media_url_https": "https://pbs.twimg.com/media/CMHAgC8WsAAcx4G.jpg",
          "url": "http://t.co/6DRoPLfaiu",
          "display_url": "pic.twitter.com/6DRoPLfaiu",
          "expanded_url": "http://twitter.com/Flickr/status/630997079737925632/photo/1",
          "type": "photo",
          "sizes": {
            "small": {
              "w": 340,
              "h": 226,
              "resize": "fit"
            },
            "thumb": {
              "w": 150,
              "h": 150,
              "resize": "crop"
            },
            "medium": {
              "w": 600,
              "h": 400,
              "resize": "fit"
            },
            "large": {
              "w": 1024,
              "h": 683,
              "resize": "fit"
            }
          }
        }
      ]
    },
    "extended_entities": {
      "media": [
        {
          "id": 630997079586943000,
          "id_str": "630997079586942976",
          "indices": [
            88,
            110
          ],
          "media_url": "http://pbs.twimg.com/media/CMHAgC8WsAAcx4G.jpg",
          "media_url_https": "https://pbs.twimg.com/media/CMHAgC8WsAAcx4G.jpg",
          "url": "http://t.co/6DRoPLfaiu",
          "display_url": "pic.twitter.com/6DRoPLfaiu",
          "expanded_url": "http://twitter.com/Flickr/status/630997079737925632/photo/1",
          "type": "photo",
          "sizes": {
            "small": {
              "w": 340,
              "h": 226,
              "resize": "fit"
            },
            "thumb": {
              "w": 150,
              "h": 150,
              "resize": "crop"
            },
            "medium": {
              "w": 600,
              "h": 400,
              "resize": "fit"
            },
            "large": {
              "w": 1024,
              "h": 683,
              "resize": "fit"
            }
          }
        }
      ]
    },
    "favorited": false,
    "retweeted": false,
    "possibly_sensitive": false,
    "possibly_sensitive_appealable": false,
    "lang": "zh"
  },
  "is_quote_status": false,
  "retweet_count": 37,
  "favorite_count": 0,
  "entities": {
    "hashtags": [],
    "symbols": [],
    "user_mentions": [
      {
        "screen_name": "Flickr",
        "name": "Flickr",
        "id": 21237045,
        "id_str": "21237045",
        "indices": [
          3,
          10
        ]
      }
    ],
    "urls": [
      {
        "url": "http://t.co/GIjNkw0pIE",
        "expanded_url": "http://bit.ly/1KNeAo2",
        "display_url": "bit.ly/1KNeAo2",
        "indices": [
          77,
          99
        ]
      }
    ],
    "media": [
      {
        "id": 630997079586943000,
        "id_str": "630997079586942976",
        "indices": [
          100,
          122
        ],
        "media_url": "http://pbs.twimg.com/media/CMHAgC8WsAAcx4G.jpg",
        "media_url_https": "https://pbs.twimg.com/media/CMHAgC8WsAAcx4G.jpg",
        "url": "http://t.co/6DRoPLfaiu",
        "display_url": "pic.twitter.com/6DRoPLfaiu",
        "expanded_url": "http://twitter.com/Flickr/status/630997079737925632/photo/1",
        "type": "photo",
        "sizes": {
          "small": {
            "w": 340,
            "h": 226,
            "resize": "fit"
          },
          "thumb": {
            "w": 150,
            "h": 150,
            "resize": "crop"
          },
          "medium": {
            "w": 600,
            "h": 400,
            "resize": "fit"
          },
          "large": {
            "w": 1024,
            "h": 683,
            "resize": "fit"
          }
        },
        "source_status_id": 630997079737925600,
        "source_status_id_str": "630997079737925632",
        "source_user_id": 21237045,
        "source_user_id_str": "21237045"
      }
    ]
  },
  "extended_entities": {
    "media": [
      {
        "id": 630997079586943000,
        "id_str": "630997079586942976",
        "indices": [
          100,
          122
        ],
        "media_url": "http://pbs.twimg.com/media/CMHAgC8WsAAcx4G.jpg",
        "media_url_https": "https://pbs.twimg.com/media/CMHAgC8WsAAcx4G.jpg",
        "url": "http://t.co/6DRoPLfaiu",
        "display_url": "pic.twitter.com/6DRoPLfaiu",
        "expanded_url": "http://twitter.com/Flickr/status/630997079737925632/photo/1",
        "type": "photo",
        "sizes": {
          "small": {
            "w": 340,
            "h": 226,
            "resize": "fit"
          },
          "thumb": {
            "w": 150,
            "h": 150,
            "resize": "crop"
          },
          "medium": {
            "w": 600,
            "h": 400,
            "resize": "fit"
          },
          "large": {
            "w": 1024,
            "h": 683,
            "resize": "fit"
          }
        },
        "source_status_id": 630997079737925600,
        "source_status_id_str": "630997079737925632",
        "source_user_id": 21237045,
        "source_user_id_str": "21237045"
      }
    ]
  },
  "favorited": false,
  "retweeted": false,
  "possibly_sensitive": false,
  "possibly_sensitive_appealable": false,
  "lang": "zh"
}
*/
public class Tweet {
    public long id;
    public String text;
    public String created_at;
    public User user;
    public ArrayList<Media> medias;
    public int retweet_count;
    public int favourites_count;

    private static String getRelativeDateTimeString(String createdAt) {
        String result = "";
        try {
            DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);
            Date dtCreateAt = df.parse(createdAt);
            result = new PrettyTime().format(dtCreateAt).replaceAll(" ago", "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Tweet fromJSON(JSONObject json) {
        Tweet tweet = null;
        try {
            tweet = new Tweet();
            tweet.id = json.getLong("id");
            tweet.text = json.getString("text");
            tweet.created_at = getRelativeDateTimeString(json.getString("created_at"));
            tweet.user = User.fromJSON(json.getJSONObject("user"));
            tweet.medias = Media.fromJSONArray(json.getJSONObject("entities").getJSONArray("media"));
            tweet.retweet_count = json.getInt("retweet_count");
            tweet.favourites_count = json.getInt("favourites_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweet;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Tweet tweet = Tweet.fromJSON(jsonArray.getJSONObject(i));
                if (tweet != null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }

        return tweets;
    }
}
