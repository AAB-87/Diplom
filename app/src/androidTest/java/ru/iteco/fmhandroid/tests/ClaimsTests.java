package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.utils.SwipeActions.ViewAfterSwipe;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.data.ClaimsData;
import ru.iteco.fmhandroid.page.AuthorizationPage;
import ru.iteco.fmhandroid.page.ClaimsPage;
import ru.iteco.fmhandroid.utils.FillInFieldsForCreateClaims;
import ru.iteco.fmhandroid.utils.StartApp;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class ClaimsTests extends RunRuleTest {

    // Общие правила
    // ViewMatcher - находим, определяем элемент в иерархии
    // ViewActions - производим действие с элементом
    // ViewAssertions - проверяем состояние найденного элемента

    static ClaimsPage claims = new ClaimsPage();
    static ClaimsData.FieldsForClaims fields = new ClaimsData.FieldsForClaims();

    @Before
    public void isAuthorizationPage() throws InterruptedException {
        AuthorizationPage.start();
        StartApp.logInWithValidData(); // если окно отображается, входим в приложение
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Открытие страницы заявок")
    public void shouldOpenClaimsPage() throws InterruptedException {
        claims.openClaimsPage();
        claims.checkOpenClaimsPage();
    }

    @Test
    @DisplayName("Просмотр первой заявки")
    public void shouldViewFirstClaims() throws InterruptedException {
        claims.openClaimsPage();
        claims.viewFirstClaims();
        claims.checkAuthorClaims();
    }

    @Test
    @DisplayName("Открытие окна для фильтрации заявок")
    public void shouldOpenFilterClaims() throws InterruptedException {
        claims.openClaimsPage();
        claims.clickFilterButton();
        claims.checkFilterWindow();
    }

    @Test
    @DisplayName("Фильтрация заявок")
    public void shouldFilterClaims() throws InterruptedException {
        claims.openClaimsPage();
        claims.clickFilterButton();
        claims.filterByInProgressClaims();
        claims.clickOkButton();
        claims.expectationSecondClaims();
        claims.checkByInProgressClaims();
    }

    @Test
    @DisplayName("Фильтрация заявкок без выбора статуса")
    public void shouldFilterClaimsWithoutStatusSelection() throws InterruptedException {
        claims.openClaimsPage();
        claims.clickFilterButton();
        claims.filterByInProgressClaims();
        claims.filterByOpenClaims();
        claims.checkMessageNothing();
    }

    @Test
    @DisplayName("Открытие окна создания заявки")
    public void shouldOpenCreateClaimsScreen() {
        claims.openCreateClaims();
        claims.viewTitleCreateText();
    }

    @Test
    @DisplayName("Создание заявки")
    public void shouldCreateClaimsWithValidData() {
        String emptyTitle = "no";
        String title = "Убрать мусор1";
        String emptyExecutor = "no";
        String withExecutorChoice = "yes";
        String chosenExecutor = "Ivanov Ivan Ivanovich";
        String executor = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "Убрать мусор в столовой";

        claims.openCreateClaims();
        FillInFieldsForCreateClaims.FillInFieldsClaims(emptyTitle, title, emptyExecutor, withExecutorChoice, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        claims.clickSaveButton();
        claims.clickFilterButton();
        claims.filterByInProgressClaims();
        claims.clickOkButton();
        ViewAfterSwipe(onView(withText(fields.getTitle)), 2, true);
    }

    @Test
    @DisplayName("Редактирование заявки") // БАГ
    public void shouldEditClaim() throws InterruptedException {
        claims.openClaimsPage();
        claims.clickFilterButton();
        claims.filterByOpenClaims();
        Thread.sleep(1000);
        claims.openSecondClaims();
        ViewAfterSwipe(onView(withText("7")), 4, true);
        claims.editClaimsButton();
        claims.editAndCheckClaimsDate();
    }

    @Test
    @DisplayName("Добавление комментария")
    // перед запуском поменяй комментарий в 2х методах
    public void shouldAddComment() throws InterruptedException {
        claims.openClaimsPage();
        claims.openFirstClaims();
        claims.addComment();
        claims.checkAddComment();
    }

    @Test
    @DisplayName("Редактирование комменария")
    // перед запуском поменяй комментарий в 2х местах
    public void shouldEditComment() throws InterruptedException {
        claims.openClaimsPage();
        claims.openFirstClaims();
        claims.editComment();
        claims.checkComment();
    }

    @Test
    @DisplayName("Смена статуса заявки")
    // перед запуском необходимо убедиться что в первой заявке (после фильтрации) есть минимум 2 комментария
    public void shouldChangeStatusClaim() throws InterruptedException {
        claims.openClaimsPage();
        claims.clickFilterButton();
        claims.filterByOpenClaims();
        claims.openFirstClaims();
        ViewAfterSwipe(onView(withText("Для смены статуса")), 4, true);
        claims.changeStatus();
        claims.swipeAndCheckStatus();
    }

    @Test
    @DisplayName("Создание заявки с пустыми полями")
    public void shouldCreateClaimsWithEmptyData() {
        claims.openCreateClaims();
        claims.clickSaveButton();
        claims.messageEmptyFields(activityTestRule);
    }

    @Test
    @DisplayName("Отмена создания заявки")
    public void shouldCancelCreateClaim() {
        claims.openCreateClaims();
        claims.clickCancelButton();
        claims.messageChangesNotSaved(activityTestRule);
        claims.waitingButtonToAppear();
        claims.checkOpenClaimsPage();
    }


}
