package io.redbee.users.application.port.`in`

import io.redbee.users.domain.User

interface GetAllUsersQuery {

    fun execute(): List<User>

}
