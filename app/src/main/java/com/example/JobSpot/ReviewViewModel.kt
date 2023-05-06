package com.example.JobSpot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReviewViewModel:ViewModel() {

    private val repository : ReviewRepository
    private val _allReviews = MutableLiveData<List<Review>>()
    val allReviews : LiveData<List<Review>> = _allReviews

    init {
        repository = ReviewRepository().getInstance()
        repository.loadReviews(_allReviews)
    }
}