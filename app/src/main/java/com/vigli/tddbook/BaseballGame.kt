package com.vigli.tddbook

class BaseballGame(number: String) {
    init {
        if (number.toSet().size != 3) {
            throw IllegalArgumentException()
        }
    }
}
