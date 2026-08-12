package com.truongdinh.waiterapp.util

fun Long.toCurrencyFormat(): String {
    return "%, d đ".format(this).replace(",", ".")
}