package com.example.JobSpot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.quicksettings.Tile
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class JobDetailsActivity : AppCompatActivity() {

    private lateinit var tvJobId: TextView
    private lateinit var tvJobCode: TextView
    private lateinit var tvJobTile: TextView
    private lateinit var tvJobSalary: TextView
    private lateinit var tvJobDescription: TextView
    private lateinit var tvJobDate: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var builder: AlertDialog.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_details)

        btnDelete = findViewById(R.id.btnDelete)
        builder = AlertDialog.Builder(this)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("jobId").toString(),
                intent.getStringExtra("jobCode").toString()


            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("jobId").toString()
            )

        }


    }

    private fun deleteRecord(id: String) {
        builder.setTitle("Delete Job")
            .setMessage("Are you sure you want to delete this job/task?")
            .setPositiveButton("Yes") { _, _ ->
                val dbRef = FirebaseDatabase.getInstance().getReference("Job").child(id)
                val mTask = dbRef.removeValue()

                mTask.addOnSuccessListener {
                    Toast.makeText(this,"Job/Task Data Deleted",Toast.LENGTH_LONG).show()

                    val intent = Intent(this, fetching::class.java)
                    finish()
                    startActivity(intent)
                }.addOnFailureListener {  error ->
                    Toast.makeText(this,"Deleting Err ${error.message}",Toast.LENGTH_LONG).show()

                }
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create().show()
    }


    private fun initView() {
        tvJobId = findViewById(R.id.tvJobId)
        tvJobCode = findViewById(R.id.tvJobCode)
        tvJobTile = findViewById(R.id.tvJobTitle)
        tvJobSalary = findViewById(R.id.tvJobSalary)
        tvJobDescription = findViewById(R.id.tvJobDescription)
        tvJobDate = findViewById(R.id.tvJobDate)


        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvJobId.text = intent.getStringExtra("jobId")
        tvJobCode.text = intent.getStringExtra("jobCode")
        tvJobTile.text = intent.getStringExtra("jobTitle")
        tvJobSalary.text = intent.getStringExtra("jobSalary")
        tvJobDescription.text = intent.getStringExtra("jobDescription")
        tvJobDate.text = intent.getStringExtra("jobDate")


    }

    private fun openUpdateDialog(
        jobId: String,
        jobCode: String
    ){
       val  mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_main,null)

        mDialog.setView(mDialogView)

        val job_code = mDialogView.findViewById<EditText>(R.id.job_code)
        val job_title = mDialogView.findViewById<EditText>(R.id.job_title)
        val job_salary = mDialogView.findViewById<EditText>(R.id.job_salary)
        val job_description = mDialogView.findViewById<EditText>(R.id.job_description)
        val job_date = mDialogView.findViewById<EditText>(R.id.job_date)
        val update_btn = mDialogView.findViewById<Button>(R.id.update_btn)

        job_code.setText(intent.getStringExtra("jobCode").toString())
        job_title.setText(intent.getStringExtra("jobTitle").toString())
        job_salary.setText(intent.getStringExtra("jobSalary").toString())
        job_description.setText(intent.getStringExtra("jobDescription").toString())
        job_date.setText(intent.getStringExtra("jobDate").toString())

        mDialog.setTitle("Updating $jobCode Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        update_btn.setOnClickListener {

            updateJobData(
                jobId,
                job_code.text.toString(),
                job_title.text.toString(),
                job_salary.text.toString(),
                job_description.text.toString(),
                job_date.text.toString()
            )
            Toast.makeText(applicationContext,"Job/Task Data Updated", Toast.LENGTH_LONG).show()


            //we are setting updated data in to text view

            tvJobCode.text = job_code.text.toString()
            tvJobTile.text = job_title.text.toString()
            tvJobSalary.text = job_salary.text.toString()
            tvJobDescription.text = job_description.text.toString()
            tvJobDate.text =  job_date.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updateJobData(
        id:String,
        code:String,
        title:String,
        salary:String,
        description:String,
        date:String

    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Job").child(id)
        val jobInfo = JobModel(id,code,title,salary,description,date )
        dbRef.setValue(jobInfo)
    }
}


