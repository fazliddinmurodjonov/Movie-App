package com.example.androiddatabaselesson2movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androiddatabaselesson2movieapp.databinding.FragmentAboutMovieBinding
import com.example.androiddatabaselesson2movieapp.db.MyDBHelper


class AboutMovieFragment : Fragment() {
    lateinit var binding: FragmentAboutMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutMovieBinding.inflate(inflater, container, false)
        val id = arguments?.getInt("movie")
        val myDBHelper = MyDBHelper(requireContext())
        val movie = myDBHelper.getMovieById(id!!)
        binding.AboutMovieNameMain.text = movie.name
        binding.AboutMovieName.text = movie.name
        binding.AboutMovieAuthors.text = movie.authors
        binding.AboutMovieAbout.text = movie.about
        binding.AboutMovieDate.text = movie.date
        binding.AboutCloseButton.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

}