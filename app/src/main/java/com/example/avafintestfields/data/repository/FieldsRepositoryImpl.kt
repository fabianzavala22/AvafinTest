package com.example.avafintestfields.data.repository

import com.example.avafintestfields.data.source.RemoteDataSource
import com.example.avafintestfields.data.model.FieldsDataResponse
import com.example.avafintestfields.domain.usecase.repository.FieldsRepository
import javax.inject.Inject

class FieldsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : FieldsRepository {

    override suspend fun getDataFields(): FieldsDataResponse {
        return remoteDataSource.getDataFields()
    }
}