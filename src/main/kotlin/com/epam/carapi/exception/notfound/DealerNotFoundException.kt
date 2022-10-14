package com.epam.carapi.exception.notfound

class DealerNotFoundException(id: Long) : NotFoundException("Dealer with id = $id not found")
