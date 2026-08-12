package com.truongdinh.waiterapp.di

import com.truongdinh.waiterapp.data.remote.api.AuthApi
import com.truongdinh.waiterapp.data.remote.api.CartApi
import com.truongdinh.waiterapp.data.remote.api.CategoryApi
import com.truongdinh.waiterapp.data.remote.api.MenuItemApi
import com.truongdinh.waiterapp.data.remote.api.TableApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideTableApi(retrofit: Retrofit): TableApi =
        retrofit.create(TableApi::class.java)

    @Provides
    @Singleton
    fun provideCategoryApi(retrofit: Retrofit): CategoryApi =
        retrofit.create(CategoryApi::class.java)

    @Provides
    @Singleton
    fun provideMenuItemApi(retrofit: Retrofit): MenuItemApi =
        retrofit.create(MenuItemApi::class.java)

    @Provides
    @Singleton
    fun provideCartApi(retrofit: Retrofit): CartApi =
        retrofit.create(CartApi::class.java)
}