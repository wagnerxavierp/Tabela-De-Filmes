package com.xavier.wagner.tabeladefilmes.application

import android.app.Application
import com.xavier.wagner.tabeladefilmes.helpers.HelperDB

class FilmeApplication : Application() {

    var helperDB: HelperDB? = null
        private set

    companion object {
        lateinit var instance: FilmeApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        helperDB = HelperDB(this)
    }
}