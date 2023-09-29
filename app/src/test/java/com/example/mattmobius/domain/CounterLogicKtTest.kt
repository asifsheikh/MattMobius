package com.example.mattmobius.domain

import com.example.mattmobius.domain.CounterEvent.IncrementEvent
import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

internal class CounterLogicKtTest {

    @Test
    fun `given counter value zero when increment event then counter value should be 1`() {
        // given
        val underTest = UpdateSpec(::update)

        underTest
            .given(CounterModel.ZERO)
            .`when`(IncrementEvent)
            .then(
                assertThatNext(
                    hasModel(CounterModel(1)),
                    hasNoEffects()
                )
            )
    }
}