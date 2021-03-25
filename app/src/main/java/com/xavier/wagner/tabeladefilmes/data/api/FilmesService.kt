package com.xavier.wagner.tabeladefilmes.data.api

import com.xavier.wagner.tabeladefilmes.data.api.response.FilmesResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmesService {

    @GET("movie/popular")
    fun obterFilmesPopulares (
        @Query("api_key") apiKey: String,
        @Query("language") language:String,
        @Query("page") page:Int
    ): Call<FilmesResult>

}