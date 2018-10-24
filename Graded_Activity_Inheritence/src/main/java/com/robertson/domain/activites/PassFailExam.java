package com.robertson.domain.activites;

import com.robertson.domain.interfaces.IGrader;

/*
    Pass/Fail Exam class is a subclass of graded activity, it takes a Letter Grader and has a total possible
     points of 10
 */
public class PassFailExam extends GradedActivity
{
    public PassFailExam(IGrader grader){

        this.grader = grader;
        this.totalPointsPossible = 10;
        this.nameProperty = "Pass Fail Exam";
    }
}
