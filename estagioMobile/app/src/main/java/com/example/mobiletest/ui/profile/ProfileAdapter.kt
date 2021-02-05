package com.example.mobiletest.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.R
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.Profile
import com.squareup.picasso.Picasso

class ProfileAdapter(
        private val activity: AppCompatActivity,
        private val onPostBodyClick: (post: Post, profile: Profile) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val postList: MutableList<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val postView = LayoutInflater.from(activity).inflate(R.layout.profile_card, parent, false)
        return ProfileViewHolder(postView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val card = postList[position]
        val profile = card.profile
        val holder = viewHolder as ProfileViewHolder

        //Carregamento de imagens por meio de uma URL
        Picasso.get()
                .load(card.image).placeholder(R.drawable.ic_restaurant_black_24dp)
                .into(holder.postPhotoProfileImageView)

        holder.postBodyLayoutProfile.setOnClickListener {
            onPostBodyClick(card, profile)
        }

    }

    fun updatePosts(posts: MutableList<Post>, clear: Boolean){
        if (clear) { this.postList.clear() }
        this.postList.addAll(posts)
        notifyDataSetChanged()
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val postPhotoProfileImageView: ImageView = itemView.findViewById(R.id.postPhotoProfile)
        val postBodyLayoutProfile: LinearLayout = itemView.findViewById(R.id.postBodyLayoutProfile)
    }
}