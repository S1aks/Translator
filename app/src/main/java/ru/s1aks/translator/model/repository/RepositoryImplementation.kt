package ru.s1aks.translator.model.repository

import io.reactivex.Observable
import ru.s1aks.translator.model.data.DataModel
import ru.s1aks.translator.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}