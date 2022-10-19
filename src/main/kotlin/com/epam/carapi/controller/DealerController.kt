package com.epam.carapi.controller

import com.epam.carapi.dto.DealerDto
import com.epam.carapi.dto.VehicleDto
import com.epam.carapi.entity.Dealer
import com.epam.carapi.service.api.DealerService
import com.epam.carapi.service.api.VehicleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dealers")
class DealerController(
    private val dealerService: DealerService,
    private val vehicleService: VehicleService
) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Dealer {
        return dealerService.getById(id)
    }

    @GetMapping
    fun getAllByCompanyName(@RequestParam("companyName") companyName: String): List<DealerDto> {
        return dealerService.getAllByCompanyName(companyName)
    }

    @GetMapping("/{id}/vehicles")
    fun getVehicles(@PathVariable id: Long): List<VehicleDto> {
        return vehicleService.getVehiclesByDealerId(id)
    }
}
