package com.ElOuedUniv.maktaba.presentation.category

/**
 * UI Actions representing user interactions on the Category screen.
 */
sealed interface CategoryUiAction {
    object RefreshCategories : CategoryUiAction
    object OnBackClick : CategoryUiAction
}
