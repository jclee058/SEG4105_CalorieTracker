package com.example.seg4105_calorietrackerapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.json.JSONObject

object AssetUtils {
    fun listTestImages(context: Context): List<String> {
        return context.assets.list("test_image")
            ?.filter { it.endsWith(".jpg") || it.endsWith(".jpeg") || it.endsWith(".png") }
            ?: emptyList()
    }

    fun loadOneTestImage(context: Context, fileName: String): Bitmap {
        context.assets.open("test_image/$fileName").use { input ->
            return BitmapFactory.decodeStream(input)
        }
    }

    fun loadAnalysisMap(context: Context): Map<String, AnalysisResult> {
        val jsonStr = context.assets.open("analysis_results.json")
            .bufferedReader()
            .use { it.readText() }

        val root = JSONObject(jsonStr)
        val map = mutableMapOf<String, AnalysisResult>()

        val keys = root.keys()
        while (keys.hasNext()) {
            val file = keys.next()
            val obj = root.getJSONObject(file)

            val isFood = obj.optBoolean("Food", false)
            val foodName = obj.optString("name", null)  // optional "name" field

            val nutritionObj = obj.optJSONObject("nutritionData")
            val nutritionData =
                if (nutritionObj != null) {
                    NutritionData(
                        calories = nutritionObj.optInt("calories", 0),
                        carbs = nutritionObj.optInt("carbs", 0),
                        protein = nutritionObj.optInt("protein", 0),
                        fat = nutritionObj.optInt("fat", 0)
                    )
                } else null

            map[file] = AnalysisResult(
                isFood = isFood,
                name = foodName,
                nutritionData = nutritionData
            )
        }

        return map
    }
}
