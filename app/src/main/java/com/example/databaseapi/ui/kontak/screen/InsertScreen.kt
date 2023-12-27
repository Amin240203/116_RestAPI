package com.example.databaseapi.ui.kontak.screen

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.databaseapi.navigation.DestinasiNavigasi
import com.example.databaseapi.ui.PenyediaViewModel
import com.example.databaseapi.ui.TopAppBarKontak
import com.example.databaseapi.ui.kontak.viewmodel.InsertUIState
import com.example.databaseapi.ui.kontak.viewmodel.InsertUiEvent
import com.example.databaseapi.ui.kontak.viewmodel.InsertViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputSiswa(
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    onValueChnge: (InsertUiEvent) -> Unit = {},
    enabled : Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = insertUiEvent.nama,
            onValueChange ={ onValueChnge(insertUiEvent.copy(nama = it))},
            label = { Text("Nama")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.email,
            onValueChange ={ onValueChnge(insertUiEvent.copy(nama = it))},
            label = { Text("Email")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.nohp,
            onValueChange ={ onValueChnge(insertUiEvent.copy(nama = it))},
            label = { Text("no HP")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled){
            Text(
                text = "Isi semua data",
                modifier = Modifier.padding(start = 12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
fun EntryKontakBody(
    insertUIState: InsertUIState,
    onSiswaValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputSiswa(
            insertUiEvent = insertUIState.insertUiEvent,
            onValueChnge = onSiswaValueChange,
            modifier = Modifier.fillMaxWidth()
            )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
            ) {
            Text(text = "Simpan")
        }
    }
}

object DestinasiEntry : DestinasiNavigasi{
    override val route = "item_entry"
    override val titleRes = "Entry Siswa"
}
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryKontakScreen(
    navigasiBack: ()-> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory),
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarKontak(
                title = DestinasiEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigasiBack
            )
        }) { innerPadding ->
        EntryKontakBody(
            insertUIState = viewModel.insertKontakState,
            onSiswaValueChange = viewModel::updateInsertKontakState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertKontak()
                    navigasiBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()).fillMaxWidth()
            )

    }
}