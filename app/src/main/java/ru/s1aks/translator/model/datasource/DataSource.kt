package ru.s1aks.translator.model.datasource

interface DataSource<T> {

    suspend fun getData(word: String): T
}
