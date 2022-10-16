package com.epam.carapi.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class DealersVehiclesId(

    @Column(name = "vehicle_id")
    val vehicleId: Long,

    @Column(name = "dealer_id")
    val dealerId: Long
) : Serializable
