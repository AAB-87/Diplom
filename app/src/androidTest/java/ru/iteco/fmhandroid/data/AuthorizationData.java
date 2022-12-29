package ru.iteco.fmhandroid.data;

public class AuthorizationData {

    private AuthorizationData() {
    }

    public static class AuthData {
        private String validLogin = "login2";
        private String validPassword = "password2";
        private String invalidLogin = "5login";
        private String invalidPassword = "password";
        private String invalidLogin50 = "Cinderella lives with her stepmother and sisters((";
        private String invalidPassword50 = "She working all day while her sisters do nothing((";
        private String validLoginUp = "LOGIN2";
        private String validPasswordUp = "PASSWORD2";

        public AuthData(String validLogin, String validPassword, String invalidLogin,
                                 String invalidPassword, String invalidLogin50, String invalidPassword50,
                                 String validLoginUp,String validPasswordUp) {
            this.validLogin = validLogin;
            this.validPassword = validPassword;
            this.invalidLogin = invalidLogin;
            this.invalidPassword = invalidPassword;
            this.invalidLogin50 = invalidLogin50;
            this.invalidPassword50 = invalidPassword50;
            this.validLoginUp = validLoginUp;
            this.validPasswordUp = validPasswordUp;
        }

        public AuthData() {
        }

        public String getValidLogin() {
            return validLogin;
        }

        public String getValidPassword() {
            return validPassword;
        }

        public String getInvalidLogin() {
            return invalidLogin;
        }

        public String getInvalidPassword() {
            return invalidPassword;
        }

        public String getInvalidLogin50() {
            return invalidLogin50;
        }

        public String getInvalidPassword50() {
            return invalidPassword50;
        }

        public String getValidLoginUp() {
            return validLoginUp;
        }

        public String getValidPasswordUp() {
            return validPasswordUp;
        }
    }


}
