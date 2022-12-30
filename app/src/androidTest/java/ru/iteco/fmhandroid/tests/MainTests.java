package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.hamcrest.Matchers.not;

import androidx.test.rule.ActivityTestRule;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.elements.MainPageElements;
import ru.iteco.fmhandroid.page.MainPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.StartApp;
import ru.iteco.fmhandroid.page.AuthorizationPage;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class MainTests {

    @Rule // указываем какое приложение будем запускать. Cм activity в AndroidManifest.xml
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    // Общие правила
    // ViewMatcher - находим, определяем элемент в иерархии
    // ViewActions - производим действие с элементом
    // ViewAssertions - проверяем состояние найденного элемента

    @Before
    public void isAuthorizationPage() throws InterruptedException {
        AuthorizationPage.start();
        StartApp.logInWithValidData(); // если окно отображается, входим в приложение
        Thread.sleep(3000);
    }

    @Test
    @DisplayName("Блок новостей и заявок отображается в главном меню")
    public void shouldNewsBlockAndClaimsBlockExist() {
        MainPage.openMainPage();
        MainPageElements.viewNewsBlock.check(matches(isDisplayed()));
        MainPageElements.viewClaimsBlock.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Переход во все новости")
    public void shouldGoToNewsBlock() {
        MainPage.openMainPage().goNewsBlock();
        MainPageElements.viewNewsList.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Переход во все заявки")
    public void shouldGoToClaimsBlock() {
        MainPage.openMainPage().goClaimsBlock();
        MainPageElements.viewClaimsList.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Раскрытие первой новости")
    public void shouldRevealFirstNews() {
        MainPage.openMainPage();
        MainPageElements.openFirstNews.perform(click());
        MainPageElements.descriptionFirstNews.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Открытие четвёртой заявки")
    public void shouldOpenFourthClaim() {
        MainPage.openMainPage().swipeClaims().openFourthClaims().expectedTitleClaims();
    }

    @Test
    @DisplayName("Скрытие блока новости")
    public void shouldHideBlockNews() {
        MainPage.openMainPage();
        MainPageElements.arrowOfNews.perform(click());
        MainPageElements.noNewsBlock.check(matches(not(isDisplayed())));
    }

    @Test
    @DisplayName("Скрытие блока заявок")
    public void shouldHideBlockClaims() {
        MainPage.openMainPage();
        MainPageElements.arrowOfClaims.perform(click());
        MainPageElements.noClaimsBlock.check(matches(not(isDisplayed())));
    }

    @Test
    @DisplayName("Переход к созданию заявки")
    public void shouldClickCreateClaim() {
        MainPage.openMainPage().openCreationClaimPage();
        MainPageElements.viewCreateClaimPage.check(matches(isDisplayed()));
    }


}