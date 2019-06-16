package com.example.mobiletest.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.R
import com.example.mobiletest.data.Food

class PostAdapter(
    private val activity: AppCompatActivity
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val foodList: MutableList<Food> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val postView = LayoutInflater.from(parent.context).inflate(R.layout.item_food_list, parent, false)
        return PostViewHolder(postView)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val food = foodList[position]
        val holder = viewHolder as PostViewHolder

        holder.foodDescription.text = food.description
        holder.foodAmount.text = food.amount.toString()+" g"
        holder.foodMeasure.text = food.measure
        holder.foodWeight.text = food.weight.toString()+" g"
        holder.foodEnergy.text = food.energy.toString()+" kcal"
        holder.foodCarbohydrate.text = food.carbohydrate.toString()+" g"
        holder.foodProtein.text = food.protein.toString()+" g"
        holder.foodFat.text = food.fat.toString()+" g"

    }

    fun updatePosts(foods: MutableList<Food>, clear: Boolean) {
        if (clear) {
            this.foodList.clear()
        }
        this.foodList.addAll(foods)
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodDescription: TextView = itemView.findViewById(R.id.foodDescriptionText)
        val foodAmount: TextView = itemView.findViewById(R.id.foodAmountText)
        val foodMeasure: TextView = itemView.findViewById(R.id.foodMeasureText)
        val foodWeight: TextView = itemView.findViewById(R.id.foodWeightText)
        val foodEnergy: TextView = itemView.findViewById(R.id.foodEnergyText)
        val foodCarbohydrate: TextView = itemView.findViewById(R.id.foodCarbohydrateText)
        val foodProtein: TextView = itemView.findViewById(R.id.foodProteinText)
        val foodFat: TextView = itemView.findViewById(R.id.foodFatText)

    }

}