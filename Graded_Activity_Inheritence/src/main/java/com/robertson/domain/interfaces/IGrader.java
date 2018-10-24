package com.robertson.domain.interfaces;

import com.robertson.domain.enums.Grade;

/*
    Interface for objects that are activity grading objects
 */
public interface IGrader
{
    Grade determineGrade(double score);
}
