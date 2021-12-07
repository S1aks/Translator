package ru.s1aks.translator.application

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin
import ru.s1aks.translator.di.application
import ru.s1aks.translator.di.mainScreen

class TranslatorApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}