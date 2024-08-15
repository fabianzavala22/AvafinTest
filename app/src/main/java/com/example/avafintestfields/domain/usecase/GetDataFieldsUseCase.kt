package com.example.avafintestfields.domain.usecase

import com.example.avafintestfields.data.model.FieldsDataResponse
import com.example.avafintestfields.domain.usecase.repository.FieldsRepository
import javax.inject.Inject

class GetDataFieldsUseCase @Inject constructor(private val repository: FieldsRepository) {

    suspend operator fun invoke(): FieldsDataResponse {
        return repository.getDataFields()
    }
}