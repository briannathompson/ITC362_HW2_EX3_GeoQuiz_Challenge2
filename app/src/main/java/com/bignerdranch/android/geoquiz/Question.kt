package com.bignerdranch.android.geoquiz

// Pg 34 Listing 2.1: Create Question Class
import androidx.annotation.StringRes        // StringRes must be imported
// Add two properties and a constructor
// the class below has a resource ID for the question text and the question answer (T or F)
/* @ StringRes not required; but recommended bc:
    1. it helps the code inspector verify that the constructor calls provide a valid resource ID;
        prevents runtime crashes when constructor is used with invalid resource id
    2. @StringRes makes code more readable for other devs */
data class Question (@StringRes val textResId: Int, val answer: Boolean)
// textResId is an Int and not a String because the resource ID is an Int (they're always an Int)
// textResId will hold the resource ID for the string we will use, not the string itself