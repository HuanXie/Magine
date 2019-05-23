package com.example.basemvvm.data

import java.lang.Exception

data class APIError(val errorCode: Int, val errorMessage: String) : Exception()