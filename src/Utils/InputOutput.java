package Utils;

import Models.Users.Client;
import Services.FileService;
import Services.UserService;

import java.util.ArrayList;


/**
 * This class provides methods for reading and writing data.
 * It includes methods for reading users, messages, friends, and posts from files, and for saving users, friends, posts, and messages to files.
 */
public class InputOutput {
    private static UserService userService = new UserService();
    private static FileService fileService = new FileService();
    ArrayList<Client> clients = userService.getClients();


    /**
     * This method reads data from files.
     * If the list of clients is empty, it reads users and messages from files.
     * It then reads friends from files and posts from a static method.
     */
    public void read() {
        if (UserService.clients.isEmpty()) {
            userService.readUsers();
            fileService.readAllMessages();
        }
        fileService.readUserFrinends(clients);
        FileService.readAllPosts();
    }

    /**
     * This method writes data to files.
     * It saves users, friends, posts, and messages to files.
     */
    public void write() {
        fileService.saveAllUsers(clients);
        fileService.saveALlFriends(clients);
        fileService.saveAllPosts(userService);
        fileService.saveAllMessages();
    }
}
