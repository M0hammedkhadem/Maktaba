package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book

class BookRepositoryImpl : BookRepository {

    private val booksList = listOf(
        Book(isbn = "978-0132350884", title = "Clean Code", nbPages = 464),
        Book(isbn = "978-0135957059", title = "The Pragmatic Programmer", nbPages = 352),
        Book(isbn = "978-0201633610", title = "Design Patterns", nbPages = 395),
        Book(isbn = "978-0201485677", title = "Refactoring", nbPages = 431),
        Book(isbn = "978-0596009205", title = "Head First Design Patterns", nbPages = 688),
        Book(isbn = "978-0262046305", title = "Introduction to Algorithms (CLRS)", nbPages = 1312),
        Book(isbn = "978-0131103627", title = "The C Programming Language", nbPages = 272),
        Book(isbn = "978-0735619678", title = "Code Complete", nbPages = 960),
        Book(isbn = "978-1260084504", title = "Database System Concepts", nbPages = 1376),
        Book(isbn = "978-1491974056", title = "Head First Android Development", nbPages = 786),
    )
    
    override fun getAllBooks(): List<Book> {
        return booksList
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return booksList.find { it.isbn == isbn }
    }

    override fun searchBooksByTitle(query: String): List<Book> {
        return booksList.filter { it.title.contains(query, ignoreCase = true) }
    }

    override fun getLongBooks(): List<Book> {
        return booksList.filter { it.nbPages > 400 }
    }
}
