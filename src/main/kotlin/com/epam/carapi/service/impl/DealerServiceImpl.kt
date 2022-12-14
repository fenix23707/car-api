package com.epam.carapi.service.impl

import com.epam.carapi.dto.DealerDto
import com.epam.carapi.entity.Dealer
import com.epam.carapi.exception.notfound.DealerNotFoundException
import com.epam.carapi.repository.api.DealerRepository
import com.epam.carapi.service.api.DealerService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DealerServiceImpl(
    private val dealerRepository: DealerRepository,
) : DealerService {

    override fun getById(id: Long): Dealer {
        return dealerRepository.findByIdOrNull(id) ?: throw DealerNotFoundException(id)
    }

    override fun getAllByCompanyName(companyName: String): List<DealerDto> {
        return dealerRepository.findAllByCompanyName(companyName).map { DealerDto.toDto(it) }
    }

    override fun getDealersByVehicleId(vehicleId: Long): List<Dealer> {
        return dealerRepository.findAllByVehicleId(vehicleId)
    }
}
