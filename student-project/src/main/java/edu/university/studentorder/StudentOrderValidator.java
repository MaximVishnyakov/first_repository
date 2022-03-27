package edu.university.studentorder;

import edu.university.studentorder.domain.*;
import edu.university.studentorder.mail.MailSender;
import edu.university.studentorder.validator.ChildrenValidator;
import edu.university.studentorder.validator.CityRegisterValidator;
import edu.university.studentorder.validator.StudentValidator;
import edu.university.studentorder.validator.WeddingValidator;

public class StudentOrderValidator {
    public static void main(String[] args) {

        checkAll();
    }

    private static void checkAll() {
        StudentOrder so = readStudentOrder();

        AnswerCityRegister answerCity = checkCityRegister(so);
        AnswerWedding answerWedding = checkWedding(so);
        AnswerChildren answerChildren = checkChildren(so);
        AnswerStudent answerStudent = checkStudent(so);

        sendMail(so);

    }

    private static StudentOrder readStudentOrder() {
        StudentOrder studentOrder = new StudentOrder();
        return studentOrder;
    }

    private static AnswerCityRegister checkCityRegister(StudentOrder so) {
        CityRegisterValidator crv = new CityRegisterValidator();
        AnswerCityRegister ans = crv.checkCityRegister(so);
        return ans;
    }

    private static AnswerChildren checkChildren(StudentOrder so) {
        ChildrenValidator cv = new ChildrenValidator();
        return cv.checkChildren(so);
    }

    private static AnswerWedding checkWedding(StudentOrder so) {
        WeddingValidator wv = new WeddingValidator();
        return wv.checkWedding(so);
    }


    private static AnswerStudent checkStudent(StudentOrder so) {
        StudentValidator sv = new StudentValidator();
        return sv.checkStudent(so);
    }

    private static void sendMail(StudentOrder so) {
            new MailSender().sendMail(so);
    }
}
