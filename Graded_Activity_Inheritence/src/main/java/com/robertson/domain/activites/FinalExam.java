package com.robertson.domain.activites;

import com.robertson.domain.interfaces.IGrader;

/*
    Final Exam class is a subclass of graded activity, it takes a Letter Grader and has a total possible points of 50
 */
public class FinalExam extends GradedActivity
{
   public FinalExam(IGrader grader){
       this.grader = grader;
       this.totalPointsPossible = 50;
       this.nameProperty = "Final Exam";
   }
}
