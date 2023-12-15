package Utils;

import Models.Users.Client;
import Services.FileService;
import Services.UserService;

import java.util.ArrayList;

public class InputOutput {
    private static UserService userService = new UserService();
    private static FileService fileService = new FileService();
    ArrayList<Client> clients = userService.getClients();

    public void read() {
        if (UserService.clients.isEmpty()) {
            userService.readUsers();
            fileService.readAllMessages();
        }
        fileService.readUserFrinends(clients);
        FileService.readAllPosts();
    }

    public void write() {
        fileService.saveAllUsers(clients);
        fileService.saveALlFriends(clients);
        fileService.saveAllPosts(userService);
        fileService.saveAllMessages();
    }
}
