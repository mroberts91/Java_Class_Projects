package com.robertson.domain.activites;

import com.robertson.domain.enums.Grade;
import com.robertson.domain.interfaces.IReportable;
import com.robertson.domain.interfaces.IGrader;

/*
    Graded Activity is the base class for educational course grades activities.
 */
public class GradedActivity implements IReportable
{
    protected String nameProperty;
    protected double score;
    protected double totalPointsPossible;
    private double percentageProperty;
    protected IGrader grader;
    private Grade gradeProperty;

    // Default Constructor
    public GradedActivity(){ }

    // Constructor that takes the injected grader object
    public GradedActivity(IGrader grader){
        this.grader = grader;
        this.totalPointsPossible = 10;
        this.nameProperty = "Graded Activity";
    }

    // Getters -- Properly implements the IReportable interface
    @Override
    public String getName() {
        return nameProperty;
    }

    @Override
    public double getPercentage() {
        return percentageProperty;
    }

    @Override
    public Grade getGrade() {
        return gradeProperty;
    }

    // Used a wrapper for the subclasses to set the percentageProperty and the gradeProperty
    protected void finalizeGrade(){
        setPercentage();
        setGrade();
    }

    // Setters
    public void setScore(double score){
        this.score = score;
        finalizeGrade();
    }
    private void setPercentage(){
        this.percentageProperty = (this.score / totalPointsPossible) * 100;
    }
    private void setGrade(){
        this.gradeProperty = grader.determineGrade(this.percentageProperty);
    }
}
