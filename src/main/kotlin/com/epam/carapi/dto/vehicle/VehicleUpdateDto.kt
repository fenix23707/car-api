package com.epam.carapi.dto.vehicle

import com.epam.carapi.dto.engine.EngineUpdateDto
import com.epam.carapi.entity.Vehicle
import java.math.BigDecimal

data class VehicleUpdateDto(
    val make: String?,
    val model: String?,
    val year: Int?,
    val price: BigDecimal?,
    val engine: EngineUpdateDto?,
) {

    fun toEntity(default: Vehicle): Vehicle {
        return Vehicle(
            id = default.id!!,
            make = make ?: default.make,
            model = model ?: default.model,
            year = year ?: default.year,
            price = price ?: default.price,
            engine = engine?.toEntity(default.engine) ?: default.engine
        )
    }
}
