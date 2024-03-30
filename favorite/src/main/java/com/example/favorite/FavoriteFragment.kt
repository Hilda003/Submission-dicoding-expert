package com.example.favorite


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.HomeAdapter
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.DaggerFavoriteComponent
import com.example.submission_dicoding_expert.di.ModuleDependensiFavorite
import com.example.submission_dicoding_expert.ui.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavoriteFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var homeAdapter : HomeAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    ModuleDependensiFavorite::class.java
                )
            )
            .build()
            .inject(this)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeAdapter()
        binding.rvFavoriteMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


            adapter = homeAdapter
        }

        homeAdapter.onClick = { selectedData ->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }

        observerFavMovie()


        binding.back.setOnClickListener {
            activity?.onBackPressed()
        }


    }




    private fun observerFavMovie() {
        favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner) { movies ->
            homeAdapter.setData(movies)
            Log.d("Favorite", "data " + homeAdapter.setData(movies))

        }
    }


}