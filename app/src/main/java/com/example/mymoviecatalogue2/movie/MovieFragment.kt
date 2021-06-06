package com.example.mymoviecatalogue2.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviecatalogue2.viewmodel.ViewModelFactory
import com.example.mymoviecatalogue2.vo.Status
import com.example.mymoviecatalogue2.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()
            viewModel.getMovie().observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when(movie.status) {
                        Status.LOADING -> fragmentMovieBinding.progressBar.visibility = View.GONE
                        Status.SUCCESS -> {
                            movieAdapter.submitList(movie.data)
                        }
                        Status.ERROR -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

}