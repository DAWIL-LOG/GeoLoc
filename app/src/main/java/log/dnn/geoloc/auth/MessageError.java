package log.dnn.geoloc.auth;

public class MessageError {

    public static String LOGIN_REQUIRED = "Email and Password are Required";
    public static String LOGIN_USER_NOT_FOUND = "Account are not found";

    public static String REGISTER_REQUIRED = "Data is awaited";
    public static String REGISTER_EMAIL_NOT_MATCH = "Email format is incorrect";
    public static String REGISTER_PASSWORD_NOT_MATCH_LENGHT = "Password must have at least 8 characters";
    public static String REGISTER_PASSWORD_NOT_MATCH_NUMBER = "password must contain numbers";
    public static String REGISTER_PASSWORD_NOT_MATCH_UPPERCASE = "Password must contain a capital letter";
    public static String REGISTER_PASSWORD_NOT_MATCH_SYMBOL = "Password must contain a special character";
}
