package ru.iteco.fmhandroid.tests;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.page.MainPage;
import ru.iteco.fmhandroid.utils.StartApp;
import ru.iteco.fmhandroid.page.AuthorizationPage;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class MainTests extends RunRuleTest {

    // Общие правила
    // ViewMatcher - находим, определяем элемент в иерархии
    // ViewActions - производим действие с элементом
    // ViewAssertions - проверяем состояние найденного элемента

    @Before
    public void isAuthorizationPage() throws InterruptedException {
        AuthorizationPage.start();
        StartApp.logInWithValidData(); // если окно отображается, входим в приложение
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Блок новостей и заявок отображается в главном меню")
    public void shouldNewsBlockAndClaimsBlockExist() {
        MainPage.openMainPage();
        MainPage.checkViewNewsBlock();
        MainPage.checkViewClaimsBlock();
    }

    @Test
    @DisplayName("Переход во все новости")
    public void shouldGoToNewsBlock() {
        MainPage.openMainPage();
        MainPage.goNewsBlock();
        MainPage.checkViewNewsList();
    }

    @Test
    @DisplayName("Переход во все заявки")
    public void shouldGoToClaimsBlock() {
        MainPage.openMainPage();
        MainPage.goClaimsBlock();
        MainPage.checkViewClaimsList();
    }

    @Test
    @DisplayName("Раскрытие первой новости")
    public void shouldRevealFirstNews() {
        MainPage.openMainPage();
        MainPage.openFirstNews();
        MainPage.viewDescriptionFirstNews();
    }

    @Test
    @DisplayName("Открытие четвёртой заявки")
    public void shouldOpenFourthClaim() {
        MainPage.openMainPage();
        MainPage.swipeClaims();
        MainPage.openFourthClaims();
        MainPage.expectedTitleClaims();
    }

    @Test
    @DisplayName("Скрытие блока новости")
    public void shouldHideBlockNews() {
        MainPage.openMainPage();
        MainPage.hideNewsBlock();
        MainPage.checkHideNewsBlock();
    }

    @Test
    @DisplayName("Скрытие блока заявок")
    public void shouldHideBlockClaims() {
        MainPage.openMainPage();
        MainPage.hideClaimsBlock();
        MainPage.checkHideClaimsBlock();
    }

    @Test
    @DisplayName("Переход к созданию заявки")
    public void shouldClickCreateClaim() {
        MainPage.openMainPage();
        MainPage.openCreationClaimPage();
        MainPage.checkViewCreateClaimPage();
    }


}