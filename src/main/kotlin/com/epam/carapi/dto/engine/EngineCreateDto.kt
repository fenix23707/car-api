package com.epam.carapi.dto.engine

import com.epam.carapi.entity.Engine
import com.epam.carapi.entity.EngineType

data class EngineCreateDto(
    val type: EngineType,
    val power: Int
) {

    fun toEntity() : Engine {
        return Engine(
            type = type,
            power = power
        )
    }
}
