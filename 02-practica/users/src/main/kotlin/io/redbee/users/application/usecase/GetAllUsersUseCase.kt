package io.redbee.users.application.usecase

import io.redbee.users.application.port.`in`.GetAllUsersQuery
import io.redbee.users.application.port.out.UserRepository
import io.redbee.users.domain.User
import org.springframework.stereotype.Component

@Component
class GetAllUsersUseCase(
        private val userRepository: UserRepository
): GetAllUsersQuery {

    override fun execute(): List<User> = userRepository.getAll()

}
