package io.redbee.users.domain

import java.time.LocalDateTime

data class User(
        val id: Int,
        val username: String,
        val displayName: String? = null,
        val email: String,
        val createdAt: LocalDateTime
)
