package com.example.mattmobius.domain

import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

internal class CounterLogicKtTest {

    val underTest = UpdateSpec(::update)

    @Test
    internal fun givenCounterValueWithZeroAndIncrementEventThenCounterValueShouldBeOne() {
        underTest
            .given(CounterModel.ZERO)
            .`when`(CounterEvent.IncrementEvent)
            .then(
                assertThatNext(
                    hasModel(CounterModel(1)),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `given counter value 0 and when decrement event then counter value should be -1`() {
        underTest
            .given(CounterModel.ZERO)
            .`when`(CounterEvent.DecrementEvent)
            .then(
                assertThatNext(
                    hasModel(CounterModel(-1)),
                    hasNoEffects()
                )
            )
    }
}