package com.example.mobiletest.data

import java.io.Serializable

class Food (
    var description: String,
    var measure: String,
    var weight: Float,
    var amount: Float,
    var energy: Float,
    var carbohydrate: Float,
    var fat: Float,
    var protein: Float,
    var fiber: Float
) : Serializable