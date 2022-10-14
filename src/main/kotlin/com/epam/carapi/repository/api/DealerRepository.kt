package com.epam.carapi.repository.api

import com.epam.carapi.entity.Dealer
import org.springframework.data.jpa.repository.JpaRepository

interface DealerRepository : JpaRepository<Dealer, Long>
