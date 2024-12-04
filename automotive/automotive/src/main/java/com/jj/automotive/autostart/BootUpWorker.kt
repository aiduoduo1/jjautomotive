package com.jj.automotive.autostart

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import android.util.Log

class BootUpWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        // 在这里执行你需要在设备开机后执行的任务
        Log.d("BootUpWorker", "设备启动完成，执行任务")

        // 返回任务执行结果
        return Result.success()
    }
}
