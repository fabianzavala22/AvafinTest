package com.example.avafintestfields.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.avafintestfields.data.repository.FieldsRepositoryImpl
import com.example.avafintestfields.data.source.RemoteDataSource
import com.example.avafintestfields.domain.usecase.GetDataFieldsUseCase
import com.example.avafintestfields.presentation.viewmodel.FieldsViewModel
import com.example.avafintestfields.ui.theme.AvafinTestFieldsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: FieldsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvafinTestFieldsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: FieldsViewModel) {
    val formFields by viewModel.formFields
    val formValid by viewModel.formValid

    Column(modifier = Modifier.padding(16.dp)) {
        formFields.forEach { (fieldName, formField) ->
            OutlinedTextField(
                value = formField.value,
                onValueChange = { viewModel.onValueChange(fieldName, it) },
                label = { Text(text = fieldName) },
                modifier = Modifier.fillMaxWidth(),
                isError = formField.errorMessage != null
            )
            formField.errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

        }

        Button(
            onClick = { viewModel.validateForm() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar")
        }

        if (!formValid) {
            Text(
                text = "El formulario contiene errores.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AvafinTestPreview() {
    AvafinTestFieldsTheme {
        val viewModel = FieldsViewModel(GetDataFieldsUseCase(FieldsRepositoryImpl(RemoteDataSource())))
        MainScreen(viewModel = viewModel)
    }
}