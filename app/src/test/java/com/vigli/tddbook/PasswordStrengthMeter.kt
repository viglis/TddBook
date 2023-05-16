package com.vigli.tddbook

class PasswordStrengthMeter {
    fun meter(s: String): PasswordStrength {
        if (s.isBlank()) {
            return PasswordStrength.INVALID
        }

        val lengthEnough = s.length >= 8
        val containsNum = meetsContainingNumberCriteria(s)
        val containsUppercase = meetsContainingUppercaseCriteria(s)

        if (lengthEnough && !containsNum && !containsUppercase) {
            return PasswordStrength.WEAK
        }
        if (!lengthEnough && containsNum && !containsUppercase) {
            return PasswordStrength.WEAK
        }
        if (!lengthEnough && !containsNum && containsUppercase) {
            return PasswordStrength.WEAK
        }

        if (!lengthEnough) {
            return PasswordStrength.NORMAL
        }

        if (!containsNum) {
            return PasswordStrength.NORMAL
        }

        if (!containsUppercase) {
            return PasswordStrength.NORMAL
        }

        return PasswordStrength.STRONG
    }

    private fun meetsContainingUppercaseCriteria(s: String): Boolean {
        for (ch in s) {
            if (Character.isUpperCase(ch)) {
                return true
            }
        }
        return false
    }

    private fun meetsContainingNumberCriteria(s: String): Boolean {
        for (ch in s) {
            if (ch in '0'..'9') {
                return true
            }
        }
        return false
    }
}
