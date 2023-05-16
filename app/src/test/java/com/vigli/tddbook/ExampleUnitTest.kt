package com.vigli.tddbook

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun plus() {
        val result = Calculator.plus(1, 2)
        assertEquals(3, result)
        assertEquals(5, Calculator.plus(4, 1))
    }
}
