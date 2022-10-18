package com.epam.carapi.repository.api

import com.epam.carapi.entity.Vehicle
import org.springframework.data.jpa.repository.JpaRepository

interface VehicleRepository : JpaRepository<Vehicle, Long>
