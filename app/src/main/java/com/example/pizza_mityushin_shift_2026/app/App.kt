package com.example.pizza_mityushin_shift_2026.app

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.example.card.di.pizzaCardModule
import com.example.main.di.pizzaCatalogModule
import com.example.network.networkModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application(), ImageLoaderFactory {

    private val imageLoader: ImageLoader by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                networkModule,
                pizzaCatalogModule,
                pizzaCardModule
            )
        }
    }
    override fun newImageLoader(): ImageLoader {
        return imageLoader
    }
}