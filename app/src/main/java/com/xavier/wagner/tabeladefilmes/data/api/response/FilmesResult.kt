package com.xavier.wagner.tabeladefilmes.data.api.response

import com.xavier.wagner.tabeladefilmes.data.model.Filme

class FilmesResult(
    val results: List<FilmeResponse>
){
    fun toFilmes(): List<Filme>{
        val lista = arrayListOf<Filme>()
        results.forEach {
            lista.add(Filme(
                    id = it.id,
                    title = it.title,
                    poster_path = it.poster_path,
                    overview = it.overview
            ))
        }
        return lista
    }
}
