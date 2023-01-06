package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.PerformException;
import androidx.test.rule.ActivityTestRule;


import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.data.AuthorizationData;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.StartApp;
import ru.iteco.fmhandroid.utils.ViewActions;


public class AuthorizationPage {

    public static AuthorizationPage checkTextInvalidUsernameOrPassword(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("Неверный логин или пароль"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
        return new AuthorizationPage();
    }

    public static AuthorizationPage checkTextLoginAndPasswordCannotBeEmpty(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("Логин и пароль не могут быть пустыми"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
        return new AuthorizationPage();
    }

    public static AuthorizationPage checkTextCharacterLimitExceeded(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("Превышен лимит символов"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
        return new AuthorizationPage();
    }

    public static AuthorizationPage checkTextTheUsersAccountIsBlocked(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("Учётная запись пользователя заблокирована"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
        return new AuthorizationPage();
    }

    public static MainPage start() {
        try {
            onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем окно авторизации
        } catch (PerformException e) { // если окно не отображается (пользователь авторизирован), ловит ошибку кладёт её в ячеёку e (e сокращённо Exception) и программа не "умирает"
            StartApp.logOutTheApplication(); // осуществляем выход из приложения
        }
        return new MainPage();
    }

    public static MainPage enterValidLoginAndPassword(AuthorizationData.AuthData data) throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(data.getValidLogin())); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(data.getValidPassword())); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопк входа
        Thread.sleep(5000);
        return new MainPage();
    }

    public static MainPage enterInvalidLoginAndPassword(AuthorizationData.AuthData data) {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(data.getInvalidLogin())); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(data.getInvalidPassword())); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопк входа
        return new MainPage();
    }

    public static MainPage enterInvalidLoginAndValidPassword(AuthorizationData.AuthData data) {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(data.getInvalidLogin())); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(data.getValidPassword())); // вводим пароль

        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопк входа
        return new MainPage();
    }

    public static MainPage enterValidLoginAndInvalidPassword(AuthorizationData.AuthData data) {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(data.getValidLogin())); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(data.getInvalidPassword())); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопк входа
        return new MainPage();
    }

    public static MainPage enterWithEmptyFields() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withId(R.id.enter_button)), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        return new MainPage();
    }

    public static MainPage enterValidLoginAndEmptyPassword(AuthorizationData.AuthData data) {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(data.getValidLogin())); // вводим логин
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        return new MainPage();
    }

    public static MainPage enterEmptyLoginAndValidPassword(AuthorizationData.AuthData data) {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(data.getValidPassword())); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        return new MainPage();
    }

    public static MainPage enterInvalidLoginIn50Characters(AuthorizationData.AuthData data) {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(data.getInvalidLogin50())); // вводим логин
        return new MainPage();
    }

    public static MainPage enterInvalidPasswordIn50Characters(AuthorizationData.AuthData data) {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(data.getInvalidPassword50())); // вводим логин
        return new MainPage();
    }

    public static MainPage enterLoginWithInValidDataMoreThan5TimesInARow(AuthorizationData.AuthData data) {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(data.getInvalidLogin())); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(data.getInvalidPassword())); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        for (int i = 0; i < 5; i++) { // пока i меньше 10
            onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        }
        return new MainPage();
    }

    public static MainPage enterLoginInUppercaseAndValidPassword(AuthorizationData.AuthData data) {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(data.getValidLoginUp())); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(data.getValidPassword())); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопк входа
        return new MainPage();
    }

    public static MainPage enterValidDataInUppercase(AuthorizationData.AuthData data) {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(data.getValidLoginUp())); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(data.getValidPasswordUp())); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопк входа
        return new MainPage();
    }


}