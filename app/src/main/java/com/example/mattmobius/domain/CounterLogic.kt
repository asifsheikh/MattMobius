package com.example.mattmobius.domain

import com.example.mattmobius.domain.CounterEvent.DecrementEvent
import com.example.mattmobius.domain.CounterEvent.IncrementEvent
import com.spotify.mobius.Next
import com.spotify.mobius.Update

fun update(
    model: CounterModel,
    event: CounterEvent,
): Next<CounterModel, CounterEffect> {
    return Next.next(model.copy(value = model.value + 1))
}
