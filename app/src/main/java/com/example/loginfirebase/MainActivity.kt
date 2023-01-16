package com.example.loginfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    //Variable de ViewBinding
    private lateinit var binding: ActivityMainBinding
    //Variables de Firebase para la autentificacion
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //se inicializa la variable de Firebase
        firebaseAuth = Firebase.auth

        //Metodo Listener cuando el usuario presiona el boton
        binding.btnIngresar.setOnClickListener(){
            signIn(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }
    }

    private fun signIn(email: String, password: String){

        //metodo de Firebase para la autenticacion
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                //se valida si los parametros son correctos
                if(task.isSuccessful){
                //Creamos una variable Intent para cambiar de Activity
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
    }
}