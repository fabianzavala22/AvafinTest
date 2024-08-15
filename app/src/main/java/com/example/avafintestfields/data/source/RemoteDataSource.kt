package com.example.avafintestfields.data.source

import com.example.avafintestfields.data.model.FieldsDataResponse
import com.example.avafintestfields.data.source.service.APIClient
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {

    suspend fun getDataFields(): FieldsDataResponse {
        return APIClient.webService.getFieldsData()
    }
}