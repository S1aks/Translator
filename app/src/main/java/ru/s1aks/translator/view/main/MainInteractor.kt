package ru.s1aks.translator.view.main

import ru.s1aks.core.viewmodel.Interactor
import ru.s1aks.model.data.AppState
import ru.s1aks.model.data.DataModel
import ru.s1aks.repository.Repository
import ru.s1aks.repository.RepositoryLocal

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
