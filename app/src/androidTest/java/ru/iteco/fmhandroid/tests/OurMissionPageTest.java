package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import androidx.test.espresso.PerformException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.ViewActions;

@RunWith(AndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class OurMissionPageTest {

    AuthorizationPageTests authorization = new AuthorizationPageTests(); // создаём объект класса Authorization чтобы его использовать

    @Rule // указываем какое приложение будем запускать. Cм activity в AndroidManifest.xml
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

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
    @DisplayName("Открытие окна Наша миссия")
    public void OpenOurMission() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.our_mission_image_button), 10000)); // ожидаем появления нужного элемента
        onView(withId(R.id.our_mission_image_button)).perform(click()); // находим иконку для входа и кликаем по ней
        onView(withId(R.id.our_mission_title_text_view)).check(matches(withText("Главное - жить любя"))); // проверяем что отображается заголовок страницы
    }

    @Test
    @DisplayName("Открытие 2ой цитаты")
    public void ViewSecondQuote() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.our_mission_image_button), 10000)); // ожидаем появления нужного элемента
        onView(withId(R.id.our_mission_image_button)).perform(click()); // находим иконку для входа и кликаем по ней
        onView(withText("Главное - жить любя")).check(matches(withText("Главное - жить любя"))); // проверяем что отображается заголовок страницы
        onView(withIndex(withId(R.id.our_mission_item_open_card_image_button), 1)).perform(click()); // с помощью утилиты находим 2ю цитату в списке и кликаем по ней
        onView(withIndex(withId(R.id.our_mission_item_description_text_view), 1)).check(matches(isDisplayed())); // проверяем что описание цитаты отображается
    }

}