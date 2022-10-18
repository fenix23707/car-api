package com.epam.carapi.service.impl

import com.epam.carapi.dto.ColorDto
import com.epam.carapi.dto.VehicleDto
import com.epam.carapi.entity.Color
import com.epam.carapi.entity.Vehicle
import com.epam.carapi.exception.notfound.VehicleNotFoundException
import com.epam.carapi.repository.api.VehicleRepository
import com.epam.carapi.service.api.ColorService
import com.epam.carapi.service.api.VehicleService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class VehicleServiceImpl(
    private val vehicleRepository: VehicleRepository,
    private val colorService: ColorService
) : VehicleService {

    override fun getById(id: Long): Vehicle {
        return vehicleRepository.findByIdOrNull(id) ?: throw VehicleNotFoundException(id)
    }

    override fun getDtoById(id: Long): VehicleDto {
        return VehicleDto.toDto(getById(id))
    }

    override fun create(vehicleDto: VehicleDto): Vehicle {
        return vehicleRepository.save(vehicleDto.toEntity())
    }

    override fun update(id: Long, vehicleDto: VehicleDto): Vehicle {
        val default = getById(id)
        return vehicleRepository.save(vehicleDto.toEntity(default))
    }

    override fun delete(id: Long) {
        checkVehicleExist(id)
        vehicleRepository.deleteById(id)
    }

    override fun addColor(vehicleId: Long, colorDto: ColorDto): Color {
        val vehicle = getById(vehicleId)
        return colorService.create(colorDto.toEntity(vehicle))
    }

    private fun checkVehicleExist(id: Long) {
        if (!vehicleRepository.existsById(id)) {
            throw VehicleNotFoundException(id)
        }
    }
}
