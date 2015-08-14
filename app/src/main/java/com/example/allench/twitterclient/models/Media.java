package com.example.allench.twitterclient.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
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
*/
@Table(name = "Media")
public class Media extends Model {
    @Column(name = "type")
    public String type;
    @Column(name = "media_url")
    public String media_url;
    @Column(name = "display_url")
    public String display_url;

    public static Media fromJSON(JSONObject json) {
        Media media = null;
        try {
            media = new Media();
            media.type = json.getString("type");
            media.media_url = json.getString("media_url");
            media.display_url = json.getString("display_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return media;
    }

    public static ArrayList<Media> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Media> medias = new ArrayList<>();

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    Media media = Media.fromJSON(jsonArray.getJSONObject(i));
                    if (media != null) {
                        medias.add(media);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }

        return medias;
    }
}
