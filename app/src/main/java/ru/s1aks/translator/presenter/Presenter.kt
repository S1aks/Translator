package ru.s1aks.translator.presenter

import ru.s1aks.translator.model.data.AppState
import ru.s1aks.translator.view.base.View

interface Presenter<V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
