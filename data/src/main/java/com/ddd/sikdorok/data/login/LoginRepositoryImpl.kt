package com.ddd.sikdorok.data.login

import com.ddd.sikdorok.data.login.data.LoginRemoteDataSource
import com.ddd.sikdorok.domain.repository.LoginRepository
import com.ddd.sikdorok.shared.base.SikdorokResponse
import com.ddd.sikdorok.shared.login.Response
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource
): LoginRepository {
    override suspend fun onCheckSikdorokUser(code: String): SikdorokResponse<Response> {
        return loginRemoteDataSource.onCheckSikdorokUser(code)
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LoginRepositoryModule {
    @Binds
    abstract fun bindsLoginRepository(repository: LoginRepositoryImpl): LoginRepository
}