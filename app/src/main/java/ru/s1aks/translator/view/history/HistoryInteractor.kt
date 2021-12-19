package ru.s1aks.translator.view.history

import ru.s1aks.core.viewmodel.Interactor
import ru.s1aks.model.data.AppState
import ru.s1aks.model.data.DataModel
import ru.s1aks.repository.Repository
import ru.s1aks.repository.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>,
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
