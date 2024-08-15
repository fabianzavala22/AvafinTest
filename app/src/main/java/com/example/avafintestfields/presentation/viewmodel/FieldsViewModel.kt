package com.example.avafintestfields.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avafintestfields.data.model.FieldsData
import com.example.avafintestfields.data.model.FieldsDataValues
import com.example.avafintestfields.data.model.FormField
import com.example.avafintestfields.domain.usecase.GetDataFieldsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FieldsViewModel @Inject constructor(
    private val getDataFieldsUseCase: GetDataFieldsUseCase
): ViewModel() {

    var formFields = mutableStateOf<Map<String, FormField>>(emptyMap())
        private set

    var formValid = mutableStateOf(true)
        private set

    init {
        getDataFields()
    }

    fun getDataFields() = viewModelScope.launch {
        try {
            val fieldsList = getDataFieldsUseCase()
            val fieldsListValues = getListValues(fieldsList.data)
            formFields.value = fieldsListValues.associate { field ->
                field.name!! to FormField(validationRule = field)
            }
        } catch (e: Exception){
            e.printStackTrace()
            e.message?.let { Log.e("fail: ", it) }
        }
    }

    private fun getListValues(fields: FieldsData): List<FieldsDataValues> {
        val fieldList = listOf(
            fields.customerLastname to "Last name",
            fields.customerPhone to "Phone",
            fields.customerMonthlyIncome to "Monthly Income",
            fields.bankIbanta to "Bank Ibanta",
            fields.language to "Language",
            fields.customerPersoncode to "Person Code",
            fields.customerEmail to "Email",
            fields.customerFirstname to "First Name",
            fields.customerGender to "Gender",
            fields.customerBirthday to "Birthday",
            fields.pepStatus to "Status",
            fields.amlCheck to "Check"
        )

        return fieldList.mapNotNull { (field, name) ->
            field?.apply { this.name = name }
        }.filter { it.visible == true }
            .sortedBy { it.order }
    }

    fun onValueChange(fieldName: String, value: String) {
        formFields.value = formFields.value.toMutableMap().apply {
            this[fieldName] = this[fieldName]?.copy(value = value) ?: return@apply
        }
    }

    fun validateForm() {
        formValid.value = true
        formFields.value = formFields.value.mapValues { (_, formField) ->
            val error = validateField(formField)
            if (error != null) formValid.value = false
            formField.copy(errorMessage = error)
        }
    }

    private fun validateField(formField: FormField): String? {
        formField.validationRule.maxlength?.let { maxLength ->
            if (formField.value.length > maxLength) {
                return "Debe tener menos de $maxLength caracteres"
            }
        }
        formField.validationRule.regex?.let { regex ->
            if (!formField.value.matches(regex.toRegex())) {
                return "Formato inválido"
            }
        }
        return null
    }


}