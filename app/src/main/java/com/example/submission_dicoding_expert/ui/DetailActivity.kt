package com.example.submission_dicoding_expert.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.core.BuildConfig
import com.example.core.domain.Movie
import com.example.core.utils.Helper.loadFromUrl
import com.example.submission_dicoding_expert.R
import com.example.submission_dicoding_expert.databinding.ActivityDetailBinding
import com.example.submission_dicoding_expert.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        detailData(movie)
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun detailData(movie: Movie?) {
        movie?.let {
            binding.tvTitle.text = movie.title
            binding.ImgMovie.loadFromUrl(BuildConfig.IMAGE_URL + movie.backdropPath)
            binding.tvDescription.text = movie.overview
            binding.tvScore.text = movie.voteAverage.toString()


            var favorite = movie.isFavorite

            setFavorite(favorite)
            binding.favoriteButton.setOnClickListener {
                favorite = !favorite
                detailViewModel.setFavoriteMovie(movie, favorite)
                setFavorite(favorite)

            }
        }
    }

    private fun setFavorite(state: Boolean) {
        if (state) {
            binding.favoriteButton.setImageResource(R.drawable.favorite)
        } else {
            binding.favoriteButton.setImageResource(R.drawable.favorite_border)
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}