package com.delivery.common.status

interface StatusCode {
    fun getHttpStatusCode(): Int
    fun getCode(): Int
    fun getMessage(): String
}