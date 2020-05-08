package com.example.mobiletest.ui.post

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
import de.hdodenhof.circleimageview.CircleImageView


//Classe responsável por renderizar cada ingrediente do post dentro de uma lista na PostActivity

class PostAdapter(
    private val activity: AppCompatActivity,
    private val onItemProfileClick: (profile: Profile) -> Unit //Callback para quando o usuário clicar no cabeçalho do Post
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }

    //Lista de Foods a ser renderizada
    private var post: Post? = null
    private val mealTypeArray: Array<String> =
        activity.resources.getStringArray(R.array.meal_type_array)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM) {
            val postView = LayoutInflater.from(activity).inflate(R.layout.food_card, parent, false)
            return PostItemViewHolder(postView)
        } else {
            val postView = LayoutInflater.from(activity).inflate(R.layout.item_card, parent, false)
            return PostHeaderViewHolder(postView)
        }
    }

    override fun getItemCount(): Int {
        if (post != null && post!!.foods.size > 0) {
            return post!!.foods.size + 2
        } else {
            return 1
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }
        return TYPE_ITEM
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is PostHeaderViewHolder && post != null) {
            val holder = viewHolder as PostHeaderViewHolder
            val profile = post!!.profile

            if (profile.name != null) {
                holder.personNameTextView.text = profile.name
            }

            if (profile.generalGoal != null) {
                holder.personGoalTextView.visibility = View.VISIBLE
                holder.personGoalTextView.text = profile.generalGoal
            } else {
                holder.personGoalTextView.visibility = View.GONE
            }

            if (post!!.energy != 0f) {
                holder.energyTextView.visibility = View.VISIBLE
                holder.energyTextView.text = String.format(
                    activity.getString(R.string.kcal_mask),
                    post!!.energy
                )
            } else {
                holder.energyTextView.visibility = View.GONE
            }

            holder.postTimeTextView.text = post!!.date.getDateFormated()
            holder.mealTypeTextView.text = mealTypeArray[post!!.mealType]

            if (post!!.isLiked) {
                holder.likeButton.setImageResource(R.drawable.ic_favorite_red_24dp)
            } else {
                holder.likeButton.setImageResource(R.drawable.ic_favorite_border_white_24dp)
            }

            //Carregamento de imagens por meio de uma URL
            Picasso.get()
                .load(post!!.image).placeholder(R.drawable.ic_restaurant_black_24dp)
                .into(holder.postImageView)


            //Carregamento de imagens por meio de uma URL
            Picasso.get()
                .load(profile.image).placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(holder.personImageView)

            holder.cardHeaderLayout.setOnClickListener {
                onItemProfileClick(profile)
            }

            holder.likeButton.setOnClickListener {
                if (post!!.isLiked) {
                    post!!.isLiked = false
                    holder.likeButton.setImageResource(R.drawable.ic_favorite_border_white_24dp)

                } else {
                    post!!.isLiked = true
                    holder.likeButton.setImageResource(R.drawable.ic_favorite_red_24dp)
                }
            }
        } else if (viewHolder is PostItemViewHolder && post != null) {
            var card: Food? = null
            if (position > post!!.foods.size) {
                card = Food(
                    description = "TOTAL",
                    carbohydrate = post!!.carbohydrate,
                    energy = post!!.energy,
                    fat = post!!.fat,
                    protein = post!!.protein,
                    amount = null,
                    measure = null,
                    weight = null
                )
            } else {
                card = post!!.foods[position - 1]
            }
            val holder = viewHolder as PostItemViewHolder

            if (card.description != null) {
                holder.foodNameTextView.text = card.description
            }

            if (card.amount != 0f && card.measure != null && card.weight != 0f) {
                holder.foodAmountTextView.visibility = View.VISIBLE
                holder.foodAmountTextView.text = String.format(
                    activity.getString(R.string.amount_mask),
                    card.amount,
                    card.measure,
                    card.weight
                )
            } else {
                holder.foodAmountTextView.visibility = View.GONE
            }
            holder.calTextView.text = String.format(
                activity.getString(R.string.kcal_mask),
                card.energy
            )
            holder.carbTextView.text = String.format(
                activity.getString(R.string.grams_mask),
                card.carbohydrate
            )
            holder.proteinTextView.text = String.format(
                activity.getString(R.string.grams_mask),
                card.protein
            )
            holder.fatTextView.text = String.format(
                activity.getString(R.string.grams_mask),
                card.fat
            )
        }
    }

    fun updatePost(post: Post?) {
        this.post = post
        notifyDataSetChanged()
    }

    //Classe usada para montar e manter as Views necessárias para exibição do Post
    inner class PostHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personNameTextView: TextView = itemView.findViewById(R.id.personName)
        val personGoalTextView: TextView = itemView.findViewById(R.id.personGoal)
        val personImageView: CircleImageView = itemView.findViewById(R.id.personProfileImage)
        val cardHeaderLayout: LinearLayout = itemView.findViewById(R.id.cardHeaderLayout)
        val postImageView: ImageView = itemView.findViewById(R.id.postPhoto)
        val likeButton: ImageView = itemView.findViewById(R.id.likeBtn)
        val energyTextView: TextView = itemView.findViewById(R.id.energyText)
        val postTimeTextView: TextView = itemView.findViewById(R.id.postTimestamp)
        val mealTypeTextView: TextView = itemView.findViewById(R.id.mealTypeTextView)
    }

    //Classe usada para montar e manter as Views necessárias para exibição dos ingredientes
    inner class PostItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodNameTextView: TextView = itemView.findViewById(R.id.tv_food)
        val foodAmountTextView: TextView = itemView.findViewById(R.id.tv_amount)
        val calTextView: TextView = itemView.findViewById(R.id.tv_cal)
        val carbTextView: TextView = itemView.findViewById(R.id.tv_carb)
        val proteinTextView: TextView = itemView.findViewById(R.id.tv_protein)
        val fatTextView: TextView = itemView.findViewById(R.id.tv_fat)
    }

}