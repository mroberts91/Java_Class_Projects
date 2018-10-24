package com.robertson.domain.factories;

import com.robertson.domain.enums.Grader;
import com.robertson.domain.graders.LetterGrader;
import com.robertson.domain.graders.PassFailGrader;
import com.robertson.domain.interfaces.IGrader;

/*
    Grader Factory returns a IGrader Object to the call which is specified by the Grader enum argument
 */
public class GraderFactory
{
    public static IGrader getGrader(Grader graderType){
        switch (graderType){
            case LETTER:
                return new LetterGrader();
            case PASS_FAIL:
                return new PassFailGrader();
            default:
                return null;
        }
    }
}
