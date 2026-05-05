package com.ElOuedUniv.maktaba.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://axdkxsyclmgbpjsgrtqm.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImF4ZGt4c3ljbG1nYnBqc2dydHFtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzczOTYzMzksImV4cCI6MjA5Mjk3MjMzOX0.h4cN8OBYtGgWGNvdu9_t25pgCDkZkq708NA5LVIRRrQ"
        ) {
            install(Postgrest)
            install(Storage)
        }
    }
}
