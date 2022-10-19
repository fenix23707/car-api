package com.epam.carapi.service.api

import com.epam.carapi.dto.ColorDto
import com.epam.carapi.dto.VehicleDto
import com.epam.carapi.entity.Color
import com.epam.carapi.entity.Vehicle

interface VehicleService {

    fun getById(id: Long): Vehicle

    fun getDtoById(id: Long): VehicleDto

    fun getAllByMake(make: String): List<VehicleDto>

    fun create(vehicleDto: VehicleDto): Vehicle

    fun update(id: Long, vehicleDto: VehicleDto): Vehicle

    fun delete(id: Long)

    fun addColor(vehicleId: Long, colorDto: ColorDto): Color
}
