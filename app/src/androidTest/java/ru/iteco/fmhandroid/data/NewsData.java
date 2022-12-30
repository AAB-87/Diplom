package ru.iteco.fmhandroid.data;

public class NewsData {

    public NewsData() {
    }

    public static class DataInNewsList {
        private String today = "30.12.2022";
        private String statusNotActive = "НЕ АКТИВНА";
        private String editText = "Отредактированный текст";
        private String titleTextSalary = "Зарплата";
        private String titleTextTradeUnion = "Профсоюз";
        private String titleAdvertisement = "Объявление";
        private String textNonExistentCategory = "Выбрана несуществующая категория";
        private String saveFailedMessages = "Сохранение не удалось. Попробуйте позднее.";


        public DataInNewsList(String today, String textNonExistentCategory, String saveFailedMessages, String statusNotActive, String editText, String titleTextSalary, String titleTextTradeUnion, String titleAdvertisement) {
            this.today = today;
            this.statusNotActive = statusNotActive;
            this.editText = editText;
            this.titleTextSalary = titleTextSalary;
            this.titleTextTradeUnion = titleTextTradeUnion;
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

        public String getTitleTextTradeUnion() {
            return titleTextTradeUnion;
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

//    public static class Category {
//        private String titleTextSalary = "Зарплата";
//        private String titleTextTradeUnion = "Профсоюз";
//        private String titleAdvertisement = "Объявдение";
//
//
//        public Category(String titleTextSalary, String titleTextTradeUnion, String titleAdvertisement) {
//            this.titleTextSalary = titleTextSalary;
//            this.titleTextTradeUnion = titleTextTradeUnion;
//            this.titleAdvertisement = titleAdvertisement;
//        }
//
//        public Category() {
//        }
//
//        public String getTitleTextSalary() {
//            return titleTextSalary;
//        }
//
//        public String getTitleTextTradeUnion() {
//            return titleTextTradeUnion;
//        }
//
//        public String getTitleAdvertisement() {
//            return titleAdvertisement;
//        }
//
//    }

//    public static class Messages {
//        private String textNonExistentCategory = "Выбрана несуществующая категория";
//        private String saveFailedMessages = "Сохранение не удалось. Попробуйте позднее.";
//
//
//        public Messages(String textNonExistentCategory, String saveFailedMessages) {
//            this.textNonExistentCategory = textNonExistentCategory;
//            this.saveFailedMessages = saveFailedMessages;
//        }
//
//        public Messages() {
//        }
//
//        public String getTextNonExistentCategory() {
//            return textNonExistentCategory;
//        }
//
//        public String getSaveFailedMessages() {
//            return saveFailedMessages;
//        }
//    }

}
