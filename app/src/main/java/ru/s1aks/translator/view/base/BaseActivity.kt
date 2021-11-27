package ru.s1aks.translator.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.s1aks.translator.model.data.AppState
import ru.s1aks.translator.presenter.Presenter

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    protected lateinit var presenter: Presenter<View>

    protected abstract fun createPresenter(): Presenter<View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }
    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}