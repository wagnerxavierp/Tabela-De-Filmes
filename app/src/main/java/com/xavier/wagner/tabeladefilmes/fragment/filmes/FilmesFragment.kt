package com.xavier.wagner.tabeladefilmes.fragment.filmes

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavier.wagner.tabeladefilmes.R
import com.xavier.wagner.tabeladefilmes.data.api.ApiTMDBService
import com.xavier.wagner.tabeladefilmes.data.model.Filme
import com.xavier.wagner.tabeladefilmes.uai.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.filmes_fragment.*


class FilmesFragment : Fragment() {

    private lateinit var viewModel: FilmesViewModel
    private lateinit var filmesPopularesAdapter: FilmesAdapter
    private lateinit var filmesProximosAdapter: FilmesAdapter
    private lateinit var filmesMaisVotadosAdapter: FilmesAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(FilmesViewModel::class.java)
        return inflater.inflate(R.layout.filmes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerFilmesPopulares()
        setupRecyclerFilmesProximos()
        setupRecyclerFilmesMainsVotados()

        esconderTeclado()

        viewModel.buscarListaFilmes(ApiTMDBService.TypeFilmes.POPULAR, ApiTMDBService.KEY, ApiTMDBService.LANGUAGE, 1)
        viewModel.buscarListaFilmes(ApiTMDBService.TypeFilmes.PROXIMOS, ApiTMDBService.KEY, ApiTMDBService.LANGUAGE, 1)
        viewModel.buscarListaFilmes(ApiTMDBService.TypeFilmes.MAIS_VOTADOS, ApiTMDBService.KEY, ApiTMDBService.LANGUAGE, 1)
    }


    private fun setupRecyclerFilmesPopulares(){
        filmesPopularesAdapter = FilmesAdapter{ clickItemFilme(it) }

        listaFilmesPopularesRECYCLERVIEW.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        listaFilmesPopularesRECYCLERVIEW.adapter =
                filmesPopularesAdapter

        viewModel.listaFilmesPopularesLiveData.observe(requireActivity(), {
            it?.let { filmesPopularesAdapter.setItemLista(it) }
        })
    }

    private fun setupRecyclerFilmesProximos(){
        filmesProximosAdapter = FilmesAdapter{ clickItemFilme(it) }

        listaFilmesProximosRECYCLERVIEW.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        listaFilmesProximosRECYCLERVIEW.adapter =
                filmesProximosAdapter

        viewModel.listaFilmesProximosLiveData.observe(requireActivity(), {
            it?.let { filmesProximosAdapter.setItemLista(it) }
        })
    }

    private fun setupRecyclerFilmesMainsVotados(){
        filmesMaisVotadosAdapter = FilmesAdapter{ clickItemFilme(it) }

        listaFilmesMaisVotadosRECYCLERVIEW.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        listaFilmesMaisVotadosRECYCLERVIEW.adapter =
                filmesMaisVotadosAdapter

        viewModel.listaFilmesMaisVotadosLiveData.observe(requireActivity(), {
            it?.let { filmesMaisVotadosAdapter.setItemLista(it) }
        })
    }

    fun clickItemFilme(filme: Filme){
        findNavController().navigate(R.id.contaFragment)
    }

    private fun esconderTeclado(){
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

}