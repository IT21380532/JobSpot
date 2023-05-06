package com.example.JobSpot

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class ReviewRepository {

    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Review")

    @Volatile private var INSTANCE :ReviewRepository ? = null

    fun getInstance(): ReviewRepository{

        return  INSTANCE ?: synchronized(this){
            val instance = ReviewRepository()

            INSTANCE = instance
            instance
        }
    }

    fun loadReviews(reviewList : MutableLiveData<List<Review>>){
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {

                    val _reviewList : List<Review> = snapshot.children.map {  dataSnapshot ->

                        dataSnapshot.getValue(Review::class.java)!!
                    }

                    reviewList.postValue(_reviewList)
                }catch (e : Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}