package dev.tonyowen.spacex.core.extensions

import java.text.NumberFormat

fun Double.formatNumber(): String {
    return NumberFormat.getInstance().format(this)
}

fun Double?.orZero() = this ?: 0.0