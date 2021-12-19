package ru.s1aks.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}
