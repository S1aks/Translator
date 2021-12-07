package ru.s1aks.translator.model.repository

import ru.s1aks.translator.model.data.DataModel
import ru.s1aks.translator.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}