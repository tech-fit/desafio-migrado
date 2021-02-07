package com.example.mobiletest.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.R
import com.example.mobiletest.data.Food

// Classe responsável por renderizar cada item do post (refeição e nutrientes) dentro de uma lista na PostActivity


class PostAdapter(
        private val activity: AppCompatActivity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    // Lista de Posts a ser renderizada
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
}
