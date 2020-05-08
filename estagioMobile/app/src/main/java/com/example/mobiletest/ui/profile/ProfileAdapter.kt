package com.example.mobiletest.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.R
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.Profile
import com.example.mobiletest.data.ProfileDetails
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

//Classe responsável por renderizar o perfil e os posts de um usuário

class ProfileAdapter(
    private val activity: AppCompatActivity,
    private val onItemPostClick: (post: Post) -> Unit //Callback para quando o usuário clicar no cabeçalho do Post
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }

    private var profile: Profile? = null
    private var postList: MutableList<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM) {
            val postView = LayoutInflater.from(activity).inflate(R.layout.image_item, parent, false)
            return ProfileItemViewHolder(postView)
        } else {
            val postView =
                LayoutInflater.from(activity).inflate(R.layout.profile_header, parent, false)
            return ProfileHeaderViewHolder(postView)
        }
    }

    override fun getItemCount(): Int {
        return postList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }
        return TYPE_ITEM
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is ProfileHeaderViewHolder && profile != null) {
            val holder = viewHolder as ProfileHeaderViewHolder

            if (profile!!.name != null) {
                holder.userNameTextView.text = profile!!.name
            }

            if (profile!!.generalGoal != null) {
                holder.userGoalTextView.visibility = View.VISIBLE
                holder.userGoalTextView.text = profile!!.generalGoal
            } else {
                holder.userGoalTextView.visibility = View.GONE
            }

            //Carregamento de imagens por meio de uma URL
            Picasso.get()
                .load(profile!!.image).placeholder(R.drawable.ic_restaurant_black_24dp)
                .into(holder.userImageView)

        } else if (viewHolder is ProfileItemViewHolder && postList.isNotEmpty()) {
            val item = postList[position - 1]
            val holder = viewHolder as ProfileItemViewHolder

            //Carregamento de imagens por meio de uma URL
            Picasso.get()
                .load(item.image).placeholder(R.drawable.ic_restaurant_black_24dp)
                .into(holder.profilePostImageView)
            holder.profilePostImageView.setOnClickListener {
                onItemPostClick(item)
            }
        }
    }

    fun updatePost(profileDetails: ProfileDetails?, clear: Boolean) {
        if (clear) {
            this.postList.clear()
            this.profile = null
        }
        if (profileDetails != null) {
            this.profile = profileDetails.profile
            this.postList.addAll(profileDetails.items)
        }
        notifyDataSetChanged()
    }

    //Classe usada para montar e manter as Views necessárias para exibição do Perfil
    inner class ProfileHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userGoalTextView: TextView = itemView.findViewById(R.id.profileUserGoal)
        val userNameTextView: TextView = itemView.findViewById(R.id.profileUserName)
        val userImageView: CircleImageView = itemView.findViewById(R.id.profileUserImage)
    }

    //Classe usada para montar e manter as Views necessárias para exibição dos Posts
    inner class ProfileItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profilePostImageView: ImageView = itemView.findViewById(R.id.profilePostImage)
    }

}