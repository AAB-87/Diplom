package ru.iteco.fmhandroid.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.data.AuthorizationData;
import ru.iteco.fmhandroid.page.MainPage;
import ru.iteco.fmhandroid.page.AuthorizationPage;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class AuthorizationTests extends RunRuleTest {

    // Общие правила
    // ViewMatcher - находим, определяем элемент в иерархии
    // ViewActions - производим действие с элементом
    // ViewAssertions - проверяем состояние найденного элемента

    private static AuthorizationData.AuthData data = new AuthorizationData.AuthData();

    @Before
    public void isAuthorizationPage() {
        AuthorizationPage.start();
    }

    @Test
    @DisplayName("Авторизация с валидными данными")
    public void shouldLogInWithValidData() throws InterruptedException {
        AuthorizationPage.enterValidLoginAndPassword(data);
        MainPage.mainPage();
    }

    @Test
    @DisplayName("Авторизация с НЕвалидными данными")
    public void shouldLogInWithInValidData() {
        AuthorizationPage.enterInvalidLoginAndPassword(data);
        AuthorizationPage.checkTextInvalidUsernameOrPassword(activityTestRule);
    }

    @Test
    @DisplayName("Вход с НЕвалидными логином и валидным паролем")
    public void shouldLogInWithInValidLoginAndValidPassword() {
        AuthorizationPage.enterInvalidLoginAndValidPassword(data);
        AuthorizationPage.checkTextInvalidUsernameOrPassword(activityTestRule);
    }

    @Test
    @DisplayName("Вход с валидным логином и НЕвалидным паролем")
    public void shouldLogInWithValidLoginAndInvalidPassword() {
        AuthorizationPage.enterValidLoginAndInvalidPassword(data);
        AuthorizationPage.checkTextInvalidUsernameOrPassword(activityTestRule);
    }

    @Test
    @DisplayName("Вход без логина и пароля")
    public void shouldLogInWithEmptyFields() {
        AuthorizationPage.enterWithEmptyFields();
        AuthorizationPage.checkTextLoginAndPasswordCannotBeEmpty(activityTestRule);
    }

    @Test
    @DisplayName("Вход с валидным логином и пустым паролем")
    public void shouldLogInWithValidLoginAndEmptyPassword() {
        AuthorizationPage.enterValidLoginAndEmptyPassword(data);
        AuthorizationPage.checkTextLoginAndPasswordCannotBeEmpty(activityTestRule);
    }

    @Test
    @DisplayName("Вход с пустым логином и валидным паролем")
    public void shouldLogInWithEmptyLoginAndValidPassword() {
        AuthorizationPage.enterEmptyLoginAndValidPassword(data);
        AuthorizationPage.checkTextLoginAndPasswordCannotBeEmpty(activityTestRule);
    }

    @Test
    @DisplayName("Ввод НЕвалидного логина в 50 символов") // БАГ
    public void shouldEnterInvalidLoginIn50Characters() {
        AuthorizationPage.enterInvalidLoginIn50Characters(data);
        AuthorizationPage.checkTextCharacterLimitExceeded(activityTestRule);
    }

    @Test
    @DisplayName("Ввод НЕвалидного пароля в 50 символов") // БАГ
    public void shouldEnterInvalidPasswordIn50Characters() {
        AuthorizationPage.enterInvalidPasswordIn50Characters(data);
        AuthorizationPage.checkTextCharacterLimitExceeded(activityTestRule);
    }

    @Test
    @DisplayName("Вход с логином в верхнем регистре и валидным паролем")
    public void shouldLogInWithLoginInUppercaseAndValidPassword() {
        AuthorizationPage.enterLoginInUppercaseAndValidPassword(data);
        AuthorizationPage.checkTextInvalidUsernameOrPassword(activityTestRule);
    }

    @Test
    @DisplayName("Вход с валидными данными в верхнем регистре")
    public void shouldLogInValidDataInUppercase() {
        AuthorizationPage.enterValidDataInUppercase(data);
        AuthorizationPage.checkTextInvalidUsernameOrPassword(activityTestRule);
    }

    @Test
    @DisplayName("Вход с НЕвалидными данными более 5и раз подряд") // БАГ
    public void shouldLogInWithInValidDataMoreThan5TimesInARow() throws InterruptedException {
        AuthorizationPage.enterLoginWithInValidDataMoreThan5TimesInARow(data);
        Thread.sleep(3000);
        AuthorizationPage.checkTextTheUsersAccountIsBlocked(activityTestRule);
    }

}

