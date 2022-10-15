package com.epam.carapi.exception.notfound

class VehicleNotFoundException(id: Long) : NotFoundException("Vehicle with id = $id not found")
