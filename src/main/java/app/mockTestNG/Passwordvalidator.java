package app.mockTestNG;

public class Passwordvalidator {


    public boolean validatePassword(String input) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return input.matches(pattern);
    }


}
