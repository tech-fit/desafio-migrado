package com.example.mobiletest.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.data.Food
import com.example.mobiletest.data.Profile
import com.example.mobiletest.R
import com.example.mobiletest.data.Post

class PostAdapter(
        private val activity: AppCompatActivity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val foodList: MutableList<Food> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val foodView = LayoutInflater.from(activity).inflate(R.layout.food_card, parent, false)
        return FoodViewHolder(foodView)
    }

    override fun getItemCount(): Int {
        return  foodList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val card = foodList[position]
        val holder = viewHolder as FoodViewHolder

        holder.foodNameTextView.text = card.description
        holder.foodQuantityTextView.text = card.amount.toString()
        holder.foodCalQuantityTextView.text = card.energy.toString()
        holder.foodCarbQuantityTextView.text = card.carbohydrate.toString()
        holder.foodProtQuantityTextView.text = card.protein.toString()
        holder.foodGordQuantityTextView.text = card.fat.toString()
    }

    fun updateFoods(foods: MutableList<Food>){
        this.foodList.clear()
        this.foodList.addAll(foods)
        notifyDataSetChanged()
    }

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foodNameTextView: TextView = itemView.findViewById(R.id.foodName)
        val foodQuantityTextView: TextView = itemView.findViewById(R.id.foodQuantity)
        val foodCalQuantityTextView: TextView = itemView.findViewById(R.id.foodCalQuantity)
        val foodCarbQuantityTextView: TextView = itemView.findViewById(R.id.foodCarbQuantity)
        val foodProtQuantityTextView: TextView = itemView.findViewById(R.id.foodProtQuantity)
        val foodGordQuantityTextView: TextView = itemView.findViewById(R.id.foodGordQuantity)
    }
}
