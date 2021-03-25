package com.xavier.wagner.tabeladefilmes.fragment.filmes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xavier.wagner.tabeladefilmes.data.api.ApiService
import com.xavier.wagner.tabeladefilmes.data.api.response.FilmesResult
import com.xavier.wagner.tabeladefilmes.data.model.Filme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmesViewModel : ViewModel() {

    val listaFilmesLiveData = MutableLiveData<MutableList<Filme>>().apply { value = _listaFilmes }
    private var _listaFilmes = mutableListOf<Filme>()

    private fun setListaFilmesLiveData(){
        listaFilmesLiveData.value = _listaFilmes
    }


    fun buscarListaFilmes(){
        ApiService
            .instance
            .obterFilmesPopulares("925073bb4947af33bb76f82ebab486c6", "pt-BR", 1)
            .enqueue(object : Callback<FilmesResult> {
                override fun onResponse(
                    call: Call<FilmesResult>, response: Response<FilmesResult>
                ) {
                    if (response.isSuccessful){
                        val listaFilmes = arrayListOf<Filme>()

                        response.body()?.let {
                            it.results.forEach {
                                listaFilmes.add(Filme(it.title))
                            }

                            _listaFilmes = listaFilmes.toMutableList()
                            setListaFilmesLiveData()
                        }
                    }
                }
                override fun onFailure(call: Call<FilmesResult>, t: Throwable) {
                }
            })

    }

}