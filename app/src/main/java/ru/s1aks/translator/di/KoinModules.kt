package ru.s1aks.translator.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.s1aks.translator.model.data.DataModel
import ru.s1aks.translator.model.datasource.RetrofitImplementation
import ru.s1aks.translator.model.datasource.RoomDataBaseImplementation
import ru.s1aks.translator.model.repository.Repository
import ru.s1aks.translator.model.repository.RepositoryImplementation
import ru.s1aks.translator.model.repository.RepositoryImplementationLocal
import ru.s1aks.translator.model.repository.RepositoryLocal
import ru.s1aks.translator.room.HistoryDataBase
import ru.s1aks.translator.view.descriptionscreen.DescriptionActivity
import ru.s1aks.translator.view.descriptionscreen.DescriptionInteractor
import ru.s1aks.translator.view.descriptionscreen.DescriptionViewModel
import ru.s1aks.translator.view.history.HistoryActivity
import ru.s1aks.translator.view.history.HistoryInteractor
import ru.s1aks.translator.view.history.HistoryViewModel
import ru.s1aks.translator.view.main.MainActivity
import ru.s1aks.translator.view.main.MainInteractor
import ru.s1aks.translator.view.main.MainViewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    scope<MainActivity> {
        viewModel { MainViewModel(interactor = get()) }
        scoped { MainInteractor(repositoryRemote = get(), repositoryLocal = get()) }
    }
}

val historyScreen = module {
    scope<HistoryActivity> {
        viewModel { HistoryViewModel(interactor = get()) }
        scoped { HistoryInteractor(repositoryRemote = get(), repositoryLocal = get()) }
    }
}

val descriptionScreen = module {
    scope<DescriptionActivity> {
        viewModel { DescriptionViewModel(interactor = get()) }
        scoped { DescriptionInteractor(repositoryRemote = get(), repositoryLocal = get()) }
    }
}