package com.example.androiddatabaselesson2movieapp.model

interface DatabaseService {

    fun addMovie(movie: Movie)
    fun deleteMovie(movie: Movie)
    fun getMovieById(id: Int): Movie
    fun updateMovie(movie: Movie): Int
    fun getAllMovie(): ArrayList<Movie>

}