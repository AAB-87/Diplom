package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.data.AuthorizationData;
import ru.iteco.fmhandroid.elements.AuthorizationPageElements;
import ru.iteco.fmhandroid.elements.MainPageElements;
import ru.iteco.fmhandroid.page.AuthorizationPage;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class AuthorizationTests {

    @Rule // указываем какое приложение будем запускать. Cм activity в AndroidManifest.xml
    public ActivityTestRule<ru.iteco.fmhandroid.ui.AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    // Общие правила
    // ViewMatcher - находим, определяем элемент в иерархии
    // ViewActions - производим действие с элементом
    // ViewAssertions - проверяем состояние найденного элемента

    private static AuthorizationData.AuthData data = new AuthorizationData.AuthData();

    @Before
    public void isAuthorizationPage() throws InterruptedException {
        AuthorizationPage.start();
    }

    @Test
    @DisplayName("Авторизация с валидными данными")
    public void shouldLogInWithValidData() throws InterruptedException {
        AuthorizationPage.enterValidLoginAndPassword(data);
        MainPageElements.mainPage.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Авторизация с НЕвалидными данными")
    public void shouldLogInWithInValidData() {
        AuthorizationPage.enterInvalidLoginAndPassword(data);
        AuthorizationPageElements.checkTextInvalidUsernameOrPassword(activityTestRule);
    }

    @Test
    @DisplayName("Вход с НЕвалидными логином и валидным паролем")
    public void shouldLogInWithInValidLoginAndValidPassword() {
        AuthorizationPage.enterInvalidLoginAndValidPassword(data);
        AuthorizationPageElements.checkTextInvalidUsernameOrPassword(activityTestRule);
    }

    @Test
    @DisplayName("Вход с валидным логином и НЕвалидным паролем")
    public void shouldLogInWithValidLoginAndInvalidPassword() {
        AuthorizationPage.enterValidLoginAndInvalidPassword(data);
        AuthorizationPageElements.checkTextInvalidUsernameOrPassword(activityTestRule);
    }

    @Test
    @DisplayName("Вход без логина и пароля")
    public void shouldLogInWithEmptyFields() {
        AuthorizationPage.enterWithEmptyFields();
        AuthorizationPageElements.checkTextLoginAndPasswordCannotBeEmpty(activityTestRule);
    }

    @Test
    @DisplayName("Вход с валидным логином и пустым паролем")
    public void shouldLogInWithValidLoginAndEmptyPassword() {
        AuthorizationPage.enterValidLoginAndEmptyPassword(data);
        AuthorizationPageElements.checkTextLoginAndPasswordCannotBeEmpty(activityTestRule);
    }

    @Test
    @DisplayName("Вход с пустым логином и валидным паролем")
    public void shouldLogInWithEmptyLoginAndValidPassword() {
        AuthorizationPage.enterEmptyLoginAndValidPassword(data);
        AuthorizationPageElements.checkTextLoginAndPasswordCannotBeEmpty(activityTestRule);
    }

    @Test
    @DisplayName("Ввод НЕвалидного логина в 50 символов") // БАГ
    public void shouldEnterInvalidLoginIn50Characters() {
        AuthorizationPage.enterInvalidLoginIn50Characters(data);
        AuthorizationPageElements.checkTextCharacterLimitExceeded(activityTestRule);
    }

    @Test
    @DisplayName("Ввод НЕвалидного пароля в 50 символов") // БАГ
    public void shouldEnterInvalidPasswordIn50Characters() {
        AuthorizationPage.enterInvalidPasswordIn50Characters(data);
        AuthorizationPageElements.checkTextCharacterLimitExceeded(activityTestRule);
    }

    @Test
    @DisplayName("Вход с НЕвалидными данными более 10 раз подряд") // БАГ
    public void shouldLogInWithInValidDataMoreThan10TimesInARow() {
        AuthorizationPage.enterLoginWithInValidDataMoreThan10TimesInARow(data);
        AuthorizationPageElements.checkTextTheUsersAccountIsBlocked(activityTestRule);
    }

    @Test
    @DisplayName("Вход с логином в верхнем регистре и валидным паролем")
    public void shouldLogInWithLoginInUppercaseAndValidPassword() {
        AuthorizationPage.enterLoginInUppercaseAndValidPassword(data);
        AuthorizationPageElements.checkTextInvalidUsernameOrPassword(activityTestRule);
    }

    @Test
    @DisplayName("Вход с валидными данными в верхнем регистре")
    public void shouldLogInValidDataInUppercase() {
        AuthorizationPage.enterValidDataInUppercase(data);
        AuthorizationPageElements.checkTextInvalidUsernameOrPassword(activityTestRule);
    }

}

