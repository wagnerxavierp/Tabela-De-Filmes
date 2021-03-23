package com.xavier.wagner.tabeladefilmes.fragment.filmes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xavier.wagner.tabeladefilmes.data.model.Filme

class FilmesViewModel : ViewModel() {

    val listaFilmesLiveData = MutableLiveData<MutableList<Filme>>().apply { value = _listaFilmes }
    private var _listaFilmes = mutableListOf<Filme>()

    private fun setListaFilmesLiveData(){
        listaFilmesLiveData.value = _listaFilmes
    }

    fun buscarListaFilmes(){
        val listaFilmes = listOf(
                Filme("Godxila 2"),
                Filme("Homem de ferro")
        )
        _listaFilmes = listaFilmes.toMutableList()
        setListaFilmesLiveData()
    }

}