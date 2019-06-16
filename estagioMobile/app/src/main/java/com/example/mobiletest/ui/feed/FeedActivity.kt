package com.example.mobiletest.ui.feed

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.R
import com.example.mobiletest.data.Feed
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.Profile
import com.example.mobiletest.ui.post.PostActivity
import com.example.mobiletest.ui.profile.ProfileActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class FeedActivity : AppCompatActivity() {

    //Repsonsável por fornecer os dados e controlar a lógica para a Activity
    private val presenter = FeedPresenter()

    //Responsável por gerenciar a lista de Posts
    private lateinit var adapter: FeedAdapter

    //Unidades de códigos que são chamadas em casos específicos
    private lateinit var onGetPostsSuccess: (Feed) -> Unit
    private lateinit var onGetMorePostsSuccess: (feed: Feed) -> Unit
    private lateinit var onGetPostsError: () -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapter = FeedAdapter(this,
                onItemProfileClick = { profile ->
                    goToProfileActivity(profile)
                },
                onPostBodyClick = { post, profile ->
                    goToPostDetailsActivity(post, profile)
                }
        )

        //Configuração da Lista que receberá os Posts
        recyclerMain.layoutManager = LinearLayoutManager(this)
        recyclerMain.adapter = adapter


        //Listener para saber se a lista está proxima do fim e assim carregar mais Posts
        recyclerMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisible = layoutManager.findLastVisibleItemPosition()

                //Carrega mais Posts na lista se o último Post visível estiver entre os 5 últimos da lista atual
                val endHasBeenReached = lastVisible + 5 >= totalItemCount
                if (totalItemCount > 0 && endHasBeenReached) {
                    geMorePosts()
                }
            }
        })

        onGetPostsSuccess = { feed: Feed ->
            showLoading(false)
            adapter.updatePosts(feed.items, true)
        }

        onGetMorePostsSuccess = { feed: Feed ->
            showLoading(false)
            adapter.updatePosts(feed.items, false)
        }

        onGetPostsError = {
            showLoading(false)
            showMessage(resources.getString(R.string.feed_load_error))
        }

        getInitialPosts()

        swipeContainer.setOnRefreshListener {
            adapter.updatePosts(mutableListOf(), true)
            getInitialPosts()
        }

    }

    private fun goToPostDetailsActivity(post: Post, profile: Profile) {

        val postIntent = Intent(this, PostActivity::class.java)
        val bundle = Bundle()

        //Passagem de parâmetro entre activities
        bundle.putSerializable(PostActivity.POST_EXTRAS, post)
        bundle.putSerializable(PostActivity.PROFILE_EXTRAS, profile)
        postIntent.putExtras(bundle)
        startActivity(postIntent)
    }

    private fun goToProfileActivity(profile: Profile) {
        val postIntent = Intent(this, ProfileActivity::class.java)
        val bundle = Bundle()

        //Passagem de parâmetro entre activities
        bundle.putSerializable(ProfileActivity.PROFILE_EXTRAS, profile)
        postIntent.putExtras(bundle)
        startActivity(postIntent)
    }

    private fun getInitialPosts() {
        showLoading(true)
        presenter.getPosts(onGetPostsSuccess, onGetPostsError)
    }

    private fun geMorePosts() {
        showLoading(true)
        presenter.getMorePosts(onGetMorePostsSuccess, onGetPostsError)
    }

    private fun showLoading(show: Boolean) {
        swipeContainer.setRefreshing(show)
    }

    private fun showMessage(message: String) {
        Snackbar.make(rootMainLayout, message, Snackbar.LENGTH_LONG).show()
    }
}
