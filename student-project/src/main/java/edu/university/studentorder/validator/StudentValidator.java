package edu.university.studentorder.validator;

import edu.university.studentorder.domain.AnswerStudent;
import edu.university.studentorder.domain.StudentOrder;

public class StudentValidator {
    public AnswerStudent checkStudent(StudentOrder so) {
        System.out.println("Student OK ");
        return new AnswerStudent();

    }
}
