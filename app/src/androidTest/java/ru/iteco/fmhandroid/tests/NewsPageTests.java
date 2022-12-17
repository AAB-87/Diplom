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
import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.FillInFieldsForCreateNews;
import ru.iteco.fmhandroid.utils.StartApp;
import ru.iteco.fmhandroid.utils.ViewActions;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class NewsPageTests {

    AuthorizationPageTests authorization = new AuthorizationPageTests(); // создаём объект класса Authorization чтобы его использовать

    @Rule // указываем какое приложение будем запускать. Cм activity в AndroidManifest.xml
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    String autofill1 = "зар";

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
        StartApp.logInWithValidData(); // если окно отображается, входим в приложение
        Thread.sleep(7000);
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
    @DisplayName("Просмотр описания первой новости")
    public void ViewDescriptionsFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.view_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.view_news_item_image_view), 0)).perform(click()); // с помощью утилиты находим 3ю новость в списке и кликаем по ней
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).check(matches(isDisplayed())); // с помощью утилиты находим описание в 1ой новости в списке и убеждаемся что оно отображается
    }

    @Test
    @DisplayName("Просмотр описания седьмой новости") // БАГ
    public void ViewDescriptionsSeventhNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.view_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.view_news_item_image_view), 6)).perform(click()); // с помощью утилиты находим 3ю новость в списке и кликаем по ней
        onView(withIndex(withId(R.id.news_item_description_text_view), 6)).check(matches(isDisplayed())); // с помощью утилиты находим описание в 7ой новости в списке и убеждаемся что оно отображается
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
    }

    @Test
    @DisplayName("Открытие фильтра новостей")
    public void OpenFilterNewsScreen() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке открытия окна фильтрации новостей
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что окно фильтрации открылось
    }

    @Test
    @DisplayName("Фильтрация новостей по категории")
    public void FilterNewsByCategory() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке для открытия окна фильтрации
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что окно фильтрации открылось
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        onView(withText("Зарплата")).inRoot((RootMatchers.isPlatformPopup())).perform(click()); // выбираем категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопке Фильтровать
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.view_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.view_news_item_image_view), 0)).perform(click()); // с помощью утилиты находим 1ую новость в списке и кликаем по ней
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).check(matches(isDisplayed())); // проверяем что описание 1ой новости отображается
    }

    @DisplayName("Автозаполнение поля выбора категории при фильтрации новостей") // Не запускается
    public void AutofillCategorySelectionFieldWhenFilteringNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке для открытия окна фильтрации
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что окно фильтрации открылось
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        onView(allOf(withHint("Категория"))).perform(replaceText(autofill1)).check(matches(withText("зар"))); // вводим первые несколько букв названия нужной категории
        onView(allOf(withHint("Зарплата"))).perform(click()); // Кликаем по появившейся категории
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(allOf(withHint("Категория"))).check(matches(withText("Зарплата"))); // проверяем что в поля Категория вписано Зарплата
    }

    @Test
    @DisplayName("Фильтрация новостей по несуществующей категории") // БАГ
    public void FilterNewsByNonExistentCategory() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке для открытия окна фильтрации
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что окно фильтрации открылось
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        onView(allOf(withHint("Категория"))).perform(replaceText("Другая")); // вводим новую категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопке Фильтровать
        onView(withText("Выбрана несуществующая категория"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

    @Test
    @DisplayName("Отмена фильтрации")
    public void CancelFilter() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке для открытия окна фильтрации
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что окно фильтрации открылось
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        onView(withText("Объявление")).inRoot((RootMatchers.isPlatformPopup())).perform(click()); // выбираем категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.cancel_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.cancel_button)).perform(click()); // кликаем по кнопке Отмена
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_title_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_title_text_view), 0)).check(matches(withText("День рождения"))); // проверяем что фильтрация НЕпроизошла
        onView(withIndex(withId(R.id.news_item_title_text_view), 1)).check(matches(withText("Благодарность"))); // проверяем что фильтрация НЕпроизошла
    }

    @Test
    @DisplayName("Фильтрация новостей по дате")
    // перед запуском поменять дату в последней строке на текущую
    public void FilterNewsByDate() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке открытия окна для фильтрации
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что окно фильтрации открылось
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_publish_date_start_text_input_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(click()); // кликаем по полю ввода первой даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(click()); // кликаем по полю ввода второй даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопке Фильтровать
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_date_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_date_text_view), 0)).check(matches(withText("07.12.2022"))); // проверяем что фильтрация произошла
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
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что открылась Панель управления
    }

    @Test
    @DisplayName("Просмотр описания первой новости в панели управления")
    public void ViewDescriptionFirstNewsInControlPanel() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображается Панель управления
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.view_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.view_news_item_image_view), 0)).perform(click()); // кликаем по кнопке раскрытия описания новости
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_description_text_view), 10000)); // описание отображается
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).check(matches(isDisplayed())); // с помощью утилиты находим описание в 1ой новости в списке и убеждаемся что оно отображается
    }

    @Test
    @DisplayName("Просмотр описания третей новости в панели управления") // БАГ
    public void ViewDescriptionThirdsNewsInControlPanel() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображается Панель управления
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.view_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.view_news_item_image_view), 2)).perform(click()); // кликаем по кнопке раскрытия описания новости
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_description_text_view), 10000)); // описание отображается
        onView(withIndex(withId(R.id.news_item_description_text_view), 2)).check(matches(isDisplayed())); // с помощью утилиты находим описание в 3ей новости в списке и убеждаемся что оно отображается
    }

    @Test
    @DisplayName("Сортировка новостей в панели управления")
    public void SortNewsInControlPanel() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображается Панель урпавления
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.sort_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.sort_news_material_button)).perform(click()); // кликаем по кнопке сортировки
    }

    @Test
    @DisplayName("Фильтрация новостей в панели управления по статусу")
    public void FilterNewsInControlPanelByStatus() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображается Панель управления
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке открытия окна для фильтрации
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_active_material_check_box), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_active_material_check_box)).perform(click()); // кликаем по чек-боксу Активна (выключаем)
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопке Фильтровать
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // ожидаем появление нужного элемента
        Thread.sleep(7000);
        onView(withIndex(withId(R.id.news_item_published_text_view), 0)).check(matches(withText("НЕ АКТИВНА"))); // проверяем что статус 1ой новости НЕ АКТИВНА
    }

    @Test
    @DisplayName("Фильтрация новостей в панели управления по дате")
    // перед запуском поменять дату в последней строке на текущую
    public void FilterNewsInControlPanelByDate() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображается Панель управления
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке открытия окна для фильтрации
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_publish_date_start_text_input_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(click()); // кликаем по полю ввода первой даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(click()); // кликаем по полю ввода второй даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопке Фильтровать
        Thread.sleep(7000);
        onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)).check(matches(withText("07.12.2022"))); // проверяем что фильтрация произошла
    }

    @Test
    @DisplayName("Фильтрация новостей в панели управления по категории")
    public void FilterNewsInControlPanelByCategory() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображается Панель управления
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке открытия окна для фильтрации
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что окно для фильтрации открылось
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по кнопке Категория
        onView(withText("Праздник")).inRoot((RootMatchers.isPlatformPopup())).perform(click()); // выбираем категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_publish_date_start_text_input_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(click()); // кликаем по полю ввода первой даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(click()); // кликаем по полю ввода второй даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопке Фильтровать
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // ожидаем появление нужного элемента
        Thread.sleep(7000);
        onView(withIndex(withId(R.id.news_item_title_text_view), 0)).check(matches(withText("Праздник"))); // проверяем что фильтрация произошла
    }

    @DisplayName("Автозаполнение поля выбора категории при фильтрации новостей в панели управления") // Не запускается
    public void AutofillCategorySelectionFieldWhenFilteringNewsInControlPanel() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображается Панель управления
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке открытия окна для фильтрации
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что окно для фильтрации открылось
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        onView(allOf(withHint("Категория"))).perform(replaceText(autofill1)).check(matches(withText("зар"))); // вводим первые несколько букв названия нужной категории
        onView(allOf(withHint("Зарплата"))).perform(click()); // Кликаем по появившейся категории
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(allOf(withHint("Категория"))).check(matches(withText("Зарплата"))); // проверяем что в поля Категория вписано Зарплата
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
    public void CreateNews() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по блоку Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображается Панель управления
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.add_news_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_news_image_view)).perform(click()); // кликаем по кнопке создания новости
        FillInFieldsForCreateNews.FillInFieldsNews(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description); // заполняем поля окна создания новости
        onView(withId(R.id.save_button)).perform(click()); // кликаем по кнопке Сохранить
        Thread.sleep(7000);
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_news_material_button)).perform(click()); // кликаем по кнопке открытия окна для фильтрации
        onView(withId(R.id.filter_news_title_text_view)).check(matches(withText("Фильтровать новости"))); // проверяем что окно для фильтрации открылось
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_category_text_auto_complete_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по кнопке Категория
        onView(withText("Зарплата")).inRoot((RootMatchers.isPlatformPopup())).perform(click()); // выбираем категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_publish_date_start_text_input_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(click()); // кликаем по полю ввода первой даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(click()); // кликаем по полю ввода второй даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filter_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filter_button)).perform(click()); // кликаем по кнопке Фильтровать
        onView(withIndex(withId(R.id.view_news_item_image_view), 0)).perform(click()); // с помощью утилиты находим 1ю новость в списке и кликаем по ней
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).check(matches(withText("Зарплата"))); // проверяем что описание созданной новости соответствует
    }

    @Test
    @DisplayName("Удаление новости")
    public void DeleteNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображается Панель управления
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.delete_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.delete_news_item_image_view), 0)).perform(click()); // кликаем по кнопке удаления 1ой новости
        onView(withId(android.R.id.button1)).perform(click()); // кликаем по кнопке подтверждения (ОК)
    }

    @Test
    @DisplayName("Редактирование описания новости")
    public void EditNewsDescription() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображается Панель управления
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.edit_news_item_image_view), 0)).perform(click()); // кликаем по кнопке редактирования 1ой новости
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_description_text_input_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.news_item_description_text_input_edit_text)).perform(replaceText("Отредактированный текст")); // меняем текст описания
        onView(withIndex(withId(R.id.save_button), 0)).perform(click()); // кликаем по кнопке Сохранить
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.view_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.view_news_item_image_view), 0)).perform(click()); // кликаем по кнопке раскрытия 1ой новости
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_description_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).check(matches(withText("Отредактированный текст"))); // проверяем что описание изменилось
    }

    @Test
    @DisplayName("Редактирование категории новости")
    public void EditNewsCategory() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображается Панель управления
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.edit_news_item_image_view), 1)).perform(click()); // кликаем по кнопке редактирования 2ой новости
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click()); // кликаем по полю Категория
        onView(allOf(withHint("Категория"))).perform(replaceText("Другая")); // вводим новую категорию
        closeSoftKeyboard(); // скрываем клавиатуру ввода
        Thread.sleep(5000);
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.save_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.save_button)).perform(click()); // кликаем по кнопке Сохранить
        onView(withText("Сохранение не удалось. Попробуйте позднее."))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
    }

    @Test
    @DisplayName("Редактирование статуса новости")
    public void EditNewsStatus() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Новости")).perform(click()); // кликаем по Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_news_material_button)).perform(click()); // кликаем по кнопке Редактирование
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_list_recycler_view), 10000)); // проверяем что отображается Панель управления
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.edit_news_item_image_view), 1)).perform(click()); // кликаем по кнопке редактирования 2ой новости
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.switcher), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.switcher)).perform(click()); // Меняем свитчер на Не активна
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.save_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.save_button)).perform(click()); // кликаем по кнопке Сохранить
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_published_text_view), 1)).check(matches(withText("Не активна"))); // проверяем что статус изменился
    }


}


