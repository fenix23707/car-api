package com.epam.carapi.repository.api

import com.epam.carapi.entity.Dealer
import com.epam.carapi.entity.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DealerRepository : JpaRepository<Dealer, Long> {

    @Query("from Dealer v where lower(v.companyName) like lower(concat('%',:companyName,'%'))")
    fun findAllByCompanyName(@Param("companyName") companyName: String): List<Dealer>

    @Query("from Dealer d join DealersVehicles dv on dv.id.dealerId = d.id where dv.id.vehicleId = :vehicleId")
    fun findAllByVehicleId(@Param("vehicleId") vehicleId: Long): List<Dealer>
}
