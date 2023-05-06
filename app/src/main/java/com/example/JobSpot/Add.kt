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
import android.app.DatePickerDialog
import android.widget.Spinner
import com.google.firebase.database.*
import java.util.*


class Add : AppCompatActivity() {
    private lateinit var job_code: EditText
    private lateinit var job_title: EditText
    private lateinit var job_salary: EditText
    private lateinit var job_des: EditText
    private lateinit var job_date: EditText


    private lateinit var add_btn: Button
    private lateinit var back_btn: Button


    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        job_code = findViewById(R.id.job_code)
        job_title = findViewById(R.id.job_title)
        job_salary = findViewById(R.id.job_salary)
        job_des = findViewById(R.id.job_des)
        job_date = findViewById(R.id.job_date)

        add_btn = findViewById(R.id.add_btn)
        back_btn = findViewById(R.id.back_btn)

        dbRef = FirebaseDatabase.getInstance().getReference("Job")

        back_btn.setOnClickListener {
            val intent = Intent(this, fetching::class.java)
            startActivity(intent)
        }
        job_date.setOnClickListener {
            showDatePicker()
        }
        //Save data

        // Save data
        add_btn.setOnClickListener {
            saveJobData()

        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDatePicker() {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)
        val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            job_date.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
        }, year, month, day)
        datePicker.show()
    }


    private fun saveJobData() {
        val jobCode = job_code.text.toString()
        val jobTile = job_title.text.toString()
        val jobSalary = job_salary.text.toString()
        val jobDescription = job_des.text.toString()
        val jobDate = job_date.text.toString()

        if (jobCode.isEmpty()) {
            job_code.error = "Please Enter Job/Task Code"
        }

        if (jobTile.isEmpty()) {
            job_title.error = "Please Enter Job/Task Title"
        }

        if (jobSalary.isEmpty()) {
            job_salary.error = "Please Enter Job/Task Salary"
        } else if (jobSalary.toIntOrNull() == null) {
            job_salary.error = "Please Enter a Valid Number for Job/Task Salary"
            return
        }

        if (jobDescription.isEmpty()) {
            job_des.error = "Please Enter Job/Task Details"
        }

        if (jobDate.isEmpty()) {
            job_date.error = "Please Enter Job/Task Publish Date"
        }

        dbRef.orderByChild("jobCode").equalTo(jobCode).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    job_code.error = "Job/Task Code Already Exists"
                    return
                } else {
                    val jobId = dbRef.push().key!!

                    val job = JobModel(jobId, jobCode, jobTile, jobSalary, jobDescription, jobDate)

                    dbRef.child(jobId).setValue(job)
                        .addOnCompleteListener {
                            Toast.makeText(this@Add, "Data Added Successfully", Toast.LENGTH_LONG)
                                .show()

                            job_code.text.clear()
                            job_title.text.clear()
                            job_salary.text.clear()
                            job_des.text.clear()
                            job_date.text.clear()

                        }.addOnFailureListener { err ->
                            Toast.makeText(this@Add, "Error ${err.message}", Toast.LENGTH_LONG)
                                .show()
                        }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@Add, "Error ${databaseError.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}



