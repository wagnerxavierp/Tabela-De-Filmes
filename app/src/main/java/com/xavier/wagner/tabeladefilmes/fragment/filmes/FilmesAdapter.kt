package com.xavier.wagner.tabeladefilmes.fragment.filmes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.xavier.wagner.tabeladefilmes.R
import com.xavier.wagner.tabeladefilmes.data.api.ApiTMDBService
import com.xavier.wagner.tabeladefilmes.data.model.Filme
import kotlinx.android.synthetic.main.item_filme.view.*

class FilmesAdapter(
        private val onClick: ((Filme) -> Unit)
) : RecyclerView.Adapter<FilmeViewHolder>() {

    private var listaFilmes: List<Filme>

    init {
        listaFilmes = listOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_filme, parent, false)
        return FilmeViewHolder(view)
    }

    fun setItemLista(listaFilmes: List<Filme>){
        if (listaFilmes.size > 5)
            this.listaFilmes = listaFilmes.subList(0, 6)
        else
            this.listaFilmes = listaFilmes

        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listaFilmes.size

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        val filme = listaFilmes[position]
        with(holder.itemView){
            Picasso
                .get()
                .load("https://image.tmdb.org/t/p/" +
                        "w${ApiTMDBService.IMAGE_WIDTH_NORMAL}/${filme.poster_path}"
                )
                .into(posterImageView)
            layoutItemFilme.setOnClickListener { onClick(filme) }
        }
    }
}

class FilmeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)