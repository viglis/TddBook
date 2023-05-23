package com.vigli.tddbook

import org.junit.jupiter.api.*

class TestLifecycle {
    init {
        println("init block")
    }

    @BeforeEach
    fun setUp() {
        println("setUp")
    }

    @Test
    fun test1() {
        println("test1")
    }

    @Test
    fun test2() {
        println("test2")
    }

    @AfterEach
    fun tearDown() {
        println("tearDown")
    }

    companion object {
        init {
            println("companion object init block")
        }

        @BeforeAll
        fun setUpAll() {
            println("setUpAll")
            fail("fail")
        }

        @AfterAll
        fun tearDownAll() {
            println("tearDownAll")
            fail("fail")
        }
    }
}
