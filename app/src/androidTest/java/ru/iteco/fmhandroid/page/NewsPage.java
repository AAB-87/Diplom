package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.data.NewsData;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.ViewActions;

public class NewsPage {

    static NewsData.DataInNewsList data = new NewsData.DataInNewsList();

    public void openNewsPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
    }

    public void checkOpenListNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.container_list_news_include), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что отображается список новостей
    }

    public void checkViewDescriptionFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_description_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).check(matches(isDisplayed())); // с помощью утилиты находим описание в 1ой новости в списке
    }

    public void checkViewDescriptionSixthNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_description_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_description_text_view), 5)).check(matches(isDisplayed())); // с помощью утилиты находим описание в 6ой новости в списке
    }

    public void openFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.category_icon_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.category_icon_image_view), 0)).perform(click()); // с помощью утилиты находим 1ую новость в списке и кликаем по ней
    }

    public void openSixthNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.view_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.view_news_item_image_view), 5)).perform(click()); // с помощью утилиты находим 6ую новость в списке и кликаем по ней
    }

    public void clickSortNews() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.sort_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.sort_news_material_button)).perform(click()); // кликаем по кнопке Сортировки
        Thread.sleep(2000);
    }

    public void checkFirstDate() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_date_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_date_text_view), 0)).check(matches(withText("17.12.2022"))); // проверяем что 1ая дата самая ранняя
    }

    public void clickOpenFilter() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке открытия окна фильтрации новостей
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что окно фильтрации открылось
    }

    public void selectCategory() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withText("Зарплата")).inRoot((RootMatchers.isPlatformPopup())).perform(click()); // выбираем категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопки Фильтровать
    }

    public void clickFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_title_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_title_text_view), 0)).perform(click()); // с помощью утилиты находим 1ую новость и кдикаем по ней
    }

    public void checkCategorySalary() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_title_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_title_text_view), 0)).check(matches(withText("Зарплата"))); // с помощью утилиты находим 1ую новость и проверяем соответствие категории
    }

    public void checkCategoryText() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_title_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_title_text_view), 0)).check(matches(withText(data.getTitleAdvertisement()))); // с помощью утилиты находим 1ую новость и проверяем соответствие категории
    }

    public void deleteFirstNews() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.delete_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.delete_news_item_image_view), 0)).perform(click()); // кликаем по кнопке удаления 1ой новости
        onView(isRoot()).perform(ViewActions.waitElement(withId(android.R.id.button1), 10000)); // ожидаем появление нужного элемента
        onView(withId(android.R.id.button1)).perform(click()); // кликаем кнопке подтверждения ОК
        Thread.sleep(2000);
    }

    public void checkDeleteFirstNews() {
        onView(allOf(withHint("Зарплата"))).check(doesNotExist()); // проверяем что текст с заголовком Зарплата не существует
    }

    public void editFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.edit_news_item_image_view), 0)).perform(click()); // кнопка редактирования 1ой новости
        onView(withId(R.id.news_item_description_text_input_edit_text)).perform(replaceText(data.getEditText())); // меняем описание новости
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.save_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.save_button)).perform(click()); // кликаем по кнопке СОХРАНИТЬ
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.view_news_item_image_view), 10000)); // ожидаем появление нужного элемента
    }

    public void clickViewNewsButton() {
        onView(withIndex(withId(R.id.view_news_item_image_view), 0)).perform(click()); // с помощью утилиты находим в списке 1ую кнопку раскрытия новости
    }

    public void checkEditText() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_description_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).check(matches(withText(data.getEditText()))); // с помощью утилиты находим описание в 1ой новости в списке
    }

    public void selectBirthdayCategory() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withText("День рождения")).inRoot((RootMatchers.isPlatformPopup())).perform(click()); // выбираем категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.cancel_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.cancel_button)).perform(click()); // кликаем по кнопки Отмена
    }

    public void selectNonexistentCategory() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        onView(allOf(withHint("Категория"))).perform(replaceText("Другая")); // вводим новую категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопке Фильтровать
    }

    public void selectDate() {
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(click()); // кликаем по полю ввода первой даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(click()); // кликаем по полю ввода второй даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопки Фильтровать
    }

    public void checkCurrentDate() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_date_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_date_text_view), 0)).check(matches(withText(data.getToday()))); // проверяем что первая дата самая поздняя
    }

    public void сlickCreateNewsButton() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.add_news_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_news_image_view)).perform(click()); // кликаем по кнопке создания новости
    }

    public void сlickSaveButton() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.save_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.save_button)).perform(click()); // кликаем по кнопке СОХРАНИТЬ
    }

    public void openControlPanel() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
    }

    public void checkTitleControlPanel() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.container_custom_app_bar_include_on_fragment_news_control_panel), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.container_custom_app_bar_include_on_fragment_news_control_panel)).check(matches(isDisplayed())); // проверяем что отображается текст Панель управления
    }

    public void checkFirstDateInControlPanel() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_create_date_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_create_date_text_view), 0)).check(matches(withText("15.12.2022"))); // проверяем что первая дата самая поздняя
    }

    public void selectCurrentDate() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_publish_date_start_text_input_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(click()); // кликаем по полю ввода первой даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(click()); // кликаем по полю ввода второй даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопки Фильтровать
    }

    public void clickCheckbox() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_active_material_check_box), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_active_material_check_box)).perform(click()); // кликаем по чек-боксу Активна
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопки Фильтровать
    }

    public void checkStatusFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_published_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_published_text_view), 0)).check(matches(withText(data.getStatusNotActive()))); // с помощью утилиты находим статус в 1ой новости
    }

    public void checkStatusSecondNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_published_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_published_text_view), 1)).check(matches(withText(data.getStatusNotActive()))); // с помощью утилиты находим статус в 2ой новости
    }

    public void editCategory() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.edit_news_item_image_view), 0)).perform(click()); // кликаем по кнопке редактирования 1ой новости
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        onView(allOf(withHint("Категория"))).perform(replaceText("Массаж")); // вводим новую категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
    }

    public void checkPublicationDateFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_publication_date_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)).check(matches(withText(containsString("08.01.2023")))); // // с помощью утилиты находим описание в 1ой новости в списке
    }

    public void editCategoryToNonexistent() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.edit_news_item_image_view), 1)).perform(click()); // кликаем по кнопке редактирования 2ой новости
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        onView(allOf(withHint("Категория"))).perform(replaceText("Другая")); // вводим новую категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
    }

    public void changeSwitcher() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.edit_news_item_image_view), 1)).perform(click()); // кликаем по кнопке редактирования 2ой новости
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.switcher), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.switcher)).perform(click()); // Меняем свитчер на Не активна
    }

    public void editDateFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.edit_news_item_image_view), 0)).perform(click()); // кнопка редактирования 1ой новости
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_publish_date_text_input_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_publish_date_text_input_edit_text)).perform(click()); // кликаем по полю ввода первой даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
    }

    public void changeCategory() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по кнопке Категория
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(withText("Объявление")).inRoot((RootMatchers.isPlatformPopup())).perform(click()); // выбираем категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопки Фильтровать
    }

    public void messageWrongCategory(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("Выбрана несуществующая категория"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
    }

    public void messageSaveFailed(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("Сохранение не удалось. Попробуйте позднее."))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
    }


}
