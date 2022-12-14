package com.example.betadiccompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.betadiccompose.Foundation.ScreenMenu.GetLogo

import com.example.betadiccompose.Runtime.MyApp
import com.example.betadiccompose.data.network.model.DataWorld
import com.example.betadiccompose.ui.Foundation.ScreenSentes.ListSentes
import com.example.betadiccompose.ui.Foundation.Shared.Floating
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun SentesScreen(onMediaClick: (DataWorld) -> Unit, viewmodel: VocabularyViewModel) {

    MyApp {
        Scaffold {
            Column {
                GetLogo(true,"Sentes")
                ListSentes(viewmodel = viewmodel)
            }

            Floating()

            //
            //GetListMenu(onMediaClick)
            //MediaList(onMediaClick = onMediaClick,modifier = Modifier.padding(it))
        }
    }

}