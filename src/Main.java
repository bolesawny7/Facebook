import Enums.Gender;
import Services.UserServices;

import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        System.out.println("1-login");
        System.out.println("2-signUP");
        Scanner input=new Scanner(System.in);
        int y= input.nextInt();
        switch (y){
            case 1:
                loginFunction();
                break;
            case 2:
                signupFunction();
                break;
            default:
                System.out.println("please enter 1 or 2");
        }
    }
    public static void loginFunction(){
        Scanner userData=new Scanner(System.in);
        String email =userData.next();
        String password =userData.next();
        UserServices loginservice=new UserServices();
        loginservice.login(email,password);
    }
    public static void signupFunction(){

        Scanner userData=new Scanner(System.in);
        System.out.println("enter your email");
        String email =userData.next();
        System.out.println("enter your first name");
        String first_name =userData.next();
        System.out.println("enter your last name");
        String last_name =userData.next();
        System.out.println("enter your password");
        String password =userData.next();
        System.out.println("enter your gender 0 => male, 1 => female");
        String genderInput = userData.next();
        Gender gender = Gender.valueOf(genderInput.toLowerCase());
        System.out.println("enter your birth_date => year, month, day");
        int year =userData.nextInt();
        int month =userData.nextInt();
        int day =userData.nextInt();
        Date date = new Date(year,month,day);

        UserServices signupService = new UserServices();

        signupService.signUp(email,first_name, last_name, password, gender, date);
    }
}



















