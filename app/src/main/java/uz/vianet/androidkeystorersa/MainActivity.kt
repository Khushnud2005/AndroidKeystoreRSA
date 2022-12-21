package uz.vianet.androidkeystorersa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.security.PrivateKey
import java.security.PublicKey

class MainActivity : AppCompatActivity() {
    private lateinit var publick: PublicKey
    private lateinit var privatek: PrivateKey
    lateinit var et_encrypt: EditText
    lateinit var et_result: EditText
    lateinit var encrypt: Button
    lateinit var decrypt: Button

    private val rsa = CryptoManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        rsa.createAsymmetricKeyPair()
        et_encrypt = findViewById(R.id.et_encrypt)
        et_result = findViewById(R.id.et_result)
        encrypt = findViewById(R.id.encrypt)
        decrypt = findViewById(R.id.decrypt)

        publick = rsa.getAsymmetricKeyPair()!!.public
        privatek = rsa.getAsymmetricKeyPair()!!.private


        encrypt.setOnClickListener {
            onEncryptStringbyRsa()
        }
        decrypt.setOnClickListener {
            onDecrytpStringByRsa()
        }
    }

    fun onEncryptStringbyRsa() {
        try {
            val input = et_encrypt.text.toString()
            var result = rsa.encrypt(input, publick)
            // var result = rsa.encrypt(input,publick)
            et_result.setText("$result")
        } catch (e: Exception) {

        }

    }

    fun onDecrytpStringByRsa() {

        try {
            val input = et_encrypt.text.toString()
            var result = rsa.decrypt(input, privatek)
            et_result.setText("$result")
        } catch (e: Exception) {

        }


    }
}