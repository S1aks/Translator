package ru.s1aks.repository

interface DataSource<T> {

    suspend fun getData(word: String): T
}
