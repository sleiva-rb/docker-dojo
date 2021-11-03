package io.redbee.users.application.port.`in`

import io.redbee.users.domain.User

interface GetUserByIdQuery {

    fun execute(id: Int): User

}
