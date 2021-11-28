package ru.s1aks.translator.application

import android.app.Application
import ru.s1aks.translator.di.AppComponent
import ru.s1aks.translator.di.DaggerAppComponent

class TranslatorApp: Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .build()
    }

    companion object{
        lateinit var component: AppComponent
    }
}