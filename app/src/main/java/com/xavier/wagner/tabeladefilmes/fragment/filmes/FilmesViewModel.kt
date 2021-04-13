package com.xavier.wagner.tabeladefilmes.fragment.filmes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xavier.wagner.tabeladefilmes.data.api.ApiTMDBService
import com.xavier.wagner.tabeladefilmes.data.api.response.FilmesResult
import com.xavier.wagner.tabeladefilmes.data.model.Filme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmesViewModel : ViewModel() {

    val listaFilmesPopularesLiveData = MutableLiveData<MutableList<Filme>>().apply { value = mutableListOf() }

    val listaFilmesProximosLiveData = MutableLiveData<MutableList<Filme>>().apply { value = mutableListOf() }

    fun buscarListaFilmes(type: String ,api_key: String, language: String, page: Int){
        ApiTMDBService.instance
            .obterFilmes(type ,api_key, language, page)
            .enqueue(object : Callback<FilmesResult> {
                override fun onResponse(
                    call: Call<FilmesResult>, response: Response<FilmesResult>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            val listaDeFilmes = it.toFilmes() as MutableList<Filme>
                            when(type){
                                ApiTMDBService.TypeFilmes.POPULARES -> {
                                    listaFilmesPopularesLiveData.value = listaDeFilmes
                                }
                                ApiTMDBService.TypeFilmes.PROXIMOS -> {
                                    listaFilmesProximosLiveData.value = listaDeFilmes
                                }
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<FilmesResult>, t: Throwable) {
                }
            })

    }

    fun buscarListaFilmesCarousel(
            api_key: String,
            language: String,
            quantities: Int,
            type: String = ApiTMDBService.TypeFilmes.CAROUSEL,
            resultCallback : (MutableList<Filme>) -> Unit)
    {
        ApiTMDBService.instance
            .obterFilmes(type ,api_key, language)
            .enqueue(object : Callback<FilmesResult> {
                override fun onResponse(
                    call: Call<FilmesResult>, response: Response<FilmesResult>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            val list = (it.toFilmes() as MutableList<Filme>)
                            resultCallback(list.subList(0, quantities))
                        }
                    }
                }
                override fun onFailure(call: Call<FilmesResult>, t: Throwable) {
                }
            })
    }
}