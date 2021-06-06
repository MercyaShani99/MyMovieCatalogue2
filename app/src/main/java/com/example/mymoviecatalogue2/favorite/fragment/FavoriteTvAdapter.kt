package com.example.mymoviecatalogue2.favorite.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.detail.DetailCatalogueActivity
import com.example.mymoviecatalogue2.databinding.ItemsTvshowBinding

class FavoriteTvAdapter: PagedListAdapter<TvShow, FavoriteTvAdapter.FavoriteTvViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }

        }
    }

    fun getSwipedData(swipedPosition: Int): TvShow? = getItem(swipedPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvAdapter.FavoriteTvViewHolder {
        val itemsTvShowBinding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTvViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: FavoriteTvAdapter.FavoriteTvViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null)
        holder.bind(tv)
    }

    inner class FavoriteTvViewHolder(private val binding: ItemsTvshowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvShow) {
            with(binding) {
                tvItemTitle.text = tv.title
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + tv.imagePath).into(imgPoster)

                itemView.setOnClickListener {
                    Intent(itemView.context, DetailCatalogueActivity::class.java).also {
                        it.putExtra(DetailCatalogueActivity.EXTRA_CATALOGUE, tv.tvId)
                        it.putExtra(DetailCatalogueActivity.EXTRA_TYPE, "movie")
                        itemView.context.startActivity(it)
                    }
                }
            }
        }
    }


}