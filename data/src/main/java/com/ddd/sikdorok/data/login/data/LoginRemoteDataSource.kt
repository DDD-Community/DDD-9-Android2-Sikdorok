package com.ddd.sikdorok.data.login.data

import com.ddd.sikdorok.shared.base.SikdorokResponse
import com.ddd.sikdorok.shared.login.Response

interface LoginRemoteDataSource {
    suspend fun onCheckSikdorokUser(code: String): SikdorokResponse<Response>

}