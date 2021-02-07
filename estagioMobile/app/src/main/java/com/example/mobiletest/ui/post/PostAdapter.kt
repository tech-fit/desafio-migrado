package com.example.mobiletest.ui.post

import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.R
import com.example.mobiletest.data.Food
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.Profile
import com.example.mobiletest.extensions.getDateFormated
import com.squareup.picasso.Picasso

// Classe responsável por renderizar cada item do post (refeição e nutrientes) dentro de uma lista na PostActivity


class PostAdapter(
        private val activity: AppCompatActivity,
        private val onItemProfileClick: (profile: Profile) -> Unit,
        private val post: Post
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    // Lista de Posts a ser renderizada
    private val foodList: MutableList<Food> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(activity)

        return when (viewType){
            R.layout.post_header -> PostHeaderViewHolder(inflater.inflate(viewType,parent, false))
            R.layout.food_card -> FoodViewHolder(inflater.inflate(viewType, parent, false))
            R.layout.post_footer -> PostFooterViewHolder(inflater.inflate(viewType, parent, false))
            else -> throw IllegalArgumentException("Unsupported layout")
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0){
            return R.layout.post_header
        } else if (position == foodList.size + 1){
            return R.layout.post_footer
        } else {
            return R.layout.food_card
        }
    }

    override fun getItemCount(): Int {
        return  foodList.size + 2
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        // Prevenção de outOfBounds ao montar a tela antes de terminar o request
        if(foodList.size < 1){
            return
        }

        if (position == 0){
            val holderHeader = viewHolder as PostHeaderViewHolder
            val profile = post.profile

            Picasso.get()
                    .load(profile.image).placeholder(R.drawable.ic_account_circle_black_24dp)
                    .into(holderHeader.personProfileImagePost)

            if (post.isLiked) {
                holderHeader.likeBtnPostImageView.setImageResource(R.drawable.ic_favorite_red_24dp)
            } else {
                holderHeader.likeBtnPostImageView.setImageResource(R.drawable.ic_favorite_border_white_24dp)
            }

            holderHeader.likeBtnPostImageView.setOnClickListener {
                if (post.isLiked) {
                    post.isLiked = false
                    holderHeader.likeBtnPostImageView.setImageResource(R.drawable.ic_favorite_border_white_24dp)

                } else {
                    post.isLiked = true
                    holderHeader.likeBtnPostImageView.setImageResource(R.drawable.ic_favorite_red_24dp)
                }
            }

            Picasso.get()
                    .load(post.image).placeholder(R.drawable.ic_restaurant_black_24dp)
                    .into(holderHeader.postPhotoImageView)

            holderHeader.cardHeaderLayout.setOnClickListener{
                onItemProfileClick(profile)
            }

            holderHeader.personNamePostTextView.text = profile.name
            holderHeader.personGoalPostTextView.text = profile.generalGoal

            holderHeader.mealTypeTextView.text = holderHeader.mealTypeArray[post.mealType]
            holderHeader.postTimestampTextView.text = post.date.getDateFormated()

        } else if (position == foodList.size + 1){
            val holderFooter = viewHolder as PostFooterViewHolder

            holderFooter.totalFoodEnergyQuantityTextView.text = String.format(
                    activity.getString(R.string.kcal_mask),
                    post.energy.round()
            )
            holderFooter.totalFoodCarbQuantityTextView.text = String.format(
                    activity.getString(R.string.carbohydrate_label),
                    post.carbohydrate.round()
            )
            holderFooter.totalFoodProtQuantityTextView.text = String.format(
                    activity.getString(R.string.protein_label),
                    post.protein.round()
            )
            holderFooter.totalFoodFatQuantityTextView.text = String.format(
                    activity.getString(R.string.fat_label),
                    post.fat.round()
            )
        } else {
            val card = foodList[position-1]
            val holder = viewHolder as FoodViewHolder

            holder.foodNameTextView.text = card.description
            holder.foodAmountTextView.text = card.amount.round().toString().plus(" ").plus(card.measure).plus(" de ").plus(card.description).plus(" ").plus(String.format(
                    activity.getString(R.string.food_grams_mask),
                    card.weight.round()
            ))
            holder.foodCalQuantityTextView.text = String.format(
                    activity.getString(R.string.energy_unity),
                    card.energy.round()
            )
            holder.foodCarbQuantityTextView.text = String.format(
                    activity.getString(R.string.carbohydrate_label),
                    card.carbohydrate.round()
            )
            holder.foodProtQuantityTextView.text = String.format(
                    activity.getString(R.string.protein_label),
                    card.protein.round()
            )
            holder.foodGordQuantityTextView.text = String.format(
                    activity.getString(R.string.fat_label),
                    card.fat.round()
            )
        }
    }

    // Responsável por limitar o número de casas decimais dos nutrientes exibidos
    fun Float.round(decimals: Int = 2): Float = "%.${decimals}f".format(this).toFloat()

    fun updateFoods(foods: MutableList<Food>, clear: Boolean){
        if (clear){ this.foodList.clear() }
        this.foodList.addAll(foods)
        notifyDataSetChanged()
    }

    // Classe usada para montar e manter as Views necessárias para exibição do Post(refeição e nutrientes)
    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foodNameTextView: TextView = itemView.findViewById(R.id.foodName)
        val foodAmountTextView: TextView = itemView.findViewById(R.id.foodAmount)
        val foodCalQuantityTextView: TextView = itemView.findViewById(R.id.foodCalQuantity)
        val foodCarbQuantityTextView: TextView = itemView.findViewById(R.id.foodCarbQuantity)
        val foodProtQuantityTextView: TextView = itemView.findViewById(R.id.foodProtQuantity)
        val foodGordQuantityTextView: TextView = itemView.findViewById(R.id.foodGordQuantity)
    }

    inner class PostHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val personNamePostTextView: TextView = itemView.findViewById(R.id.personNamePost)
        val personGoalPostTextView: TextView = itemView.findViewById(R.id.personGoalPost)
        val personProfileImagePost: ImageView = itemView.findViewById(R.id.personProfileImagePost)
        val cardHeaderLayout: LinearLayout = itemView.findViewById(R.id.cardHeaderLayout)
        val postTimestampTextView: TextView = itemView.findViewById(R.id.postTimestamp)
        val postPhotoImageView: ImageView = itemView.findViewById(R.id.postPhoto)
        val likeBtnPostImageView: ImageView = itemView.findViewById(R.id.likeBtnPost)
        val mealTypeTextView: TextView = itemView.findViewById(R.id.mealTypeTextView)
        val mealTypeArray: Array<String> = activity.resources.getStringArray(R.array.meal_type_array)
    }

    inner class PostFooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val totalFoodEnergyQuantityTextView: TextView = itemView.findViewById(R.id.totalFoodEnergyQuantity)
        val totalFoodCarbQuantityTextView: TextView = itemView.findViewById(R.id.totalFoodCarbQuantity)
        val totalFoodProtQuantityTextView: TextView = itemView.findViewById(R.id.totalFoodProtQuantity)
        val totalFoodFatQuantityTextView: TextView = itemView.findViewById(R.id.totalFoodFatQuantity)
    }
}
