package Models.Users;

import Enums.Gender;
import Enums.RelationshipStatus;
import Models.Users.User;

import java.util.Date;

public class Client extends User {
    public Client(String email, String last_name, String first_name, String password, RelationshipStatus status, Gender gender, Date birth_date, String phone) {
        super(email, last_name, first_name, password, status, gender, birth_date, phone);
    }

    public Client(String email, String last_name, String first_name, String password, RelationshipStatus status, Gender gender, Date birth_date) {
        super(email, last_name, first_name, password, status, gender, birth_date);
    }

    public Client(String email, String last_name, String first_name, String password, String phone, Gender gender, Date birth_date) {
        super(email, last_name, first_name, password, phone, gender, birth_date);
    }

    public Client(String email, String last_name, String first_name, String password, Gender gender, Date birth_date) {
        super(email, last_name, first_name, password, gender, birth_date);
    }
}
