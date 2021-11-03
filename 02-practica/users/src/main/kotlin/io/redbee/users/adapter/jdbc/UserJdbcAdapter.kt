package io.redbee.users.adapter.jdbc

import io.redbee.users.adapter.jdbc.mapper.UserDatabaseModelRowMapper
import io.redbee.users.adapter.jdbc.mapper.UserDatabaseModelRowMapper.Companion.createdAtColumnLabel
import io.redbee.users.adapter.jdbc.mapper.UserDatabaseModelRowMapper.Companion.displayNameColumnLabel
import io.redbee.users.adapter.jdbc.mapper.UserDatabaseModelRowMapper.Companion.emailColumnLabel
import io.redbee.users.adapter.jdbc.mapper.UserDatabaseModelRowMapper.Companion.idColumnLabel
import io.redbee.users.adapter.jdbc.mapper.UserDatabaseModelRowMapper.Companion.usernameColumnLabel
import io.redbee.users.application.port.out.UserRepository
import io.redbee.users.domain.User
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UserJdbcAdapter(
        private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
): UserRepository {

    override fun getAll(): List<User> = namedParameterJdbcTemplate
            .query(getAllUsersSql, UserDatabaseModelRowMapper())
            .map { it.toDomain() }

    override fun getById(id: Int): User =
            namedParameterJdbcTemplate
                    .queryForObject(getUserByIdSql, MapSqlParameterSource(idSqlParam, id), UserDatabaseModelRowMapper())
                    .let { it!!.toDomain() }

    companion object {

        @JvmStatic
        private val idSqlParam: String = "id"

        @JvmStatic
        private val getAllUsersSql: String = """
            SELECT
                u.id AS $idColumnLabel,
                u.username AS $usernameColumnLabel,
                u.display_name AS $displayNameColumnLabel,
                u.email AS $emailColumnLabel,
                u.created_at AS $createdAtColumnLabel
            FROM users AS u
        """.trimIndent()

        @JvmStatic
        private val getUserByIdSql: String = "$getAllUsersSql WHERE u.id = :$idSqlParam"

    }

}
