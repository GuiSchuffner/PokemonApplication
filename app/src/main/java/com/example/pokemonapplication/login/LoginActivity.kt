package com.example.pokemonapplication.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapplication.databinding.ActivityLoginBinding
import com.example.pokemonapplication.home.searchpokemon.view.HomeActivity
import com.firebase.ui.auth.AuthUI

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private var launchLoginActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        else{
            Log.i("aaaa", "Sign in unsuccessful ${result?.resultCode}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginButton.setOnClickListener {
            launchLoginFlow()
        }
    }

    private fun launchLoginFlow(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
        )
        launchLoginActivity.launch(
            AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        )
    }
}
