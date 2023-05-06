package com.example.JobSpot

 class Job {
    private var JobID:String = ""
    private var JobTitle:String= ""
    private var jobSalary: String=""
    private var jobDescription: String = ""
    private var jobDate: String=""

    constructor()

    fun setJobID(JobID:String){
        this.JobID = JobID
    }

    fun getJobID():String{
        return JobID
    }

    fun setJobTitle(JobTitle:String){
        this.JobTitle = JobTitle
    }

    fun getJobTitle():String{
        return JobTitle
    }

    fun setJobDate(jobDate:String){
        this.jobDate = jobDate
    }

    fun getJobDate():String{
        return jobDate
    }

    fun setJobDescription(jobDescription:String){
        this.jobDescription = jobDescription
    }

    fun getJobDescription():String{
        return jobDescription
    }

    fun setJobSalary(jobSalary:String){
        this.jobSalary = jobSalary
    }

    fun getJobSalary():String{
        return jobSalary
    }


}
