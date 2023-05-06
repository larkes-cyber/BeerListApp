package com.example.beerlistapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.beerlistapp.domain.Beer

@Composable
fun BeerItem(beer:Beer) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(6.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .padding(start = 8.dp)
                .padding(end = 11.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = beer.image_url,
                contentDescription = "",
                modifier = Modifier.weight(1f).height(150.dp)
            )
            Column(
                modifier = Modifier.weight(3f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = beer.name,
                    style = MaterialTheme.typography.h6,
                    color = Color.Black
                )
                Text(
                    text = beer.first_brewed,
                    color = Color.LightGray
                )
                Text(
                    text = beer.description,
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
    }


}