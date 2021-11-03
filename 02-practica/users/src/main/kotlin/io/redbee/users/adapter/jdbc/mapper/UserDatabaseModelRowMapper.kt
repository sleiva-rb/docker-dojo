package io.redbee.users.adapter.jdbc.mapper

import io.redbee.users.adapter.jdbc.model.UserDatabaseModel
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class UserDatabaseModelRowMapper : RowMapper<UserDatabaseModel> {

    override fun mapRow(rs: ResultSet, rowNum: Int): UserDatabaseModel =
            UserDatabaseModel(
                    id = rs.getInt(idColumnLabel),
                    username = rs.getString(usernameColumnLabel),
                    displayName = rs.getString(displayNameColumnLabel),
                    email = rs.getString(emailColumnLabel),
                    createdAt = rs.getTimestamp(createdAtColumnLabel).toLocalDateTime()
            )

    companion object {
        @JvmStatic
        val idColumnLabel: String = "id"

        @JvmStatic
        val usernameColumnLabel: String = "username"

        @JvmStatic
        val displayNameColumnLabel: String = "displayName"

        @JvmStatic
        val emailColumnLabel: String = "email"

        @JvmStatic
        val createdAtColumnLabel: String = "createdAt"
    }

}
