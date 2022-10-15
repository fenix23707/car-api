package com.epam.carapi.dto.engine

import com.epam.carapi.entity.Engine
import com.epam.carapi.entity.EngineType

data class EngineUpdateDto(
    val type: EngineType?,
    val power: Int?
) {

    fun toEntity(default: Engine) : Engine {
        return Engine(
            id = default.id!!,
            type = type ?: default.type,
            power = power ?: default.power
        )
    }
}
