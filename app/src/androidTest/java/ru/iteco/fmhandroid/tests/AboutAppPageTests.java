package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

import androidx.test.espresso.PerformException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.ViewActions;

@RunWith(AndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class AboutAppPageTests {

    AuthorizationPageTests authorization = new AuthorizationPageTests(); // создаём объект класса Authorization чтобы его использовать

    @Rule // указываем какое приложение будем запускать. Cм activity в AndroidManifest.xml
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    // Общие правила
    // ViewMatcher - находим, определяем элемент в иерархии
    // ViewActions - производим действие с элементом
    // ViewAssertions - проверяем состояние найденного элемента

    @Before
    public void IsAuthorizationScreenOpen() throws InterruptedException { // проверяем состояние приложения перед запуском тестов
        try {
            authorization.logInToTheApp(); // открывается ли окно авторизации
        } catch (PerformException e) { // если нет, ловит ошибку кладёт её в ячеёку e (e сокращённо Exception) и программа не "умирает"
            return; // значит уже авторизован
        }
        authorization.logInWithValidData(); // если да, то логинится
        Thread.sleep(5500);
    }

    @Test
    @DisplayName("Открытие страницы О приложении")
    public void OpenAboutAppPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("О приложении")).perform(click()); // кликаем по О приложении
        onView(withId(R.id.about_version_title_text_view)).check(matches(isDisplayed())); // проверяем что верстия приложения отображается
    }
    @Test
    @DisplayName("Проверка версии приложения")
    public void CheckVersionApp() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("О приложении")).perform(click()); // кликаем по О приложении
        onView(withId(R.id.about_version_value_text_view)).check(matches(withText("1.0.0"))); // проверяем какая версия отображается
    }

    @Test
    @DisplayName("Переход по ссылке Политики конфиденциальности")
    public void ClickPrivacyPolicy() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("О приложении")).perform(click()); // кликаем по О приложении
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.about_privacy_policy_value_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.about_privacy_policy_value_text_view)).perform(click()); // кликаем по ссылке Политика конфиденциальности
        intended(allOf(hasData("https://vhospice.org/#/privacy-policy/"), hasAction(Intent.ACTION_VIEW))); // переход по ссылке
    }

    @Test
    @DisplayName("Переход по ссылке Пользовательское соглашение")
    public void ClickUserAgreement() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("О приложении")).perform(click()); // кликаем по О приложении
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.about_terms_of_use_value_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.about_terms_of_use_value_text_view)).perform(click()); // кликаем по ссылке Пользовательское соглашение
        intended(allOf(hasData("https://vhospice.org/#/terms-of-use"), hasAction(Intent.ACTION_VIEW))); // переход по ссылке
    }

    @Test
    @DisplayName("Проверка названия производителия и года создания приложения")
    public void CheckCompanyNameAndYear() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("О приложении")).perform(click()); // кликаем по О приложении
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.about_company_info_label_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.about_company_info_label_text_view)).check(matches(withText("© Айтеко, 2022"))); // кликаем по ссылке Пользовательское соглашение
    }

}