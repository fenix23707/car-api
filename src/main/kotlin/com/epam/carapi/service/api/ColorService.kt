package com.epam.carapi.service.api

import com.epam.carapi.entity.Color

interface ColorService {

    fun getAllByVehicleId(vehicleId: Long): List<Color>

    fun create(color: Color): Color

    fun deleteByVehicleId(vehicleId: Long)
}
