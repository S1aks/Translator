package ru.s1aks.translator.model.repository

import ru.s1aks.translator.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}