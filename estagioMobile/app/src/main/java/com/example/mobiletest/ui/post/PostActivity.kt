package com.example.mobiletest.ui.post

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobiletest.R
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.Profile
import com.example.mobiletest.extensions.getDateFormated
import com.example.mobiletest.ui.profile.ProfileActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_post.*
import org.w3c.dom.Text

class PostActivity : AppCompatActivity() {

    companion object {
        const val POST_EXTRAS = "postExtras"
        const val PROFILE_EXTRAS = "profileExtras"
    }

    private lateinit var post: Post
    private val presenter = PostPresenter()
    private lateinit var adapter: PostAdapter

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

        adapter = PostAdapter(this)

        //Configuração da Lista que receberá os Posts
        recyclerPost.layoutManager = LinearLayoutManager(this)
        recyclerPost.adapter = adapter

        onGetPostsSuccess = { post: Post ->
            adapter.updateFoods(post.foods)
        }

        onGetPostsError = {
        }

        showProfile(post.profile)

        showTotalNutrients(post)

        getInitialPosts()

    }

    private fun goToProfileActivity(profile: Profile) {
        val postIntent = Intent(this, ProfileActivity::class.java)
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

    fun showTotalNutrients(post: Post){
        val totalFoodEnergyQuantity = findViewById<TextView>(R.id.totalFoodEnergyQuantity)
        val totalFoodCarbQuantity = findViewById<TextView>(R.id.totalFoodCarbQuantity)
        val totalProtQuantity = findViewById<TextView>(R.id.totalFoodProtQuantity)
        val totalFatQuantity = findViewById<TextView>(R.id.totalFoodFatQuantity)
        val mealTypeArray: Array<String> = resources.getStringArray(R.array.meal_type_array)
        val mealTypeTextView = findViewById<TextView>(R.id.mealTypeTextView)
        val postTimestamp = findViewById<TextView>(R.id.postTimestamp)

        totalFoodEnergyQuantity.text = post.energy.toString().plus(" kcal")
        totalFoodCarbQuantity.text = post.carbohydrate.toString().plus(" g")
        totalProtQuantity.text = post.protein.toString().plus(" g")
        totalFatQuantity.text = post.fat.toString().plus(" g")
        mealTypeTextView.text = mealTypeArray[post.mealType]
        postTimestamp.text = post.date.getDateFormated()
    }

    fun showProfile(profile: Profile){
        val personName = findViewById<TextView>(R.id.personName)
        val personObjective = findViewById<TextView>(R.id.personObjective)
        val profileImage = findViewById<CircleImageView>(R.id.personProfileImage)
        val postPhoto = findViewById<ImageView>(R.id.postPhoto)

        Picasso.get()
                .load(profile.image).placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(profileImage)

        Picasso.get()
                .load(post.image).placeholder(R.drawable.ic_restaurant_black_24dp)
                .into(postPhoto)

        profileImage.setOnClickListener{
            goToProfileActivity(profile)
        }

        personName.text = profile.name
        personObjective.text = profile.generalGoal
    }

    private fun getInitialPosts() {
        presenter.getPostDetail(onGetPostsSuccess, onGetPostsError, post.feedHash)
    }
}
