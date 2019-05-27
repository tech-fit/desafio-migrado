package com.example.mobiletest.ui.feed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobiletest.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class FeedActivity : AppCompatActivity() {

    private val presenter = FeedPresenter()
    private lateinit var adapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FeedAdapter(this,
            onItemProfileClick = { profile, post ->

            },
            onPostBodyClick = { post->

            }
        )

        recyclerMain.layoutManager = LinearLayoutManager(this)
        recyclerMain.adapter = adapter

        presenter.getPosts(
            onSuccess = {feed->
                adapter.updatePosts(feed.items, true)
            },
            onError = {
                showSnackBar("Erro ao carregar o feed")
            }
        )

    }

    private fun showSnackBar(message: String) {
        Snackbar.make(rootMainLayout, message, Snackbar.LENGTH_LONG).show()
    }
}
