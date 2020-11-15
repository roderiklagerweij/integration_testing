package com.rl.integrationtestingsample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rl.integrationtestingsample.R

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commitNow()
        }
    }
}