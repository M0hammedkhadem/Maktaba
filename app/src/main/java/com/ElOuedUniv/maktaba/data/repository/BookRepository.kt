package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book

interface BookRepository {
    
    fun getAllBooks(): List<Book>
    
    fun getBookByIsbn(isbn: String): Book?

    fun searchBooksByTitle(query: String): List<Book>

    fun getLongBooks(): List<Book>
}
