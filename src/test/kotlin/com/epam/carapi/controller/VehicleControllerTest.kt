package com.epam.carapi.controller

import com.epam.carapi.dto.ColorDto
import com.epam.carapi.dto.EngineDto
import com.epam.carapi.dto.VehicleDto
import com.epam.carapi.entity.Color
import com.epam.carapi.entity.Dealer
import com.epam.carapi.entity.EngineType
import com.epam.carapi.exception.notfound.VehicleNotFoundException
import com.epam.carapi.service.api.DealerService
import com.epam.carapi.service.api.VehicleService
import com.fasterxml.jackson.databind.ObjectMapper
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener
import org.springframework.http.MediaType
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import java.math.BigDecimal

@WebMvcTest(VehicleController::class)
@TestExecutionListeners(MockitoTestExecutionListener::class)
class VehicleControllerTest : AbstractTestNGSpringContextTests() {

    companion object {
        private const val VEHICLE_ID = 1L
        private const val ENGINE_ID = 2L
        private const val DEALER_ID = 3L
        private const val BASE_URL = "/vehicles"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var vehicleService: VehicleService

    @MockBean
    private lateinit var dealerService: DealerService

    private lateinit var objectMapper: ObjectMapper

    @BeforeClass
    fun init() {
        objectMapper = ObjectMapper()
    }

    @AfterMethod
    fun tearDown() {
        Mockito.reset(vehicleService)
        Mockito.reset(dealerService)
    }

    @Test
    fun `get by id should return valid dto`() {
        val vehicle = buildVehicleDto(VEHICLE_ID)
        Mockito.`when`(vehicleService.getDtoById(VEHICLE_ID)).thenReturn(vehicle)

        mockMvc.get("$BASE_URL/$VEHICLE_ID")
            .andExpect { status { isOk() } }
            .andExpect { content { json(objectMapper.writeValueAsString(vehicle)) } }
    }

    @Test
    fun `get by id should return not found status`() {
        Mockito.`when`(vehicleService.getDtoById(VEHICLE_ID)).thenThrow(VehicleNotFoundException(VEHICLE_ID))

        mockMvc.get("$BASE_URL/$VEHICLE_ID")
            .andExpect { status { isNotFound() } }
            .andExpect { jsonPath("$.status") { value("NOT_FOUND") } }
            .andExpect { jsonPath("$.message") { value("Vehicle with id = $VEHICLE_ID not found") } }
    }

    @Test
    fun `should create new vehicle`() {
        val newVehicle = buildVehicleDto(null)
        val createdVehicle = buildVehicleDto(VEHICLE_ID).toEntity()
        Mockito.`when`(vehicleService.create(any())).thenReturn(createdVehicle)

        mockMvc.post(BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newVehicle)
        }
            .andExpect { status { isCreated() } }
            .andExpect { content { json(objectMapper.writeValueAsString(createdVehicle)) } }
    }

    @Test
    fun `update by id should successfully update vehicle`() {
        val make = "new make"
        val vehicle = VehicleDto(make = make);
        val updatedVehicle = buildVehicleDto(VEHICLE_ID).copy(make = make).toEntity()
        Mockito.`when`(vehicleService.update(eq(VEHICLE_ID), any())).thenReturn(updatedVehicle)

        mockMvc.put("$BASE_URL/$VEHICLE_ID") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(vehicle)
        }
            .andExpect { status { isOk() } }
            .andExpect { content { json(objectMapper.writeValueAsString(updatedVehicle)) } }
    }

    @Test
    fun `update by id should return not fount status`() {
        Mockito.`when`(vehicleService.update(eq(VEHICLE_ID), any())).thenThrow(VehicleNotFoundException(VEHICLE_ID))

        mockMvc.put("$BASE_URL/$VEHICLE_ID") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(buildVehicleDto(null))
        }
            .andExpect { status { isNotFound() } }
            .andExpect { jsonPath("$.status") { value("NOT_FOUND") } }
            .andExpect { jsonPath("$.message") { value("Vehicle with id = $VEHICLE_ID not found") } }
    }

    @Test
    fun `should successfully delete by id`() {
        mockMvc.delete("$BASE_URL/$VEHICLE_ID")
            .andExpect { status { isOk() } }
        Mockito.verify(vehicleService, times(1)).delete(eq(VEHICLE_ID))
    }

    @Test
    fun `delete by id should return not fount status`() {
        Mockito.`when`(vehicleService.delete(VEHICLE_ID)).thenThrow(VehicleNotFoundException(VEHICLE_ID))

        mockMvc.delete("$BASE_URL/$VEHICLE_ID")
            .andExpect { status { isNotFound() } }
            .andExpect { jsonPath("$.status") { value("NOT_FOUND") } }
            .andExpect { jsonPath("$.message") { value("Vehicle with id = $VEHICLE_ID not found") } }
        Mockito.verify(vehicleService, times(1)).delete(eq(VEHICLE_ID))
    }

    @Test
    fun `should successfully add color`() {
        val hex = "df73ff"
        val color = ColorDto(hex = hex)
        val createdColor = Color(id = 1, hex = hex, buildVehicleDto(VEHICLE_ID).toEntity())
        Mockito.`when`(vehicleService.addColor(eq(VEHICLE_ID), any())).thenReturn(createdColor)

        mockMvc.post("$BASE_URL/$VEHICLE_ID/colors") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(color)
        }
            .andExpect { status { isCreated() } }
            .andExpect { content { json(objectMapper.writeValueAsString(createdColor)) } }
    }

    @Test
    fun `should return list of dealers by vehicle id`() {
        val dealers = listOf(buildDealer())
        Mockito.`when`(dealerService.getDealersByVehicleId(VEHICLE_ID)).thenReturn(dealers)

        mockMvc.get("$BASE_URL/$VEHICLE_ID/dealers")
            .andExpect { status { isOk() } }
            .andExpect { content { json(objectMapper.writeValueAsString(dealers)) } }
    }

    private fun buildVehicleDto(id: Long?): VehicleDto {
        return VehicleDto(
            id = id,
            make = "Audi",
            model = "A3",
            year = 2022,
            price = BigDecimal(26990.0),
            engine = buildEngine(),
            colors = buildColors()
        )
    }

    private fun buildEngine(): EngineDto {
        return EngineDto(
            id = ENGINE_ID, type = EngineType.GAS, power = 200
        )
    }

    private fun buildColors(): List<ColorDto> {
        return listOf(
            ColorDto(1, "40e0d0"),
            ColorDto(2, "86b7e8"),
        )
    }

    private fun buildDealer(): Dealer {
        return Dealer(
            id = DEALER_ID,
            companyName = "default company name",
            directorName = "default director name",
            phone = "default phone"
        )
    }
}
