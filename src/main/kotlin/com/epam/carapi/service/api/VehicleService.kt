package com.epam.carapi.service.api

import com.epam.carapi.dto.color.ColorCreateDto
import com.epam.carapi.dto.vehicle.VehicleCreateDto
import com.epam.carapi.dto.vehicle.VehicleResponseDto
import com.epam.carapi.dto.vehicle.VehicleUpdateDto
import com.epam.carapi.entity.Color
import com.epam.carapi.entity.Vehicle

interface VehicleService {

    fun getById(id: Long): Vehicle

    fun getResponseDtoById(id: Long): VehicleResponseDto

    fun create(vehicleDto: VehicleCreateDto): Vehicle

    fun update(id: Long, vehicleDto: VehicleUpdateDto): Vehicle

    fun delete(id: Long)

    fun addColor(vehicleId: Long, colorDto: ColorCreateDto): Color
}
