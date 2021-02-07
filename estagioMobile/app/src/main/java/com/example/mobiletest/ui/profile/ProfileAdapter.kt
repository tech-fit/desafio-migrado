package com.example.mobiletest.ui.profile

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
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

//Classe responsável por renderizar cada item do post dentro de uma lista na ProfileActivity
class ProfileAdapter(
        private val activity: AppCompatActivity,
        private val onPostBodyClick: (post: Post, profile: Profile) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //Lista de Posts a ser renderizada
    private val postList: MutableList<Post> = mutableListOf()

    // Retorna a View utilizada na posição
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(activity)

        return when (viewType){
            R.layout.profile_header -> ProfileHeaderViewHolder(inflater.inflate(viewType, parent, false))
            R.layout.profile_card -> ProfileViewHolder(inflater.inflate(viewType, parent, false))
            else -> throw IllegalArgumentException("Unsupported layout")
        }
    }

    override fun getItemCount(): Int {
        return postList.size + 1
    }

    // Retorna qual View utilizar dependendo da posição da lista
    override fun getItemViewType(position: Int): Int {
        if (position == 0){
            return R.layout.profile_header
        } else {
            return R.layout.profile_card
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        // Prevenção de outOfBounds ao montar a tela antes de terminar o request
        if (postList.size < 1){
            return
        }
        // Carrega as informações da Header
        if (position == 0){
            val holderProfile = viewHolder as ProfileHeaderViewHolder
            val profile = postList[0].profile

            holderProfile.personGoalProfileTextView.text = profile.generalGoal
            holderProfile.personNameProfileTextView.text = profile.name

            //Carregamento de imagens por meio de uma URL
            Picasso.get()
                    .load(profile.image).placeholder(R.drawable.ic_account_circle_black_24dp)
                    .into(holderProfile.personProfileImageProfile)

        // Carrega as informações do Card
        } else {
            val card = postList[position-1]
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

    }

    fun updatePosts(posts: MutableList<Post>, clear: Boolean){
        if (clear) { this.postList.clear() }
        this.postList.addAll(posts)
        notifyDataSetChanged()
    }

    // Classe usada para montar e manter as Views necessárias para exibição do Post
    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val postPhotoProfileImageView: ImageView = itemView.findViewById(R.id.postPhotoProfile)
        val postBodyLayoutProfile: LinearLayout = itemView.findViewById(R.id.postBodyLayoutProfile)
    }

    // Classe usada para montar e manter as Views necessárias para exibição do Profile
    inner class ProfileHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val personNameProfileTextView: TextView = itemView.findViewById(R.id.personNameProfile)
        val personGoalProfileTextView: TextView = itemView.findViewById(R.id.personGoalProfile)
        val personProfileImageProfile: CircleImageView = itemView.findViewById(R.id.personProfileImageProfile)
    }
}