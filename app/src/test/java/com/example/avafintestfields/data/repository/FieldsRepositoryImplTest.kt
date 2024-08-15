package com.example.avafintestfields.data.repository

import com.example.avafintestfields.data.model.FieldsData
import com.example.avafintestfields.data.model.FieldsDataResponse
import com.example.avafintestfields.data.source.RemoteDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Before

class FieldsRepositoryImplTest {

    private lateinit var repository: FieldsRepositoryImpl
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        remoteDataSource = mockk()
        repository = FieldsRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `getDataFields should return correct data`() = runBlocking {
        val expectedResponse = FieldsDataResponse(1, FieldsData())

        coEvery { remoteDataSource.getDataFields() } returns expectedResponse

        val result = repository.getDataFields()
        assertEquals(expectedResponse, result)
    }
}