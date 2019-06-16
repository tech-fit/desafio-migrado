package com.example.mobiletest.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.R
import com.example.mobiletest.data.Feed
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.Profile
import com.example.mobiletest.ui.post.PostActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.item_profile.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var profile: Profile
    private lateinit var presenter: ProfilePresenter
    private lateinit var adapter: ProfileAdapter

    companion object {
        const val POST_EXTRAS = "postExtras"
        const val PROFILE_EXTRAS = "profileExtras"
    }


    private lateinit var onGetPostsSuccess: (Feed) -> Unit
    private lateinit var onGetMorePostsSuccess: (feed: Feed) -> Unit
    private lateinit var onGetPostsError: () -> Unit



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setupToolbar(resources.getString(R.string.profile))

        val extras = intent.extras


        if (extras != null && extras.containsKey(PROFILE_EXTRAS)) {
            //Recuperar parâmetro passado entre activities
            profile = extras.getSerializable(PROFILE_EXTRAS) as Profile
        }


        presenter = ProfilePresenter(profile.id)

        bindProfileInfos(profile = profile)

        adapter = ProfileAdapter(this,
            onPostBodyClick = { post, profile->
                goToPostDetailsActivity(post, profile)
            }
        )

        //Configuração da Lista que receberá os Posts
        recyclerProfile.layoutManager = GridLayoutManager(this, 3)
        recyclerProfile.adapter = adapter

        //Listener para saber se a lista está proxima do fim e assim carregar mais Posts
        recyclerProfile.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
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
        bundle.putSerializable(POST_EXTRAS, post)
        bundle.putSerializable(PROFILE_EXTRAS, profile)
        startActivity(postIntent)
    }

    private fun setupToolbar(name: String) {
        supportActionBar?.let { actionBar ->
        actionBar.title = name

            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
        }
    }

    private fun bindProfileInfos(profile: Profile){
        val name: TextView = findViewById(R.id.nameTextView)
        val goal: TextView = findViewById(R.id.generalGoalTextView)
        val image: ImageView = findViewById(R.id.personProfileImage)

        name.text = profile.name
        goal.text = profile.generalGoal
        Picasso.get()
            .load(profile.image).placeholder(R.drawable.ic_account_circle_black_24dp)
            .into(image)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getInitialPosts() {
        showLoading(true)
        presenter.getPosts(onGetPostsSuccess, onGetPostsError, this.profile.id)
    }

    private fun geMorePosts() {
        showLoading(true)
        presenter.getMorePosts(onGetMorePostsSuccess, onGetPostsError)
    }

    private fun showLoading(show: Boolean) {
        swipeContainer.setRefreshing(show)
    }

    private fun showMessage(message: String) {
        Snackbar.make(rootProfileLayout, message, Snackbar.LENGTH_LONG).show()
    }

}
