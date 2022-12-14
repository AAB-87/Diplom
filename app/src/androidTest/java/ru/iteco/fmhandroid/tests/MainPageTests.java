package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.click;
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
import androidx.test.rule.ActivityTestRule;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.StartApp;
import ru.iteco.fmhandroid.utils.ViewActions;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class MainPageTests {

    AuthorizationPageTests authorization = new AuthorizationPageTests(); // создаём объект класса Authorization чтобы его использовать

    @Rule // указываем какое приложение будем запускать. Cм activity в AndroidManifest.xml
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

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
        onView(withText("Заявки")).check(matches(isDisplayed())); // проверяем что заголовок Заявки отображается
        onView(withText("ВСЕ ЗАЯВКИ")).perform(click()); // кликаем по Все Заявки
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed())); // проверяем что страница заявок отображается
    }

    @Test
    @DisplayName("Раскрытие первой новости")
    public void RevealFirstNews() {
        onView(withIndex(withId(R.id.news_item_material_card_view), 0)).perform(click()); // с помощью утилиты находим 1ю новость в списке и кликаем по ней
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).check(matches(isDisplayed())); // проверяем что описание новости отображается
    }

    @Test
    @DisplayName("Открытие четвёртой заявки")
    public void OpenFourthClaim() {
        onView(withId(R.id.main_swipe_refresh)).perform(swipeUp()); // прокручиваем страницу для видимости нужного элемента
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_card), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.claim_list_card), 3)).perform(click()); // с помощью утилиты находим 4ю заявку в списке и кликаем по ней
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.title_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.title_text_view)).check(matches(isDisplayed())); // проверяем что заголовок заявки отображается
    }

    @Test
    @DisplayName("Скрытие блока новости")
    public void HideBlockNews() {
        onView(withIndex(withId(R.id.expand_material_button), 0)).perform(click()); // кликаем по кнопке сокрытия блока новостей
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).check(matches(not(isDisplayed()))); // проверяем что последние созданные новости не отображаются
    }

    @Test
    @DisplayName("Скрытие блока заявок")
    public void HideBlockClaims() {
        onView(withIndex(withId(R.id.expand_material_button), 1)).perform(click()); // кликаем по кнопке сокрытия блока заявок
        onView(withIndex(withId(R.id.description_material_text_view), 0)).check(matches(not(isDisplayed()))); // проверяем что последние созданные заявки не отображаются
    }

    @Test
    @DisplayName("Переход к созданию заявки")
    public void ClickCreateClaim() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.add_new_claim_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_new_claim_material_button)).perform(click()); // кликаем по кнопке создания заявки
        onView(withId(R.id.title_edit_text)).check(matches(isDisplayed())); // проверяем что страница создания заявки отображается
    }

    @Test
    @DisplayName("Отмена создания заявки")
    public void CancelCreateClaim() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.add_new_claim_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_new_claim_material_button)).perform(click()); // кликаем по кнопке создания заявки
        onView(withId(R.id.title_edit_text)).check(matches(isDisplayed())); // проверяем что страница создания заявки отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.cancel_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.cancel_button)).perform(click()); // кликаем по кнопке отмена
        onView(withText("Изменения не будут сохранены. Вы действительно хотите выйти?"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // магия
        onView(isRoot()).perform(ViewActions.waitElement(withId(android.R.id.button1), 10000)); // ожидаем появление нужного элемента
        onView(withId(android.R.id.button1)).perform(click()); // кликаем по кнопке OK
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.our_mission_image_button), 10000)); // ожидаем появления нужного элемента
        onView(withId(R.id.our_mission_image_button)).check(matches(isDisplayed())); // проверяем что отображается кнопка входа в Наша миссия
    }

}