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
    private val onPostBodyClick: (profile: Profile) -> Unit//Callback para quando o usuário clicar no corpo do Post
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //Lista de Posts a ser renderizada
    private val postList: MutableList<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val postView = LayoutInflater.from(activity).inflate(R.layout.item_profile, parent, false)
        return PostViewHolder(postView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val card = postList[position]
        val profile = card.profile

        val holder = viewHolder as PostViewHolder

        //Carregamento de imagens por meio de uma URL
        Picasso.get()
            .load(card.image).placeholder(R.drawable.ic_restaurant_black_24dp)
            .into(holder.postImageView)


        holder.cardHeaderLayout.setOnClickListener {
            onPostBodyClick(profile)
        }
    }

    fun updatePosts(posts: MutableList<Post>, clear: Boolean) {
        if (clear) {
            this.postList.clear()
        }
        this.postList.addAll(posts)
        notifyDataSetChanged()
    }

    //Classe usada para montar e manter as Views necessárias para exibição do Post
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardHeaderLayout: LinearLayout = itemView.findViewById(R.id.cardHeaderLayoutProfile)
        val postImageView: ImageView = itemView.findViewById(R.id.postPhoto)

    }

}