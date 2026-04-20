package com.ElOuedUniv.maktaba.domain.usecase

import com.ElOuedUniv.maktaba.data.model.Category
import com.ElOuedUniv.maktaba.data.repository.CategoryRepository

/**
 * Use case to get the list of categories
 * TP2 - Exercise 3
 */
class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    /**
     * Bonus 3: Sort categories alphabetically by name
     */
    operator fun invoke(): List<Category> {
        return categoryRepository.getAllCategories().sortedBy { it.name }
    }
}
