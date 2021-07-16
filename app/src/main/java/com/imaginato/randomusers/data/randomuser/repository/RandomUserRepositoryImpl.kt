package com.imaginato.randomusers.data.randomuser.repository

import com.imaginato.randomusers.data.randomuser.entity.RandomUserResponse
import com.imaginato.randomusers.domain.randomuser.repository.RandomUserRepository
import javax.inject.Inject

/**
 * Random User Repository implementation
 * @param randomUserApi object of RandomUserAPI endpoint
 */
class RandomUserRepositoryImpl @Inject constructor(
    private val randomUserApi: RandomUserApi
) : RandomUserRepository {

    /**
     * to fetch the random users list
     * @param perPageCount number of users will get on API call
     */
    override suspend fun fetchRandomUsers(perPageCount: Int): RandomUserResponse {
        return randomUserApi.fetchRandomUsers(perPageCount)
    }
}
