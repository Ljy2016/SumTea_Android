package com.sum.mod_thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.sum.common.constant.ESP_32_MAIN
import com.sum.common.constant.VIDEO_ACTIVITY_PLAYER

@Route(path = ESP_32_MAIN)
class ThreadCpuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_cpu)
    }
}