package com.epam.carapi.service.api

import com.epam.carapi.entity.Dealer

interface DealerService {

    fun getById(id: Long): Dealer
}
