package com.example.speechtotext

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.speechtotext.databinding.ActivityMainBinding
import java.util.Locale

lateinit var binding: ActivityMainBinding
lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        activityResultLauncher  = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback {result ->
            val resultCode = result.resultCode
            var data = result.data
            if (resultCode== RESULT_OK && data != null)
            {var speakresult : ArrayList<String> = data
                ?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
            binding.text.text = speakresult[0]}

        })
        binding.mic.setOnClickListener {

        }
convertSpeech()
        }
    fun convertSpeech(){
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
        startActivityForResult(intent,1)
        activityResultLauncher.launch(intent)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode ==1 && resultCode == RESULT_OK && data != null){
            var speakresult : ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
            binding.text.text = speakresult[0]

        }
    }

    }
