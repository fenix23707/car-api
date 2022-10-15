package com.epam.carapi.repository.api

import com.epam.carapi.entity.Color
import org.springframework.data.jpa.repository.JpaRepository

interface ColorRepository : JpaRepository<Color, Long> {

    fun findAllByVehicleId(vehicleId: Long): List<Color>

    fun deleteByVehicleId(vehicleId: Long): Long
}
