package com.example.mattmobius.domain

import com.example.mattmobius.domain.CounterEvent.DecrementEvent
import com.example.mattmobius.domain.CounterEvent.IncrementEvent
import com.spotify.mobius.Next
import com.spotify.mobius.Update

fun update(
    model: CounterModel,
    event: CounterEvent,
): Next<CounterModel, CounterEffect> {
    if (event == IncrementEvent) return Next.next(CounterModel(1))
    if(event == DecrementEvent) return Next.next(CounterModel(-1))
    return Next.noChange()
}
