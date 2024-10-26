package com.srizan.cruiseman.network.mapper

import com.srizan.cruiseman.domain.entity.ProfileApiEntity
import com.srizan.cruiseman.domain.entity.UserEntity
import com.srizan.cruiseman.domain.entity.UserInfoEntity
import com.srizan.cruiseman.network.model.ProfileApiResponse

class ProfileApiMapper : Mapper<ProfileApiResponse, ProfileApiEntity> {
    override fun mapFromApiResponse(type: ProfileApiResponse): ProfileApiEntity {
        return ProfileApiEntity(
            data = UserInfoEntity(
                UserEntity(
                    name = type.data?.user?.name ?: "",
                    phone = type.data?.user?.phone ?: "",
                    address = type.data?.user?.address ?: "",
                )
            ),
            message = type.message ?: "",
            statusCode = type.statusCode ?: 0,
        )
    }
}