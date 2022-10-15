package com.epam.carapi.dto.vehicle

import com.epam.carapi.dto.engine.EngineCreateDto
import com.epam.carapi.entity.Vehicle
import java.math.BigDecimal

data class VehicleCreateDto(
    val make: String,
    val model: String,
    val year: Int,
    val price: BigDecimal,
    val engine: EngineCreateDto,
) {

    fun toEntity(): Vehicle {
        return Vehicle(
            make = make,
            model = model,
            year = year,
            price = price,
            engine = engine.toEntity()
        )
    }
}
