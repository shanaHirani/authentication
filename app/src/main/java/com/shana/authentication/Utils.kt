package com.shana.authentication


fun isUserNameValid(user: String?): Boolean {
    if (user != null)
        return user.length > 1
    else
        return false
}

fun isPassWordValid(password: String?): Boolean {
    if (password != null)
        return password.length > 1
    else
        return false
}