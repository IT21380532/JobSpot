package com.example.JobSpot

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.EditTextPreference
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.JobSpot.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.app.DatePickerDialog
import android.widget.Spinner
import java.util.*

class reviewsform : AppCompatActivity() {
    private lateinit var review_code: EditText
    private lateinit var review_rate: EditText
    private lateinit var review_com: EditText

    private lateinit var re_btn: Button
    private lateinit var ko_btn: Button



    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviewsform)




            review_code = findViewById(R.id.review_code)
            review_rate = findViewById(R.id.review_rate)
            review_com = findViewById(R.id.review_com)

            re_btn = findViewById(R.id.re_btn)
            ko_btn = findViewById(R.id.ko_btn)


            dbRef = FirebaseDatabase.getInstance().getReference("Review")
         ko_btn.setOnClickListener {
            val intent = Intent(this,MainActivity3::class.java)
            startActivity(intent)
        }

            //Save data

            // Save data
            re_btn.setOnClickListener {
                saveReviewData()

            }
        }



        private fun saveReviewData() {
            val reviewCode = review_code.text.toString()
            val reviewRate = review_rate.text.toString()
            val reviewCom = review_com.text.toString()

            if (reviewCode.isEmpty()) {
                review_code.error = "Please Enter Review Code"
            }

            if (reviewRate.isEmpty()) {
                review_rate.error = "Please Enter Customer Rating"
            }

            if (reviewCom.isEmpty()) {
                review_com.error = "Please Enter Customer Feedback "
            }


            val reviewId = dbRef.push().key!!

            val review = ReviewModel(reviewId,reviewCode,reviewRate,reviewCom)

            dbRef.child(reviewId).setValue(review)
                .addOnCompleteListener {
                    Toast.makeText(this,"Data Added Successfully",Toast.LENGTH_LONG).show()

                    review_code.text.clear()
                    review_rate.text.clear()
                    review_com.text.clear()

                }.addOnFailureListener { err->
                    Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
                }
        }
    }




