package io.redbee.users.adapter.controller.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.redbee.users.domain.User
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserResponse(
        val id: Int,
        val username: String,
        val displayName: String,
        val email: String,
        val createdAt: LocalDateTime
) {

    companion object {

        fun from(domain: User): UserResponse =
                UserResponse(
                        id = domain.id,
                        username = domain.username,
                        displayName = domain.displayName ?: domain.username,
                        email = domain.email,
                        createdAt = domain.createdAt
                )

    }

}
