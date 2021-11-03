package io.redbee.users.adapter.jdbc.model

import io.redbee.users.domain.User
import java.time.LocalDateTime

data class UserDatabaseModel(
        val id: Int?,
        val username: String?,
        val displayName: String?,
        val email: String?,
        val createdAt: LocalDateTime?
) {

    fun toDomain(): User = User(
            id = this.id!!,
            username = this.username!!,
            displayName = this.displayName,
            email = this.email!!,
            createdAt = this.createdAt!!
    )

}
