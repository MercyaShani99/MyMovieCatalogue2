package com.example.mymoviecatalogue2.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymoviecatalogue2.Constant
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.databinding.ItemsTvshowBinding
import com.example.mymoviecatalogue2.detail.MovieTvDetail

class TvShowAdapter : PagedListAdapter<TvShow, TvShowAdapter.CatalogueViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueViewHolder {
        val tvShowBinding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogueViewHolder(tvShowBinding)
    }

    override fun onBindViewHolder(holder: CatalogueViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null)
        holder.bind(tvShow)
    }

    class CatalogueViewHolder(private val binding: ItemsTvshowBinding): RecyclerView.ViewHolder(binding.root)  {
        fun bind(content: TvShow) {
            with(binding) {
                tvItemTitle.text = content.title
                tvItemDate.text = content.firstAirDate
                Glide.with(itemView.context)
                        .load(Constant.POSTER_BASE_URL + content.imagePath).into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MovieTvDetail::class.java)
                    intent.putExtra(MovieTvDetail.EXTRA_CATALOGUE, content.tvId)
                    intent.putExtra(MovieTvDetail.EXTRA_TYPE, "tv")

                    itemView.context.startActivity(intent)
                }
            }
        }
    }


}