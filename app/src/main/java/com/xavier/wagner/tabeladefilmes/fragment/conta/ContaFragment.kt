package com.xavier.wagner.tabeladefilmes.fragment.conta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xavier.wagner.tabeladefilmes.R

class ContaFragment : Fragment() {

    private lateinit var viewModel: ContaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.conta_fragment, container, false)
    }

}