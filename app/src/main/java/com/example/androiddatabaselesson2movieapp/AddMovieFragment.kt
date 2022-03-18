package com.example.androiddatabaselesson2movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.androiddatabaselesson2movieapp.databinding.FragmentAddMovieBinding
import com.example.androiddatabaselesson2movieapp.db.MyDBHelper
import com.example.androiddatabaselesson2movieapp.model.Movie


class AddMovieFragment : Fragment() {

    lateinit var binding: FragmentAddMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMovieBinding.inflate(inflater, container, false)
        var myDBHelper = MyDBHelper(requireContext())
        binding.movieSaveButton.setOnClickListener {
            val movieName = binding.movieName.text.toString()
            val movieAuthors = binding.movieAuthors.text.toString()
            val movieAbout = binding.movieAbout.text.toString()
            val movieDate = binding.movieDate.text.toString()
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

                var movie = Movie(movieName, movieAuthors, movieAbout, movieDate)
                myDBHelper.addMovie(movie)
                Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }

        }
        return binding.root
    }

}