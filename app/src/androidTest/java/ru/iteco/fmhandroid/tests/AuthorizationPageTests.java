package ru.iteco.fmhandroid.tests;

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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.utils.StartApp;
import ru.iteco.fmhandroid.utils.ViewActions;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class AuthorizationPageTests {

    @Rule // указываем какое приложение будем запускать. Cм activity в AndroidManifest.xml
    public ActivityTestRule<ru.iteco.fmhandroid.ui.AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    public static final
    String validLogin = "login2";
    String validPassword = "password2";
    String invalidLogin = "5login";
    String invalidPassword = "password";
    String invalidLogin50 = "Cinderella lives with her stepmother and sisters((";
    String invalidPassword50 = "She working all day while her sisters do nothing((";
    String validLoginUp = "LOGIN2";
    String validPasswordUp = "PASSWORD2";


    // Общие правила
    // ViewMatcher - находим, определяем элемент в иерархии
    // ViewActions - производим действие с элементом
    // ViewAssertions - проверяем состояние найденного элемента

    @Before
    public void isAuthorization() throws InterruptedException {
        try {
            onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем окно авторизации
        } catch (PerformException e) { // если окно не отображается (пользователь авторизирован), ловит ошибку кладёт её в ячеёку e (e сокращённо Exception) и программа не "умирает"
            StartApp.logOutTheApplication(); // осуществляем выход из приложения
        }
        Thread.sleep(7000);
    }

    @Test
    @DisplayName("Авторизация с валидными данными")
    public void logInWithValidData() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(validLogin)).check(matches(withText("login2"))); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(validPassword)).check(matches(withText("password2"))); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопк входа
        onView(withId(R.id.container_custom_app_bar_include_on_fragment_main)).check(matches(isDisplayed())); // убеждаемся что вошли в приложение (отображается ВХОСПИСЕ)
    }

    @Test
    @DisplayName("Вход с НЕвалидными логин и пароль")
    public void logInWithInValidData() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(invalidLogin)).check(matches(withText("5login"))); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(invalidPassword)).check(matches(withText("password"))); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withText("Неверный логин или пароль"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

    @Test
    @DisplayName("Вход с НЕвалидными логином и валидным паролем")
    public void logInWithInValidLoginAndValidPassword() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(invalidLogin)).check(matches(withText("5login"))); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(validPassword)).check(matches(withText("password2"))); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withText("Неверный логин или пароль"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

    @Test
    @DisplayName("Вход с валидным логином и НЕвалидным паролем")
    public void logInWithValidLoginAndInvalidPassword() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(validLogin)).check(matches(withText("login2"))); // вводим логин
        onView(allOf(withHint("Пароль"))).perform(replaceText(invalidPassword)).check(matches(withText("password"))); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withText("Неверный логин или пароль"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

    @Test
    @DisplayName("Вход с пустыми полями")
    public void logInWithEmptyData() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withId(R.id.enter_button)), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withText("Логин и пароль не могут быть пустыми"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

    @Test
    @DisplayName("Вход с валидным логином и пустым паролем")
    public void logInWithValidLoginAndEmptyPassword() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(validLogin)).check(matches(withText("login2"))); // вводим логин
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withText("Логин и пароль не могут быть пустыми"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

    @Test
    @DisplayName("Вход с пустым логином и валидным паролем")
    public void logInWithEmptyLoginAndValidPassword() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(invalidPassword)).check(matches(withText("password"))); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withText("Логин и пароль не могут быть пустыми"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

    @Test
    @DisplayName("Ввод НЕвалидного логина в 50 символов") // БАГ
    public void enterInvalidLoginIn50Characters() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(invalidLogin50)).check(matches(withText("Cinderella lives with her stepmother and sisters(("))); // вводим логин
        onView(withText("Превышен лимит символов"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

    @Test
    @DisplayName("Ввод НЕвалидного пароля в 50 символов") // БАГ
    public void enterInvalidPasswordIn50Characters() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(invalidPassword50)).check(matches(withText("She working all day while her sisters do nothing(("))); // вводим логин
        onView(withText("Превышен лимит символов"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

    @Test
    @DisplayName("Вход с НЕвалидными логин и пароль более 10 раз подряд") // БАГ
    public void logInWithInValidDataMoreThan10TimesInARow() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(invalidLogin)).check(matches(withText("5login"))); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(invalidPassword)).check(matches(withText("password"))); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопке входа
        onView(withText("Учётная запись пользователя заблокирована"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

    @Test
    @DisplayName("Вход с валидным логином в верхнем регистре и валидным паролем")
    public void loginValidLoginInUppercaseAndValidPassword() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(validLoginUp)).check(matches(withText("LOGIN2"))); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(validPassword)).check(matches(withText("password2"))); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопк входа
        onView(withText("Неверный логин или пароль"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

    @Test
    @DisplayName("Вход с валидным логином и валидным паролем в верхнем регистре")
    public void loginValidLoginAndValidPasswordInUppercase() {
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Логин")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Логин"))).perform(replaceText(validLogin)).check(matches(withText("login2"))); // вводим логин
        onView(isRoot()).perform(ViewActions.waitElement(allOf(withHint("Пароль")), 10000)); // ожидаем появление нужного элемента
        onView(allOf(withHint("Пароль"))).perform(replaceText(validPasswordUp)).check(matches(withText("PASSWORD2"))); // вводим пароль
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withId(R.id.enter_button)).perform(click()); // кликаем по кнопк входа
        onView(withText("Неверный логин или пароль"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

}