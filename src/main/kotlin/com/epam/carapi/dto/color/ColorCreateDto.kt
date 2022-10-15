package com.epam.carapi.dto.color

import com.epam.carapi.entity.Color
import com.epam.carapi.entity.Vehicle

data class ColorCreateDto(
    val hex: String
) {
    fun toEntity(vehicle: Vehicle): Color {
        return Color(
            hex = hex,
            vehicle = vehicle
        )
    }
}
