package com.dngwjy.grako.ui.detail

import com.dngwjy.grako.data.model.AreaResponse

/**
 * Created by wijaya on 15/06/19
 */
interface DetailView {
    fun isLoading(state:Boolean)
    fun showResult(data:List<AreaResponse.Area>)
}