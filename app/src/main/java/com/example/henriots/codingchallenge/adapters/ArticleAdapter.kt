package com.example.henriots.codingchallenge.adapters

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater

import com.example.henriots.codingchallenge.models.Article
import com.example.henriots.codingchallenge.R
import com.example.henriots.codingchallenge.network.VolleySingleton
import kotlinx.android.synthetic.main.item_article_large.view.*
import kotlinx.android.synthetic.main.item_article_regular.view.*


/**
 * Created by henriots on 22/11/2017.
 */

class ArticleAdapter(private var articles: List<Article>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    /** Large top article view type.  */
    val VIEW_TYPE_LARGE = 0
    /** Regular article view type.  */
    val VIEW_TYPE_REGULAR = 1

    override fun getItemViewType(position: Int): Int {
        return if (position.equals(0)) VIEW_TYPE_LARGE else VIEW_TYPE_REGULAR
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType.equals(VIEW_TYPE_LARGE)) {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_article_large, parent, false)
            return ViewHolderLarge(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_article_regular, parent, false)
            return ViewHolderRegular(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.getItemViewType().equals(VIEW_TYPE_LARGE)) {
            var viewHolderLarge: ViewHolderLarge = holder as ViewHolderLarge
            viewHolderLarge.bind(articles[position])
        } else {
            var viewHolderRegular: ViewHolderRegular = holder as ViewHolderRegular
            viewHolderRegular.bind(articles[position])
        }
    }

    override fun getItemCount() = articles.size

    /*
     * Method for refreshing list, can be initiated by pullToRefresh
     * This is a very basic refresh implementation as there was no
     * specification how refresh should behave. Another way would
     * be to compare old article list and new article list based on
     * 'guid' and do replacing by articles not by whole list. This
     * way we wouldn't have to reload every item every time.
     */
    fun refresh(articles: List<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    /*
     * Viewholder for large top article
     */
    class ViewHolderLarge(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(article: Article) {
            /** Setting publishing date.  */
            if (Build.VERSION.SDK_INT >= 24) {
                itemView.pub_date_large.text = Html.fromHtml("<b>" + article.getDate()
                        + "</b> " + article.getTime(), 0) // for 24 api and up
            } else {
                itemView.pub_date_large.text = Html.fromHtml("<b>" + article.getDate()
                        + "</b> " + article.getTime()) // or for older api
            }

            /** Setting title.  */
            itemView.title_large.text = article.title

            /** Downloading async and caching image.  */
            val imageLoader = VolleySingleton.instance?.imageLoader
            itemView.image_large.setImageUrl(article.image, imageLoader)
        }
    }

    /*
     * Viewholder for regular article
     */
    class ViewHolderRegular(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(article: Article) {
            /** Setting publishing date.  */
            if (Build.VERSION.SDK_INT >= 24) {
                itemView.pub_date_regular.text = Html.fromHtml("<b>" + article.getDate()
                        + "</b> " + article.getTime(), 0) // for 24 api and up
            } else {
                itemView.pub_date_regular.text = Html.fromHtml("<b>" + article.getDate()
                        + "</b> " + article.getTime()) // or for older api
            }

            /** Setting title.  */
            itemView.title_regular.text = article.title

            /** Downloading async and caching image.  */
            val imageLoader = VolleySingleton.instance?.imageLoader
            itemView.thumbnail_regular.setImageUrl(article.thumbnail, imageLoader)
        }
    }
}



