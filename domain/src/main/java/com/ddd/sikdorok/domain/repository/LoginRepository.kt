package com.ddd.sikdorok.domain.repository

import com.ddd.sikdorok.shared.base.SikdorokResponse
import com.ddd.sikdorok.shared.login.Response

interface LoginRepository {
    suspend fun onCheckSikdorokUser(code: String): SikdorokResponse<Response>

}