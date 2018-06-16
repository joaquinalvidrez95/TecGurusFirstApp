package com.joaquinalvidrez.tecgurusfirstapp.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.joaquinalvidrez.tecgurusfirstapp.R
import kotlinx.android.synthetic.main.activity_content_provider.*

const val REQUEST_CALL_PERMISSION = 12

class ContentProviderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        button_content_provider_call.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                call(edit_text_phone.text.toString())

            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.CALL_PHONE),
                        REQUEST_CALL_PERMISSION)
            }
        }

        button_send_sms.setOnClickListener {
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:${edit_text_phone.text}")).apply {
                putExtra("sms_body", edit_text_sms_body.text.toString())
            })
        }

        button_send_email.setOnClickListener {

            val i = Intent(Intent.ACTION_SEND, Uri.parse("mailto:")).apply {
                putExtra(Intent.EXTRA_EMAIL, arrayOf("joaquinalvidrez95@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Email Test")
                putExtra(Intent.EXTRA_TEXT, "dlfkdlkdlkdlkllfklfldlkdkf")
                type = "message/rfc822"

            }
            startActivity(Intent.createChooser(i, "Elige man"))
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            call(edit_text_phone.text.toString())
        }
    }

    private fun call(number: String) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$number")))
        }
    }
}
