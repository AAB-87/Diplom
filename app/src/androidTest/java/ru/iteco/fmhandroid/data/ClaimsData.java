package ru.iteco.fmhandroid.data;

public class ClaimsData {

    public ClaimsData() {
    }

    public static class StatusData {
        public String getAtWorkText;

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
        public String getDescriptionEdited;
        public String getChangeStatusClaim;

        private String title = "Убрать мусор1";
        private String descriptionEdited = "Отредактированный текст";
        private String changeStatusClaim = "Для смены статуса";


        public FieldsForClaims(String title, String descriptionEdited, String changeStatusClaim) {
            this.title = title;
            this.descriptionEdited = descriptionEdited;
            this.changeStatusClaim = changeStatusClaim;
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

