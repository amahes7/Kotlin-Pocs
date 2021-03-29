package com.example.userregistrationapplication.utils

import android.os.Bundle
import android.util.Log
import androidx.browser.customtabs.CustomTabsCallback

class RabbitCallback : CustomTabsCallback() {
    override fun onNavigationEvent(navigationEvent: Int, extras: Bundle?) {
        super.onNavigationEvent(navigationEvent, extras)
        Log.d("Nav", navigationEvent.toString())
        when (navigationEvent) {
            1 -> Log.d("Navigation", "Start") // NAVIGATION_STARTED
            2 -> Log.d("Navigation", "Finished") // NAVIGATION_FINISHED
            3 -> Log.d("Navigation", "Failed") // NAVIGATION_FAILED
            4 -> Log.d("Navigation", "Aborted") // NAVIGATION_ABORTED
            5 -> Log.d("Navigation", "Tab Shown") // TAB_SHOWN
            6 -> Log.d("Navigation", "Tab Hidden") // TAB_HIDDEN
            else -> Log.d("Navigation", "Else")
        }
    }
}