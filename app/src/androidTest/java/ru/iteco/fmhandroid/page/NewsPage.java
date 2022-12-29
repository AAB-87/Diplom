package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import androidx.test.espresso.matcher.RootMatchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.utils.ViewActions;

public class NewsPage {

    public static void openNewsPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
    }

    public static void openFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.category_icon_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.category_icon_image_view), 0)).perform(click()); // с помощью утилиты находим 1ую новость в списке и кликаем по ней
    }

    public static void openSixthNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.view_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.view_news_item_image_view), 5)).perform(click()); // с помощью утилиты находим 6ую новость в списке и кликаем по ней
    }

    public static void clickSortNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.sort_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.sort_news_material_button)).perform(click()); // кликаем по кнопке Сортировки
    }

    public static void checkFirstDate() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_date_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_date_text_view), 0)).check(matches(withText("15.12.2022"))); // проверяем что 1ая дата самая ранняя
    }

    public static void clickOpenFilter() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке открытия окна фильтрации новостей
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что окно фильтрации открылось
    }

    public static void selectCategory() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withText("Зарплата")).inRoot((RootMatchers.isPlatformPopup())).perform(click()); // выбираем категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
    }

    public static void selectNonexistentCategory() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        onView(allOf(withHint("Категория"))).perform(replaceText("Другая")); // вводим новую категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопке Фильтровать
    }

    public static void selectDate() {
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(click()); // кликаем по полю ввода первой даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(click()); // кликаем по полю ввода второй даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
    }

    public static void checkCurrentDate() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_date_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_date_text_view), 0)).check(matches(withText("26.12.2022"))); // проверяем что первая дата самая поздняя
    }

    public static void openControlPanel() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        Thread.sleep(3000);
    }

    public static void checkFirstDateInControlPanel() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_create_date_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_create_date_text_view), 0)).check(matches(withText("15.12.2022"))); // проверяем что первая дата самая поздняя
    }

    public static void selectCurrentDate() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_publish_date_start_text_input_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(click()); // кликаем по полю ввода первой даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(click()); // кликаем по полю ввода второй даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
    }

    public static void editCategory() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.edit_news_item_image_view), 0)).perform(click()); // кликаем по кнопке редактирования 1ой новости
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        onView(allOf(withHint("Категория"))).perform(replaceText("Массаж")); // вводим новую категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        Thread.sleep(5000);
    }

    public static void editCategoryToNonexistent() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.edit_news_item_image_view), 1)).perform(click()); // кликаем по кнопке редактирования 2ой новости
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        onView(allOf(withHint("Категория"))).perform(replaceText("Другая")); // вводим новую категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        Thread.sleep(5000);
    }

    public static void changeSwitcher() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.edit_news_item_image_view), 1)).perform(click()); // кликаем по кнопке редактирования 2ой новости
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.switcher), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.switcher)).perform(click()); // Меняем свитчер на Не активна
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.save_button), 10000)); // ожидаем появление нужного элемента
    }

    public static void changeFirstDate() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_publish_date_text_input_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_publish_date_text_input_edit_text)).perform(click()); // кликаем по полю ввода первой даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
    }

    public static void changeCategory() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по кнопке Категория
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withText("Профсоюз")).inRoot((RootMatchers.isPlatformPopup())).perform(click()); // выбираем категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
    }


}
