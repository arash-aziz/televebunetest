package com.example.githubtest.di


import android.content.Context
import android.net.ConnectivityManager
import com.example.githubtest.data.network.repo.user.UserRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.example.githubtest.data.network.BaseInterceptor
import com.example.githubtest.data.network.ConnectivityInterceptor
import com.example.githubtest.data.network.service.UserService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
val networkModule = module {
    single { provideConnectivityManager(androidContext()) }
    factory<BaseInterceptor> { provideConnectivityInterceptor(get() , androidContext()) }
    single { provideOkHttpClient(get() , get()) }

    single { provideApi(get() , UserService::class.java) }

    single { provideLoggingInterceptor() }
    scope(named("baseURL")) { scoped { provideBaseUrlHolder() } }
    single { provideRetrofit(get()) }
    single { provideUuidUtil(androidContext()) }
    single { provideDeviceCapacity() }
}

private fun provideUuidUtil(context : Context) =""


private fun provideDeviceCapacity() =""

private fun provideConnectivityManager(context : Context) =
    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

private fun provideConnectivityInterceptor(
    connectivityManager : ConnectivityManager , context : Context ,
) = ConnectivityInterceptor(connectivityManager)

private fun provideOkHttpClient(
    connectivityInterceptor : BaseInterceptor ,
    loggingInterceptor : HttpLoggingInterceptor ,
) : OkHttpClient {
    val httpClient = OkHttpClient.Builder()
        .connectTimeout(3 , TimeUnit.MINUTES)
        .readTimeout(3 , TimeUnit.MINUTES)
        .writeTimeout(3 , TimeUnit.MINUTES)
    httpClient.addInterceptor(connectivityInterceptor).addInterceptor(loggingInterceptor)
    return httpClient.build()
}

fun provideBaseUrlHolder() = "https://api.github.com/"
private fun provideRetrofit(
    okHttpClient : OkHttpClient ,
) : Retrofit {
    val scope = getKoin().getOrCreateScope(
        "baseUrlId" , named("baseURL"))
    val baseUrl = scope.getScope("baseUrlId").get<String>()
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
}

fun provideLoggingInterceptor() : HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BODY
    return logger
}

fun <T> provideApi(retrofit : Retrofit , service : Class<T>) : T = retrofit.create(service)


//"https://api.github.com/"

