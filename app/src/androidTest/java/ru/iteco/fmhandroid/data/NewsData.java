package ru.iteco.fmhandroid.data;

public class NewsData {

    public NewsData() {
    }

    public static class DataInNewsList {
        private String today = "09.01.2023";
        private String statusNotActive = "НЕ АКТИВНА";
        private String editText = "Отредактированный текст";
        private String titleTextSalary = "Зарплата";
        private String titleAdvertisement = "Объявление";
        private String textNonExistentCategory = "Выбрана несуществующая категория";
        private String saveFailedMessages = "Сохранение не удалось. Попробуйте позднее.";


        public DataInNewsList(String today, String textNonExistentCategory, String saveFailedMessages, String statusNotActive, String editText, String titleTextSalary, String titleAdvertisement) {
            this.today = today;
            this.statusNotActive = statusNotActive;
            this.editText = editText;
            this.titleTextSalary = titleTextSalary;
            this.titleAdvertisement = titleAdvertisement;
            this.textNonExistentCategory = textNonExistentCategory;
            this.saveFailedMessages = saveFailedMessages;
        }

        public DataInNewsList() {
        }

        public String getToday() {
            return today;
        }

        public String getStatusNotActive() {
            return statusNotActive;
        }

        public String getEditText() {
            return editText;
        }
        public String getTitleTextSalary() {
            return titleTextSalary;
        }

        public String getTitleAdvertisement() {
            return titleAdvertisement;
        }
        public String getTextNonExistentCategory() {
            return textNonExistentCategory;
        }

        public String getSaveFailedMessages() {
            return saveFailedMessages;
        }

    }


}
