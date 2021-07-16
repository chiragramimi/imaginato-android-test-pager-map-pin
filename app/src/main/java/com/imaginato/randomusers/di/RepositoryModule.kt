package com.imaginato.randomusers.di

import com.imaginato.randomusers.data.randomuser.repository.RandomUserRepositoryImpl
import com.imaginato.randomusers.domain.randomuser.repository.RandomUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Repository module
 */
@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepo(userRepo: RandomUserRepositoryImpl): RandomUserRepository = userRepo
}