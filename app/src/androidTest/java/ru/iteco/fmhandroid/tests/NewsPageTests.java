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
import androidx.test.espresso.matcher.RootMatchers;
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

public class NewsPageTests {

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
    @DisplayName("Открытие страницы новостей")
    public void OpenNewsPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
    }

    @Test
    @DisplayName("Просмотр третьей новости")
    public void ViewThirdNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(withIndex(withId(R.id.view_news_item_image_view), 2)).perform(click()); // с помощью утилиты находим 3ю новость в списке и кликаем по ней
        onView(withIndex(withId(R.id.news_item_material_card_view), 2)).check(matches(isDisplayed())); // ожидаем появления описания 3ей новости
    }

    @Test
    @DisplayName("Сортировка новостей")
    public void SortNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.sort_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.sort_news_material_button)).perform(click()); // кликаем по кнопке Сортировки
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_date_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_date_text_view), 0)).check(matches(withText("23.11.2022"))); // проверяем что сортировка
    }

    @Test
    @DisplayName("Открытие фильтра новостей")
    public void OpenFilterNewsScreen() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке Сортировка
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что фильтр открылся
    }

    @Test
    @DisplayName("Фильтрация новостей по категории")
    public void FilterNewsByCategory() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке Сортировка
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что фильтр открылся
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по кнопке Категория
        onView(withText("Зарплата")).inRoot((RootMatchers.isPlatformPopup())).perform(click());
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопке Фильтровать
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.view_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.view_news_item_image_view), 2)).perform(click()); // с помощью утилиты находим 3ю новость в списке и кликаем по ней
        onView(withId(R.id.news_item_description_text_view)).check(matches(withText("Новое описание")));
    }

    @Test
    @DisplayName("Фильтрация новостей по дате")
    public void FilterNewsByDate() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке Сортировка
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что фильтр открылся
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_publish_date_start_text_input_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(click()); // кликаем по полю ввода даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(click()); // кликаем по полю ввода даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопке ОК
        onView(withIndex(withId(R.id.news_item_date_text_view), 0)).check(matches(withText("23.11.2022"))); // проверяем что сортировка произошла
    }

    @Test
    @DisplayName("Открытие страницы панели управления")
    public void OpenControlPanelScreen() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображаются новости для редактирования
    }

    @Test
    @DisplayName("Сортировка новостей в панели управления")
    public void SortNewsInControlPanelScreen() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображаются новости для редактирования
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.sort_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.sort_news_material_button)).perform(click()); // кликаем по кнопке сортировки
        onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)).check(matches(withText("23.11.2022"))); // проверяем что сортировка произошла
    }

    String emptyCategory = "no";
    String withCategoryChoice = "yes";
    String chosenCategory = "Зарплата";
    String category = "no";
    String title = "no";
    String emptyDate = "no";
    String emptyTime = "no";
    String withDialPadOrTextInput = "dial"; // ввод с помощью циферблата
    String saveOrCancelTime = "save";
    String emptyDescription = "no";
    String description = "Зарплата";

    @Test
    @DisplayName("Создание новости")
    public void CreateNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по блоку Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображаются новости для редактирования
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.add_news_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_news_image_view)).perform(click()); // кликаем по кнопке создания новости
        FillInFieldsForCreateNews.FillInFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description); // заполняем поля
        onView(withId(R.id.save_button)).perform(click()); // кликаем по кнопке Сохранить
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.add_news_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_news_image_view)).check(matches(isDisplayed())); // проверяем что отображается кнопка создания новости
    }


}


