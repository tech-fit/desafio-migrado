package com.example.mobiletest.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.data.Food
import com.example.mobiletest.R
import org.w3c.dom.Text

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
        holder.foodAmountTextView.text = card.amount.toString().plus(" ").plus(card.measure).plus(" de ").plus(card.description).plus(" (").plus(card.weight.toString()).plus(" g)")
        holder.foodCalQuantityTextView.text = card.energy.toString().plus(" kcal")
        holder.foodCarbQuantityTextView.text = card.carbohydrate.toString().plus(" g")
        holder.foodProtQuantityTextView.text = card.protein.toString().plus(" g")
        holder.foodGordQuantityTextView.text = card.fat.toString().plus(" g")
    }

    fun updateFoods(foods: MutableList<Food>){
        this.foodList.clear()
        this.foodList.addAll(foods)
        notifyDataSetChanged()
    }

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foodNameTextView: TextView = itemView.findViewById(R.id.foodName)
        val foodAmountTextView: TextView = itemView.findViewById(R.id.foodAmount)
        val foodCalQuantityTextView: TextView = itemView.findViewById(R.id.foodCalQuantity)
        val foodCarbQuantityTextView: TextView = itemView.findViewById(R.id.foodCarbQuantity)
        val foodProtQuantityTextView: TextView = itemView.findViewById(R.id.foodProtQuantity)
        val foodGordQuantityTextView: TextView = itemView.findViewById(R.id.foodGordQuantity)
    }
}
