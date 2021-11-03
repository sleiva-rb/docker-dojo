package io.redbee.users.adapter.jdbc.mapper

import io.redbee.users.adapter.jdbc.mapper.UserDatabaseModelRowMapper.Companion.createdAtColumnLabel
import io.redbee.users.adapter.jdbc.mapper.UserDatabaseModelRowMapper.Companion.displayNameColumnLabel
import io.redbee.users.adapter.jdbc.mapper.UserDatabaseModelRowMapper.Companion.emailColumnLabel
import io.redbee.users.adapter.jdbc.mapper.UserDatabaseModelRowMapper.Companion.idColumnLabel
import io.redbee.users.adapter.jdbc.mapper.UserDatabaseModelRowMapper.Companion.usernameColumnLabel
import io.redbee.users.mock.MockFactory.createUserDatabaseModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.sql.ResultSet
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.Month

class UserDatabaseModelRowMapperTest {

    @Test
    @DisplayName("Test map row from result set to model")
    fun testMapRowFromResultSetToModel(){
        val id = 10
        val mockedCreatedAt = LocalDateTime.of(2021, Month.JANUARY, 10, 0,0,0, 0)

        val numRow = 1
        val resultSet = mock(ResultSet::class.java)
        `when`(resultSet.getInt(eq(idColumnLabel))).thenReturn(id)
        `when`(resultSet.getString(eq(usernameColumnLabel))).thenReturn("username-$id")
        `when`(resultSet.getString(eq(displayNameColumnLabel))).thenReturn("display-name-test")
        `when`(resultSet.getString(eq(emailColumnLabel))).thenReturn("email@test.com")
        `when`(resultSet.getTimestamp(eq(createdAtColumnLabel))).thenReturn(Timestamp.valueOf(mockedCreatedAt))

        val rowMapper = UserDatabaseModelRowMapper()

        val result = rowMapper.mapRow(resultSet, numRow)

        val expected = createUserDatabaseModel(id, mockedCreatedAt)

        assertEquals(expected, result)
    }

}
