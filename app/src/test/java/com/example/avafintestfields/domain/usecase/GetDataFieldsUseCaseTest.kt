package com.example.avafintestfields.domain.usecase

import com.example.avafintestfields.data.model.FieldsData
import com.example.avafintestfields.data.model.FieldsDataResponse
import com.example.avafintestfields.domain.usecase.repository.FieldsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetDataFieldsUseCaseTest {

    private lateinit var getDataFieldsUseCase: GetDataFieldsUseCase
    private val repository: FieldsRepository = mock()

    @Before
    fun setUp() {
        getDataFieldsUseCase = GetDataFieldsUseCase(repository)
    }

    @Test
    fun `invoke should return correct FieldsDataResponse`() = runBlocking {
        val expectedResponse = FieldsDataResponse(1, FieldsData())
        Mockito.`when`(repository.getDataFields()).thenReturn(expectedResponse)

        val result = getDataFieldsUseCase()

        assertEquals(expectedResponse, result)
    }
}