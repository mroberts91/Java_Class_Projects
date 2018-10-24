package com.robertson.domain.graders;

import com.robertson.domain.enums.Grade;
import com.robertson.domain.interfaces.IGrader;

import static com.robertson.domain.enums.Grade.FAIL;
import static com.robertson.domain.enums.Grade.PASS;

/*
    PassFail Grader returns a grade for a pass fail activity that is based on the percentage that is passed in.
 */
public class PassFailGrader implements IGrader
{
    @Override
    public Grade determineGrade(double percentage)
    {
        // If 70 or greater return pass else false
        if (percentage >= 70)
            return PASS;
        else
            return FAIL;
    }
}
