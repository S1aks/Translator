package ru.s1aks.translator.model.repository

import ru.s1aks.translator.model.data.AppState
import ru.s1aks.translator.model.data.DataModel
import ru.s1aks.translator.model.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}
