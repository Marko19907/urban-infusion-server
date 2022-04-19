package no.ntnu.webdev.webproject7.utilities

import org.springframework.security.crypto.bcrypt.BCrypt

private const val MIN_PASSWORD_LENGTH = 8;
private const val MAX_PASSWORD_LENGTH = 20;

fun hashPassword(password: String): String {
    return BCrypt.hashpw(password, BCrypt.gensalt());
}

fun checkPasswordLength(password: String): Boolean {
    if (password.isBlank()) {
        return false;
    }
    if (password.length < MIN_PASSWORD_LENGTH || password.length > MAX_PASSWORD_LENGTH) {
        return false;
    }
    return true;
}