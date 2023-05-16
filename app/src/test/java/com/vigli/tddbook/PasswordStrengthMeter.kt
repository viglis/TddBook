package com.vigli.tddbook

class PasswordStrengthMeter {
    fun meter(s: String): PasswordStrength {
        if (s.length < 8) {
            return PasswordStrength.NORMAL
        }

        if (!meetsContainingNumberCriteria(s)) {
            return PasswordStrength.NORMAL
        }

        return PasswordStrength.STRONG
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
