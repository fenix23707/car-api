package com.epam.carapi.service.impl

import com.epam.carapi.dto.color.ColorCreateDto
import com.epam.carapi.dto.vehicle.VehicleCreateDto
import com.epam.carapi.dto.vehicle.VehicleResponseDto
import com.epam.carapi.dto.vehicle.VehicleUpdateDto
import com.epam.carapi.entity.Color
import com.epam.carapi.entity.Vehicle
import com.epam.carapi.exception.notfound.VehicleNotFoundException
import com.epam.carapi.repository.api.VehicleRepository
import com.epam.carapi.service.api.ColorService
import com.epam.carapi.service.api.VehicleService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class VehicleServiceImpl(
    private val vehicleRepository: VehicleRepository,
    private val colorService: ColorService
) : VehicleService {

    override fun getById(id: Long): Vehicle {
        return vehicleRepository.findByIdOrNull(id) ?: throw VehicleNotFoundException(id)
    }

    override fun getResponseDtoById(id: Long): VehicleResponseDto {
        val vehicle = getById(id)
        val colors = colorService.getAllByVehicleId(id)
        return VehicleResponseDto.fromEntity(vehicle, colors)
    }

    @Transactional
    override fun create(vehicleDto: VehicleCreateDto): Vehicle {
        return vehicleRepository.save(vehicleDto.toEntity())
    }

    @Transactional
    override fun update(id: Long, vehicleDto: VehicleUpdateDto): Vehicle {
        val default = getById(id)
        return vehicleRepository.save(vehicleDto.toEntity(default))
    }

    @Transactional
    override fun delete(id: Long) {
        checkVehicleExist(id)
        colorService.deleteByVehicleId(id)
        vehicleRepository.deleteById(id)
    }

    override fun addColor(vehicleId: Long, colorDto: ColorCreateDto): Color {
        val vehicle = getById(vehicleId)
        return colorService.create(colorDto.toEntity(vehicle))
    }

    private fun checkVehicleExist(id: Long) {
        if (!vehicleRepository.existsById(id)) {
            throw VehicleNotFoundException(id)
        }
    }
}
