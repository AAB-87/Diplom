package ru.iteco.fmhandroid.data;

public class NewsData {

    public NewsData() {
    }

    public static class DataInNewsList {
        private String today = "26.12.2022";
        private String statusNotActive = "НЕ АКТИВНА";
        private String editText = "Отредактированный текст";


        public DataInNewsList(String today, String statusNotActive, String editText) {
            this.today = today;
            this.statusNotActive = statusNotActive;
            this.editText = editText;
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

    }

    public static class Category {
        private String titleTextSalary = "Зарплата";
        private String titleTextTradeUnion = "Профсоюз";


        public Category(String titleTextSalary, String titleTextTradeUnion) {
            this.titleTextSalary = titleTextSalary;
            this.titleTextTradeUnion = titleTextTradeUnion;
        }

        public Category() {
        }

        public String getTitleTextSalary() {
            return titleTextSalary;
        }

        public String getTitleTextTradeUnion() {
            return titleTextTradeUnion;
        }

    }

    public static class Messages {
        private String textNonExistentCategory = "Выбрана несуществующая категория";
        private String saveFailedMessages = "Сохранение не удалось. Попробуйте позднее.";


        public Messages(String textNonExistentCategory, String saveFailedMessages) {
            this.textNonExistentCategory = textNonExistentCategory;
            this.saveFailedMessages = saveFailedMessages;
        }

        public Messages() {
        }

        public String getTextNonExistentCategory() {
            return textNonExistentCategory;
        }

        public String getSaveFailedMessages() {
            return saveFailedMessages;
        }
    }

}
