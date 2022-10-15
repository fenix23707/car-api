package com.epam.carapi.dto

import com.epam.carapi.entity.Engine
import com.epam.carapi.entity.EngineType

data class EngineDto(
    val id: Long? = null,
    val type: EngineType?,
    val power: Int?
) {
    companion object {
        fun toDto(engine: Engine): EngineDto {
            return EngineDto(
                id = engine.id,
                type = engine.type,
                power = engine.power,
            )
        }
    }

    fun toEntity(): Engine {
        return Engine(
            power = this.power!!,
            type = this.type!!
        )
    }

    fun toEntity(default: Engine): Engine {
        return Engine(
            power = this.power ?: default.power,
            type = this.type ?: default.type
        )
    }
}
