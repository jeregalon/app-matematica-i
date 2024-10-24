package com.example.prueba4._backend.data.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://jbaycstmoggkesztkgtt.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpiYXljc3Rtb2dna2VzenRrZ3R0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjQzNDM4NzQsImV4cCI6MjAzOTkxOTg3NH0.1jXMV1hee-f5T8ehKpq6U_OcIxlCUt8bjupgWxT_dig"
    ) {
        install(Auth)
        install(Postgrest)
    }
}