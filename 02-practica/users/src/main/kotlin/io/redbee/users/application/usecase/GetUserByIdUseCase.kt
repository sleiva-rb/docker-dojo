package io.redbee.users.application.usecase

import io.redbee.users.application.port.`in`.GetUserByIdQuery
import io.redbee.users.application.port.out.UserRepository
import io.redbee.users.domain.User
import org.springframework.stereotype.Component

@Component
class GetUserByIdUseCase(
        private val userRepository: UserRepository
): GetUserByIdQuery {

    override fun execute(id: Int): User = userRepository.getById(id)

}
