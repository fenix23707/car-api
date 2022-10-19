package com.epam.carapi.repository.api

import com.epam.carapi.entity.Dealer
import com.epam.carapi.entity.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface VehicleRepository : JpaRepository<Vehicle, Long> {

    @Query("from Vehicle v where lower(v.make) like lower(concat('%', :make,'%'))")
    fun findAllByMake(make: String): List<Vehicle>

    @Query("from Vehicle v join DealersVehicles dv on dv.id.vehicleId = v.id where dv.id.dealerId = :dealerId")
    fun findAllByDealerId(@Param("dealerId") dealerId: Long): List<Vehicle>
}
