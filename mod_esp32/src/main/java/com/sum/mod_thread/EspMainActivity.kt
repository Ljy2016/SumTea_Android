package com.sum.mod_thread

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.sum.common.constant.ESP_32_MAIN
import com.sum.framework.base.BaseDataBindActivity
import com.sum.framework.base.BaseMvvmActivity
import com.sum.framework.toast.TipsToast
import com.sum.mod_thread.databinding.ActivityEspMainBinding
import com.sum.mod_thread.viewmodel.EspViewModel

@Route(path = ESP_32_MAIN)
class EspMainActivity : BaseMvvmActivity<ActivityEspMainBinding, EspViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.btnSendMessage.setOnClickListener {
            mViewModel.sendMessage(mBinding.etMessage.text.toString())
        }
    }

    override fun initData() {
        super.initData()
        mViewModel.espLiveData.observe(this) {
            TipsToast.showTips(it)
            Log.e(TAG, "initData: $it")
        }
    }
}