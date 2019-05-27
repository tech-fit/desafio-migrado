package com.example.mobiletest.extensions

fun String.getDateFormated(): String {
    val newDate = this.replace("-".toRegex(), "")
    return newDate.substring(6, 8) + "/" + newDate.substring(4, 6) + "/" + newDate.substring(0, 4)
}