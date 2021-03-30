package com.xavier.wagner.tabeladefilmes.fragment.filmes

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.xavier.wagner.tabeladefilmes.R
import com.xavier.wagner.tabeladefilmes.data.api.ApiTMDBService
import com.xavier.wagner.tabeladefilmes.data.model.Filme
import kotlinx.android.synthetic.main.filmes_fragment.*


class FilmesFragment : Fragment() {

    private lateinit var viewModel: FilmesViewModel
    private lateinit var filmesAdapter: FilmesAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(FilmesViewModel::class.java)
        return inflater.inflate(R.layout.filmes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        buscarFilmeIMAGEBUTTON.setOnClickListener {  }
        esconderTeclado()
        viewModel.buscarListaFilmes(ApiTMDBService.KEY, ApiTMDBService.LANGUAGE, 1)
    }


    private fun setupRecycler(){
        filmesAdapter = FilmesAdapter{ clickItemFilme(it) }
        viewModel.listaFilmesLiveData.observe(requireActivity(), {
            it?.let { filmesAdapter.setItemLista(it) }
        })
        listaFilmesRECYCLERVIEW.layoutManager = GridLayoutManager(requireContext(), 2)
        listaFilmesRECYCLERVIEW.adapter = filmesAdapter
    }

    fun clickItemFilme(filme: Filme){
        Toast.makeText(requireContext(), filme.title, Toast.LENGTH_SHORT).show()
    }

    private fun esconderTeclado(){
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

}