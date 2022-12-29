package ru.iteco.fmhandroid.data;

public class MissionData {

    private MissionData() {
    }

    public static class QuotesData {
        private String titleText = "Главное - жить любя";
        private String titleEighthQuote = "Важен каждый!";
        private String titleFifthQuote = "Служение человеку с теплом, любовью и заботой";

        public QuotesData(String titleText) {
            this.titleText = titleText;
            this.titleEighthQuote = titleEighthQuote;
            this.titleFifthQuote = titleEighthQuote;
        }

        public QuotesData() {

        }

        public String getTitleText() {
            return titleText;
        }

        public String getTitleEighthQuote() {
            return titleEighthQuote;
        }

        public String getTitleFifthQuote() {
            return titleFifthQuote;
        }
    }

}
