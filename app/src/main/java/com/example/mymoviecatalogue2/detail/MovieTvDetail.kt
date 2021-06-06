package com.example.mymoviecatalogue2.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mymoviecatalogue2.Constant
import com.example.mymoviecatalogue2.R
import com.example.mymoviecatalogue2.viewmodel.ViewModelFactory
import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.databinding.ActivityMovieTvDetailBinding
import com.example.mymoviecatalogue2.databinding.ContentMovieTvDetailBinding
import com.example.mymoviecatalogue2.vo.Status


class MovieTvDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_CATALOGUE = "extra_catalogue"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var detailMovieTvDetailBinding: ContentMovieTvDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailCatalogueBinding = ActivityMovieTvDetailBinding.inflate(layoutInflater)
        detailMovieTvDetailBinding = activityDetailCatalogueBinding.detailContent

        setContentView(activityDetailCatalogueBinding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = "Catalogue Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this@MovieTvDetail, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val catalogueId = extras.getInt(EXTRA_CATALOGUE)
            val catalogueType = extras.getString(EXTRA_TYPE)
            if (catalogueId != 0 && catalogueType != null) {
                when(catalogueType) {
                    "tv" -> {
                        getDataTv(catalogueId)
                    }
                    "movie" -> {
                        getDataMovie(catalogueId)
                    }
                }
            }
        }
    }

    private fun getDataMovie(idMovie: Int) {
        detailViewModel.getDetailMovie(idMovie).observe(this, {
            when(it.status) {
                Status.LOADING -> detailMovieTvDetailBinding.progressBar.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    if (it.data != null) {
                        detailMovieTvDetailBinding.progressBar.visibility = View.GONE
                        showMovieDetail(it.data)
                    }
                }
                Status.ERROR -> {
                    detailMovieTvDetailBinding.progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, "Data Failed to Load", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    private fun getDataTv(idTv: Int) {
        detailViewModel.getDetailTvShow(idTv).observe(this, {
            when(it.status) {
                Status.LOADING -> detailMovieTvDetailBinding.progressBar.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    if (it.data != null) {
                        detailMovieTvDetailBinding.progressBar.visibility = View.GONE
                        showTvShowDetail(it.data)
                    }
                }
                Status.ERROR -> {
                    detailMovieTvDetailBinding.progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, "Data Failed to Load", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    private fun showTvShowDetail(tvShow: TvShow) {
        detailMovieTvDetailBinding.textTitle.text = tvShow.title
        detailMovieTvDetailBinding.textDate.text = tvShow.releaseDate
        detailMovieTvDetailBinding.textDescription.text = tvShow.overview
        Glide.with(this)
                .load(Constant.POSTER_BASE_URL + tvShow.imagePath)
                .transform(RoundedCorners(20))
                .into(detailMovieTvDetailBinding.imagePoster)
    }

    private fun showMovieDetail(movie: Movie) {
        detailMovieTvDetailBinding.textTitle.text = movie.title
        detailMovieTvDetailBinding.textDate.text = movie.releaseDate
        detailMovieTvDetailBinding.textDescription.text = movie.overview
        Glide.with(this)
                .load(Constant.POSTER_BASE_URL + movie.imagePath)
                .transform(RoundedCorners(20))
                .into(detailMovieTvDetailBinding.imagePoster)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        this.menu = menu
        when(intent.getStringExtra(EXTRA_TYPE)) {
            "tv" -> {
                detailViewModel.dataDetailTv.observe(this, { detailTv ->
                    if (detailTv != null) {
                        when(detailTv.status) {
                            Status.LOADING -> detailMovieTvDetailBinding.progressBar.visibility = View.GONE
                            Status.SUCCESS -> if (detailTv.data != null) {
                                detailMovieTvDetailBinding.progressBar.visibility = View.GONE
                                val state = detailTv.data.isFav

                                setBtnFavorite(state)
                            }
                            Status.ERROR -> {
                                detailMovieTvDetailBinding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Something Error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
            "movie" -> {
                detailViewModel.dataDetailMovie.observe(this, { detailMovie ->
                    if (detailMovie != null) {
                        when(detailMovie.status) {
                            Status.LOADING -> detailMovieTvDetailBinding.progressBar.visibility = View.GONE
                            Status.SUCCESS -> if (detailMovie.data != null) {
                                detailMovieTvDetailBinding.progressBar.visibility = View.GONE
                                val state = detailMovie.data.isFav
                                setBtnFavorite(state)
                            }
                            Status.ERROR -> {
                                detailMovieTvDetailBinding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Something Error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            when(intent.getStringExtra(EXTRA_TYPE)) {
                "tv" -> {
                        detailViewModel.setFavoriteTv()
                }
                "movie" -> {
                        detailViewModel.setFavoriteMovie()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBtnFavorite(state: Boolean) {
        if (menu == null) return

        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.favorite_btn)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.favorite_border_btn)
        }
    }


}