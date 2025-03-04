package com.sum.main.ui.category.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sum.common.constant.ESP_32_MAIN
import com.sum.common.constant.LOGIN_ACTIVITY_LOGIN
import com.sum.common.model.CategoryItem
import com.sum.common.model.CategorySecondItem
import com.sum.framework.toast.TipsToast
import com.sum.network.callback.IApiErrorCallback
import com.sum.network.manager.ApiManager
import com.sum.network.viewmodel.BaseViewModel

/**
 * @author mingyan.su
 * @date   2023/3/3 8:12
 * @desc   分类ViewModel
 */
class CategoryViewModel : BaseViewModel() {
    val categoryItemLiveData = MutableLiveData<MutableList<CategoryItem>?>()

    /**
     * 获取分类信息
     * 不依赖repository,错误回调实现IApiErrorCallback
     */
    fun getCategoryData() {
        launchUIWithResult(responseBlock = {
            ApiManager.api.getCategoryData()
        }, errorCall = object : IApiErrorCallback {
            override fun onError(code: Int?, error: String?) {
                super.onError(code, error)
                TipsToast.showTips(error)
                categoryItemLiveData.value = null
            }
        }) {
            categoryItemLiveData.value = addMyCategory(it)
        }
    }

    private fun addMyCategory(source: MutableList<CategoryItem>?): MutableList<CategoryItem> {
        val myCategories = mutableListOf(CategoryItem(66661, "线程").apply {
            articles?.addAll(
                mutableListOf(
                    CategorySecondItem(666611, LOGIN_ACTIVITY_LOGIN, "线程与性能"),
                    CategorySecondItem(666611, ESP_32_MAIN, "ESP_32").apply {
                        type="internal"
                    }
                )
            )
        })
        source?.addAll(0, myCategories)
        return source ?: myCategories
    }

}