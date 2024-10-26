package com.srizan.cruiseman.network.mapper

import com.srizan.cruiseman.domain.entity.LoginApiEntity
import com.srizan.cruiseman.domain.entity.TokenData
import com.srizan.cruiseman.network.model.LoginApiResponse

class LoginApiMapper : Mapper<LoginApiResponse, LoginApiEntity> {
    override fun mapFromApiResponse(type: LoginApiResponse): LoginApiEntity {
        return LoginApiEntity(
            token = TokenData(
                accessToken = type.data?.accessToken ?: "",
                refreshToken = type.data?.refreshToken ?: ""
            ),
            message = type.message ?: "",
            statusCode = type.statusCode ?: 0,
        )
    }
}