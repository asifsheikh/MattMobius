package com.example.mattmobius.domain

/**
 * these are the side effects from the counter logic
 */
sealed class CounterEffect {
    object PlaySound : CounterEffect()
}