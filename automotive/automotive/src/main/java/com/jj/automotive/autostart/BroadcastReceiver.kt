package com.jj.automotive.autostart

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class BootBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // 设备启动完成后，调度任务
            val bootUpRequest = OneTimeWorkRequest.Builder(BootUpWorker::class.java)
                .build()

            // 使用 WorkManager 调度 Worker
            WorkManager.getInstance(context).enqueue(bootUpRequest)
        }
    }
}
