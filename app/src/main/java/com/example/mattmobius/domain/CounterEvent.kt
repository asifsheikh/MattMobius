package com.example.mattmobius.domain

sealed class CounterEvent {
    object IncrementEvent : CounterEvent()
    object DecrementEvent : CounterEvent()
}