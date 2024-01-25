import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите login");
        String login = scanner.nextLine();
        System.out.println("Введите password");
        String password = scanner.nextLine();
        System.out.println("подтвердите password");
        String confirmPassword = scanner.nextLine();
        try {
            checker(login, password, confirmPassword);
        } catch (WrongLoginException e) {
            e.printStackTrace();
            System.out.println("login введен не верно, повторите ввод корректно.  \n" +
                    "login должен содержать в себе только латинские буквы, цифры и знак подчеркивания. \n" +
                    "По длинне login должен быть равен или меньше 20 символов и не может быть пустым");
        } catch (WrongPasswordException e) {
            e.printStackTrace();
            System.out.println("password или confirmPassword введен не верно, повторите ввод корректно.  \n" +
                    "password должен содержать в себе только латинские буквы, цифры и знак подчеркивания. \n" +
                    "По длинне password должен быть равен или меньше 20 символов и не может быть пустым. \n" +
                    "Либо пароли не равны");
        }

    }

    static void checker(String login, String password, String confirmPassword) {
        if (!matcher(login)) {
            throw new WrongLoginException("Неверно передано значение в login");
        }
        if (!matcher(password)) {
            throw new WrongPasswordException("Неверно передано значение в password");
        }
        if (!matcher(confirmPassword) || confirmPassword != password) {
            throw new WrongPasswordException("Неверно передано значение в confirmPassword или значения паролей не равны");
        }
    }

    static boolean matcher(String text) {
        Pattern pattern = Pattern.compile("\\w*");
        Matcher matcher = pattern.matcher(text);
        System.out.println(text);
        System.out.println("matcher=" + matcher.matches());
        return matcher.matches() && text.length() <= 20 && !text.isEmpty();
    }
}