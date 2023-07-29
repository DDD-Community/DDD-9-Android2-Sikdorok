package com.ddd.sikdorok.core_api.login

import com.ddd.sikdorok.core_api.service.UserService
import com.ddd.sikdorok.data.login.data.LoginRemoteDataSource
import com.ddd.sikdorok.shared.base.SikdorokResponse
import com.ddd.sikdorok.shared.login.Request
import com.ddd.sikdorok.shared.login.Response
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

internal class LoginRemoteDataSourceImpl @Inject constructor(
    private val sikdorokLoginService: UserService.SikdorokLogin
): LoginRemoteDataSource {
    override suspend fun onCheckSikdorokUser(code: String): SikdorokResponse<Response> {
        return sikdorokLoginService.requestkakaoLogin(Request.Kakao(code))
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LoginModule {
    @Binds
    abstract fun providesLoginRemoteData(
        dataSource: LoginRemoteDataSourceImpl
    ): LoginRemoteDataSource
}
