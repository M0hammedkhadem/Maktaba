package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book

/**
 * Repository for managing book data
 * This follows the Repository pattern to abstract data sources
 */
class BookRepository {

    private val booksList = listOf(
        Book(isbn = "978-0132350884", title = "Clean Code", nbPages = 464),
        Book(isbn = "978-0135957059", title = "The Pragmatic Programmer", nbPages = 352),
        Book(isbn = "978-0201633610", title = "Design Patterns", nbPages = 395),
        Book(isbn = "978-0134494166", title = "Clean Architecture", nbPages = 432),
        Book(isbn = "978-0134757599", title = "Refactoring", nbPages = 448),
        // Added 5 more books (Exercise 2)
        Book(isbn = "978-0262033848", title = "Introduction to Algorithms", nbPages = 1312),
        Book(isbn = "978-1617294549", title = "Kotlin in Action", nbPages = 450),
        Book(isbn = "978-1491974056", title = "Head First Android Development", nbPages = 786),
        Book(isbn = "978-1119299301", title = "Android Programming: The Big Nerd Ranch Guide", nbPages = 600),
        Book(isbn = "978-0321356680", title = "Effective Java", nbPages = 416)
    )

    /**
     * Get all books from the repository
     * @return List of all books
     */
    fun getAllBooks(): List<Book> {
        return booksList
    }

    /**
     * Get a book by ISBN
     * @param isbn The ISBN of the book to find
     * @return The book if found, null otherwise
     */
    fun getBookByIsbn(isbn: String): Book? {
        return booksList.find { it.isbn == isbn }
    }

    /**
     * Challenge: Search books by title
     */
    fun searchBooksByTitle(query: String): List<Book> {
        return booksList.filter { it.title.contains(query, ignoreCase = true) }
    }

    /**
     * Bonus 3: Filter books by page count (> 400 pages)
     */
    fun getLongBooks(): List<Book> {
        return booksList.filter { it.nbPages > 400 }
    }
}
