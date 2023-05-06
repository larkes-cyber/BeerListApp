package com.example.beerlistapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.beerlistapp.domain.Beer

@Composable
fun BeerListScreen(
    beers: LazyPagingItems<Beer>
) {

    if(beers.loadState.refresh is LoadState.Loading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }else{
        LazyColumn(modifier = Modifier.fillMaxSize()){

            items(beers){beer ->
                if(beer != null){
                    BeerItem(beer)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                if(beers.loadState.append is LoadState.Loading){
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

        }
    }

}