package com.arkademy.ankasa.flight

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class SearchResultViewModel : ViewModel(), CoroutineScope {
//    private lateinit var service: flightApiService
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

//    fun set
}