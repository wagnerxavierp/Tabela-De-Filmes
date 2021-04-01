package com.xavier.wagner.tabeladefilmes.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiTMDBService {

    var instance: FilmesService =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FilmesService::class.java)

    const val KEY = "925073bb4947af33bb76f82ebab486c6"
    const val LANGUAGE = "pt-BR"

    object TypeFilmes{
        const val POPULAR = "popular"
        const val PROXIMOS = "upcoming"
        const val MAIS_VOTADOS = "top_rated"
    }

}