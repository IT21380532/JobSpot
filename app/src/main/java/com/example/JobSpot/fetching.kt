package com.example.JobSpot

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class fetching : AppCompatActivity() {

    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var jobList: ArrayList<JobModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        jobRecyclerView = findViewById(R.id.rvJob)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        jobList = arrayListOf<JobModel>()

        getJobsData()

    }

    private fun getJobsData() {

        jobRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Job")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                jobList.clear()
                if (snapshot.exists()) {
                    for (jobSnap in snapshot.children) {
                        val jobData = jobSnap.getValue(JobModel::class.java)
                        jobList.add(jobData!!)
                    }
                    val mAdapter = JobAdapter(jobList)
                    jobRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : JobAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@fetching, JobDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("jobId", jobList[position].jobId)
                            intent.putExtra("jobCode", jobList[position].jobCode)
                            intent.putExtra("jobTitle", jobList[position].jobTile)
                            intent.putExtra("jobSalary", jobList[position].jobSalary)
                            intent.putExtra("jobDescription", jobList[position].jobDescription)
                            intent.putExtra("jobDate", jobList[position].jobDate)
                            startActivity(intent)
                        }
                    })
                    jobRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}