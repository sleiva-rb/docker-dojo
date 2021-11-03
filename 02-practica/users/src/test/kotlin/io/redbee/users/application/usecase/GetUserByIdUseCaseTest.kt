package io.redbee.users.application.usecase

import io.redbee.users.application.port.`in`.GetUserByIdQuery
import io.redbee.users.application.port.out.UserRepository
import io.redbee.users.domain.User
import io.redbee.users.mock.MockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

@DisplayName("Test get user by id use case")
class GetUserByIdUseCaseTest {

    private val mockedUser: User = MockFactory.createUser()

    private val userRepository: UserRepository = mock(UserRepository::class.java)

    private val getUserByIdQuery: GetUserByIdQuery = GetUserByIdUseCase(
            userRepository = userRepository
    )

    @Test
    @DisplayName("When use case is executed, should return an user for specific id")
    fun testUseCaseExecuteUserRepositoryShouldBeInvokedOnlyOnce() {
        val id = 10

        `when`(userRepository.getById(eq(id))).thenReturn(mockedUser)

        val result = getUserByIdQuery.execute(id)

        verify(userRepository, times(1)).getById(eq(id))

        assertEquals(mockedUser, result)
    }

}
