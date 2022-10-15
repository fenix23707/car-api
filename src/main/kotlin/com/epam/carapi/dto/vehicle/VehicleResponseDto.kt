package com.epam.carapi.dto.vehicle

import com.epam.carapi.entity.Color
import com.epam.carapi.entity.Engine
import com.epam.carapi.entity.Vehicle
import java.math.BigDecimal

data class VehicleResponseDto(
    val id: Long,
    val make: String,
    val model: String,
    val year: Int,
    val price: BigDecimal,
    val engine: Engine,
    val colors: List<Color>
) {
    companion object {
        fun fromEntity(vehicle: Vehicle, colors: List<Color>) : VehicleResponseDto {
            return VehicleResponseDto(
                id = vehicle.id!!,
                make = vehicle.make,
                model = vehicle.model,
                year = vehicle.year,
                price = vehicle.price,
                engine = vehicle.engine,
                colors = colors
            )
        }
    }
}
