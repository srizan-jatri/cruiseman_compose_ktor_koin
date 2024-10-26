package com.srizan.cruiseman.domain.entity


data class ProfileApiEntity(
    val `data`: UserInfoEntity,
    val message: String,
    val statusCode: Int
)


data class UserInfoEntity(
    val user: UserEntity
)


data class UserEntity(
    val address: String,
    val name: String,
    val phone: String,
)