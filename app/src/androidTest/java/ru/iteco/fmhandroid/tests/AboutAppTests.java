package ru.iteco.fmhandroid.tests;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.page.AboutAppPage;
import ru.iteco.fmhandroid.page.AuthorizationPage;
import ru.iteco.fmhandroid.utils.StartApp;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class AboutAppTests extends RunRuleTest {

    // Общие правила
    // ViewMatcher - находим, определяем элемент в иерархии
    // ViewActions - производим действие с элементом
    // ViewAssertions - проверяем состояние найденного элемента

    @Before
    public void isAuthorizationPage() throws InterruptedException {
        AuthorizationPage.start();
        StartApp.logInWithValidData(); // если окно отображается, входим в приложение
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Открытие страницы О приложении")
    public void shouldOpenAboutAppPage() {
        AboutAppPage.openAboutApp();
        AboutAppPage.checkOpenAboutAppPage();
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
