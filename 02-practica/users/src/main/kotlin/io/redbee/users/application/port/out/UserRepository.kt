package io.redbee.users.application.port.out

import io.redbee.users.domain.User

interface UserRepository {

    fun getAll(): List<User>

    fun getById(id: Int): User

}
