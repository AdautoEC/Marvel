package com.k4tr1n4.core.model.utils

import java.math.BigInteger
import java.security.MessageDigest

fun String.formatDate(): String {
    val date = this.split("T")[0].split("-")
    return String.format("%s/%s/%s", date[2], date[1], date[0])
}