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
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
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

        adapter = PostAdapter(this)

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

        showProfile(post.profile)

        showPostPhoto(post)

        showTotalNutrients(post)

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

    fun showTotalNutrients(post: Post){
        val totalFoodEnergyQuantity = findViewById<TextView>(R.id.totalFoodEnergyQuantity)
        val totalFoodCarbQuantity = findViewById<TextView>(R.id.totalFoodCarbQuantity)
        val totalProtQuantity = findViewById<TextView>(R.id.totalFoodProtQuantity)
        val totalFatQuantity = findViewById<TextView>(R.id.totalFoodFatQuantity)

        totalFoodEnergyQuantity.text = post.energy.round().toString().plus(" kcal")
        totalFoodCarbQuantity.text = post.carbohydrate.round().toString().plus(" g")
        totalProtQuantity.text = post.protein.round().toString().plus(" g")
        totalFatQuantity.text = post.fat.round().toString().plus(" g")
    }

    // Responsável por limitar o número de casas decimais dos nutrientes exibidos
    fun Float.round(decimals: Int = 2): Float = "%.${decimals}f".format(this).toFloat()

    // Responsável por carregar as informações do perfil da Pessoa
    fun showProfile(profile: Profile){
        val personName = findViewById<TextView>(R.id.personNamePost)
        val personObjective = findViewById<TextView>(R.id.personGoalPost)
        val profileImage = findViewById<CircleImageView>(R.id.personProfileImagePost)

        Picasso.get()
                .load(profile.image).placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(profileImage)

        profileImage.setOnClickListener{
            goToProfileActivity(profile)
        }

        personName.text = profile.name
        personObjective.text = profile.generalGoal
    }

    // Responsável por carregar as informações do Post da pessoa
    fun showPostPhoto(post: Post){
        val postPhoto = findViewById<ImageView>(R.id.postPhoto)
        val likeBtnPost = findViewById<ImageView>(R.id.likeBtnPost)
        val mealTypeArray: Array<String> = resources.getStringArray(R.array.meal_type_array)
        val mealTypeTextView = findViewById<TextView>(R.id.mealTypeTextView)
        val postTimestamp = findViewById<TextView>(R.id.postTimestamp)

        if (post.isLiked) {
            likeBtnPost.setImageResource(R.drawable.ic_favorite_red_24dp)
        } else {
            likeBtnPost.setImageResource(R.drawable.ic_favorite_border_white_24dp)
        }

        likeBtnPost.setOnClickListener {
            if (post.isLiked) {
                post.isLiked = false
                likeBtnPost.setImageResource(R.drawable.ic_favorite_border_white_24dp)

            } else {
                post.isLiked = true
                likeBtnPost.setImageResource(R.drawable.ic_favorite_red_24dp)
            }
        }

        Picasso.get()
                .load(post.image).placeholder(R.drawable.ic_restaurant_black_24dp)
                .into(postPhoto)

        mealTypeTextView.text = mealTypeArray[post.mealType]
        postTimestamp.text = post.date.getDateFormated()
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
