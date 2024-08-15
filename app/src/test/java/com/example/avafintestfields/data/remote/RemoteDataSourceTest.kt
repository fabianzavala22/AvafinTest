package com.example.avafintestfields.data.remote

import com.example.avafintestfields.data.model.FieldsData
import com.example.avafintestfields.data.model.FieldsDataResponse
import com.example.avafintestfields.data.model.FieldsDataValues
import com.example.avafintestfields.data.source.RemoteDataSource
import com.example.avafintestfields.data.source.service.WebService
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RemoteDataSourceTest {

    @RelaxedMockK
    private lateinit var mockWebService: WebService

    private lateinit var remoteDataSource: RemoteDataSource

    private val mockFieldsDataValues = FieldsDataValues(
        name = "customer-lastname",
        visible = true,
        order = 1,
        maxlength = 10,
        type = "text",
        regex = "^[a-zA-Z]*$"
    )

    private val mockCustomerPhone = FieldsDataValues(
        name = "customer-phone",
        visible = true,
        order = 2,
        maxlength = 15,
        type = "text",
        regex = "^[0-9]*$"
    )

    private val mockFieldsData = FieldsData(
        customerLastname = mockFieldsDataValues,
        customerPhone = mockCustomerPhone
    )

    private val mockFieldsDataResponse = FieldsDataResponse(
        ok = 1,
        data = mockFieldsData
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { mockWebService.getFieldsData() } returns mockFieldsDataResponse
        remoteDataSource = RemoteDataSource()
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `getDataFields should return FieldsDataResponse`() = runTest {
        val result = remoteDataSource.getDataFields()
        assertEquals(mockFieldsDataResponse, result)
    }
}
