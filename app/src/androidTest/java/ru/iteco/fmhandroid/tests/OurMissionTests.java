package ru.iteco.fmhandroid.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.data.MissionData;
import ru.iteco.fmhandroid.page.AuthorizationPage;
import ru.iteco.fmhandroid.page.OurMissionPage;
import ru.iteco.fmhandroid.utils.StartApp;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class OurMissionTests extends RunRuleTest {

    // Общие правила
    // ViewMatcher - находим, определяем элемент в иерархии
    // ViewActions - производим действие с элементом
    // ViewAssertions - проверяем состояние найденного элемента

    MissionData.QuotesData text = new MissionData.QuotesData();

    @Before
    public void isAuthorizationPage() throws InterruptedException {
        AuthorizationPage.start();
        StartApp.logInWithValidData(); // если окно отображается, входим в приложение
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Открытие страницы Наша миссия")
    public void shouldOpenOurMission() {
        OurMissionPage.openOurMission();
        OurMissionPage.checkTitleMissionPage();
    }

    @Test
    @DisplayName("Открытие 2ой цитаты")
    public void shouldOpenSecondQuote() {
        OurMissionPage.openOurMission();
        OurMissionPage.openSecondQuote();
        OurMissionPage.viewDescriptionSecondQuote();
    }

    @Test
    @DisplayName("Скролим и открываем 5ую цитату") // БАГ
    public void shouldScrollAndViewFifthQuote() {
        OurMissionPage.openOurMission();
        OurMissionPage.scrollAndOpenPageQuote();
        OurMissionPage.viewDescriptionFifthQuote();
    }


}
