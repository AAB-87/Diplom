package ru.iteco.fmhandroid.data;

public class ClaimsData {

    public ClaimsData() {
    }

    public static class StatusData {

        private String atWorkText = "В работе";

        public StatusData(String atWorkText) {

            this.atWorkText = atWorkText;
        }

        public StatusData() {
        }

        public String getAtWorkText() {
            return atWorkText;
        }
    }

    public static class FieldsForClaims {
        public String getTitle;

        private String title = "Убрать мусор1";

        public FieldsForClaims(String title) {
            this.title = title;
        }

        public FieldsForClaims() {
        }

    }

    public static class ErrorMessage {
        private String emptyFieldsText = "Заполните пустые поля";
        private String confirmationText = "Изменения не будут сохранены. Вы действительно хотите выйти?";

        public ErrorMessage(String emptyFieldsText, String confirmationText) {
            this.emptyFieldsText = emptyFieldsText;
            this.confirmationText = confirmationText;
        }

        public ErrorMessage() {
        }

        public String getEmptyFieldsText() {
            return emptyFieldsText;
        }
        public String getConfirmationText() { return confirmationText; }
    }

}

