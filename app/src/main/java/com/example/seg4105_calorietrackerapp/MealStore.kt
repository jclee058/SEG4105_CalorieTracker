package com.example.seg4105_calorietrackerapp

/**
 * In-memory storage for meals.
 * - Survives Activity navigation and back stack.
 * - Clears automatically when app process is killed.
 */
object MealStore {

    data class MealEntry(
        val mealType: String,     // "breakfast" | "lunch" | "dinner"
        val imageName: String,
        val foodName: String,
        val calories: Int,
        val carbs: Int,
        val protein: Int,
        val fat: Int
    )

    private val meals = mutableMapOf<String, MealEntry>()

    fun saveMeal(entry: MealEntry) {
        meals[entry.mealType] = entry
    }

    fun getMeal(mealType: String): MealEntry? = meals[mealType]

    fun getAllMeals(): List<MealEntry> = meals.values.toList()

    fun mealsLoggedCount(): Int = meals.size

    fun totalCalories(): Int = meals.values.sumOf { it.calories }

    fun clearAll() {
        meals.clear()
    }
}

