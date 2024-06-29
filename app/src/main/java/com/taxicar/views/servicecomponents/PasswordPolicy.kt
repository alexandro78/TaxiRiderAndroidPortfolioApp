package com.taxicar.views.servicecomponents

internal fun validatePassword(passwordPolicyValidation: String): String {
    val maxPasswordLength = 16
    val minPasswordLength = 8
    val specialCharactersRegex = "[!@#\$%^&*(),.?\":{}|<>]".toRegex()
    val latinAlphabetRegex = "[a-zA-Z]+".toRegex()
    var checkedPassword = ""

    if (passwordPolicyValidation.length > maxPasswordLength) {
        checkedPassword = "Пароль повинен містити максимум 16 символів!"
    } else if (passwordPolicyValidation.length < minPasswordLength) {
        checkedPassword = "Пароль повинен містити мінімум 8 символів!"
    } else if (specialCharactersRegex.containsMatchIn(passwordPolicyValidation)) {
        checkedPassword = "Пароль не повинен містити спеціальні символи!"
    } else if (!latinAlphabetRegex.matches(passwordPolicyValidation)) {
        checkedPassword = "Пароль повинен складатись тільки з символів латинського алфавіту!"
    }

    return checkedPassword
}
