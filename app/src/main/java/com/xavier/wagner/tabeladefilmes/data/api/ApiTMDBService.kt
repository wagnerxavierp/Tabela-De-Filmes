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

    const val IMAGE_BACKDROP_WIDTH = 780
    //const val IMAGE_HEIGTH_SLIDE = ( (56.28/100.0) * IMAGE_WIDTH_SLIDE ).toInt()

    const val IMAGE_WIDTH_NORMAL = 154
    //const val IMAGE_HEIGTH_NORMAL = ( (150.0/100.0) * IMAGE_WIDTH_NORMAL ).toInt()

    object TypeFilmes{
        const val POPULAR = "popular"
        const val PROXIMOS = "upcoming"
        const val MAIS_VOTADOS = "top_rated"
    }

}