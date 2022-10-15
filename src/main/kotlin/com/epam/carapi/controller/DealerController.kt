package com.epam.carapi.controller

import com.epam.carapi.entity.Dealer
import com.epam.carapi.service.api.DealerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dealers")
class DealerController(private val dealerService: DealerService) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Dealer {
        return dealerService.getById(id)
    }
}
