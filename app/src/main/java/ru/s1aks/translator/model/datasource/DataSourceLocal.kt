package ru.s1aks.translator.model.datasource

import ru.s1aks.translator.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}
