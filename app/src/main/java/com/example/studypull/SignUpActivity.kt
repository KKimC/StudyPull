package com.example.studypull

import com.example.studypull.navigation.model.UserD
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.studypull.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignUpBinding
    //인증 객체
    lateinit var auth : FirebaseAuth

    private lateinit var dbRef :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //인증 객체 초기화
        auth = Firebase.auth

        dbRef = Firebase.database.reference

        binding.signUpButton.setOnClickListener {
            val name = binding.nameEdittext.text.toString().trim()
            val email = binding.emailEdittext.text.toString().trim()
            val password = binding.passwordEdittext.text.toString().trim()

            signUp(name,email,password)
        }
    }

    private fun signUp(name:String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"회원가입 성공",Toast.LENGTH_SHORT).show()
                    val intent : Intent = Intent(this@SignUpActivity,MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"회원가입 실패",Toast.LENGTH_SHORT).show()
                    Log.d("SignUp","Error : ${task.exception}")
                }
            }
    }
    private fun addUserToDatabase(name: String,email: String,uId: String){
        dbRef.child("user").child(uId).setValue(UserD(name,email,uId))
    }
}