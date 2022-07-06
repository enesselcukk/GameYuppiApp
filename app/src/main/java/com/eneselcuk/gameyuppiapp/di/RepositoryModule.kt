package com.eneselcuk.gameyuppiapp.di


import com.eneselcuk.gameyuppiapp.data.remote.service.GameService
import com.eneselcuk.gameyuppiapp.data.remote.repos.GameRepositoryImpl
import com.eneselcuk.gameyuppiapp.domain.repository.GameRepos
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesRepository(
        api: GameService,
    ) = GameRepositoryImpl(api)

}

@Module
@InstallIn(ViewModelComponent::class)
interface Repos {
    @Binds
    fun games(gameRepositoryImpl: GameRepositoryImpl): GameRepos

}

