package com.xavier.wagner.tabeladefilmes.data.model

data class Filme (
        var id: Int,
        val title: String,
        var poster_path: String,
        var overview: String,
        var backdrop_path: String,
        var genre_ids: List<Int>,
        var popularity: Double
)