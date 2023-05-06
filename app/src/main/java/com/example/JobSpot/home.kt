

package com.example.JobSpot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.JobSpot.R


class home : AppCompatActivity() {

    private lateinit var jo_btn: Button
    private lateinit var task_btn: Button
    private lateinit var j_btn: Button
    private lateinit var review_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        jo_btn = findViewById(R.id.jo_btn)
        task_btn = findViewById(R.id.task_btn)
        j_btn = findViewById(R.id.j_btn)
        review_btn = findViewById(R.id.review_btn)

        jo_btn.setOnClickListener {
            val intent = Intent(this,fetching::class.java)
            startActivity(intent)
        }

        task_btn.setOnClickListener {
            val intent = Intent(this,fetching::class.java)
            startActivity(intent)
        }

        j_btn.setOnClickListener {
            val intent = Intent(this,Add::class.java)
            startActivity(intent)
        }

        review_btn.setOnClickListener {
            val intent = Intent(this,reviewsform::class.java)
            startActivity(intent)
        }
    }
}