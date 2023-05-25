package com.vigli.tddbook

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File

class Chapter6Test {
    @Test
    fun meetsAllCriteria_Then_Strong() {
        // when
        val sut = PasswordStrengthMeter()
        val result = sut.meter("ab12!@AB")

        // then
        assertEquals(PasswordStrength.STRONG, result)
    }

    @Test
    fun genGame_With_DupNumber_Then_Fail() {
        assertThrows<IllegalArgumentException> {
            BaseballGame("110")
        }
    }

    @Test
    fun noDataFile_Then_Exception() {
        givenNoFile("badpath.txt")

        val dataFile = File("badpath.txt")
        assertThrows<IllegalArgumentException> {
            MathUtils.sum(dataFile)
        }
    }

    private fun givenNoFile(path: String) {
        val file = File(path)
        if (file.exists()) file.delete()
    }

    private fun givenDataFile(path: String, vararg lines: String) {
        // 파일 생성
        // 파일에 라인 추가
    }

    class MathUtils {
        companion object {
            fun sum(dataFile: File): Int {
                if (!dataFile.exists()) {
                    throw IllegalArgumentException()
                }
                return 0
            }
        }
    }
}
