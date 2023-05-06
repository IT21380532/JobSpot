package com.example.firebaservrealdbk.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.JobSpot.R
import com.example.JobSpot.Review


class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val reviewList = ArrayList<Review>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.review_item,
            parent,false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = reviewList[position]

        holder.reviewId.text = currentitem.reviewId
        holder.reviewCode.text = currentitem.reviewCode
        holder.reviewRate.text = currentitem.reviewRate
        holder.reviewCom.text = currentitem.reviewCom

    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun updateReviewList(reviewList : List<Review>){

        this.reviewList.clear()
        this.reviewList.addAll(reviewList)
        notifyDataSetChanged()

    }

    class  MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val reviewId : TextView = itemView.findViewById(R.id.tvfirstName)
        val reviewCode : TextView = itemView.findViewById(R.id.tvlastName)
        val reviewRate : TextView = itemView.findViewById(R.id.tvage)
        val reviewCom : TextView = itemView.findViewById(R.id.tvid)

    }

}