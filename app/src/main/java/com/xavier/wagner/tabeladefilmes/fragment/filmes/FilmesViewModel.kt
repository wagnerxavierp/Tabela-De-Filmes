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
                Filme("Homem de ferro"),
                Filme("Dinossauro Rex"),
                Filme("Park Dinos"),
                Filme("Guardiões da galáxia"),
                Filme("Comédia"),
                Filme("Ação")
        )
        _listaFilmes = listaFilmes.toMutableList()
        setListaFilmesLiveData()
    }

}