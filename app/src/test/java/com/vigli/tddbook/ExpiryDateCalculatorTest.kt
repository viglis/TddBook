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

    @Test
    fun `납부일과 한달 뒤 일자가 같지 않음`() {
        assertExpiryDate(
            LocalDate.of(2019, 1, 31),
            10_000,
            LocalDate.of(2019, 2, 28)
        )

        assertExpiryDate(
            LocalDate.of(2019, 5, 31),
            10_000,
            LocalDate.of(2019, 6, 30)
        )

        assertExpiryDate(
            LocalDate.of(2020, 1, 31),
            10_000,
            LocalDate.of(2020, 2, 29)
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
