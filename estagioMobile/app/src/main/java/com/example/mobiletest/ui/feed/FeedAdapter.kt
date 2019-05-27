package com.example.mobiletest.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.R
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.Profile
import com.example.mobiletest.extensions.getDateFormated
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class FeedAdapter(
    private val activity: AppCompatActivity,
    private val onItemProfileClick: (profile: Profile, post: Post) -> Unit,
    private val onPostBodyClick: (post: Post) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val postList: MutableList<Post> = mutableListOf()
    private val mealTypeArray: Array<String> = activity.resources.getStringArray(R.array.meal_type_array)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val postView = LayoutInflater.from(activity).inflate(R.layout.item_card, parent, false)
        return PostViewHolder(postView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val card = postList[position]
        val profile = card.profile
        val holder = viewHolder as PostViewHolder

        if (profile.name != null) {
            holder.personNameTextView.text = profile.name
        }

        if (profile.generalGoal != null) {
            holder.personGoalTextView.visibility = View.VISIBLE
            holder.personGoalTextView.text = profile.generalGoal
        } else {
            holder.personGoalTextView.visibility = View.GONE
        }

        if (card.energy != 0f) {
            holder.energyTextView.visibility = View.VISIBLE
            holder.energyTextView.text = String.format(
                activity.getString(R.string.kcal_mask),
                card.energy
            )
        } else {
            holder.energyTextView.visibility = View.GONE
        }

        holder.postTimeTextView.text = card.date.getDateFormated()
        holder.mealTypeTextView.text = mealTypeArray[card.mealType]

        if (card.isLiked) {
            holder.likeButton.setImageResource(R.drawable.ic_favorite_red_24dp)
        } else {
            holder.likeButton.setImageResource(R.drawable.ic_favorite_border_white_24dp)
        }

        Picasso.with(holder.postImageView.context)
            .load(card.image).placeholder(R.drawable.ic_restaurant_black_24dp)
            .into(holder.postImageView)


        Picasso.with(holder.personImageView.context)
            .load(profile.image).placeholder(R.drawable.ic_account_circle_black_24dp)
            .into(holder.personImageView)

        holder.cardHeaderLayout.setOnClickListener {
            onItemProfileClick(
                profile,
                card
            )
        }

        holder.likeButton.setOnClickListener {
            if (card.isLiked) {
                card.isLiked = false
                holder.likeButton.setImageResource(R.drawable.ic_favorite_border_white_24dp)

            } else {
                card.isLiked = true
                holder.likeButton.setImageResource(R.drawable.ic_favorite_red_24dp)
            }
        }

        holder.postBodyLayout.setOnClickListener {
            onPostBodyClick(card)
        }

    }

    fun updatePosts(posts: MutableList<Post>, clear: Boolean){
        if (clear) { this.postList.clear() }
        this.postList.addAll(posts)
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personNameTextView: TextView = itemView.findViewById(R.id.personName)
        val personGoalTextView: TextView = itemView.findViewById(R.id.personGoal)
        val personImageView: CircleImageView = itemView.findViewById(R.id.personProfileImage)
        val cardHeaderLayout: LinearLayout = itemView.findViewById(R.id.cardHeaderLayout)
        val postImageView: ImageView = itemView.findViewById(R.id.postPhoto)
        val likeButton: ImageView = itemView.findViewById(R.id.likeBtn)
        val energyTextView: TextView = itemView.findViewById(R.id.energyText)
        val postTimeTextView: TextView = itemView.findViewById(R.id.postTimestamp)
        val postBodyLayout: LinearLayout = itemView.findViewById(R.id.postBodyLayout)
        val mealTypeTextView: TextView = itemView.findViewById(R.id.mealTypeTextView)
    }

}