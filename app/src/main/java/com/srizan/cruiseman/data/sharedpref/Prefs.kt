package com.srizan.cruiseman.data.sharedpref

import android.content.Context

object Prefs {
    fun initializeHelper(
        applicationContext: Context,
        fileName: String = applicationContext.packageName
    ) =
        SharedPrefHelper.initialize(applicationContext, fileName)

    fun clearAll() = SharedPrefHelper.clearAllCache()

    var isUserLoggedIn
        get() = SharedPrefHelper.getBoolean(SpKey.LOGIN_STATUS)
        set(value) = SharedPrefHelper.putBool(SpKey.LOGIN_STATUS, value)

    var accessToken
        get() = SharedPrefHelper.getString(SpKey.ACCESS_TOKEN)
        set(value) = SharedPrefHelper.putString(SpKey.ACCESS_TOKEN, value)

    var refreshToken
        get() = SharedPrefHelper.getString(SpKey.REFRESH_TOKEN)
        set(value) = SharedPrefHelper.putString(SpKey.REFRESH_TOKEN, value)

    var name
        get() = SharedPrefHelper.getString(SpKey.NAME)
        set(value) = SharedPrefHelper.putString(SpKey.NAME, value)

    var phone
        get() = SharedPrefHelper.getString(SpKey.PHONE)
        set(value) = SharedPrefHelper.putString(SpKey.PHONE, value)

    var address
        get() = SharedPrefHelper.getString(SpKey.ADDRESS)
        set(value) = SharedPrefHelper.putString(SpKey.ADDRESS, value)

}

internal object SpKey {

    //Auth
    const val LOGIN_STATUS = "loginStatus"
    const val ACCESS_TOKEN = "accessToken"
    const val REFRESH_TOKEN = "refreshToken"

    const val NAME = "name"
    const val PHONE = "phone"
    const val ADDRESS = "address"
}