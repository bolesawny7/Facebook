import Services.UserServices;

import java.util.Scanner;

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
        }
    }
    public static void loginFunction(){
        Scanner userData=new Scanner(System.in);
        String email =userData.next();
        String password =userData.next();
        UserServices login=new UserServices();
        login.checkValues(email,password);

    }public static void signupFunction(){
        Scanner userData=new Scanner(System.in);
        String email =userData.next();
        String password =userData.next();
        UserServices login=new UserServices();
        login.checkValues(email,password);
    }
}

















