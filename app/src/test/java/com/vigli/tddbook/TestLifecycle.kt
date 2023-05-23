package com.vigli.tddbook

import org.junit.jupiter.api.*

@DisplayName("테스트 라이프사이클 관련 테스트")
class TestLifecycle {
    init {
        println("init block")
    }

    @BeforeEach
    fun setUp() {
        println("setUp")
    }

    @DisplayName("테스트1")
    @Disabled
    @Test
    fun test1() {
        println("test1")
        fail("실행되면 안됨")
    }

    @DisplayName("테스트2")
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
