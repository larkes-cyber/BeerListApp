package com.example.beerlistapp.data.mappers

import com.example.beerlistapp.data.local.BeerEntity
import com.example.beerlistapp.domain.Beer

fun BeerEntity.toBeer():Beer{
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        first_brewed = first_brewed,
        image_url = image_url
    )
}