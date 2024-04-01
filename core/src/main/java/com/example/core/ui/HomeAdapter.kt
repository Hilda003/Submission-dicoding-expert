package com.example.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BuildConfig
import com.example.core.databinding.ItemRowBinding
import com.example.core.domain.Movie
import com.example.core.utils.Helper.loadFromUrl


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var listData = ArrayList<Movie>()
    var onClick : ((Movie) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie : Movie) {
            with(binding) {
                movie.posterPath.let {
                    ImgMovie.loadFromUrl(BuildConfig.IMAGE_URL + it)
                }
                tvMovie.text = movie.title
                txtLanguage.text= movie.originalLanguage
                tvScore.text = movie.voteAverage.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onClick?.invoke(listData[adapterPosition])
            }
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        return ViewHolder(
            ItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int =  listData.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }
}