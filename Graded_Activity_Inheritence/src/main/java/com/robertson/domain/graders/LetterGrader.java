package com.robertson.domain.graders;

import com.robertson.domain.enums.Grade;
import com.robertson.domain.interfaces.IGrader;

import static com.robertson.domain.enums.Grade.*;

/*
    Letter Grader determines the letter grade for an activity based on the percentage that is passed to the
    determineGrade method.
 */
public class LetterGrader implements IGrader
{
    @Override
public Grade determineGrade(double percentage){
    if (percentage >= 90)
        return A;
    else if (percentage >= 80)
        return B;
    else if (percentage >=70)
        return C;
    else if (percentage >= 60)
        return D;
    else
        return F;
}
}
