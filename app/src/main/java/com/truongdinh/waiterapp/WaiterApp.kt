package com.truongdinh.waiterapp

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.truongdinh.waiterapp.ui.features.order.FakeBarProcessor
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WaiterApp : Application() {
    @Inject
    lateinit var fakeBarProcessor: FakeBarProcessor

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        fakeBarProcessor.start()
    }
}