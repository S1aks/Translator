package ru.s1aks.translator.di

import dagger.Module
import dagger.Provides
import ru.s1aks.translator.model.data.DataModel
import ru.s1aks.translator.model.repository.Repository
import ru.s1aks.translator.view.main.MainInteractor
import javax.inject.Named
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}