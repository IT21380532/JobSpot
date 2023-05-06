package com.example.JobSpot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.JobSpot.R

class tasks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)


        val myListData = mutableListOf<mydata>()
        myListData.add(mydata(R.drawable.mm,"T0001","Rs.5,000/=","Mason","The one day..more"))
        myListData.add(mydata(R.drawable.cc,"T0002","Rs.3,000/=","Carpenter","The one day..more"))
        myListData.add(mydata(R.drawable.rrr,"T0003","Rs.4,000/=","Road Sweeper","The one day..more"))
        myListData.add(mydata(R.drawable.ce,"T0004","Rs.2,000/=","Cement Mixer","The one day..more"))
        myListData.add(mydata(R.drawable.gs,"T0005","Rs.8,000/=","Garden Sweeper","The one day..more"))
        myListData.add(mydata(R.drawable.ck,"T0006","Rs.6,000/=","Children Watch","The one day..more"))
        val myListView = findViewById<ListView>(R.id.my_list_view)
        myListView.adapter = MyCustomAdapter(this,myListData)
    }
}