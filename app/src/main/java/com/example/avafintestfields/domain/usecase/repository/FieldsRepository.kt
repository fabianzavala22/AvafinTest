package com.example.avafintestfields.domain.usecase.repository

import com.example.avafintestfields.data.model.FieldsDataResponse

interface FieldsRepository {

    suspend fun getDataFields(): FieldsDataResponse
}