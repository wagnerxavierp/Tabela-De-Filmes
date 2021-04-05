package com.xavier.wagner.tabeladefilmes.fragment.filmes

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ViewListener
import com.xavier.wagner.tabeladefilmes.R
import com.xavier.wagner.tabeladefilmes.data.api.ApiTMDBService
import com.xavier.wagner.tabeladefilmes.data.model.Filme
import kotlinx.android.synthetic.main.filmes_fragment.*
import kotlinx.android.synthetic.main.view_custom_carousel.view.*

class FilmesFragment : Fragment() {

    private lateinit var viewModel: FilmesViewModel
    private lateinit var filmesPopularesAdapter: FilmesAdapter
    private lateinit var filmesProximosAdapter: FilmesAdapter
    private lateinit var listaFilmesCarousel: MutableList<Filme>

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

        esconderTeclado()

        viewModel.buscarListaFilmes(
                ApiTMDBService.TypeFilmes.POPULAR,
                ApiTMDBService.KEY,
                ApiTMDBService.LANGUAGE,
                1
        )
        viewModel.buscarListaFilmes(
                ApiTMDBService.TypeFilmes.PROXIMOS,
                ApiTMDBService.KEY,
                ApiTMDBService.LANGUAGE,
                1
        )
        viewModel.buscarListaFilmesCarousel(
                ApiTMDBService.TypeFilmes.MAIS_VOTADOS,
                ApiTMDBService.KEY,
                ApiTMDBService.LANGUAGE,
                1
        ){
            setupCarousel(it)
        }
    }


    private fun setupRecyclerFilmesPopulares(){
        filmesPopularesAdapter = FilmesAdapter{ clickItemFilme(it) }

        filmesPopularesRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        filmesPopularesRecycler.adapter =
                filmesPopularesAdapter

        viewModel.listaFilmesPopularesLiveData.observe(requireActivity(), {
            it?.let { filmesPopularesAdapter.setItemLista(it) }
        })
    }

    private fun setupRecyclerFilmesProximos(){
        filmesProximosAdapter = FilmesAdapter{ clickItemFilme(it) }

        filmesProximosRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        filmesProximosRecycler.adapter =
                filmesProximosAdapter

        viewModel.listaFilmesProximosLiveData.observe(requireActivity(), {
            it?.let { filmesProximosAdapter.setItemLista(it) }
        })
    }

    private fun setupCarousel(filmes: MutableList<Filme>){
        listaFilmesCarousel = if(filmes.size >= 6) filmes.subList(0, 6) else filmes

        carouselFilmes.setViewListener(viewListener)

        carouselFilmes.pageCount = listaFilmesCarousel.size
        carouselFilmes.setImageClickListener { position ->
            clickItemFilme(listaFilmesCarousel.get(position))
        }
    }

    var viewListener: ViewListener = ViewListener { position ->
        val rootViewGroup = null
        val customView = layoutInflater.inflate(R.layout.view_custom_carousel, rootViewGroup)
        Picasso
                .get()
                .load("https://image.tmdb.org/t/p/" +
                        "w${ApiTMDBService.IMAGE_WIDTH_SLIDE}/" +
                        listaFilmesCarousel.get(position).backdrop_path
                )
                .into(customView.imageView)
        customView.titleItemSlide.text = listaFilmesCarousel.get(position).title

        customView
    }

    fun clickItemFilme(filme: Filme){
        val bundle = Bundle()
        bundle.putSerializable("filme", filme)
        //findNavController().navigate(R.id.contaFragment, bundle)
        Toast.makeText(requireContext(), filme.title, Toast.LENGTH_SHORT).show()
    }

    private fun esconderTeclado(){
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

}