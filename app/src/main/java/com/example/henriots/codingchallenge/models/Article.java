package com.example.henriots.codingchallenge.models;

/**
 * Created by henriots on 20/11/2017.
 */

public class Article {

    /** Thumbnail of regular article. */
    private String mThumbnail;

    /** Image of large top article */
    private String mImage;

    /** Title of article. */
    private String mTitle;

    /** Publishing date of article*/
    private String mPubDate;

    public String getmThumbnail() {
        return mThumbnail;
    }

    public void setmThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPubDate() {
        return mPubDate;
    }

    public void setmPubDate(String mPubDate) {
        this.mPubDate = mPubDate;
    }
}
