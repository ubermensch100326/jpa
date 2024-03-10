package main03;

import jpabasic.reserve.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static NewUserService newUserService = new NewUserService();
    private static GetUserService getUserService = new GetUserService();
    private static ChangeNameService changeNameService = new ChangeNameService();
    private static RemoveUserService removeUserService = new RemoveUserService();

    public static void main(String[] args) {
        EMF.init();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Enter a command: ");
                String command = br.readLine();
                if (command == null) break;
                if (command.startsWith("new ")) {
                    handleNew(command);
                } else if (command.startsWith("get ")) {
                    handleGet(command);
                } else if (command.startsWith("change ")) {
                    handleChange(command);
                } else if (command.startsWith("remove ")) {
                    handleRemove(command);
                } else if (command.equals("exit")) {
                    break;
                } else {
                    System.out.println("Unknown command");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            EMF.close();
        }
    }

    private static void handleNew(String command) {
        String[] str = command.substring(4).split(" ");
        User user = new User(str[0], str[1], LocalDateTime.now());
        try {
            newUserService.saveNewUser(user);
            logger.info("New user is saved: {}", user);
        } catch (Exception e) {
            logger.error("User already exists!", user.getEmail());
        }
    }

    private static void handleGet(String command) {
        String email = command.substring(4);
        try {
            User user = getUserService.getUser(email);
            logger.info("User is found: {}", user);
        } catch (Exception e) {
            logger.error("User not found!", email);
        }
    }

    private static void handleChange(String command) {
        String[] str = command.substring(7).split(" ");
        try {
            changeNameService.changeName(str[0], str[1]);
            logger.info("User name is changed: {}", str[0], str[1]);
        } catch (Exception e) {
            logger.error("User not found!", str[0]);
        }
    }

    private static void handleRemove(String command) {
        String email = command.substring(7);
        try {
            removeUserService.removeUser(email);
            logger.info("User is removed: {}", email);
        } catch (Exception e) {
            logger.error("User not found!", email);
        }
    }
}
