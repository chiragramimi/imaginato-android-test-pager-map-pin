package com.imaginato.randomusers.domain.randomuser.usecase

import com.imaginato.randomusers.data.randomuser.entity.RandomUserResponse
import com.imaginato.randomusers.domain.base.BaseUseCase
import com.imaginato.randomusers.domain.randomuser.repository.RandomUserRepository
import javax.inject.Inject

/**
 * Fetch random user user case
 * @param randomUserRepository random user repository
 */
class FetchRandomUserUseCase @Inject constructor(
    private val randomUserRepository: RandomUserRepository
) : BaseUseCase<RandomUserResponse, FetchRandomUserUseCase.Param>() {

    data class Param(val page: Int)

    /**
     * Call the fetchRandomUsers API
     */
    override suspend fun execute(params: Param): RandomUserResponse {
        return randomUserRepository.fetchRandomUsers(params.page)
    }
}
