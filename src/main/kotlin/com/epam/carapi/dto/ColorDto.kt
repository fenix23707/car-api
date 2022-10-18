package com.epam.carapi.dto

import com.epam.carapi.entity.Color
import com.epam.carapi.entity.Vehicle

data class ColorDto(
    val id: Long? = null,
    val hex: String?
) {
    companion object {
        fun toDto(color: Color): ColorDto {
            return ColorDto(
                id = color.id,
                hex = color.hex
            )
        }
    }

    fun toEntity(vehicle: Vehicle): Color {
        return Color(
            id = this.id,
            hex = this.hex!!,
            vehicle = vehicle
        )
    }
}
