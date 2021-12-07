package ru.s1aks.translator.model.datasource

import ru.s1aks.translator.model.data.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return listOf()
    }
}