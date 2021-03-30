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

    val listaFilmesLiveData = MutableLiveData<MutableList<Filme>>().apply { value = _listaFilmes }
    private var _listaFilmes = mutableListOf<Filme>()

    private fun setListaFilmesLiveData(){
        listaFilmesLiveData.value = _listaFilmes
    }

    fun buscarListaFilmes(api_key: String, language: String, page: Int){
        ApiTMDBService
            .instance
            .obterFilmesPopulares(api_key, language, page)
            .enqueue(object : Callback<FilmesResult> {
                override fun onResponse(
                    call: Call<FilmesResult>, response: Response<FilmesResult>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            _listaFilmes = it.toFilmes() as MutableList<Filme>
                            setListaFilmesLiveData()
                        }
                    }
                }
                override fun onFailure(call: Call<FilmesResult>, t: Throwable) {
                }
            })

    }

}