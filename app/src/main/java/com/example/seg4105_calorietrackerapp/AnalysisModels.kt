package com.example.seg4105_calorietrackerapp

/**
 * Simple data models for parsing and storing nutrition analysis results.
 * - NutritionData holds macro + calorie numbers.
 * - AnalysisResult represents one image's classification and optional nutrition info.
 */
data class NutritionData(
    val calories: Int = 0,
    val carbs: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0
)

data class AnalysisResult(
    val isFood: Boolean = false,
    val name: String? = null,
    val nutritionData: NutritionData? = null
)
