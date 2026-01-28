package com.example.pizza_mityushin_shift_2026.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger() // Логирование ошибок Koin
            androidContext(this@App)

            // ЗАГРУЖАЕМ ВСЕ МОДУЛИ ЗДЕСЬ
            modules(
//                networkModule,      // из :data:network
//                databaseModule,     // из :data:database
            )
        }
    }
}