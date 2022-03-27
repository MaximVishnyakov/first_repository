package edu.university.studentorder.validator;

import edu.university.studentorder.domain.AnswerCityRegister;
import edu.university.studentorder.domain.StudentOrder;

public class CityRegisterValidator {

    private String hostName;
    private String login;
    private String password;
    private int port;

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        System.out.println("CityRegister OK ");
        return new AnswerCityRegister();
    }
}
