package com.ElOuedUniv.maktaba.presentation.book

import com.ElOuedUniv.maktaba.data.model.Book

/**
 * UI State for the Book list screen.
 */
data class BookUiState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isAddingBook: Boolean = false // State for the FAB form
) {
    // Bonus 1: Book counter
    val bookCount: Int get() = books.size
    
    // Bonus 2: Total pages calculation
    val totalPages: Int get() = books.sumOf { it.nbPages }
}
