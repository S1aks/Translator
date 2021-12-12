package ru.s1aks.translator.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import ru.s1aks.translator.di.application
import ru.s1aks.translator.di.descriptionScreen
import ru.s1aks.translator.di.historyScreen
import ru.s1aks.translator.di.mainScreen

class TranslatorApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen, descriptionScreen))
        }
    }
}