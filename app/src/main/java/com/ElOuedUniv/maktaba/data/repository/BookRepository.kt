package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    
    fun getAllBooks(): Flow<List<Book>>
    
    fun getBookByIsbn(isbn: String): Book?

    fun addBook(book: Book)

    /**
     * Exercise 4.2 Challenge: Search books by title
     */
    fun searchBooksByTitle(query: String): List<Book>

    /**
     * Bonus Exercise 3: Filter books by page count (> 400)
     */
    fun getLongBooks(): List<Book>
}
