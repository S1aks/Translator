package ru.s1aks.translator.di

import androidx.room.Room
import org.koin.dsl.module
import ru.s1aks.model.data.DataModel
import ru.s1aks.repository.*
import ru.s1aks.repository.room.HistoryDataBase
import ru.s1aks.translator.view.descriptionscreen.DescriptionInteractor
import ru.s1aks.translator.view.descriptionscreen.DescriptionViewModel
import ru.s1aks.translator.view.history.HistoryInteractor
import ru.s1aks.translator.view.history.HistoryViewModel
import ru.s1aks.translator.view.main.MainInteractor
import ru.s1aks.translator.view.main.MainViewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(
            get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}

val descriptionScreen = module {
    factory { DescriptionViewModel(get()) }
    factory { DescriptionInteractor(get(), get()) }
}