package com.example.beerlistapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.beerlistapp.data.local.BeerEntity
import com.example.beerlistapp.data.mappers.toBeer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    pager: Pager<Int, BeerEntity>
): ViewModel() {

    val beersFlow = pager
        .flow
        .map {pagingData ->
            pagingData.map {
                it.toBeer()
            }
        }.cachedIn(viewModelScope)

}