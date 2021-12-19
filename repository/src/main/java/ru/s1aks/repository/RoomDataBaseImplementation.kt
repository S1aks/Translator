package ru.s1aks.repository

import ru.s1aks.model.data.AppState
import ru.s1aks.model.data.DataModel
import ru.s1aks.repository.room.HistoryDao

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return when (word) {
            "" -> mapHistoryEntityToSearchResult(historyDao.all())
            else -> mapHistoryEntityToSearchResult(listOf(historyDao.getDataByWord(word)))
        }

    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
