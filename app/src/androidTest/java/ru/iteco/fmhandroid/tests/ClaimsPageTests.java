package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.utils.SwipeActions.ViewAfterSwipe;
import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

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

public class ClaimsPageTests {

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
    @DisplayName("Открытие страницы заявки")
    public void OpenClaimsPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Заявки")).perform(click()); // кликаем по Заявки
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed())); // проверяем что страница заявок отображается
    }

    @Test
    @DisplayName("Просмотр второй заявки")
    public void ViewSecondClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Заявки")).perform(click()); // кликаем по Заявки
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed())); // проверяем что страница заявок отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.description_material_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.description_material_text_view), 1)).perform(click()); // с помощью утилиты находим 2ю заявку в списке и кликаем по ней
    }

    @Test
    @DisplayName("Открытие фильтра заявкок")
    public void OpenFilterClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Заявки")).perform(click()); // кликаем по Заявки
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed())); // проверяем что страница заявок отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filters_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filters_material_button)).perform(click()); // кликаем по кнопке открытия  фильтра
        onView(withId(R.id.claim_filter_dialog_title)).check(matches(isDisplayed())); // проверяем что окно Фильтрация отображается
    }

    @Test
    @DisplayName("Фильтрация заявкок")
    public void FilterClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Заявки")).perform(click()); // кликаем по Заявки
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed())); // проверяем что страница заявок отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filters_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filters_material_button)).perform(click()); // кликаем по кнопке открытия окна фильтрации
        onView(withId(R.id.claim_filter_dialog_title)).check(matches(isDisplayed())); // проверяем что окно Фильтрация отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.item_filter_open), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.item_filter_open)).perform(click()); // убираем галочку с чек-бокса Открыта
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_filter_ok_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.claim_list_filter_ok_material_button)).perform(click()); // кликаем по кнопке ОК
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_recycler_view), 10000)); // проверяем что отображается список заявок
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_card), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.claim_list_card), 0)).perform(click()); // с помощью утилиты находим 1ю заявку в списке и кликаем по ней
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.status_label_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.status_label_text_view)).check(matches(withText("В работе"))); // проверяем что заявка имеет статус В работе
    }

    @Test
    @DisplayName("Открытие окна создания заявки")
    public void OpenCreateClaimsScreen() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Заявки")).perform(click()); // кликаем по Заявки
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed())); // проверяем что страница заявок отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filters_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_new_claim_material_button)).perform(click()); // кликаем по кнопке создания заявки
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.title_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.title_edit_text)).check(matches(isDisplayed())); // проверяем что отображается поле Title
    }

    String emptyTitle = "no";
    String title = "Убрать мусор";
    String emptyExecutor = "no";
    String withExecutorChoice = "yes";
    String chosenExecutor = "Смирнов Петр Петрович";
    String executor = "no";
    String emptyDate = "no";
    String emptyTime = "no";
    String withDialPadOrTextInput = "dial";
    String saveOrCancelTime = "save";
    String emptyDescription = "no";
    String description = "Убрать мусор в столовой";

    @Test
    @DisplayName("Создание заявки")
    public void CreateClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Заявки")).perform(click()); // кликаем по Заявки
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed())); // проверяем что страница заявок отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filters_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_new_claim_material_button)).perform(click()); // кликаем по кнопке создания заявки
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.title_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.title_edit_text)).check(matches(isDisplayed())); // проверяем что отображается поле Title
        FillInFieldsForCreateClaims.FillInFieldsClaims(emptyTitle, title, emptyExecutor, withExecutorChoice, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description); // Заполняем поля для создания Заявки
        onView(withId(R.id.save_button)).perform(click()); // кликаем по кнопке Сохранить
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.add_new_claim_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_new_claim_material_button)).check(matches(isDisplayed())); // проверяем что отображается кнопка создания новости
    }

    @Test
    @DisplayName("Добавление комментария")
    public void AddComment() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Заявки")).perform(click()); // кликаем по Заявки
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_swipe_refresh), 10000)); // проверяем что отображается список заявок
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_card), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.claim_list_card), 0)).perform(click()); // с помощью утилиты находим 1ю заявку в списке и кликаем по ней
        ViewAfterSwipe(onView(withText("Новый")), 2, true); // свайпаем вниз до последнего комментария
        Thread.sleep(5000);
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.add_comment_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_comment_image_button)).check(matches(isDisplayed())); // убеждаемся что кнопка добавления комментария отображается
        onView(withId(R.id.add_comment_image_button)).perform(click()); // кликаем по кнопке добавления комментария
        onView(withId(R.id.save_button)).check(matches(isDisplayed())); // убеждаемся что кнопка Сохранить видна
        onView(withId(R.id.comment_text_input_layout)).perform(replaceText("Новый")); // вписываем комментарий
        onView(withId(R.id.save_button)).perform(click()); // кликаем по кнопке Сохранить
        onView(withId(R.id.title_text_view)).check(matches(isDisplayed())); // убеждаемся что заголовок открытой Заявки отображается
        ViewAfterSwipe(onView(withText("Новый")), 2, true); // свайпаем вниз до последнего комментария
        Thread.sleep(5000);
        onView(withId(R.id.add_comment_image_button)).check(matches(isDisplayed())); // убеждаемся что кнопка добавления комментария видна
        onView(withText("Новый")).check(matches(isDisplayed())); // проверяем что комментарий отображается
    }

}