package net.testportal.suitmedia

import androidx.multidex.MultiDexApplication
import net.testportal.suitmedia.data.remote.ReqresinApiClient
import net.testportal.suitmedia.data.remote.ReqresinApiService

class EsemApp : MultiDexApplication() {

    val service: ReqresinApiService by lazy {
        ReqresinApiClient.reqresinApiService
    }
}
