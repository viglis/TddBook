package com.vigli.tddbook

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDate

class ExpiryDateCalculatorTest {
    @Test
    fun `만원 납부하면 한달 뒤가 만료일이 됨`() {
        assertExpiryDate(
            LocalDate.of(2019, 3, 1),
            10_000,
            LocalDate.of(2019, 4, 1)
        )

        assertExpiryDate(
            LocalDate.of(2019, 5, 5),
            10_000,
            LocalDate.of(2019, 6, 5)
        )
    }

    private fun assertExpiryDate(billingDate: LocalDate, payAmount: Int, expectedExpiryDate: LocalDate) {
        val realExpiryDate = ExpiryDateCalculator().calculateExpiryDate(billingDate, payAmount)
        assertEquals(expectedExpiryDate, realExpiryDate)
    }
}

class ExpiryDateCalculator {
    fun calculateExpiryDate(billingDate: LocalDate, payAmount: Int): LocalDate {
        return billingDate.plusMonths(1)
    }
}
