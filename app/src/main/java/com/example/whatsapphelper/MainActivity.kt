package com.example.whatsapphelper

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var phno : String = ""

        if(intent.action == Intent.ACTION_PROCESS_TEXT){
            phno = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()

        }
        phno = checkAndEdit(phno)
        if(phno == "*"){
            Toast.makeText(this ,"Check the Entered Number" ,Toast.LENGTH_SHORT).show()
        }
        else{
            launchWhatsapp(phno)
        }

        finish()

    }

    private fun launchWhatsapp(phno: String) {
        var intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        intent.data = Uri.parse("https://wa.me/$phno")
        startActivity(intent)
        if(packageManager.resolveActivity(intent,0) != null){
            startActivity(intent)
        }else{
            Toast.makeText(this, "Whatsapp Not Found !" , Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkAndEdit(phno: String): String {
        if(phno.length == 10){
            if(phno.isDigitsOnly()){
                return "91"+ phno
            }
            else{
                return "*"
            }
        }
        if(phno.length == 13){
            if(phno.substring(0,3) == "+91"){
                return phno.substring(1)
            }
        }
        return "*"
    }
}