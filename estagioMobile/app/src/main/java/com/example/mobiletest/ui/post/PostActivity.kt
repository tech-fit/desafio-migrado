package com.example.mobiletest.ui.post

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mobiletest.R
import com.example.mobiletest.data.Post

class PostActivity : AppCompatActivity() {

    companion object {
        const val POST_EXTRAS = "postExtras"
        const val PROFILE_EXTRAS = "profileExtras"
    }

    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val extras = intent.extras

        if (extras != null && extras.containsKey(POST_EXTRAS)) {
            //Recuperar parâmetro passado entre activities
            post = extras.getSerializable(POST_EXTRAS) as Post
        }

        setupToolbar(resources.getString(R.string.post_details))

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

    /*
    // TODO: Implementar a tela de detalhes do Post
     */
}
