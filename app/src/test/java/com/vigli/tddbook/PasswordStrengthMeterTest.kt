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

        assertStrength("Ab!cdef", PasswordStrength.NORMAL)
    }

    @Test
    fun meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL)
    }

    private fun assertStrength(password: String, expStr: PasswordStrength) {
        val result = sut.meter(password)
        assertEquals(expStr, result)
    }
}
