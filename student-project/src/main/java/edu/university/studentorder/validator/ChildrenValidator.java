package edu.university.studentorder.validator;

import edu.university.studentorder.domain.AnswerChildren;
import edu.university.studentorder.domain.StudentOrder;

public class ChildrenValidator {

    public AnswerChildren checkChildren(StudentOrder so) {
        System.out.println("Children OK ");
        return  new AnswerChildren();
    }
}
