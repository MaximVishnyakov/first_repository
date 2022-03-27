package edu.university.studentorder.validator;

import edu.university.studentorder.domain.AnswerWedding;
import edu.university.studentorder.domain.StudentOrder;

public class WeddingValidator {
    public AnswerWedding checkWedding(StudentOrder so) {
        System.out.println("Wedding OK ");
        return new AnswerWedding();
    }
}
