package com.robertson.domain.activites;

import com.robertson.domain.interfaces.IGrader;

/*
    Essay class is a subclass of graded activity, it takes a Letter Grader and has a total possible points of 100
 */
public class Essay extends GradedActivity
{
    public Essay(IGrader grader){
        super(grader);
        this.grader = grader;
        this.totalPointsPossible = 100;
        this.nameProperty = "Essay";

    }
}
