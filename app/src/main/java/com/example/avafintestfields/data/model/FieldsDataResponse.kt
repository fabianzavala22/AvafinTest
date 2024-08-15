package com.example.avafintestfields.data.model


import com.google.gson.annotations.SerializedName

data class FormField(
    var value: String = "",
    val validationRule: FieldsDataValues,
    var errorMessage: String? = null
)

data class FieldsDataResponse(
    @SerializedName("ok")
    val ok: Int,
    @SerializedName("data")
    val data: FieldsData
)

data class FieldsData (
    @SerializedName("customer-lastname")
    val customerLastname: FieldsDataValues? = null,
    @SerializedName("customer-phone")
    val customerPhone: FieldsDataValues? = null,
    @SerializedName("customer-monthly-income")
    val customerMonthlyIncome: FieldsDataValues? = null,
    @SerializedName("bank-iban")
    val bankIbanta: FieldsDataValues? = null,
    @SerializedName("language")
    val language: FieldsDataValues? = null,
    @SerializedName("customer-personcode")
    val customerPersoncode: FieldsDataValues? = null,
    @SerializedName("customer-email")
    val customerEmail: FieldsDataValues? = null,
    @SerializedName("customer-firstname")
    val customerFirstname: FieldsDataValues? = null,
    @SerializedName("customer-gender")
    val customerGender: FieldsDataValues? = null,
    @SerializedName("customer-birthday")
    val customerBirthday: FieldsDataValues? = null,
    @SerializedName("pep-status")
    val pepStatus: FieldsDataValues? = null,
    @SerializedName("aml-check")
    val amlCheck: FieldsDataValues? = null
)

data class FieldsDataValues (
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("visible")
    val visible: Boolean? = null,
    @SerializedName("order")
    val order: Int? = null,
    @SerializedName("maxlength")
    val maxlength: Int? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("regex")
    val regex: String? = null
)