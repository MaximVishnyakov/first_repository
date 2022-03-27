package edu.university.studentorder;

import edu.university.studentorder.domain.StudentOrder;

public class SaveStudentOrder {
    public static void main(String[] args) {

        StudentOrder so = new StudentOrder();
        so.hFirstname = "Rex";
        so.hLastname = "Over";
        so.wFirstname = "Liza";
        so.wLastname = "Over";

        int a1 = saveStudentOrder(so);
        System.out.println(a1);


    }

    static int saveStudentOrder(StudentOrder studentOrder) {
        int q = 199;
        System.out.println("save OK");
        return  q ;
    }
}
