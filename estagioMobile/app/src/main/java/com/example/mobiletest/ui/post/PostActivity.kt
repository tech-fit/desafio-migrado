package com.example.mobiletest.ui.post

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobiletest.R
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.Profile
import com.example.mobiletest.ui.profile.ProfileActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_post.swipeContainerPost

class PostActivity : AppCompatActivity() {

    // Objeto que acompanha a activity para retornar dados do Post
    companion object {
        const val POST_EXTRAS = "postExtras"
    }

    private lateinit var post: Post

    //Responsável por fornecer os dados e controlar a lógica para a Activity
    private val presenter = PostPresenter()

    //Responsável por gerenciar a lista de Posts
    private lateinit var adapter: PostAdapter

    //Unidades de códigos que são chamadas em casos específicos
    private lateinit var onGetPostsSuccess: (Post) -> Unit
    private lateinit var onGetPostsError: () -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val extras = intent.extras

        if (extras != null && extras.containsKey(POST_EXTRAS)) {
            //Recuperar parâmetro passado entre activities
            post = extras.getSerializable(POST_EXTRAS) as Post
        }

        setupToolbar(resources.getString(R.string.post_details))

        adapter = PostAdapter(this,
                onItemProfileClick = { profile ->
                    goToProfileActivity(profile)
                },
                post = post
        )

        //Configuração da Lista que receberá os Posts(refeição e nutrientes)
        recyclerPost.layoutManager = LinearLayoutManager(this)
        recyclerPost.adapter = adapter

        onGetPostsSuccess = { post: Post ->
            showLoading(false)
            adapter.updateFoods(post.foods, true)
        }

        onGetPostsError = {
            showLoading(false)
            showMessage(resources.getString(R.string.post_load_error))
        }

        getInitialPosts()

        swipeContainerPost.setOnRefreshListener {
            adapter.updateFoods(mutableListOf(), true)
            getInitialPosts()
        }

    }

    private fun goToProfileActivity(profile: Profile) {
        val postIntent = Intent(this, ProfileActivity::class.java)
        val bundle = Bundle()

        //Passagem de parâmetro entre activities
        bundle.putSerializable(ProfileActivity.PROFILE_EXTRAS, profile)
        postIntent.putExtras(bundle)
        startActivity(postIntent)
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

    private fun getInitialPosts() {
        presenter.getPostDetail(onGetPostsSuccess, onGetPostsError, post.feedHash)
    }

    private fun showLoading(show: Boolean) {
        swipeContainerPost.setRefreshing(show)
    }

    private fun showMessage(message: String) {
        Snackbar.make(rootPostLayout, message, Snackbar.LENGTH_LONG).show()
    }

}
