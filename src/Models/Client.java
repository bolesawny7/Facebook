package Models;

import Enums.RelationshipStatus;

import java.util.Date;

public class Client extends User {
    public Client(String email, String last_name, String first_name, String password, RelationshipStatus status, boolean gender, Date birth_date, String phone) {
        super(email, last_name, first_name, password, status, gender, birth_date, phone);
    }

    public Client(String email, String last_name, String first_name, String password, RelationshipStatus status, boolean gender, Date birth_date) {
        super(email, last_name, first_name, password, status, gender, birth_date);
    }

    public Client(String email, String last_name, String first_name, String password, String phone, boolean gender, Date birth_date) {
        super(email, last_name, first_name, password, phone, gender, birth_date);
    }

    public Client(String email, String last_name, String first_name, String password, boolean gender, Date birth_date) {
        super(email, last_name, first_name, password, gender, birth_date);
    }
}
