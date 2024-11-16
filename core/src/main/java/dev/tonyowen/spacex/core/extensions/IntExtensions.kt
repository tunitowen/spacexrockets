package dev.tonyowen.spacex.core.extensions

import java.text.NumberFormat

fun Int.formatNumber(): String {
    return NumberFormat.getInstance().format(this)
}

fun Int?.orZero() = this ?: 0