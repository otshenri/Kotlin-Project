package com.example.henriots.codingchallenge.models

import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by henriots on 22/11/2017.
 */
class ArticleParser {
    /** Class tag. */
    val TAG = ArticleParser::class.java.simpleName;

    /** JSON path names. */
    val kThumbnail = "thumbnail"
    val kpubDate = "pubDate"
    val kTitle = "title"
    val kEnclosure = "enclosure"
    val kLink = "link"

    /**
     *@return article list
     */
    fun parseArticles(json: JSONObject) : List<Article> {
        var articles:MutableList<Article> = ArrayList<Article>()

        var items: JSONArray = json.getJSONArray("items");
        for (i in 0..items.length()-1) {
            var article:Article = Article()

            var item:JSONObject = items.getJSONObject(i)
            article.thumbnail = item.getString(kThumbnail);
            article.pubDate = item.getString(kpubDate);
            article.title = item.getString(kTitle);
            var enclosure = item.optJSONObject(kEnclosure)
            if (enclosure != null) {
                article.image = enclosure.getString(kLink)
            }
            articles.add(article)
        }

        return articles
    }
}