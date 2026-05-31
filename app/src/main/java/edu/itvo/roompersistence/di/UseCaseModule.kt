package edu.itvo.roompersistence.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import edu.itvo.roompersistence.domain.repository.PlayerRepository
import edu.itvo.roompersistence.domain.repository.StadiumRepository

import edu.itvo.roompersistence.domain.usecase.*

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    // =========================
    // PLAYER USE CASES
    // =========================
    @Provides
    fun providePlayerUseCases(
        repository: PlayerRepository
    ): PlayerUseCases {
        return PlayerUseCases(
            addPlayer = AddPlayerUseCase(repository),
            getPlayers = GetPlayersUseCase(repository),
            deletePlayer = DeletePlayerUseCase(repository)
        )
    }

    // =========================
    // STADIUM USE CASES
    // =========================
    @Provides
    fun provideStadiumUseCases(
        repository: StadiumRepository
    ): StadiumUseCases {
        return StadiumUseCases(
            getStadiums = GetStadiumsUseCase(repository),
            addStadium = AddStadiumUseCase(repository),
            deleteStadium = DeleteStadiumUseCase(repository)
        )
    }
}