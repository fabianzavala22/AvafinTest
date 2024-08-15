package com.example.avafintestfields.di

import com.example.avafintestfields.presentation.viewmodel.FieldsViewModel
import com.example.avafintestfields.data.source.RemoteDataSource
import com.example.avafintestfields.data.repository.FieldsRepositoryImpl
import com.example.avafintestfields.domain.usecase.GetDataFieldsUseCase
import com.example.avafintestfields.domain.usecase.repository.FieldsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideFieldsRepository(remoteDataSource: RemoteDataSource): FieldsRepository {
        return FieldsRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun provideGetFieldsDataUseCase(fieldsRepository: FieldsRepository): GetDataFieldsUseCase {
        return GetDataFieldsUseCase(fieldsRepository)
    }

    @Provides
    fun provideFieldsViewModel(getDataFieldsUseCase: GetDataFieldsUseCase): FieldsViewModel {
        return FieldsViewModel(getDataFieldsUseCase)
    }
}
