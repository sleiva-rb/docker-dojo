package io.redbee.users.adapter.controller

import io.redbee.users.adapter.controller.model.UserResponse
import io.redbee.users.application.port.`in`.GetAllUsersQuery
import io.redbee.users.application.port.`in`.GetUserByIdQuery
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserControllerRestAdapter(
        private val getAllUsersQuery: GetAllUsersQuery,
        private val getUserByIdQuery: GetUserByIdQuery
) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponse>> = getAllUsersQuery
            .execute()
            .map { domain -> UserResponse.from(domain) }
            .let { userResponseList -> ResponseEntity.ok(userResponseList) }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Int): ResponseEntity<UserResponse> =
            getUserByIdQuery
                    .execute(id)
                    .let { domain -> UserResponse.from(domain) }
                    .let { userResponse -> ResponseEntity.ok(userResponse) }

}
