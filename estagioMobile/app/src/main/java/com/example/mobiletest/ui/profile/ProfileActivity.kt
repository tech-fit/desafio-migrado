package com.example.mobiletest.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.R
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.Profile
import com.example.mobiletest.data.ProfileDetails
import com.example.mobiletest.ui.post.PostActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    //Responsável por fornecer os dados e controlar a lógica para a Activity
    private val presenter = ProfilePresenter()

    //Responsável por gerenciar o Profile
    private lateinit var adapter: ProfileAdapter

    companion object {
        const val PROFILE_EXTRAS = "profileExtras"
    }

    private lateinit var profile: Profile

    //Unidades de códigos que são chamadas em casos específicos
    private lateinit var onGetPostSuccess: (ProfileDetails) -> Unit
    private lateinit var onGetMorePostSuccess: (ProfileDetails) -> Unit
    private lateinit var onGetPostError: () -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        adapter = ProfileAdapter(this,
            onItemPostClick = { post ->
                goToPostDetailsActivity(post)
            }
        )

        val extras = intent.extras

        if (extras != null && extras.containsKey(ProfileActivity.PROFILE_EXTRAS)) {
            //Recuperar parâmetro passado entre activities
            profile = extras.getSerializable(ProfileActivity.PROFILE_EXTRAS) as Profile
        }

        setupToolbar(resources.getString(R.string.profile))

        // Configuração da Lista que receberá os posts
        val manager = GridLayoutManager(this, 3)
        // Manipulação do primeiro grid para ocupar a primeira linha com o header
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    0 -> 3
                    else -> 1
                }
            }
        }
        rv_profile.layoutManager = manager
        rv_profile.adapter = adapter

        //Listener para saber se a lista está proxima do fim e assim carregar mais posts
        rv_profile.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager
                val totalItemCount = gridLayoutManager.itemCount
                val lastVisible = gridLayoutManager.findLastVisibleItemPosition()

                //Carrega mais Posts na lista se o último Post visível estiver entre os 5 últimos da lista atual
                val endHasBeenReached = lastVisible + 15 >= totalItemCount
                if (totalItemCount > 0 && endHasBeenReached) {
                    getMorePosts()
                }
            }
        })

        onGetPostSuccess = { profileDetails: ProfileDetails ->
            showLoading(false)
            adapter.updatePost(profileDetails, true)
        }

        onGetMorePostSuccess = { profileDetails: ProfileDetails ->
            showLoading(false)
            adapter.updatePost(profileDetails, false)
        }

        onGetPostError = {
            showLoading(false)
            showMessage(resources.getString(R.string.mini_post_load_error))
        }

        if (::profile.isInitialized) {
            getPost()
        }

        swipeContainerProfile.setOnRefreshListener {
            adapter.updatePost(null, true)
            getPost()
        }
    }

    private fun setupToolbar(name: String) {
        supportActionBar?.let { actionBar ->
            actionBar.title = name
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getPost() {
        showLoading(true)
        presenter.getPosts(profile.id, onGetPostSuccess, onGetPostError)
    }

    private fun getMorePosts() {
        showLoading(true)
        presenter.getMorePosts(profile.id, onGetMorePostSuccess, onGetPostError)
    }

    private fun showLoading(show: Boolean) {
        swipeContainerProfile.setRefreshing(show)
    }

    private fun showMessage(message: String) {
        Snackbar.make(rootMainLayout, message, Snackbar.LENGTH_LONG).show()
    }

    private fun goToPostDetailsActivity(post: Post) {

        val postIntent = Intent(this, PostActivity::class.java)
        val bundle = Bundle()

        //Passagem de parâmetro entre activities
        bundle.putSerializable(PostActivity.POST_EXTRAS, post)
        postIntent.putExtras(bundle)
        startActivity(postIntent)
    }

}
