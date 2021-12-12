package ru.s1aks.translator.model.datasource

import ru.s1aks.translator.model.data.AppState
import ru.s1aks.translator.model.data.DataModel
import ru.s1aks.translator.room.HistoryDao
import ru.s1aks.translator.utils.convertDataModelSuccessToEntity
import ru.s1aks.translator.utils.mapHistoryEntityToSearchResult

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
