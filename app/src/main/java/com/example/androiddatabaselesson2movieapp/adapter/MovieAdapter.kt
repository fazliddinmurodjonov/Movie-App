package com.example.androiddatabaselesson2movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddatabaselesson2movieapp.databinding.ItemMovieBinding
import com.example.androiddatabaselesson2movieapp.model.Movie

class MovieAdapter(var movieList: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    lateinit var adapterListener: OnMyItemClickListener
    lateinit var adapterEditButtonListener: OnMyItemClickEditButton
    lateinit var adapterDeleteButtonListener: OnMyItemClickDeleteButton


    inner class MyViewHolder(var binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(movie: Movie, position: Int) {
            binding.movieNameItem.text = movie.name
            binding.movieAuthorsItem.text = movie.authors
            binding.movieDateItem.text = movie.date
            binding.root.setOnClickListener {
                adapterListener.onMyItemClick(movie, position)
            }
            binding.movieEditButtonItem.setOnClickListener {
                adapterEditButtonListener.onMyItemClick(movie, position)
            }
            binding.movieDeleteButtonItem.setOnClickListener {
                adapterDeleteButtonListener.onMyItemClick(movie, position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movieList[position]
        holder.onBind(movie, position)
    }

    override fun getItemCount() = movieList.size

    interface OnMyItemClickListener {
        fun onMyItemClick(movie: Movie, position: Int)
    }

    fun setOnMyItemClickListener(listener: OnMyItemClickListener) {
        adapterListener = listener
    }

    interface OnMyItemClickEditButton {
        fun onMyItemClick(movie: Movie, position: Int)
    }

    fun setOnMyItemClickEditButtonListener(listener: OnMyItemClickEditButton) {
        adapterEditButtonListener = listener
    }

    interface OnMyItemClickDeleteButton {
        fun onMyItemClick(movie: Movie, position: Int)
    }

    fun setOnMyItemClickDeleteButtonListener(listener: OnMyItemClickDeleteButton) {
        adapterDeleteButtonListener = listener
    }
}