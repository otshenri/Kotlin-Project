package com.example.henriots.codingchallenge.models

import android.util.Log

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*



/**
 * Created by henriots on 22/11/2017.
 */

class Article {
    /** The class tag.  */
    val TAG = Article::class.java.simpleName;
    /** Date and time format from JSON.  */
    val ORIGINAL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    /** Date format to be displayed.  */
    val DATE_FORMAT = "MMM d, yyyy"
    /** Time formate to be displayed.  */
    val TIME_FORMAT = "hh:mm a"


    /** Thumbnail of regular article.  */
    var thumbnail: String? = null

    /** Image of large top article  */
    var image: String? = null

    /** Title of article.  */
    var title: String? = null

    /** Publishing date of article */
    var pubDate: String? = null

    /**
     * Sets the initial amount that the view is scrolled
     * @return formatted date only
     */
    fun getDate(): String? {
        val format = SimpleDateFormat(ORIGINAL_DATETIME_FORMAT)

        /** Return null if pubDate is null or if unable to parse pubDate.  */
        if (pubDate != null && dateParser(pubDate!!, format) != null) {
            val date = dateParser(pubDate!!, format)
            val formatter = SimpleDateFormat(DATE_FORMAT)
            return formatter.format(date)
        } else {
            return null
        }
    }

    /**
     * Sets the initial amount that the view is scrolled
     * @return formatted time only
     */
    fun getTime(): String? {
        val format = SimpleDateFormat(ORIGINAL_DATETIME_FORMAT)

        /** Return null if pubDate is null or if unable to parse pubDate.  */
        if (pubDate != null && dateParser(pubDate!!, format) != null) {
            val date = dateParser(pubDate!!, format)
            val formatter = SimpleDateFormat(TIME_FORMAT)
            return formatter.format(date)
        } else {
            return null
        }
    }

    /**
     * Universal string to Date parser method
     * @params string to parse, format as SimpleDateFormat
     */
    fun dateParser(date: String, format: SimpleDateFormat): Date? {
        try {
            return format.parse(date)
        } catch (ex: ParseException) {
            Log.e(TAG, ex.message)
            return null
        }
    }

    override fun toString(): String {
        return "Article(thumbnail=$thumbnail, image=$image, title=$title, pubDate=$pubDate)"
    }


}
