package com.xavier.wagner.tabeladefilmes.fragment.filmes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xavier.wagner.tabeladefilmes.R

class FilmesFragment : Fragment() {

    //private lateinit var viewModel: FilmesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.filmes_fragment, container, false)
    }

}