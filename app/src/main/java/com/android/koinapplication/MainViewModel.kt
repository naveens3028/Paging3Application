package com.android.koinapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.koinapplication.model.Data
import com.android.koinapplication.network.ApiInterface
import com.android.koinapplication.network.RetrofitModule
import com.android.koinapplication.paging.DataPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainViewModel : ViewModel() {

    lateinit var dataFlow : Flow<Int>

    fun getListData(): Flow<PagingData<Data>> {
        return Pager(config = PagingConfig(pageSize = 6),
            pagingSourceFactory = {
                DataPagingSource(
                    RetrofitModule.getRetroInstance().create(ApiInterface::class.java)
                )
            }).flow.cachedIn(viewModelScope)
    }

    fun getDataWithFlow(){
        dataFlow = flow {
            (0..10).forEach {
                this.emit(it)
                kotlinx.coroutines.delay(1000)
            }
        }.flowOn(Dispatchers.Default)
    }
}