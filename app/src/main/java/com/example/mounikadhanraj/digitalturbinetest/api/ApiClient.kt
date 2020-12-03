package com.example.mounikadhanraj.digitalturbinetest.api



import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

object APIClient {

    val WRITE_TIMEOUT = 60
    val READ_TIMEOUT = 30
    val CONNECT_TIMEOUT = 30

    private const val BASE_URL = "http://ads.appia.com/"


    private fun buildRetrofitWithInterceptors(): Retrofit {


         val queryInterceptor = Interceptor { chain ->
            val newUrl = chain.request().url
                    .newBuilder()
                    .addQueryParameter("lname", "dhanraj")
                    .build()

             val newRequest = chain.request()
                     .newBuilder()
                     .url(newUrl)
                     .build()

            chain.proceed(newRequest)
        }

        val okhttpBuilder = OkHttpClient.Builder()
                .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)


        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY }



        okhttpBuilder.addInterceptor(loggingInterceptor)
        okhttpBuilder.addInterceptor(queryInterceptor)

        val okHttpClient = okhttpBuilder.build()

        val builder = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
        return builder.build()
    }

    val apiService: APIService by lazy { buildRetrofitWithInterceptors().create(APIService::class.java) }
}