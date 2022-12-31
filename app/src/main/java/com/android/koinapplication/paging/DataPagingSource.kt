package com.android.koinapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.koinapplication.model.Data
import com.android.koinapplication.network.ApiInterface

class DataPagingSource(val apiService: ApiInterface) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
        val response = apiService.getUserData(nextPage)

        var nextPageNumber: Int? = null
        if (response.page != null && response.page!! < response.totalPages!!) {
            nextPageNumber = response.page!! + 1
        }
        var prevPageNumber: Int? = null
        if (response.page != null && response.page!! > 0) {
            prevPageNumber = response.page!! - 1
        }
        return LoadResult.Page(response.data, null, nextPageNumber)

    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }


}