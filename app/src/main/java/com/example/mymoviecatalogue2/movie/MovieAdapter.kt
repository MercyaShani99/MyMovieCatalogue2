package com.example.mymoviecatalogue2.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.detail.DetailCatalogueActivity
import com.example.mymoviecatalogue2.databinding.ItemsMovieBinding

class MovieAdapter : PagedListAdapter<Movie, MovieAdapter.CatalogueViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogueViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: CatalogueViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null)
        holder.bind(movie)
    }

    class CatalogueViewHolder(private val binding: ItemsMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(content: Movie) {
            with(binding) {
                tvItemTitle.text = content.title
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + content.imagePath).into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCatalogueActivity::class.java)
                    intent.putExtra(DetailCatalogueActivity.EXTRA_CATALOGUE,content.movieId)
                    intent.putExtra(DetailCatalogueActivity.EXTRA_TYPE, "movie")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}