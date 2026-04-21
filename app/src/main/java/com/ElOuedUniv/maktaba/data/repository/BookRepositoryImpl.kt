package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {

    private val _booksList = mutableListOf(
        Book(isbn = "978-0-13-235088-4", title = "Clean Code", nbPages = 464),
        Book(isbn = "978-0-13-595705-9", title = "The Pragmatic Programmer", nbPages = 352),
        Book(isbn = "978-0-201-63361-0", title = "Design Patterns", nbPages = 395),
        Book(isbn = "978-0-13-475759-9", title = "Refactoring", nbPages = 448),
        Book(isbn = "978-1-492-04069-9", title = "Head First Design Patterns", nbPages = 672),
        Book(isbn = "978-0-262-03384-8", title = "Introduction to Algorithms", nbPages = 1312),
        Book(isbn = "978-1-617-29329-0", title = "Kotlin in Action", nbPages = 360),
        Book(isbn = "978-0-13-468599-1", title = "Effective Java", nbPages = 416),
        Book(isbn = "978-0-321-12521-7", title = "Domain-Driven Design", nbPages = 560),
        Book(isbn = "978-0-13-708107-3", title = "The Clean Coder", nbPages = 256)
    )

    private val booksFlow = MutableSharedFlow<List<Book>>(replay = 1).apply {
        tryEmit(_booksList.toList())
    }
    
    override fun getAllBooks(): Flow<List<Book>> = flow {
        delay(2000) // Simulate delay
        emitAll(booksFlow)
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return _booksList.find { it.isbn == isbn }
    }

    override fun addBook(book: Book) {
        _booksList.add(book)
        booksFlow.tryEmit(_booksList.toList())
    }

    override fun searchBooksByTitle(query: String): List<Book> {
        return _booksList.filter { it.title.contains(query, ignoreCase = true) }
    }

    override fun getLongBooks(): List<Book> {
        return _booksList.filter { it.nbPages > 400 }
    }
}
