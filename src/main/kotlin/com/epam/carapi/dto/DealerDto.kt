package com.epam.carapi.dto

import com.epam.carapi.entity.Dealer

data class DealerDto(
    val id: Long? = null,
    val companyName: String? = null,
    val directorName: String? = null,
    val phone: String? = null
) {

    companion object {
        fun toDto(dealer: Dealer): DealerDto {
            return DealerDto(
                id = dealer.id,
                companyName = dealer.companyName,
                directorName = dealer.directorName,
                phone = dealer.phone
            )
        }
    }
}
