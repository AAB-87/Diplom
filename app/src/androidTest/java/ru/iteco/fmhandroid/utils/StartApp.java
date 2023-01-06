package ru.iteco.fmhandroid.utils;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;

public class StartApp {

    @DisplayName("Авторизация с валидными данными")
    public static void logInWithValidData() {
        String validLogin = "login2";
        String validPassword = "password2";
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(validLogin)).check(matches(withText("login2"))); // вводим логин в этот элемент
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(validPassword)).check(matches(withText("password2"))); // вводим пароль в этот элемент
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопк входа
        onView(withId(R.id.container_custom_app_bar_include_on_fragment_main)).check(matches(isDisplayed())); // убеждаемся что вошли в приложение (отображается ВХОСПИСЕ)
    }

    @DisplayName("Выход из аккаунта")
    public static void logOutTheApplication() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.authorization_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.authorization_image_button)).perform(click()); // кликаем по иконке для выхода
        onView(withText("Выйти")).check(matches(isDisplayed())); // проверяем что всплывает кнопка Выйти
        onView(withText("Выйти")).perform(click()); // кликаем по всплывающей кнопке
        onView(withText("Авторизация")).check(matches(withText("Авторизация"))); // проверяем что отображается страница для входа
    }

}
