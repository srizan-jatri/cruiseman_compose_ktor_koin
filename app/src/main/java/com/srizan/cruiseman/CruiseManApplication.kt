package com.srizan.cruiseman

import android.app.Application
import com.srizan.cruiseman.data.sharedpref.Prefs
import com.srizan.cruiseman.di.HttpModule
import com.srizan.cruiseman.di.KoinModule
import com.srizan.cruiseman.di.repoModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger
import org.koin.ksp.generated.module


class CruiseManApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Prefs.initializeHelper(applicationContext)
        startKoin {
            logger(PrintLogger(level = Level.INFO))
            modules(
                repoModule,
                HttpModule,
                KoinModule().module
            )
        }
    }
}