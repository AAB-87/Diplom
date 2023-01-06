package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.data.MissionData;
import ru.iteco.fmhandroid.elements.OurMissionPageElements;
import ru.iteco.fmhandroid.page.AuthorizationPage;
import ru.iteco.fmhandroid.page.OurMissionPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.StartApp;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class OurMissionTests {

    @Rule // указываем какое приложение будем запускать. Cм activity в AndroidManifest.xml
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

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
        OurMissionPageElements.viewTitleMissionPage.check(matches(withText(text.getTitleText())));
    }

    @Test
    @DisplayName("Открытие 2ой цитаты")
    public void shouldOpenSecondQuote() {
        OurMissionPage.openOurMission();
        OurMissionPageElements.openSecondQuote.perform(click());
        OurMissionPageElements.descriptionSecondQuote.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Скролим и открываем 5ую цитату") // БАГ
    public void shouldScrollAndViewFifthQuote() {
        OurMissionPage.openOurMission().scrollAndOpenPageQuote();
        OurMissionPageElements.descriptionFifthQuote.check(matches(isDisplayed()));
    }


}
