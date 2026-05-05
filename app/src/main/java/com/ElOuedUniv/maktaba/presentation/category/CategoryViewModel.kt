package com.ElOuedUniv.maktaba.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.model.Category
import com.ElOuedUniv.maktaba.domain.usecase.GetCategoriesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    // Single UI State (Exercise 1 pattern)
    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    // One-time UI Events (Exercise 3)
    private val _uiEvent = MutableSharedFlow<CategoryUiEvent>()
    val uiEvent: SharedFlow<CategoryUiEvent> = _uiEvent.asSharedFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getCategoriesUseCase()
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
                    _uiEvent.emit(CategoryUiEvent.ShowSnackbar("Error loading categories: ${e.message}"))
                }
                .collect { categoryList ->
                    _uiState.update { it.copy(isLoading = false, categories = categoryList) }
                }
        }
    }

    /**
     * Exercise 3: Handle UI Actions
     */
    fun onAction(action: CategoryUiAction) {
        when (action) {
            CategoryUiAction.RefreshCategories -> refreshCategories()
            CategoryUiAction.OnBackClick -> {
                viewModelScope.launch {
                    _uiEvent.emit(CategoryUiEvent.NavigateBack)
                }
            }
        }
    }

    fun refreshCategories() {
        loadCategories()
    }

    /**
     * Bonus 2: Get category by ID
     */
    fun getCategoryById(id: String): Category? {
        return uiState.value.categories.find { it.id == id }
    }
}
