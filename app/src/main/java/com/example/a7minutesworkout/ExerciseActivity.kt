package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import java.util.Locale

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding: ActivityExerciseBinding? = null

//    first gridlayout
    private var restTimer : CountDownTimer? = null
    private var restProgress = 0
//    second frame layout
    private var exerciseTimer : CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var tts:TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            tts = TextToSpeech(this,this)

            exerciseList = Constants.defaultExerciseList()
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        setUpRestView()
    }
        private fun setUpRestView(){
            binding?.flProgressBar?.visibility = View.VISIBLE
            binding?.tvTitle?.visibility =  View.VISIBLE
            binding?.tvExerciseName?.visibility = View.INVISIBLE
            binding?.flExerciseView?.visibility = View.INVISIBLE
            binding?.ivImage?.visibility = View.INVISIBLE
            binding?.tvUpcomingLabel?.visibility = View.VISIBLE
            binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE

            if(restTimer != null){
                restTimer?.cancel()
                restProgress =0
            }
            binding?.tvUpcomingExerciseName?.text = exerciseList!![currentExercisePosition + 1].getName()
            setRestProgressBar()
        }
    private fun setUpExerciseView(){
        binding?.flProgressBar?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility =  View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.tvUpcomingLabel?.visibility = View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE

        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        speakOut(exerciseList !! [currentExercisePosition].getName())

        binding?.ivImage?.setImageResource(exerciseList !![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList !! [currentExercisePosition].getName()

        setExerciseProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress

        restTimer = object :CountDownTimer
            (10000,
            1000){
            override fun onTick(millisUntilFinished: Long) {
               restProgress++
                binding?.progressBar?.progress =10- restProgress
                binding?.tvTimer?.text = (10- restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
              setUpExerciseView()
            }

        }.start()

    }

    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object :CountDownTimer
            (3000,
            100){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress =30- exerciseProgress
                binding?.tvTimerExercise?.text = (30- exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition <exerciseList?.size!! -1){
                    setUpRestView()
                }else {
                    finish()
                    val intent = Intent(this@ExerciseActivity,
                        FinishActivity::class.java)
                    startActivity(intent)
                }
            }

        }.start()

    }


    override fun onDestroy(){
        super.onDestroy()

        if(restTimer != null){
            restTimer?.cancel()
            restProgress =0
        }
        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
//        shutting down text to speech when activity is destroyed!
        if(tts != null){
            tts !!.stop()
            tts !!.shutdown()
        }

        binding =null
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The language specified is not supported!")
            }
        }else{ Log.e("TTS","Initialization is Filled!")}
    }

    private fun speakOut(text:String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")
    }
}