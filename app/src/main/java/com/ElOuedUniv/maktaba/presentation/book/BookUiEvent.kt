package com.ElOuedUniv.maktaba.presentation.book

/**
 * One-time UI events (System events) for the Book screen.
 * Used for effects like showing snackbars, navigation, etc.
 */
sealed interface BookUiEvent {
    data class ShowSnackbar(val message: String) : BookUiEvent
    data class NavigateToBookDetail(val isbn: String) : BookUiEvent
    object NavigateToAddBook : BookUiEvent
}
