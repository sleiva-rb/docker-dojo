package io.redbee.users.application.usecase

import io.redbee.users.application.port.`in`.GetAllUsersQuery
import io.redbee.users.application.port.out.UserRepository
import io.redbee.users.mock.MockFactory
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.Collections.singletonList

@DisplayName("Test get all users use case")
class GetAllUsersUseCaseTest {

    private val userRepository: UserRepository = mock(UserRepository::class.java)

    private val getAllUsersQuery: GetAllUsersQuery = GetAllUsersUseCase(
            userRepository = userRepository
    )

    @Test
    @DisplayName("When use case is executed, should return a list of users")
    fun testUseCaseExecuteUserRepositoryShouldBeInvoked() {
        val mockedUsers = singletonList(
                MockFactory.createUser()
        )

        `when`(userRepository.getAll()).thenReturn(mockedUsers)

        val expectedUserInResponse = MockFactory.createUser()
        val result = getAllUsersQuery.execute()

        verify(userRepository, times(1)).getAll()

        assertTrue {
            result.contains(expectedUserInResponse)
        }
    }

}
