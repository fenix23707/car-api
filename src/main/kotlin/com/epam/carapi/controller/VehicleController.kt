package com.epam.carapi.controller

import com.epam.carapi.dto.ColorDto
import com.epam.carapi.dto.VehicleDto
import com.epam.carapi.entity.Color
import com.epam.carapi.entity.Vehicle
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
class VehicleController(private val vehicleService: VehicleService) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): VehicleDto {
        return vehicleService.getDtoById(id)
    }

    @PostMapping
    fun create(@RequestBody vehicleDto: VehicleDto): ResponseEntity<Vehicle> {
        val vehicle = vehicleService.create(vehicleDto)
        return ResponseEntity(vehicle, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody vehicleDto: VehicleDto): ResponseEntity<Vehicle> {
        val vehicle = vehicleService.update(id, vehicleDto)
        return ResponseEntity(vehicle, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        vehicleService.delete(id)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/{id}/colors")
    fun addColor(@PathVariable id: Long, @RequestBody colorDto: ColorDto): ResponseEntity<Color> {
        val color = vehicleService.addColor(id, colorDto)
        return ResponseEntity(color, HttpStatus.CREATED)
    }
}
