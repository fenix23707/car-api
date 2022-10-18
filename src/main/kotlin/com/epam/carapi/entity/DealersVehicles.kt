package com.epam.carapi.entity

import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

@Entity
@Table(name = "dealers_vehicles")
data class DealersVehicles(

    @EmbeddedId
    val id: DealersVehiclesId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vehicleId")
    val vehicle: Vehicle,

    @ManyToOne
    @MapsId("dealerId")
    val dealer: Dealer,
)
