package com.example.databaseapi

import android.app.Application
import com.example.databaseapi.repositori.AppContainer
import com.example.databaseapi.repositori.KontakContainer

class KontakApplication : Application(){
    lateinit var container: AppContainer

    override fun onCreate(){
        super.onCreate()
        container = KontakContainer()
    }
}