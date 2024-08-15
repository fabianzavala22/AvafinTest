package com.example.avafintestfields.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.avafintestfields.data.model.FieldsData
import com.example.avafintestfields.data.model.FieldsDataResponse
import com.example.avafintestfields.data.model.FieldsDataValues
import com.example.avafintestfields.data.model.FormField
import com.example.avafintestfields.domain.usecase.GetDataFieldsUseCase
import com.example.avafintestfields.presentation.viewmodel.FieldsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FieldsViewModelValidationTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()
    private val testScope = TestScope(dispatcher)
    private val getDataFieldsUseCase: GetDataFieldsUseCase = mockk()
    private lateinit var viewModel: FieldsViewModel

    private val mockCustomerLastname = FieldsDataValues(
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
        customerLastname = mockCustomerLastname,
        customerPhone = mockCustomerPhone
    )

    private val mockFieldsDataResponse = FieldsDataResponse(
        ok = 1,
        data = mockFieldsData
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        coEvery { getDataFieldsUseCase() } returns mockFieldsDataResponse
        viewModel = FieldsViewModel(getDataFieldsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `validateForm should mark invalid field correctly`() = testScope.runTest {
        viewModel.formFields.value = mapOf(
            "Field1" to FormField(
                value = "TooLongValue",
                validationRule = FieldsDataValues(maxlength = 5)
            )
        )

        viewModel.validateForm()

        assertEquals(false, viewModel.formValid.value)
        assertEquals("Debe tener menos de 5 caracteres", viewModel.formFields.value["Field1"]?.errorMessage)
    }
}