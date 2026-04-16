package com.ElOuedUniv.maktaba.presentation.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.domain.usecase.GetBooksUseCase
import com.ElOuedUniv.maktaba.domain.usecase.AddBookUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val addBookUseCase: AddBookUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<BookUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getBooksUseCase()
                .catch { error ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
                    _uiEvent.emit(BookUiEvent.ShowSnackbar(error.message ?: "Unknown error"))
                }
                .collect { bookList ->
                    val totalPages = bookList.sumOf { it.nbPages } // TP1 Bonus 2
                    _uiState.update { it.copy(
                        books = bookList, 
                        isLoading = false,
                        totalPages = totalPages
                    ) }
                }
        }
    }

    fun onAction(action: BookUiAction) {
        when (action) {
            BookUiAction.RefreshBooks -> loadBooks()
            BookUiAction.OnAddBookClick -> {
                _uiState.update { it.copy(isAddingBook = true) }
            }
            BookUiAction.OnDismissAddBook -> {
                _uiState.update { it.copy(isAddingBook = false) }
            }
            is BookUiAction.OnAddBookConfirm -> {
                viewModelScope.launch {
                    val newBook = Book(isbn = action.isbn, title = action.title, nbPages = action.nbPages)
                    addBookUseCase(newBook)
                    _uiState.update { it.copy(isAddingBook = false) }
                    _uiEvent.emit(BookUiEvent.ShowSnackbar("Book added successfully"))
                }
            }
        }
    }
}
