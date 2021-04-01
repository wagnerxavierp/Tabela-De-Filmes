package com.xavier.wagner.tabeladefilmes.data.api.response

data class FilmeResponse(
    var id: Int,
    var poster_path: String,
    var title: String,
    var overview: String,
    var backdrop_path: String,
    var genre_ids: List<Int>,
    var popularity: Double
)
