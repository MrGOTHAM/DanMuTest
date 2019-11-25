package com.example.danmu.launcherKotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by anchaoguang on 2019-11-19.
 *
 */
class LauncherKotlin {
    private fun doLauncher(){
        println("start")
        GlobalScope.launch {
            delay(1000)
            println("middle")
        }
        Thread.sleep(2000)
        println("end")
    }

    fun main() {
        doLauncher()
    }
}