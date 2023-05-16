package com.vigli.tddbook

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PasswordStrengthMeterTest {
    private val sut = PasswordStrengthMeter()
    @Test
    fun meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG)

        assertStrength("abc1!Add", PasswordStrength.STRONG)
    }

    @Test
    fun meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("Ab12!c", PasswordStrength.NORMAL)
    }

    @Test
    fun meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL)
    }

    @Test
    fun emptyInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID)
    }

    @Test
    fun meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL)
    }

    @Test
    fun meetsOnlyLengthCriteria_Then_Normal() {
        assertStrength("abdefghi", PasswordStrength.WEAK)
    }

    @Test
    fun meetsOnlyNumCriteria_Then_Normal() {
        assertStrength("12345", PasswordStrength.WEAK)
    }

    @Test
    fun meetsOnlyUpperCriteria_Then_Normal() {
        assertStrength("ABZEF", PasswordStrength.WEAK)
    }

    @Test
    fun meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK)
    }

    private fun assertStrength(password: String, expStr: PasswordStrength) {
        val result = sut.meter(password)
        assertEquals(expStr, result)
    }
}
