package com.example.avafintestfields.presentation.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

class CoroutineTestRule(
    val dispatcher: CoroutineDispatcher = StandardTestDispatcher()
) {

    fun runBlockingTest(block: suspend TestScope.() -> Unit) = runTest(dispatcher) {
        block()
    }
}