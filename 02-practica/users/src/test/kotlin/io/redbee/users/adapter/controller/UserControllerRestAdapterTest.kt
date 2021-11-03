package io.redbee.users.adapter.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.redbee.users.adapter.controller.model.UserResponse
import io.redbee.users.application.port.`in`.GetAllUsersQuery
import io.redbee.users.application.port.`in`.GetUserByIdQuery
import io.redbee.users.mock.MockFactory.createUser
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito.`when`
import org.mockito.Mockito.eq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@DisplayName("Test user controller rest adapter")
@WebMvcTest(controllers = [UserControllerRestAdapter::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerRestAdapterTest{

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var getAllUsersQuery: GetAllUsersQuery

    @MockBean
    private lateinit var getUserByIdQuery: GetUserByIdQuery

    @Test
    fun testGetAllUsersReturnOK() {
        val someUser = createUser(10)
        val anotherUser = createUser(20)

        val mockedUsers = listOf(
                someUser,
                anotherUser,
        )

        `when`(getAllUsersQuery.execute()).thenReturn(mockedUsers)

        val expectedResponse = listOf(
                UserResponse.from(someUser),
                UserResponse.from(anotherUser)
        )

        mockMvc.perform(
                get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect {
            status().isOk
            content().json(objectMapper.writeValueAsString(expectedResponse))
        }
    }

    @Test
    fun testGetUserByIdReturnOK() {
        val id = 50
        val mockedUser = createUser(id)

        `when`(getUserByIdQuery.execute(eq(id))).thenReturn(mockedUser)

        val expectedResponse = UserResponse.from(mockedUser)

        mockMvc.perform(
                get("/api/v1/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect {
            status().isOk
            content().json(objectMapper.writeValueAsString(expectedResponse))
        }
    }
}
