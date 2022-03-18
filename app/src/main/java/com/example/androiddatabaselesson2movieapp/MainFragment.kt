package com.example.androiddatabaselesson2movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.androiddatabaselesson2movieapp.adapter.MovieAdapter
import com.example.androiddatabaselesson2movieapp.databinding.FragmentMainBinding
import com.example.androiddatabaselesson2movieapp.db.MyDBHelper
import com.example.androiddatabaselesson2movieapp.model.Movie

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val myDBHelper = MyDBHelper(requireContext())
        binding.AddMovieButton.setOnClickListener {
            findNavController().navigate(R.id.addMovieFragment)
        }
        val movieList = myDBHelper.getAllMovie()
        var recyclerViewAdapter = MovieAdapter(movieList)
        binding.RecyclerViewMovies.adapter = recyclerViewAdapter

        recyclerViewAdapter.setOnMyItemClickListener(object :
            MovieAdapter.OnMyItemClickListener {
            override fun onMyItemClick(movie: Movie, position: Int) {
                val bundleOf = bundleOf("movie" to movie.id)
                findNavController().navigate(R.id.aboutMovieFragment, bundleOf)
            }

        })

        recyclerViewAdapter.setOnMyItemClickEditButtonListener(object : MovieAdapter.OnMyItemClickEditButton{
            override fun onMyItemClick(movie: Movie, position: Int) {
                val bundleOf = bundleOf("movieId" to movie.id)
                findNavController().navigate(R.id.editMovieFragment,bundleOf)
            }

        })
        recyclerViewAdapter.setOnMyItemClickDeleteButtonListener(object:MovieAdapter.OnMyItemClickDeleteButton{
            override fun onMyItemClick(movie: Movie, position: Int) {
            myDBHelper.deleteMovie(movie)
                movieList.remove(movie)
                recyclerViewAdapter.notifyItemRemoved(movieList.size)
                recyclerViewAdapter.notifyItemRangeChanged(position,movieList.size)
            }

        })
        return binding.root
    }
}