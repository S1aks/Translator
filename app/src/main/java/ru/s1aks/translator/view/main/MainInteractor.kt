package ru.s1aks.translator.view.main

import io.reactivex.Observable
import ru.s1aks.translator.di.NAME_LOCAL
import ru.s1aks.translator.di.NAME_REMOTE
import ru.s1aks.translator.model.data.AppState
import ru.s1aks.translator.model.data.DataModel
import ru.s1aks.translator.model.repository.Repository
import ru.s1aks.translator.viewmodel.Interactor
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository
        } else {
            localRepository
        }.getData(word).map { AppState.Success(it) }
    }
}
