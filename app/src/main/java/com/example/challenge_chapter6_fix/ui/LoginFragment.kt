package com.example.challenge_chapter6_fix.ui

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challenge_chapter6_fix.R
import com.example.challenge_chapter6_fix.ViewModelFactory
import com.example.challenge_chapter6_fix.data.DataUserManager
import com.example.challenge_chapter6_fix.databinding.FragmentLoginBinding
import com.example.challenge_chapter6_fix.viewModel.UserViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private lateinit var pref: DataUserManager
    private lateinit var analytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        analytics = Firebase.analytics
        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cekLogin()
        var username = ""
        var password = ""

        binding.login.setOnClickListener{
            viewModel.getDataUsername().observe(viewLifecycleOwner){
                username = it.toString()
            }
            viewModel.getDataPassword().observe(viewLifecycleOwner){
                password = it.toString()
            }
            val _username = binding.username.text.toString()
            val _password = binding.password.text.toString()

            if(username == _username && password == _password){
                viewModel.setIsLogin(true)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            else {
                Toast.makeText(
                    requireContext(),
                    "The username or password is incorrect!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        val signInLauncher = registerForActivityResult(
            FirebaseAuthUIActivityResultContract()
        ) { res ->
            onSignInResult(res)
        }

        binding.btnLogin.setOnClickListener(){
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.PhoneBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.TwitterBuilder().build())

            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signInIntent)
        }
    }
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    private fun cekLogin() {
        viewModel.getIsLogin().observe(viewLifecycleOwner){
            Handler(Looper.myLooper()!!).postDelayed({
                if(it == true)
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            },1000)
        }
    }
}