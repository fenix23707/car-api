package com.epam.carapi.controller

import com.epam.carapi.entity.Color
import com.epam.carapi.service.api.ColorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ColorController(
    private val colorService: ColorService
) {

    @GetMapping("/vehicles/{vehicleId}/colors")
    fun getAllByVehicleId(@PathVariable vehicleId: Long): List<Color> {
        return colorService.getAllByVehicleId(vehicleId)
    }
}
