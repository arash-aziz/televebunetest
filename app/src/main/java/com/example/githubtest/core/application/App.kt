package com.example.githubtest.core.application

import android.app.Application
import com.example.githubtest.di.databaseModule
import com.example.githubtest.di.networkModule
import com.example.githubtest.di.repositoryModule
import com.example.githubtest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent.getKoin

class App : Application() {


//        if (!BuildConfig.DEBUG) "70820b27-7781-44a8-bd1e-9a4237f584fa" else "66864604-34b0-4fe8-a495-fc3a745fd984"
    private lateinit var scope: Scope
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules( viewModelModule, networkModule, repositoryModule,databaseModule)
        }
        scope = getKoin().getOrCreateScope("baseUrlId", named("baseURL"))






    }


}