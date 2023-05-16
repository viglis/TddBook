package com.vigli.tddbook

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PasswordStrengthMeterTest {
    @Test
    fun meetsAllCriteria_Then_Strong() {
        val meter = PasswordStrengthMeter()
        val result = meter.meter("ab12!@AB")
        assertEquals(PasswordStrength.STRONG, result)

        val result2 = meter.meter("abc1!Add")
        assertEquals(PasswordStrength.STRONG, result2)
    }

    @Test
    fun meetsOtherCriteria_except_for_Length_Then_Normal() {
        val meter = PasswordStrengthMeter()
        val result = meter.meter("ab12!@A")
        assertEquals(PasswordStrength.NORMAL, result)

        val result2 = meter.meter("Ab12!c")
        assertEquals(PasswordStrength.NORMAL, result2)
    }

    @Test
    fun meetsOtherCriteria_except_for_number_Then_Normal() {
        val meter = PasswordStrengthMeter()
        val result = meter.meter("ab!@ABqwer")
        assertEquals(PasswordStrength.NORMAL, result)
    }
}
