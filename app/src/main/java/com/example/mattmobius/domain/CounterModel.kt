package com.example.mattmobius.domain

data class CounterModel(
    val value: Int,
) {
    companion object {
        val ZERO = CounterModel(0)
    }
}
