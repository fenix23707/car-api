package com.epam.carapi.controller

import com.epam.carapi.dto.color.ColorCreateDto
import com.epam.carapi.dto.vehicle.VehicleCreateDto
import com.epam.carapi.dto.vehicle.VehicleResponseDto
import com.epam.carapi.dto.vehicle.VehicleUpdateDto
import com.epam.carapi.entity.Color
import com.epam.carapi.entity.Dealer
import com.epam.carapi.entity.Vehicle
import com.epam.carapi.service.api.DealerService
import com.epam.carapi.service.api.VehicleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vehicles")
class VehicleController(
    private val vehicleService: VehicleService,
    private val dealerService: DealerService
) {

    @GetMapping("/{id}")
    fun getResponseDtoById(@PathVariable id: Long): VehicleResponseDto {
        return vehicleService.getResponseDtoById(id);
    }

    @PostMapping
    fun create(@RequestBody vehicleDto: VehicleCreateDto): ResponseEntity<Vehicle> {
        val vehicle = vehicleService.create(vehicleDto)
        return ResponseEntity(vehicle, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody vehicleDto: VehicleUpdateDto): ResponseEntity<Vehicle> {
        val vehicle = vehicleService.update(id, vehicleDto)
        return ResponseEntity(vehicle, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        vehicleService.delete(id)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/{id}/colors")
    fun addColor(@PathVariable id: Long, @RequestBody colorDto: ColorCreateDto): ResponseEntity<Color> {
        val color = vehicleService.addColor(id, colorDto)
        return ResponseEntity(color, HttpStatus.CREATED)
    }

    @GetMapping("/{id}/dealers")
    fun getDealers(@PathVariable id: Long): List<Dealer> {
        return dealerService.getDealersByVehicleId(id)
    }
}
