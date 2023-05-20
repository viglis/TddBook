package com.vigli.tddbook

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDate

class ExpiryDateCalculatorTest {
    @Test
    fun `만원 납부하면 한달 뒤가 만료일이 됨`() {
        val billingDate = LocalDate.of(2019, 3, 1)
        val payAmount = 10_000

        val expiryDate = ExpiryDateCalculator().calculateExpiryDate(billingDate, payAmount)

        assertEquals(LocalDate.of(2019, 4, 1), expiryDate)

        val billingDate2 = LocalDate.of(2019, 5, 5)
        val payAmount2 = 10_000

        val expiryDate2 = ExpiryDateCalculator().calculateExpiryDate(billingDate2, payAmount2)

        assertEquals(LocalDate.of(2019, 6, 5), expiryDate2)
    }
}

class ExpiryDateCalculator {
    fun calculateExpiryDate(billingDate: LocalDate, payAmount: Int): LocalDate {
        return billingDate.plusMonths(1)
    }
}
