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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {

    //Responsável por fornecer os dados e controlar a lógica para a Activity
    private val presenter = PostPresenter()

    //Responsável por gerenciar o Post
    private lateinit var adapter: PostAdapter

    //Unidades de códigos que são chamadas em casos específicos
    private lateinit var onGetPostSuccess: (Post) -> Unit
    private lateinit var onGetPostError: () -> Unit

    companion object {
        const val POST_EXTRAS = "postExtras"
    }

    private lateinit var feedHash: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        adapter = PostAdapter(this,
            onItemProfileClick = { profile ->
                goToProfileActivity(profile)
            }
        )

        val extras = intent.extras

        if (extras != null && extras.containsKey(POST_EXTRAS)) {
            //Recuperar parâmetro passado entre activities
            val extra = extras.getSerializable(POST_EXTRAS) as Post
            feedHash = extra.feedHash
        }

        setupToolbar(resources.getString(R.string.post_details))

        //Configuração da Lista que receberá os ingredientes
        rv_post.layoutManager = LinearLayoutManager(this)
        rv_post.adapter = adapter

        onGetPostSuccess = { post: Post ->
            showLoading(false)
            adapter.updatePost(post)
        }

        onGetPostError = {
            showLoading(false)
            showMessage(resources.getString(R.string.post_load_error))
        }

        if (::feedHash.isInitialized) {
            getPost()
        }

        swipeContainerPost.setOnRefreshListener {
            adapter.updatePost(null)
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
        presenter.getPosts(feedHash, onGetPostSuccess, onGetPostError)
    }

    private fun goToProfileActivity(profile: Profile) {
        val postIntent = Intent(this, ProfileActivity::class.java)
        val bundle = Bundle()

        //Passagem de parâmetro entre activities
        bundle.putSerializable(ProfileActivity.PROFILE_EXTRAS, profile)
        postIntent.putExtras(bundle)
        startActivity(postIntent)
    }

    private fun showLoading(show: Boolean) {
        swipeContainerPost.setRefreshing(show)
    }

    private fun showMessage(message: String) {
        Snackbar.make(rootMainLayout, message, Snackbar.LENGTH_LONG).show()
    }
}
