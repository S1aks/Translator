package ru.s1aks.translator.model.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}
