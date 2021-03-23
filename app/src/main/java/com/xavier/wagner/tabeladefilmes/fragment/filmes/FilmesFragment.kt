package com.xavier.wagner.tabeladefilmes.fragment.filmes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavier.wagner.tabeladefilmes.R
import com.xavier.wagner.tabeladefilmes.data.model.Filme
import kotlinx.android.synthetic.main.filmes_fragment.*
import java.security.Provider


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
    }

    private fun setupRecycler(){
        filmesAdapter = FilmesAdapter(
                listOf(
                        Filme("Godxila 2"),
                        Filme("Homem de ferro")
                )
        ){ clickItemFilme(it) }

        listaFilmesRECYCLERVIEW.layoutManager = LinearLayoutManager(requireContext())
        listaFilmesRECYCLERVIEW.adapter = filmesAdapter
    }

    fun clickItemFilme(filme: Filme){
        Toast.makeText(requireContext(), filme.nome, Toast.LENGTH_LONG).show()
    }

}