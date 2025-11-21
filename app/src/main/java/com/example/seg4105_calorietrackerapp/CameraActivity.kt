package com.example.seg4105_calorietrackerapp

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

/**
 * CameraActivity shows a random test image as a "camera preview".
 **/
class CameraActivity : AppCompatActivity() {

    // UI views
    private lateinit var btnBack: TextView
    private lateinit var btnCapture: ImageButton
    private lateinit var btnSwitchCamera: ImageButton
    private lateinit var ivThumbnail: ImageView
    private lateinit var ivPreviewImage: ImageView
    private lateinit var tvResult: TextView
    private lateinit var tvCenterHint: TextView

    // Assets data
    private var testImages: List<String> = emptyList()
    private lateinit var analysisMap: Map<String, AnalysisResult>

    // Current image and lock state
    private var currentFileName: String? = null
    private var lockedNotFood = false

    private val mealResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { res ->
            if (res.resultCode != RESULT_OK || res.data == null) return@registerForActivityResult
            setResult(RESULT_OK, res.data)
            finish()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        // Bind views from XML
        btnBack = findViewById(R.id.btnBack)
        btnCapture = findViewById(R.id.btnCapture)
        btnSwitchCamera = findViewById(R.id.btnSwitchCamera)
        ivThumbnail = findViewById(R.id.ivThumbnail)
        ivPreviewImage = findViewById(R.id.ivPreviewImage)
        tvResult = findViewById(R.id.tvResult)
        tvCenterHint = findViewById(R.id.tvCenterHint)

        // Load test images + analysis JSON
        testImages = AssetUtils.listTestImages(this)
        analysisMap = AssetUtils.loadAnalysisMap(this)

        // Back arrow -> return to DayView
        btnBack.setOnClickListener { finish() }

        // Pick a random image when entering
        if (testImages.isNotEmpty()) {
            currentFileName = testImages[Random.nextInt(testImages.size)]
            showImageAndResult(currentFileName!!)
        }

        // "Capture" button logic
        btnCapture.setOnClickListener {
            if (lockedNotFood) return@setOnClickListener

            val fileName = currentFileName ?: return@setOnClickListener
            val result = analysisMap[fileName]

            // If not food: show hint and lock UI
            if (result == null || !result.isFood) {
                showCenterHint()
                lockedNotFood = true
                btnCapture.isEnabled = false
                btnSwitchCamera.isEnabled = false
                return@setOnClickListener
            }

            // Food detected -> go to MealResultActivity
            val mealType = intent.getStringExtra("mealType") ?: "Meal"
            val n = result.nutritionData
            val foodName = result.name ?: fileName.substringBeforeLast(".")

            val goResult = Intent(this, MealResultActivity::class.java).apply {
                putExtra("imageName", fileName)
                putExtra("mealType", mealType)
                putExtra("isFood", true)
                putExtra("foodName", foodName)
                putExtra("calories", n?.calories ?: 0)
                putExtra("carbs", n?.carbs ?: 0)
                putExtra("protein", n?.protein ?: 0)
                putExtra("fat", n?.fat ?: 0)
            }

            mealResultLauncher.launch(goResult)
        }

        // Placeholder for real camera switching later
        btnSwitchCamera.setOnClickListener {
            // TODO: implement CameraX switch
        }
    }

    // Display the selected image in center + thumbnail, and show analysis text
    private fun showImageAndResult(fileName: String) {
        val bitmap = AssetUtils.loadOneTestImage(this, fileName)

        ivPreviewImage.setImageBitmap(bitmap)
        ivThumbnail.setImageBitmap(bitmap)

        val result = analysisMap[fileName]
        if (result == null) {
            tvResult.text = "No analysis result for $fileName"
            return
        }

        val displayName = result.name ?: fileName

        tvResult.text =
            if (!result.isFood) {
                "$displayName ❌\nNot food."
            } else {
                val n = result.nutritionData
                if (n == null) {
                    "$displayName ✅\nFood detected, but no nutrition data."
                } else {
                    "$displayName ✅\n" +
                            "Calories: ${n.calories}\n" +
                            "Carbs: ${n.carbs} g\n" +
                            "Protein: ${n.protein} g\n" +
                            "Fat: ${n.fat} g"
                }
            }
    }
    // Show the big center hint text
    private fun showCenterHint() {
        tvCenterHint.visibility = View.VISIBLE
    }
}
