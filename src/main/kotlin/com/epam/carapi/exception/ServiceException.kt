package com.epam.carapi.exception

import org.springframework.http.HttpStatus

abstract class ServiceException(val status: HttpStatus, override val message: String) : RuntimeException(message)
