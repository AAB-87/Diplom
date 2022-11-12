package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.matcher.ViewMatchers;
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

public class MainPageTests {

    AuthorizationTests authorization = new AuthorizationTests(); // создаём объект класса Authorization чтобы его использовать

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
    @DisplayName("Блок новостей и заявок отображается в главном меню")
    public void NewsBlockAndClaimsBlockExist() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.container_list_news_include_on_fragment_main), 10000)); // ожидаем появление нужного элемента
        onView(withText("Новости")).check(matches(isDisplayed())); // проверяем что заголовок Новости отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.container_list_claim_include_on_fragment_main), 10000)); // ожидаем появление нужного элемента
        onView(withText("Заявки")).check(matches(isDisplayed())); // проверяем что заголовок Заявки отображается
    }

    @Test
    @DisplayName("Переход во все новости")
    public void GoToNewsBlock() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.container_list_news_include_on_fragment_main), 10000)); // ожидаем появление нужного элемента
        onView(withText("Новости")).check(matches(isDisplayed())); // проверяем что заголовок Новости отображается
        onView(withText("ВСЕ НОВОСТИ")).perform(click()); // кликаем по Все Новости
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что страница новостей отображается
    }

    @Test
    @DisplayName("Переход во все заявки")
    public void GoToClaimsBlock() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.container_list_news_include_on_fragment_main), 10000)); // ожидаем появление нужного элемента
        onView(withText("Заявки")).check(matches(isDisplayed())); // проверяем что заголовок Новости отображается
        onView(withText("ВСЕ ЗАЯВКИ")).perform(click()); // кликаем по Все Новости
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed())); // проверяем что страница заявок отображается
    }

    @Test
    @DisplayName("Раскрытие второй новости")
    public void RevealSecondNews() {
        onView(withIndex(withId(R.id.news_item_material_card_view), 1)).perform(click()); // с помощью утилиты находим 2ю новость в списке и кликаем по ней
        onView(withIndex(withId(R.id.news_item_description_text_view), 1)).check(matches(isDisplayed())); // проверяем что описание новости отображается
    }

    @Test
    @DisplayName("Раскрытие первой заявки")
    public void RevealFirstClaim () {
//        onView((withId(R.id.claim_list_recycler_view))).perform(actionOnItemAtPosition(claimPosition, swipeUp()));
//        onView(ViewMatchers.withText("ВСЕ ЗАЯВКИ")).perform(swipeUp());
//        onView(withIndex(withText("ВСЕ ЗАЯВКИ"), 4)).perform(scrollTo(), click());
        onView(withIndex(withId(R.id.container_list_claim_include_on_fragment_main), 3)).perform(scrollTo(), click());
//        onView(withIndex(withId(R.id.claim_list_card), 0)).perform(click()); // с помощью утилиты находим 1ю заявку в списке и кликаем по ней
//        onView(withId(R.id.title_text_view)).check(matches(isDisplayed())); // проверяем что описание заявки отображается


    }

    @Test
    @DisplayName("Раскрытие/скрытие блока новости")
    public void RevealAndHideBlockNews() {
        onView(withIndex(withId(R.id.expand_material_button), 0)).perform(doubleClick());
        // не знаю как проверить
    }

    @Test
    @DisplayName("Раскрытие/скрытие блока заявки")
    public void RevealAndHideBlockClaims() {
        onView(withIndex(withId(R.id.expand_material_button), 1)).perform(doubleClick());
        // не знаю как проверить
    }

    @Test
    @DisplayName("Переход к созданию заявки")
    public void ClickCreateClaim() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.add_new_claim_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_new_claim_material_button)).perform(click()); // кликаем по кнопке создания заявки
        onView(withId(R.id.title_edit_text)).check(matches(isDisplayed())); // проверяем что страница создания заявки отображается


    }

}