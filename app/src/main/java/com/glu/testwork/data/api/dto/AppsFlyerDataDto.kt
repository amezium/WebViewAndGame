package com.glu.testwork.data.api.dto

import com.google.gson.annotations.SerializedName

data class AppsFlyerDataDto(
    @SerializedName("install_time") val installTime: String?,
    @SerializedName("af_status") val afStatus: String?,
    @SerializedName("af_message") val afMessage: String?,
    @SerializedName("is_firstLaunch") val isFirstLaunch: String?,
    @SerializedName("af_id_key") val afIdKey: String?
)
