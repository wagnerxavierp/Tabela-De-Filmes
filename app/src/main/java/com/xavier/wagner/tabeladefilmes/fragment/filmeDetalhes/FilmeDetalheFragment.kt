package com.xavier.wagner.tabeladefilmes.fragment.filmeDetalhes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.xavier.wagner.tabeladefilmes.R
import com.xavier.wagner.tabeladefilmes.application.FilmeApplication
import com.xavier.wagner.tabeladefilmes.data.api.ApiTMDBService
import com.xavier.wagner.tabeladefilmes.data.model.Filme
import kotlinx.android.synthetic.main.filme_detalhe_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmeDetalheFragment : Fragment() {

    companion object{
        const val ARGUMENTO = "filme"
    }

    private var favorito: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.filme_detalhe_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (arguments?.getSerializable(ARGUMENTO) as Filme).let {
            setupFilme( it )
            setupListener( it )
        }
    }

    private fun setupFilme(filme: Filme?){
        filme?.let {
            Glide
                .with(requireContext())
                .load("https://image.tmdb.org/t/p/" +
                        "w${ApiTMDBService.IMAGE_BACKDROP_WIDTH}/" +
                        filme.backdrop_path
                )
                .into(cartazDetalheImage)
            titleDetalheTextView.text = filme.title
            descricaoDetalheTextView.text = filme.overview

            GlobalScope.launch {
                try {
                    FilmeApplication.instance.helperDB?.isFilmeFavorito(it.id)?.run {
                        if (this) {
                            favorito = true
                            withContext(Dispatchers.Main) {
                                favoritoImageView.setImageResource(R.drawable.ic_favorite_24)
                            }
                        }
                        else{
                            favorito = false
                            withContext(Dispatchers.Main) {
                                favoritoImageView.setImageResource(R.drawable.ic_favorite_border_24)
                            }
                        }
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    fun setupListener(filme: Filme){
        favoritoImageView.setOnClickListener {
            GlobalScope.launch {
                try {
                    if (favorito){
                        favorito = false
                        FilmeApplication.instance.helperDB?.removerFavorito(filme.id)
                        withContext(Dispatchers.Main) {
                            favoritoImageView.setImageResource(R.drawable.ic_favorite_border_24)
                        }
                    }else{
                        favorito = true
                        FilmeApplication.instance.helperDB?.salvarFilmeFavorito(filme.id)
                        withContext(Dispatchers.Main) {
                            favoritoImageView.setImageResource(R.drawable.ic_favorite_24)
                        }
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}