package com.example.mattmobius.domain

import com.example.mattmobius.domain.CounterEvent.DecrementEvent
import com.example.mattmobius.domain.CounterEvent.IncrementEvent
import com.spotify.mobius.Effects
import com.spotify.mobius.First
import com.spotify.mobius.Next
import com.spotify.mobius.Update


fun init(model: CounterModel): First<CounterModel, CounterEffect> = First.first(model)

fun update(
    model: CounterModel,
    event: CounterEvent,
): Next<CounterModel, CounterEffect> = if (event == DecrementEvent) {
    Next.next(model.copy(value = model.value - 1))
} else {
    Next.next(
        model.copy(value = model.value + 1),
        Effects.effects(CounterEffect.PlaySound)
    )
}
