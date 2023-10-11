package com.example.mattmobius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mattmobius.base.mobius.DeferredEventSource
import com.example.mattmobius.domain.CounterEffect
import com.example.mattmobius.domain.CounterEvent
import com.example.mattmobius.domain.CounterModel
import com.example.mattmobius.domain.init
import com.example.mattmobius.domain.update
import com.example.mattmobius.ui.screen.CounterScreen
import com.example.mattmobius.ui.theme.MattMobiusTheme
import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.Init
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.android.AndroidLogger
import com.spotify.mobius.android.MobiusAndroid
import com.spotify.mobius.functions.Consumer
import com.spotify.mobius.rx3.RxMobius
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer

class MainActivity : ComponentActivity(), Connectable<CounterModel, CounterEvent> {

    private lateinit var controller: MobiusLoop.Controller<CounterModel, CounterEvent>

    private val eventSource = DeferredEventSource<CounterEvent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = MobiusAndroid.controller(
            /* loopFactory = */ createLoop(),
            /* defaultModel = */ CounterModel.ZERO,
            /* init = */ Init(::init)
        )
        controller.connect(this)
    }

    override fun onResume() {
        super.onResume()
        controller.start()
    }

    override fun onPause() {
        super.onPause()
        controller.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        controller.disconnect()
    }

    override fun connect(output: Consumer<CounterEvent>): Connection<CounterModel> {
        return object : Connection<CounterModel> {
            override fun dispose() {

            }

            override fun accept(value: CounterModel) {
                render(value)
            }
        }
    }

    private fun createLoop(): MobiusLoop.Builder<CounterModel, CounterEvent, CounterEffect> =
        RxMobius.loop(
            ::update,
            effectHandler()
        )
            .eventSource(eventSource)
            .logger(
                AndroidLogger.tag("Matt Mobius")
            )

    private fun effectHandler(): ObservableTransformer<CounterEffect, CounterEvent> {
        return ObservableTransformer { Observable.never() }
    }

    private fun render(model: CounterModel) {
        setContent {
            MattMobiusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterScreen(
                        model = model,
                        eventSource = eventSource
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MattMobiusTheme {
        CounterScreen(
            model = CounterModel.ZERO,
            eventSource = DeferredEventSource()
        )
    }
}