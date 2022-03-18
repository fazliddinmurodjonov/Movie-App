package com.example.androiddatabaselesson2movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.androiddatabaselesson2movieapp.databinding.FragmentEditMovieBinding
import com.example.androiddatabaselesson2movieapp.db.MyDBHelper
import com.example.androiddatabaselesson2movieapp.model.Movie


class EditMovieFragment : Fragment() {
    lateinit var binding: FragmentEditMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditMovieBinding.inflate(inflater, container, false)
        val id = arguments?.getInt("movieId")
        val myDBHelper = MyDBHelper(requireContext())
        val movie = myDBHelper.getMovieById(id!!)

        binding.EditMovieName.setText(movie.name)
        binding.EditMovieAuthors.setText(movie.authors)
        binding.EditMovieAbout.setText(movie.about)
        binding.EditMovieDate.setText(movie.date)

        binding.EditMovieButton.setOnClickListener {
            val movieName = binding.EditMovieName.text.toString()
            val movieAuthors = binding.EditMovieAuthors.text.toString()
            val movieAbout = binding.EditMovieAbout.text.toString()
            val movieDate = binding.EditMovieDate.text.toString()
            var name = false
            var authors = false
            var about = false
            var date = false

            for (c in movieName) {
                if (c != ' ') {
                    name = true
                }
            }
            for (movieAuthor in movieAuthors) {
                if (movieAuthor != ' ') {
                    authors = true
                }
            }
            for (c in movieAbout) {
                if (c != ' ') {
                    about = true
                }
            }
            for (c in movieDate) {
                if (c != ' ') {
                    date = true
                }
            }
            if (movieName != "" && movieAuthors != "" && movieAbout != "" && movieDate != "" && name && authors && about && date) {

                var movie = Movie(id, movieName, movieAuthors, movieAbout, movieDate)
                myDBHelper.updateMovie(movie)
                Toast.makeText(requireContext(), "Changed", Toast.LENGTH_SHORT).show()

                findNavController().popBackStack()
            }
        }

        return binding.root
    }

}