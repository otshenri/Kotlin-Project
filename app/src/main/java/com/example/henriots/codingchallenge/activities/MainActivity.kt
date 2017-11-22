package com.example.henriots.codingchallenge.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.content.DialogInterface
import android.os.Build
import android.support.v7.app.AlertDialog
import android.view.View

import com.example.henriots.codingchallenge.R
import com.example.henriots.codingchallenge.adapters.ArticleAdapter
import com.example.henriots.codingchallenge.network.ApiController
import com.example.henriots.codingchallenge.network.VolleyService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    /** The class tag.  */
    val TAG = MainActivity::class.java.simpleName;

    /** Adapter for article list.  */
    var articleAdapter: ArticleAdapter = ArticleAdapter(ArrayList())

    /** Network service */
    val service = VolleyService()

    /** API controller */
    val apiController = ApiController(service)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setSwipeRefreshListener()
    }

    override fun onResume() {
        super.onResume()
        updateArticleList()
    }

    /*
     * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
     * performs a swipe-to-refresh gesture.
     */
    fun setSwipeRefreshListener() {
        swiperefresh.setOnRefreshListener(
                SwipeRefreshLayout.OnRefreshListener {
                    Log.i(TAG, "onRefresh called")
                    updateArticleList()
                    swiperefresh.isRefreshing = false
                }
        )
    }

    /*
     * Sets layoutmanager and adapter for articles recyclerview.
     */
    fun setupRecyclerView() {
        recycler_view_articles.layoutManager = LinearLayoutManager(this,
                LinearLayout.VERTICAL, false)
        recycler_view_articles.adapter = articleAdapter
    }

    /*
     * Requests articles from web and refreshes article adapter
     */
    fun updateArticleList() {
        startSpinner()
        apiController.get(service.PATH) { response ->
            if (response != null) {
                val articles = service.jsonParser.parseArticles(response)
                articleAdapter.refresh(articles)
            } else {
                /** Failed to get articles, show alert and offer retry*/

                val builder: AlertDialog.Builder
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
                } else {
                    builder = AlertDialog.Builder(this)
                }
                builder.setTitle(getString(R.string.title_alert_unable_to_connect))
                        .setMessage(getString(R.string.message_alert_unable_to_connect))
                        .setPositiveButton(getString(R.string.btn_retry), DialogInterface.OnClickListener { dialog, which ->
                            // Try to update again
                            updateArticleList()
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
            }
            stopSpinner()
        }
    }

    /*
     * Starts loading spinner
     */
    fun startSpinner() {
        progress_bar.visibility = View.VISIBLE
    }

    /*
     * Stops loading spinner
     */
    fun stopSpinner() {
        progress_bar.visibility = View.GONE
    }
}
