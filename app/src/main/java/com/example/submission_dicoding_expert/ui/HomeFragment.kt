package com.example.submission_dicoding_expert.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.ui.HomeAdapter
import com.example.submission_dicoding_expert.DetailActivity
import com.example.submission_dicoding_expert.R
import com.example.submission_dicoding_expert.databinding.FragmentHomeBinding
import com.example.submission_dicoding_expert.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private var homeAdapter = HomeAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMovie()
        observeMovie()
        setSearch()
        setSearch()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun setMovie() {

        homeAdapter = HomeAdapter()

        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
            setHasFixedSize(true)
        }
        homeAdapter.onClick = {
            val intent = Intent(context, DetailActivity::class.java)
//            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

    }

    private fun observeMovie() {
        homeViewModel.getMovies().observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when(movie) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        homeAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE

                    }
                }

            }
        }
    }

    private fun setSearch() {
       homeViewModel.movieResult.observe(viewLifecycleOwner) {
              homeAdapter.setData(it)
           Log.d("HomeFragment", "setSearch: $it")

        }
    }

    private fun observeSearch() {
        homeViewModel.getMovies().observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        it.data?.let { data ->
                        homeAdapter.setData(data)
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}