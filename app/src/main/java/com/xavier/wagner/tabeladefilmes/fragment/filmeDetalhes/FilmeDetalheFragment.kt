package com.xavier.wagner.tabeladefilmes.fragment.filmeDetalhes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.xavier.wagner.tabeladefilmes.R
import com.xavier.wagner.tabeladefilmes.data.api.ApiTMDBService
import com.xavier.wagner.tabeladefilmes.data.model.Filme
import kotlinx.android.synthetic.main.filme_detalhe_fragment.*

class FilmeDetalheFragment : Fragment() {

    companion object{
        const val ARGUMENTO = "filme"
    }

    //private lateinit var viewModel: FilmeDetalheViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.filme_detalhe_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFilme(
            arguments?.getSerializable(ARGUMENTO) as Filme
        )
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
        }
    }
}