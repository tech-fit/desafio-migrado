package com.example.mobiletest.ui.post

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.R
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.Profile
import com.example.mobiletest.extensions.getDateFormated
import com.example.mobiletest.ui.profile.ProfileActivity
import com.example.mobiletest.ui.profile.ProfileAdapter
import com.example.mobiletest.ui.profile.ProfilePresenter
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.swipeContainer
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_profile.*

class PostActivity : AppCompatActivity() {

    private lateinit var post: Post
    private lateinit var profile: Profile
    private lateinit var postPresenter: PostPresenter
    private lateinit var profilePresenter: ProfilePresenter
    private lateinit var adapter: PostAdapter

    //private val mealTypeArray: Array<String> = this.resources.getStringArray(R.array.meal_type_array)

    companion object {
        const val POST_EXTRAS = "postExtras"
        const val PROFILE_EXTRAS = "profileExtras"
    }

    private lateinit var onGetPostsSuccess: (Post) -> Unit
    private lateinit var onGetPostsError: () -> Unit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val extras = intent.extras

        if (extras != null && extras.containsKey(POST_EXTRAS)) {
            //Recuperar parâmetro passado entre activities
            post = extras.getSerializable(POST_EXTRAS) as Post
            profile = post.profile

            postPresenter = PostPresenter(feedHash = post.feedHash)
            profilePresenter = ProfilePresenter(id = profile.id)

            bindPostInfos(profile, post)

            cardHeaderLayout.setOnClickListener {
                goToProfileActivity(profile)
            }

            likeBtn.setOnClickListener{
                if(!post.isLiked){
                    likeBtn.setImageResource(R.drawable.ic_favorite_red_24dp)
                    post.isLiked = true
                }
                else{
                    likeBtn.setImageResource(R.drawable.ic_favorite_border_white_24dp)
                    post.isLiked = false
                }
            }

            adapter = PostAdapter(this)

            //Configuração da Lista que receberá as Foods
            recyclerFoods.layoutManager = LinearLayoutManager(this)
            recyclerFoods.adapter = adapter


            onGetPostsSuccess = { post: Post ->
                showLoading(false)
                adapter.updatePosts(post.foods, true)
            }

            onGetPostsError = {
                showLoading(false)
                showMessage(resources.getString(R.string.feed_load_error))
            }

            swipeContainer.setOnRefreshListener {
                adapter.updatePosts(mutableListOf(), true)
                getPostDetails()
            }

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

    private fun getPostDetails() {
        showLoading(true)
        postPresenter.getPostDetails(onGetPostsSuccess, onGetPostsError, this.post.feedHash)
    }

    private fun bindPostInfos(profile: Profile, post: Post){
        val name: TextView = findViewById(R.id.personName)
        val goal: TextView = findViewById(R.id.personGoal)
        val profileImage: ImageView = findViewById(R.id.personProfileImage)
        val postImage: ImageView = findViewById(R.id.postPhoto)
        val likeBtn: ImageView = findViewById(R.id.likeBtn)
        val totalCal: TextView = findViewById(R.id.foodTotalEnergyText)
        val totalCarb: TextView = findViewById(R.id.foodTotalCarbohydrateText)
        val totalProt: TextView = findViewById(R.id.foodTotalProteinText)
        val totalFat: TextView = findViewById(R.id.foodTotalFatText)
        val labelTotal: TextView = findViewById(R.id.labelTotal)
        val labelCal: TextView = findViewById(R.id.labelCal)
        val labelCarb: TextView = findViewById(R.id.labelCarb)
        val labelProt: TextView = findViewById(R.id.labelProt)
        val labelFat: TextView = findViewById(R.id.labelFat)
        val mealTypeArray: Array<String> = resources.getStringArray(R.array.meal_type_array)

        name.text = profile.name
        goal.text = profile.generalGoal
        Picasso.get()
            .load(profile.image).placeholder(R.drawable.ic_account_circle_black_24dp)
            .into(profileImage)

        Picasso.get()
            .load(post.image).placeholder(R.drawable.ic_restaurant_black_24dp)
            .into(postImage)

        if(post.isLiked){
            likeBtn.setImageResource(R.drawable.ic_favorite_red_24dp)
        }

        postTimestamp.text = post.date.getDateFormated()

        mealTypeTextView.text = mealTypeArray[post.mealType]

        labelTotal.text = "TOTAL"
        labelCal.text = "Cal"
        labelCarb.text = "Carb"
        labelProt.text = "Prot"
        labelFat.text = "Gord"



        if (post.energy != null)
            totalCal.text = post.energy.toString()+" kcal"
        else
            totalCal.text = "XXX kcal"

        if (post.totalCarbohydrate != null)
            totalCarb.text = post.totalCarbohydrate.toString()+" g"
        else
            totalCarb.text = "XXX g"

        if (post.totalProtein != null)
            totalProt.text = post.totalProtein.toString()+" g"
        else
            totalProt.text = "XXX g"

        if (post.totalFat != null)
            totalFat.text = post.totalFat.toString()+" g"
        else
            totalFat.text = "XXX g"

    }

    private fun goToProfileActivity(profile: Profile){
        val postIntent = Intent(this, ProfileActivity::class.java)
        val bundle = Bundle()

        //Passagem de parâmetro entre activities
        bundle.putSerializable(PROFILE_EXTRAS, profile)
        postIntent.putExtras(bundle)

        startActivity(postIntent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(show: Boolean) {
        swipeContainer.setRefreshing(show)
    }

    private fun showMessage(message: String) {
        Snackbar.make(rootProfileLayout, message, Snackbar.LENGTH_LONG).show()
    }
}
