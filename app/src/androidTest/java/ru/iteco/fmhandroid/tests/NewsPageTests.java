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
import ru.iteco.fmhandroid.tests.AuthorizationTests;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.ViewActions;

@RunWith(AndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class NewsPageTests {

    AuthorizationTests authorization = new AuthorizationTests(); // создаём объект класса Authorization чтобы его использовать

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
    @DisplayName("Открытие страницы новостей")
    public void OpenNewsPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
    }

    @Test
    @DisplayName("Просмотр третьей новости")
    public void ViewNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(withIndex(withId(R.id.news_item_material_card_view), 2)).perform(click()); // с помощью утилиты находим 3ю новость в списке и кликаем по ней
    }

    @Test
    @DisplayName("Сортировка списка новостей")
    public void SortNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.sort_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.sort_news_material_button)).perform(click()); // кликаем по кнопке Сортировки
        // нужно проверить что сортировка произошла
    }

    @Test
    @DisplayName("Открытие фильтра новостей")
    public void FilterNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке Сортировка
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что фильтр открылся
    }

    @Test
    @DisplayName("Открытие окна редактирования новостей")
    public void NewsEditScreen() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображаются новости для редактирования
    }



}


