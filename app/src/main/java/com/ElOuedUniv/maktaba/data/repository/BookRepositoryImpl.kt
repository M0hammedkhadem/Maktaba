package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl @Inject constructor() : BookRepository {

    private val _booksList = mutableListOf(
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

    private val booksFlow = MutableSharedFlow<List<Book>>(replay = 1).apply {
        tryEmit(_booksList.toList())
    }
    
    override fun getAllBooks(): Flow<List<Book>> = flow {
        delay(1000) // Simulate delay
        emitAll(booksFlow)
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return _booksList.find { it.isbn == isbn }
    }

    override fun addBook(book: Book) {
        _booksList.add(book)
        booksFlow.tryEmit(_booksList.toList())
    }

    override fun searchBooksByTitle(query: String): Flow<List<Book>> {
        return booksFlow.map { list ->
            list.filter { it.title.contains(query, ignoreCase = true) }
        }
    }

    override fun getLongBooks(): Flow<List<Book>> {
        return booksFlow.map { list ->
            list.filter { it.nbPages > 400 }
        }
    }
}
