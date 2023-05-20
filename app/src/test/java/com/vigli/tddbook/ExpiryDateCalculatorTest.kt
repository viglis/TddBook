package com.vigli.tddbook

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDate

class ExpiryDateCalculatorTest {
    @Test
    fun `만원 납부하면 한달 뒤가 만료일이 됨`() {
        assertExpiryDate(
            PayData(
                billingDate = LocalDate.of(2019, 3, 1),
                payAmount = 10_000
            ),
            LocalDate.of(2019, 4, 1)
        )

        assertExpiryDate(
            PayData(
                billingDate = LocalDate.of(2019, 5, 5),
                payAmount = 10_000
            ),
            LocalDate.of(2019, 6, 5)
        )
    }

    @Test
    fun `납부일과 한달 뒤 일자가 같지 않음`() {
        assertExpiryDate(
            PayData(
                billingDate = LocalDate.of(2019, 1, 31),
                payAmount = 10_000
            ),
            LocalDate.of(2019, 2, 28)
        )

        assertExpiryDate(
            PayData(
                billingDate = LocalDate.of(2019, 5, 31),
                payAmount = 10_000
            ),
            LocalDate.of(2019, 6, 30)
        )

        assertExpiryDate(
            PayData(
                billingDate = LocalDate.of(2020, 1, 31),
                payAmount = 10_000
            ),
            LocalDate.of(2020, 2, 29)
        )
    }

    @Test
    fun `첫 납부일과 만료이 일자가 다를때 만원 납부`() {
        val payData = PayData(
            firstBillingDate = LocalDate.of(2019, 1, 31),
            billingDate = LocalDate.of(2019, 2, 28),
            payAmount = 10_000
        )

        assertExpiryDate(payData, LocalDate.of(2019, 3, 31))

        val payData2 = PayData(
            firstBillingDate = LocalDate.of(2019, 1, 30),
            billingDate = LocalDate.of(2019, 2, 28),
            payAmount = 10_000
        )

        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30))

        val payData3 = PayData(
            firstBillingDate = LocalDate.of(2019, 5, 31),
            billingDate = LocalDate.of(2019, 6, 30),
            payAmount = 10_000
        )

        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31))
    }

    @Test
    fun `이만원 이상 납부하면 비례해서 만료일 계산`() {
        assertExpiryDate(
            PayData(
                billingDate = LocalDate.of(2019, 3, 1),
                payAmount = 20_000
            ),
            LocalDate.of(2019, 5, 1)
        )

        assertExpiryDate(
            PayData(
                billingDate = LocalDate.of(2019, 3, 1),
                payAmount = 30_000
            ),
            LocalDate.of(2019, 6, 1)
        )
    }

    @Test
    fun `첫 납부일과 만료일 일자가 다를 때 2만원 이상 납부 사례 추가`() {
        assertExpiryDate(
            PayData(
                firstBillingDate = LocalDate.of(2019, 1, 31),
                billingDate = LocalDate.of(2019, 2, 28),
                payAmount = 20_000
            ),
            LocalDate.of(2019, 4, 30)
        )
    }

    private fun assertExpiryDate(payData: PayData, expectedExpiryDate: LocalDate) {
        val realExpiryDate = ExpiryDateCalculator().calculateExpiryDate(payData)
        assertEquals(expectedExpiryDate, realExpiryDate)
    }
}

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        val addedMonths = payData.payAmount / 10_000L

        return if (payData.firstBillingDate != null) {
            expiryDateUsingFirstBillingDate(payData, addedMonths)
        } else {
            payData.billingDate.plusMonths(addedMonths)
        }
    }

    private fun expiryDateUsingFirstBillingDate(payData: PayData, addedMonths: Long): LocalDate {
        val candidateExp = payData.billingDate.plusMonths(addedMonths)
        return if (isSameDayOfMonth(payData.billingDate, candidateExp)) {
            val dayLenOfCandiMon = candidateExp.lengthOfMonth()
            candidateExp.withDayOfMonth(
                payData.firstBillingDate?.dayOfMonth?.coerceAtMost(dayLenOfCandiMon) ?: throw IllegalStateException()
            )
        } else {
            candidateExp
        }
    }

    private fun isSameDayOfMonth(firstBillingDate: LocalDate, candidateExp: LocalDate): Boolean {
        return firstBillingDate.dayOfMonth == candidateExp.dayOfMonth
    }
}

data class PayData(
    val firstBillingDate: LocalDate? = null,
    val billingDate: LocalDate,
    val payAmount: Int
) {
}
