package com.ddd.sikdorok.splash

import androidx.lifecycle.viewModelScope
import com.ddd.sikdorok.core_ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel(), SplashContract {

    private val _state = MutableStateFlow(SplashContract.State())
    override val state: StateFlow<SplashContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SplashContract.Effect>()
    override val effect: SharedFlow<SplashContract.Effect> = _effect.asSharedFlow()

    private var deeplinkReceived: Boolean = false

    override fun event(event: SplashContract.Event) {
        viewModelScope.launch {
            when (event) {
                SplashContract.Event.DueTime -> {
                    if (deeplinkReceived.not()) { // 딥링크 리스너 동작 시 동시 동작 X
                        _effect.emit(SplashContract.Effect.GoToMain())
                    }
                }
                is SplashContract.Event.DeepLink -> {
                    deeplinkReceived = true
                    _effect.emit(SplashContract.Effect.GoToMain(event.deeplink))
                }
            }
        }
    }
}