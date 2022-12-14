package com.example.betadiccompose.ui.Foundation.ScreenNiveles

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betadiccompose.Domain.model.DataNiveles
import com.example.betadiccompose.data.network.model.DataVocabulary
import com.example.betadiccompose.ui.ViewModel.VocabularyViewModel

@Composable
fun ListNiveles(viewmodel: VocabularyViewModel, modifier: Modifier, onMediaClick: (DataNiveles) -> Unit)  {

    val lst = viewmodel.lstniveles.value

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp ),
        contentPadding = PaddingValues(6.dp),
        modifier = modifier

    ){

        lst.forEachIndexed { index, e ->
            val span = if(e.id % 3 == 0){
                GridItemSpan(2)
            }else{
                GridItemSpan(1)
            }

            item(span = {span}) {
                ItemNiveles(onClick =  {onMediaClick(e)},item = e,Modifier.padding(6.dp))
            }
        }


    }
}