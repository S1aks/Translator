package ru.s1aks.translator.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.s1aks.translator.view.main.MainActivity
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}