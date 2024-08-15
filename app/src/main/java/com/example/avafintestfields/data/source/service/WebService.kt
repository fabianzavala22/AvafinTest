package com.example.avafintestfields.data.source.service

import com.example.avafintestfields.data.model.FieldsDataResponse
import retrofit2.http.GET

interface WebService {

    @GET("getRegistrationfieldsResponse.json")
    suspend fun getFieldsData(): FieldsDataResponse
}