package com.xavier.wagner.tabeladefilmes.fragment.filmes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xavier.wagner.tabeladefilmes.R
import com.xavier.wagner.tabeladefilmes.data.model.Filme
import kotlinx.android.synthetic.main.item_filme.view.*

class FilmesAdapter(
        private val listaFilmes: List<Filme>,
        private val onClick: ((Filme) -> Unit)
) : RecyclerView.Adapter<FilmeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_filme, parent, false)
        return FilmeViewHolder(view)
    }

    override fun getItemCount(): Int = listaFilmes.size

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        val filme = listaFilmes[position]
        with(holder.itemView){
            titleTEXTVIEW.text = filme.nome
            layoutItemFilme.setOnClickListener { onClick(filme) }
        }
    }
}

class FilmeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)