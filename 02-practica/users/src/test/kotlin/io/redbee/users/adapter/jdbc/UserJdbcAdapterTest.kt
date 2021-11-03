package io.redbee.users.adapter.jdbc

import io.redbee.users.adapter.jdbc.model.UserDatabaseModel
import io.redbee.users.application.port.out.UserRepository
import io.redbee.users.mock.MockFactory
import io.redbee.users.mock.MockFactory.createUserDatabaseModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import java.time.LocalDateTime
import java.util.Collections.singletonList

@DisplayName("Test user JDBC adapter")
class UserJdbcAdapterTest {

    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate = mock(NamedParameterJdbcTemplate::class.java)

    private val userRepository: UserRepository = UserJdbcAdapter(
            namedParameterJdbcTemplate = namedParameterJdbcTemplate
    )

    @Test
    @DisplayName("When get all users, NamedParameterJdbcTemplate.query() should be invoked")
    fun testGetAllUsersFromJdbcTemplateQueryShouldBeInvokedOnlyOnce() {
        val mockedUserDatabaseModel = singletonList(createUserDatabaseModel(createdAt = LocalDateTime.MIN))

        `when`(namedParameterJdbcTemplate.query(anyString(), any<RowMapper<UserDatabaseModel>>()))
                .thenReturn(mockedUserDatabaseModel)

        val expectedResult = singletonList(MockFactory.createUser())

        val result = userRepository.getAll()

        verify(namedParameterJdbcTemplate, times(1))
                .query(anyString(), any<RowMapper<UserDatabaseModel>>())

        assertEquals(expectedResult, result)
    }

    @Test
    @DisplayName("When get all users, NamedParameterJdbcTemplate.queryForObject() should be invoked")
    fun testGetByIdFromJdbcTemplateQueryForObjectShouldBeInvoked() {
        val id = 10

        val mockedUserDatabaseModel = createUserDatabaseModel(id, LocalDateTime.MIN)

        `when`(
                namedParameterJdbcTemplate.queryForObject(
                        anyString(),
                        any(SqlParameterSource::class.java),
                        any<RowMapper<UserDatabaseModel>>()
                )
        ).thenReturn(mockedUserDatabaseModel)

        val result = userRepository.getById(id)

        assertEquals(MockFactory.createUser(id), result)
    }

}
