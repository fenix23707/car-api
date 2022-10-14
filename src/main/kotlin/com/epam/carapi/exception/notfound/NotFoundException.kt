package com.epam.carapi.exception.notfound

import com.epam.carapi.exception.ServiceException
import org.springframework.http.HttpStatus

abstract class NotFoundException(message: String) : ServiceException(HttpStatus.NOT_FOUND, message)
