package com.julioolivares90.zeromangaview.ui.theme.pages.detailPage

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(navHostController: NavHostController){
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        LargeTopAppBar(title = { Text(text = "Large Top AppBarExample") }, scrollBehavior = scrollBehavior)
    }, content = {
        Column {
            Box(modifier = Modifier.fillMaxSize()){
                Column {
                    Text(text = "Detail Page!!")
                    Button(onClick = { navHostController.popBackStack() }) {
                        Text(text = "Click to back")
                    }
                }

            }
        }
    })

}