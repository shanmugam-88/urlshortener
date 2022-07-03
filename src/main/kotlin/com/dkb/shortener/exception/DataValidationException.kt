package com.dkb.shortener.exception

import java.lang.RuntimeException

class DataValidationException (var errorMessage : String) : RuntimeException() {
    override val message: String?
        get() = super.message
}