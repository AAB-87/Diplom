package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.utils.ViewActions;

public class MainPage {

    public static void mainPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.container_list_news_include_on_fragment_main), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.container_list_news_include_on_fragment_main)).check(matches(isDisplayed())); // проверяем что отображается блок новостей
    }

    public static void openMainPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.container_list_news_include_on_fragment_main), 10000)); // ожидаем появление нужного элемента
    }

    public static void checkViewNewsBlock() {
        onView(withText("Новости")).check(matches(isDisplayed())); // проверяем что заголовок Новости отображается
    }

    public static void checkViewClaimsBlock() {
        onView(withText("Заявки")).check(matches(isDisplayed())); // проверяем что заголовок Заявки отображается
    }

    public static void goNewsBlock() {
        onView(withText("ВСЕ НОВОСТИ")).perform(click()); // кликаем по Все Новости
    }

    public static void checkViewNewsList() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.container_list_news_include), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что заголовок Новости отображается
    }

    public static void goClaimsBlock() {
        onView(withText("ВСЕ ЗАЯВКИ")).perform(click()); // кликаем по Все Заявки
    }

    public static void checkViewClaimsList() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_recycler_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed())); // проверяем что заголовок Новости отображается
    }

    public static void openFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_material_card_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_material_card_view), 0)).perform(click()); // с помощью утилиты находим 1ю новость в списке
    }

    public static void viewDescriptionFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_description_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).perform(click()); // с помощью утилиты находим описание 1ой новости в списке
    }

    public static void swipeClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_swipe_refresh), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_swipe_refresh)).perform(swipeUp()); // прокручиваем страницу для видимости нужного элемента
    }

    public static void hideNewsBlock() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.expand_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.expand_material_button), 0)).perform(click()); // кликаем по кнопке сокрытия блока новостей
    }

    public static void checkHideNewsBlock() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_material_card_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_material_card_view), 0)).check(matches(not(isDisplayed()))); // проверяем что блок новостей не отображается
    }

    public static void hideClaimsBlock() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.expand_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.expand_material_button), 1)).perform(click()); // кликаем по кнопке сокрытия блока заявок
    }

    public static void checkHideClaimsBlock() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_card), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.claim_list_card), 1)).check(matches(not(isDisplayed()))); // проверяем что блок заявок не отображается
    }

    public static void openFourthClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_card), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.claim_list_card), 3)).perform(click()); // с помощью утилиты находим 4ю заявку в списке и кликаем по ней
    }

    public static void expectedTitleClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.title_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.title_text_view)).check(matches(isDisplayed())); // проверяем что заголовок заявки отображается
    }

    public static void openCreationClaimPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.add_new_claim_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_new_claim_material_button)).perform(click()); // кликаем по кнопке создания заявки
    }

    public static void checkViewCreateClaimPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.title_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.title_edit_text)).check(matches(isDisplayed())); // проверяем что страница создания заявки отображается
    }



}
