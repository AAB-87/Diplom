package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.rule.ActivityTestRule;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.elements.AboutAppPageElements;
import ru.iteco.fmhandroid.page.AboutAppPage;
import ru.iteco.fmhandroid.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.StartApp;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class AboutAppTests {

    @Rule // указываем какое приложение будем запускать. Cм activity в AndroidManifest.xml
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    // Общие правила
    // ViewMatcher - находим, определяем элемент в иерархии
    // ViewActions - производим действие с элементом
    // ViewAssertions - проверяем состояние найденного элемента

    @Before
    public void isAuthorizationPage() throws InterruptedException {
        AuthorizationPage.start();
        StartApp.logInWithValidData(); // если окно отображается, входим в приложение
        Thread.sleep(3000);
    }

    @Test
    @DisplayName("Открытие страницы О приложении")
    public void shouldOpenAboutAppPage() {
        AboutAppPage.openAboutApp();
        AboutAppPageElements.viewVersion.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Проcмотр версии приложения")
    public void shouldViewVersionApp() {
        AboutAppPage.openAboutApp().checkVersionApp();
    }

    @Test
    @DisplayName("Переход по ссылке Политики конфиденциальности") // БАГ
    public void shouldViewPrivacyPolicy() {
        AboutAppPage.openAboutApp().clickPrivacyPolicy();
    }

    @Test
    @DisplayName("Переход по ссылке Пользовательское соглашение") // БАГ
    public void shouldViewUserAgreement() {
        AboutAppPage.openAboutApp().clickUserAgreement();
    }

    @Test
    @DisplayName("Проcмотр имени производителя и года создания приложения")
    public void shouldViewCompanyNameAndYear() {
        AboutAppPage.openAboutApp().checkCompanyNameAndYear();
    }


}
