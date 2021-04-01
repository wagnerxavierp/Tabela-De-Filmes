package com.xavier.wagner.tabeladefilmes.fragment.pesquisar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xavier.wagner.tabeladefilmes.R

class PesquisarFragment : Fragment() {

    private lateinit var viewModel: PesquisarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pesquisar_fragment, container, false)
    }

}