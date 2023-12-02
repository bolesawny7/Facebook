import Enums.Gender;
import Models.Users.User;
import Services.UserService;

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
        UserService userService = new UserService();
        switch (y){
            case 1:
                userService.login();
                break;
            case 2:
                userService.signUp();
                break;
            default:
                System.out.println("please enter 1 or 2");
        }
    }
}




















