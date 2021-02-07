package com.example.mobiletest.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.R
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.Profile
import com.example.mobiletest.data.ProfileDetail
import com.example.mobiletest.ui.post.PostActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    companion object {
        const val PROFILE_EXTRAS = "profileExtras"
    }

    //Responsável por fornecer os dados e controlar a lógica para a Activity
    private val presenter = ProfilePresenter()

    private lateinit var profile: Profile

    //Responsável por gerenciar a lista de Posts
    private lateinit var adapter: ProfileAdapter

    //Unidades de códigos que são chamadas em casos específicos
    private lateinit var onGetPostsSuccess: (ProfileDetail) -> Unit
    private lateinit var onGetMorePostsSuccess: (profileDetail: ProfileDetail) -> Unit
    private lateinit var onGetPostsError: () -> Unit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val extras = intent.extras

        if (extras != null && extras.containsKey(PROFILE_EXTRAS)) {
            //Recuperar parâmetro passado entre activities
            profile = extras.getSerializable(PROFILE_EXTRAS) as Profile
        }

        adapter = ProfileAdapter(this,
                onPostBodyClick = { post, profile ->
                    goToPostDetailsActivity(post, profile)
                })

        setupToolbar(resources.getString(R.string.profile))

        //Configuração da Lista que receberá os Posts
        val layoutManager = GridLayoutManager(this, 3)

        // Define o layout do GridLayout, temos o primeiro elemento ocupando as 3 colunas, enquanto os demais elementos ocupam somente 1 coluna
        layoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    0 -> 3
                    else -> 1
                }
            }
        }

        recyclerProfile.layoutManager = layoutManager
        recyclerProfile.adapter = adapter

        //Listener para saber se a lista está proxima do fim e assim carregar mais Posts
        recyclerProfile.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisible = layoutManager.findLastVisibleItemPosition()

                //Carrega mais Posts na lista se o último Post visível estiver entre os 5 últimos da lista atual
                val endHasBeenReached = lastVisible + 6 >= totalItemCount
                if (totalItemCount > 0 && endHasBeenReached) {
                    geMorePosts()
                }
            }
        })

        onGetPostsSuccess = { profileDetail: ProfileDetail ->
            showLoading(false)
            adapter.updatePosts(profileDetail.items, true)
        }

        onGetMorePostsSuccess = { profileDetail: ProfileDetail ->
            showLoading(false)
            adapter.updatePosts(profileDetail.items, false)
        }

        onGetPostsError = {
            showLoading(false)
            showMessage(resources.getString(R.string.feed_load_error))
        }

        getInitialPosts()

        swipeContainerProfile.setOnRefreshListener {
            adapter.updatePosts(mutableListOf(), true)
            getInitialPosts()
        }
    }

    private fun goToPostDetailsActivity(post: Post, profile: Profile) {

        val postIntent = Intent(this, PostActivity::class.java)
        val bundle = Bundle()

        //Passagem de parâmetro entre activities
        bundle.putSerializable(PostActivity.POST_EXTRAS, post)
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

    private fun getInitialPosts() {
        showLoading(true)
        presenter.getProfileDetail(onGetPostsSuccess, onGetPostsError, profile.id)
    }

    private fun geMorePosts() {
        showLoading(true)
        presenter.getMorePosts(onGetMorePostsSuccess, onGetPostsError)
    }

    private fun showLoading(show: Boolean) {
        swipeContainerProfile.setRefreshing(show)
    }

    private fun showMessage(message: String) {
        Snackbar.make(rootProfileLayout, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
