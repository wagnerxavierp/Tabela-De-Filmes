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

    val listaFilmesPopularesLiveData = MutableLiveData<MutableList<Filme>>().apply { value = _listaFilmesPopulares }
    private var _listaFilmesPopulares = mutableListOf<Filme>()

    val listaFilmesProximosLiveData = MutableLiveData<MutableList<Filme>>().apply { value = _listaFilmesProximos }
    private var _listaFilmesProximos = mutableListOf<Filme>()

    fun buscarListaFilmes(type: String ,api_key: String, language: String, page: Int){
        ApiTMDBService.instance
            .obterFilmes(type ,api_key, language, page)
            .enqueue(object : Callback<FilmesResult> {
                override fun onResponse(
                    call: Call<FilmesResult>, response: Response<FilmesResult>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            if(type.equals(ApiTMDBService.TypeFilmes.POPULAR)){
                                _listaFilmesPopulares = it.toFilmes() as MutableList<Filme>
                                listaFilmesPopularesLiveData.value = _listaFilmesPopulares
                            }else if(type.equals(ApiTMDBService.TypeFilmes.PROXIMOS)){
                                _listaFilmesProximos = it.toFilmes() as MutableList<Filme>
                                listaFilmesProximosLiveData.value = _listaFilmesProximos
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<FilmesResult>, t: Throwable) {
                }
            })

    }


    fun buscarListaFilmesCarousel(type: String ,api_key: String, language: String, page: Int, resultCallback : (MutableList<Filme>) -> Unit){
        ApiTMDBService.instance
            .obterFilmes(type ,api_key, language, page)
            .enqueue(object : Callback<FilmesResult> {
                override fun onResponse(
                    call: Call<FilmesResult>, response: Response<FilmesResult>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            resultCallback(it.toFilmes() as MutableList<Filme>)
                        }
                    }
                }
                override fun onFailure(call: Call<FilmesResult>, t: Throwable) {
                }
            })
    }
}