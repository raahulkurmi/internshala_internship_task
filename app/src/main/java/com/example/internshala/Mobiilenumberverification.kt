package com.example.internshala

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.internshala.databinding.ActivityMobiilenumberverificationBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class Mobiilenumberverification : AppCompatActivity() {
    private  val binding by lazy {
        ActivityMobiilenumberverificationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener {
if(binding.editTextText2.text.toString().isNotEmpty()){
    if(binding.editTextText2.text.toString().length==10){
        var number=binding.editTextText2.text.trim().toString()
        number=binding.textView5.text.toString()+number
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)



    }else{
        Toast.makeText(this, "Please enter the correct  number", Toast.LENGTH_SHORT).show()
    }

}else{
    Toast.makeText(this, "Please enter the number", Toast.LENGTH_SHORT).show()
}


        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        Firebase.auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {


                    val user = task.result?.user
                } else {

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {

                    }

                }
            }
    }


   val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.

            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.


            if (e is FirebaseAuthInvalidCredentialsException) {
                Log.d("TAG","onverficationsent : ${e.toString()}")

            } else if (e is FirebaseTooManyRequestsException) {
                Log.d("TAG","onverficationsent : ${e.toString()}")
            }


        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            var number1=binding.editTextText2.text.trim().toString()
            number1=binding.textView5.text.toString()+number1
            val intent=Intent(this@Mobiilenumberverification,Verifyphonenumber::class.java)
            intent.putExtra("otp",verificationId)
            intent.putExtra("resendtoken",token)
            intent.putExtra("mobile",number1)
            Toast.makeText(this@Mobiilenumberverification, "Otp sent", Toast.LENGTH_SHORT).show()

            startActivity(intent )
        }
    }
}