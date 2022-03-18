package com.example.androiddatabaselesson2movieapp.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.androiddatabaselesson2movieapp.model.DatabaseService
import com.example.androiddatabaselesson2movieapp.model.Movie
import com.example.androiddatabaselesson2movieapp.utils.Constant

class MyDBHelper(context: Context) :
    SQLiteOpenHelper(context, Constant.DB_NAME, null, Constant.VERSION), DatabaseService {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table movie (${Constant.ID} integer not null primary key autoincrement unique,${Constant.NAME} text not null,${Constant.AUTHORS} text not null, ${Constant.ABOUT} text not null, ${Constant.DATE} text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists ${Constant.TABLE_NAME}")
        onCreate(db)
    }

    override fun addMovie(movie: Movie) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.NAME, movie.name)
        contentValues.put(Constant.AUTHORS, movie.authors)
        contentValues.put(Constant.ABOUT, movie.about)
        contentValues.put(Constant.DATE, movie.date)
        database.insert(Constant.TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun deleteMovie(movie: Movie) {
        val database = this.writableDatabase
        database.delete(Constant.TABLE_NAME, "${Constant.ID} = ?", arrayOf("${movie.id}"))
        database.close()
    }

    override fun getMovieById(id: Int): Movie {
        val database = this.readableDatabase
        var cursor = database.query(
            Constant.TABLE_NAME,
            arrayOf(Constant.ID, Constant.NAME, Constant.AUTHORS, Constant.ABOUT, Constant.DATE),
            "${Constant.ID} = ?",
            arrayOf("$id"),
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        return Movie(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getString(4)
        )
    }

    override fun updateMovie(movie: Movie): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.ID, movie.id)
        contentValues.put(Constant.NAME, movie.name)
        contentValues.put(Constant.AUTHORS, movie.authors)
        contentValues.put(Constant.ABOUT, movie.about)
        contentValues.put(Constant.DATE, movie.date)
        return database.update(
            Constant.TABLE_NAME,
            contentValues,
            "${Constant.ID} = ?",
            arrayOf("${movie.id}")
        )
    }

    override fun getAllMovie(): ArrayList<Movie> {
        val movieList = ArrayList<Movie>()
        val query = "select *from ${Constant.TABLE_NAME}"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val authors = cursor.getString(2)
                val about = cursor.getString(3)
                val date = cursor.getString(4)
                val movie = Movie(id, name, authors, about, date)
                movieList.add(movie)
            } while (cursor.moveToNext())
        }
        return movieList
    }

}