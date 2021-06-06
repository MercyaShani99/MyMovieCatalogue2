package com.example.mymoviecatalogue2.tvshow

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
import com.example.mymoviecatalogue2.databinding.FragmentTvShowBinding


class TvShowFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()
            viewModel.getTvShow().observe(viewLifecycleOwner, { tvShow ->
                if(tvShow != null ) {
                    when(tvShow.status) {
                        Status.LOADING -> fragmentTvShowBinding.progressBar.visibility = View.GONE
                        Status.SUCCESS -> {
                            tvShowAdapter.submitList(tvShow.data)
//                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

}