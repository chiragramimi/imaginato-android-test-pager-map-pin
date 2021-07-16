package com.imaginato.randomusers.domain.randomuser.repository

import com.imaginato.randomusers.data.randomuser.entity.RandomUserResponse

/**
 * Random user repository interface
 */
interface RandomUserRepository {
    suspend fun fetchRandomUsers(perPageCount: Int): RandomUserResponse
}
