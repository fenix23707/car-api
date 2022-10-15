package com.epam.carapi.service.impl

import com.epam.carapi.entity.Color
import com.epam.carapi.repository.api.ColorRepository
import com.epam.carapi.service.api.ColorService
import org.springframework.stereotype.Service

@Service
class ColorServiceImpl(
    private val colorRepository: ColorRepository,
) : ColorService {

    override fun getAllByVehicleId(vehicleId: Long): List<Color> {
        return colorRepository.findAllByVehicleId(vehicleId)
    }

    override fun create(color: Color): Color {
        return colorRepository.save(color)
    }

    override fun deleteByVehicleId(vehicleId: Long) {
        colorRepository.deleteByVehicleId(vehicleId)
    }
}
