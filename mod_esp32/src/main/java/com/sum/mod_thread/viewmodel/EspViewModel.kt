package com.sum.mod_thread.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sum.common.model.User
import com.sum.framework.toast.TipsToast
import com.sum.main.repository.EspRepository
import com.sum.network.viewmodel.BaseViewModel

/**
 * @author mingyan.su
 * @date   2023/3/25 16:38
 * @desc   登录viewModel
 */
class EspViewModel : BaseViewModel() {

    private val espRepository by lazy { EspRepository() }
    val espLiveData = MutableLiveData<String>()


    /**
     * 发送消息
     */
    fun sendMessage(message: String) {
        launchUI(errorBlock = { _, error ->
            TipsToast.showTips(error)
            espLiveData.value = ""
        }) {
            val data = espRepository.sendMessage(message)
            espLiveData.value = data
        }
    }

}