package com.dngwjy.grako.ui.search

import com.dngwjy.grako.data.model.PlaceResponse

/**
 * Created by wijaya on 16/06/19
 */
interface SearchView {
    fun isLoading(state:Boolean)
    fun showData(data:List<PlaceResponse.Place>)
}