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
        Book(isbn = "9780132350884", title = "Clean Code", nbPages = 464, imageUrl = "https://covers.openlibrary.org/b/isbn/9780132350884-L.jpg"),
        Book(isbn = "9780201616224", title = "The Pragmatic Programmer", nbPages = 352, imageUrl = "https://covers.openlibrary.org/b/isbn/9780201616224-L.jpg"),
        Book(isbn = "9780201633610", title = "Design Patterns", nbPages = 395, imageUrl = "https://covers.openlibrary.org/b/isbn/9780201633610-L.jpg"),
        Book(isbn = "9780201485677", title = "Refactoring", nbPages = 461, imageUrl = "https://covers.openlibrary.org/b/isbn/9780201485677-L.jpg"),
        Book(isbn = "9780596007126", title = "Head First Design Patterns", nbPages = 694, imageUrl = "https://covers.openlibrary.org/b/isbn/9780596007126-L.jpg"),
        Book(isbn = "9780262033848", title = "Introduction to Algorithms", nbPages = 1312, imageUrl = "https://covers.openlibrary.org/b/isbn/9780262033848-L.jpg"),
        Book(isbn = "9780131103627", title = "The C Programming Language", nbPages = 272, imageUrl = "https://covers.openlibrary.org/b/isbn/9780131103627-L.jpg"),
        Book(isbn = "9781449356261", title = "Learning Python", nbPages = 1648, imageUrl = "https://covers.openlibrary.org/b/isbn/9781449356261-L.jpg"),
        Book(isbn = "9780131873256", title = "Database Systems: The Complete Book", nbPages = 1200, imageUrl = "https://covers.openlibrary.org/b/isbn/9780131873256-L.jpg"),
        Book(isbn = "9780134190540", title = "Android Programming: The Big Nerd Ranch Guide", nbPages = 624, imageUrl = "https://covers.openlibrary.org/b/isbn/9780134190540-L.jpg")
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

    /**
     * Bonus 3: Get books with more than 400 pages
     */
    fun getLongBooks(): List<Book> {
        return _booksList.filter { it.nbPages > 400 }
    }
}
