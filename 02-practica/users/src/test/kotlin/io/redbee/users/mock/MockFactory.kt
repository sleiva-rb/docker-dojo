package io.redbee.users.mock

import io.redbee.users.adapter.jdbc.model.UserDatabaseModel
import io.redbee.users.domain.User
import java.time.LocalDateTime

object MockFactory {

    fun createUser(id: Int = 1): User =
            User(
                    id = id,
                    username = "username-$id",
                    displayName = "display-name-test",
                    email = "email@test.com",
                    createdAt = LocalDateTime.MIN
            )

    fun createUserDatabaseModel(
            id: Int = 1,
            createdAt: LocalDateTime = LocalDateTime.MIN
    ): UserDatabaseModel =
            UserDatabaseModel(
                    id = id,
                    username = "username-$id",
                    displayName = "display-name-test",
                    email = "email@test.com",
                    createdAt = createdAt
            )

}
