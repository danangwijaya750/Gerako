package com.dngwjy.gerakopenyedia.ui.main.fragment.home

import com.dngwjy.gerakopenyedia.data.model.AreaResponse

/**
 * Created by wijaya on 15/06/19
 */
interface HomeView {
    fun isLoading(state:Boolean)
    fun showResult(data:List<AreaResponse.Area>)
}