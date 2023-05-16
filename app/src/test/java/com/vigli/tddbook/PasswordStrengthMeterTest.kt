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
}
