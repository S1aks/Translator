package ru.s1aks.translator.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.s1aks.translator.model.data.DataModel
import ru.s1aks.translator.model.datasource.RetrofitImplementation
import ru.s1aks.translator.model.datasource.RoomDataBaseImplementation
import ru.s1aks.translator.model.repository.Repository
import ru.s1aks.translator.model.repository.RepositoryImplementation
import ru.s1aks.translator.view.main.MainInteractor
import ru.s1aks.translator.view.main.MainViewModel

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImplementation(
        RetrofitImplementation()) }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImplementation(
        RoomDataBaseImplementation()) }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
