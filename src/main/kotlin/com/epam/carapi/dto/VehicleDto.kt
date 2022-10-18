package com.epam.carapi.dto

import com.epam.carapi.entity.Vehicle
import java.math.BigDecimal

data class VehicleDto(
    val id: Long?,
    val make: String?,
    val model: String?,
    val year: Int?,
    val price: BigDecimal?,
    val engine: EngineDto?,
    val colors: List<ColorDto>?
) {
    companion object {
        fun toDto(vehicle: Vehicle): VehicleDto {
            return VehicleDto(
                id = vehicle.id,
                make = vehicle.make,
                model = vehicle.model,
                year = vehicle.year,
                price = vehicle.price,
                engine = EngineDto.toDto(vehicle.engine),
                colors = vehicle.colors.map { ColorDto.toDto(it) }
            )
        }
    }

    fun toEntity(): Vehicle {
        return Vehicle(
            make = this.make!!,
            model = this.model!!,
            year = this.year!!,
            price = this.price!!,
            engine = this.engine!!.toEntity(),
            colors = emptySet()
        )
    }

    fun toEntity(default: Vehicle): Vehicle {
        return Vehicle(
            id = default.id!!,
            make = this.make ?: default.make,
            model = this.model ?: default.model,
            year = this.year ?: default.year,
            price = this.price ?: default.price,
            engine = this.engine?.toEntity(default.engine) ?: default.engine,
            colors = default.colors
        )
    }
}
