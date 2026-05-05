package com.ElOuedUniv.maktaba.presentation.category

/**
 * One-time UI events (System events) for the Category screen.
 */
sealed interface CategoryUiEvent {
    data class ShowSnackbar(val message: String) : CategoryUiEvent
    object NavigateBack : CategoryUiEvent
}
