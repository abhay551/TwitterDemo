package com.abhay.twitterdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.abhay.twitterdemo.ui.login.LoginFragment


class MainActivity : AppCompatActivity() {
    private var toolbar_title: TextView? = null
    private lateinit var context: Context
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        context = this
        init()
    }

    private fun init() {
        toolbar_title = findViewById(R.id.toolbar_title)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    fun setToolbarTitle(toolbarTitle: String) {
        toolbar_title!!.text = toolbarTitle
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val loginFragment = navHostFragment.childFragmentManager.fragments[0];
        if (loginFragment is LoginFragment) {
            loginFragment.onActivityResult(requestCode, resultCode, data)
        }
    }
}