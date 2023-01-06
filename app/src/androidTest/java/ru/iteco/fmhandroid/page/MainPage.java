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

    public static MainPage mainPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.container_list_news_include_on_fragment_main), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.container_list_news_include_on_fragment_main)).check(matches(isDisplayed())); // проверяем что отображается блок новостей
        return new MainPage();
    }

    public static MainPage openMainPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.container_list_news_include_on_fragment_main), 10000)); // ожидаем появление нужного элемента
        return new MainPage();
    }

    public static MainPage checkViewNewsBlock() {
        onView(withText("Новости")).check(matches(isDisplayed())); // проверяем что заголовок Новости отображается
        return new MainPage();
    }

    public static MainPage checkViewClaimsBlock() {
        onView(withText("Заявки")).check(matches(isDisplayed())); // проверяем что заголовок Заявки отображается
        return new MainPage();
    }

    public static MainPage goNewsBlock() {
        onView(withText("ВСЕ НОВОСТИ")).perform(click()); // кликаем по Все Новости
        return new MainPage();
    }

    public static MainPage checkViewNewsList() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.container_list_news_include), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.container_list_news_include)).check(matches(isDisplayed())); // проверяем что заголовок Новости отображается
        return new MainPage();
    }

    public static MainPage goClaimsBlock() {
        onView(withText("ВСЕ ЗАЯВКИ")).perform(click()); // кликаем по Все Заявки
        return new MainPage();
    }

    public static MainPage checkViewClaimsList() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_recycler_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed())); // проверяем что заголовок Новости отображается
        return new MainPage();
    }

    public static MainPage openFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_material_card_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_material_card_view), 0)).perform(click()); // с помощью утилиты находим 1ю новость в списке
        return new MainPage();
    }

    public static MainPage viewDescriptionFirstNews() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_description_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).perform(click()); // с помощью утилиты находим описание 1ой новости в списке
        return new MainPage();
    }

    public static MainPage swipeClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_swipe_refresh), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_swipe_refresh)).perform(swipeUp()); // прокручиваем страницу для видимости нужного элемента
        return new MainPage();
    }

    public static MainPage hideNewsBlock() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.expand_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.expand_material_button), 0)).perform(click()); // кликаем по кнопке сокрытия блока новостей
        return new MainPage();
    }

    public static MainPage checkHideNewsBlock() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.news_item_material_card_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.news_item_material_card_view), 0)).check(matches(not(isDisplayed()))); // проверяем что блок новостей не отображается
        return new MainPage();
    }

    public static MainPage hideClaimsBlock() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.expand_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.expand_material_button), 1)).perform(click()); // кликаем по кнопке сокрытия блока заявок
        return new MainPage();
    }

    public static MainPage checkHideClaimsBlock() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_card), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.claim_list_card), 1)).check(matches(not(isDisplayed()))); // проверяем что блок заявок не отображается
        return new MainPage();
    }

    public static MainPage openFourthClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_card), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.claim_list_card), 3)).perform(click()); // с помощью утилиты находим 4ю заявку в списке и кликаем по ней
        return new MainPage();
    }

    public static MainPage expectedTitleClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.title_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.title_text_view)).check(matches(isDisplayed())); // проверяем что заголовок заявки отображается
        return new MainPage();
    }

    public static MainPage openCreationClaimPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.add_new_claim_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_new_claim_material_button)).perform(click()); // кликаем по кнопке создания заявки
        return new MainPage();
    }

    public static MainPage checkViewCreateClaimPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.title_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.title_edit_text)).check(matches(isDisplayed())); // проверяем что страница создания заявки отображается
        return new MainPage();
    }



}
