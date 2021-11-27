package ru.s1aks.translator.view.base

import ru.s1aks.translator.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}