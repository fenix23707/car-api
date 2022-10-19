package com.epam.carapi.repository.api

import com.epam.carapi.entity.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface VehicleRepository : JpaRepository<Vehicle, Long> {

    @Query("from Vehicle v where lower(v.make) like lower(concat('%', :make,'%'))")
    fun findAllByMake(make: String): List<Vehicle>
}
