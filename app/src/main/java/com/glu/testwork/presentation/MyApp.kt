package com.glu.testwork.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.onesignal.OneSignal

class MyApp : Application() {
    private var getData = false

    companion object {
        var liveDataAppsFlyer = MutableLiveData<MutableMap<String, Any>>()
    }

    override fun onCreate() {
        super.onCreate()
        AppsFlyerLib.getInstance().init("K8y9Hfqssw5kTENaPDVzM4", appsFlyerConversion(), this)
        AppsFlyerLib.getInstance().start(this)
        OneSignal.initWithContext(this)
        OneSignal.setAppId("80c23fc6-ae26-4ab6-85ba-96e44bcc485e")
    }

    private fun appsFlyerConversion(): AppsFlyerConversionListener {

        return object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                if (!getData) {
                    data?.let {
                        liveDataAppsFlyer.postValue(it)
                    }
                    getData = true
                }
            }

            override fun onConversionDataFail(error: String?) {
                if (!getData) {
                    liveDataAppsFlyer.postValue(mutableMapOf())
                    getData = true
                }
            }

            override fun onAppOpenAttribution(data: MutableMap<String, String>?) {}

            override fun onAttributionFailure(error: String?) {}
        }
    }
}