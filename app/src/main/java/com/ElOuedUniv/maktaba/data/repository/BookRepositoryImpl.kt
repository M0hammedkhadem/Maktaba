package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book

class BookRepositoryImpl : BookRepository {

    private val booksList = listOf(
        Book(
            isbn = "978-0-13-235088-4",
            title = "Clean Code",
            nbPages = 464,
            imageUrl = "https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg"
        ),
        Book(
            isbn = "978-0-13-595705-9",
            title = "The Pragmatic Programmer",
            nbPages = 352,
            imageUrl = "https://m.media-amazon.com/images/I/41H36uY84LL._SX396_BO1,204,203,200_.jpg"
        ),
        Book(
            isbn = "978-0-201-63361-0",
            title = "Design Patterns",
            nbPages = 395,
            imageUrl = "https://m.media-amazon.com/images/I/51szD9HC9pL._SX395_BO1,204,203,200_.jpg"
        ),
        Book(
            isbn = "978-0-13-475759-9",
            title = "Refactoring",
            nbPages = 448,
            imageUrl = "https://m.media-amazon.com/images/I/416H93T05tL._SX398_BO1,204,203,200_.jpg"
        ),
        Book(
            isbn = "978-1-492-04069-9",
            title = "Head First Design Patterns",
            nbPages = 672,
            imageUrl = "https://m.media-amazon.com/images/I/51r-78o9X0L._SX379_BO1,204,203,200_.jpg"
        ),
        Book(
            isbn = "978-0-262-03384-8",
            title = "Introduction to Algorithms",
            nbPages = 1312,
            imageUrl = "https://m.media-amazon.com/images/I/41T07nqZneL._SX412_BO1,204,203,200_.jpg"
        ),
        Book(
            isbn = "978-1-617-29329-0",
            title = "Kotlin in Action",
            nbPages = 360,
            imageUrl = "https://m.media-amazon.com/images/I/41-S6C86-5L._SX397_BO1,204,203,200_.jpg"
        ),
        Book(
            isbn = "978-0-13-468599-1",
            title = "Effective Java",
            nbPages = 416,
            imageUrl = "https://m.media-amazon.com/images/I/418VIs-6hTL._SX375_BO1,204,203,200_.jpg"
        ),
        Book(
            isbn = "978-0-321-12521-7",
            title = "Domain-Driven Design",
            nbPages = 560,
            imageUrl = "https://m.media-amazon.com/images/I/51hG6Z1mHlL._SX376_BO1,204,203,200_.jpg"
        ),
        Book(
            isbn = "978-0-13-708107-3",
            title = "The Clean Coder",
            nbPages = 256,
            imageUrl = "https://m.media-amazon.com/images/I/51f0qXy2FBL._SX376_BO1,204,203,200_.jpg"
        )
    )
    
    override fun getAllBooks(): List<Book> {
        return booksList
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return booksList.find { it.isbn == isbn }
    }

    fun searchBooksByTitle(query: String): List<Book> {
        return booksList.filter { it.title.contains(query, ignoreCase = true) }
    }

    fun getLongBooks(): List<Book> {
        return booksList.filter { it.nbPages > 400 }
    }
}
