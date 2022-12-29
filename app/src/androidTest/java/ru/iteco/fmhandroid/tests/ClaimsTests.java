package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.utils.SwipeActions.ViewAfterSwipe;

import androidx.test.rule.ActivityTestRule;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.data.ClaimsData;
import ru.iteco.fmhandroid.elements.ClaimsPageElements;
import ru.iteco.fmhandroid.page.AuthorizationPage;
import ru.iteco.fmhandroid.page.ClaimsPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.FillInFieldsForCreateClaims;
import ru.iteco.fmhandroid.utils.StartApp;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class ClaimsTests {

    @Rule // указываем какое приложение будем запускать. Cм activity в AndroidManifest.xml
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    // Общие правила
    // ViewMatcher - находим, определяем элемент в иерархии
    // ViewActions - производим действие с элементом
    // ViewAssertions - проверяем состояние найденного элемента

    static ClaimsPageElements element = new ClaimsPageElements();
    static ClaimsPage claims = new ClaimsPage();
    static ClaimsData.StatusData data = new ClaimsData.StatusData();
    static ClaimsData.FieldsForClaims fields = new ClaimsData.FieldsForClaims();
    static ClaimsData.ErrorMessage message = new ClaimsData.ErrorMessage();


    @Before
    public void isAuthorizationPage() throws InterruptedException {
        AuthorizationPage.start();
        StartApp.logInWithValidData(); // если окно отображается, входим в приложение
        Thread.sleep(3000);
    }

    @Test
    @DisplayName("Открытие страницы заявки")
    public void ShouldOpenClaimsPage() {
        claims.openClaimsPage();
        element.claimsPage.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Просмотр первой заявки")
    public void ShouldViewFirstClaims() throws InterruptedException {
        claims.openClaimsPage();
        Thread.sleep(2000);
        element.firstClaims.perform(click());
        Thread.sleep(2000);
        element.claimsAuthor.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Открытие фильтра заявкок")
    public void ShouldOpenFilterClaims() throws InterruptedException {
        claims.openClaimsPage();
        Thread.sleep(2000);
        element.filterButton.perform(click());
        Thread.sleep(2000);
        element.filterPage.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Фильтрация заявкок")
    public void ShouldFilterClaims() throws InterruptedException {
        claims.openClaimsPage();
        Thread.sleep(2000);
        element.filterButton.perform(click());
        Thread.sleep(2000);
        element.checkBoxOpen.perform(click());
        element.okButton.perform(click());
        Thread.sleep(2000);
        claims.expectationSecondClaims();
        Thread.sleep(4000);
        element.claimsStatus.check(matches(withText(data.getAtWorkText())));
    }

    @Test
    @DisplayName("Фильтрация заявкок без выбора статуса")
    public void ShouldFilterClaimsWithoutStatusSelection() throws InterruptedException {
        claims.openClaimsPage();
        Thread.sleep(2000);
        element.filterButton.perform(click());
        Thread.sleep(2000);
        element.checkBoxOpen.perform(click());
        element.checkBoxAtWork.perform(click());
        element.okButton.perform(click());
        Thread.sleep(2000);
        element.messageNothing.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Открытие окна создания заявки")
    public void ShouldOpenCreateClaimsScreen() {
        claims.openCreateClaims();
        element.titleCreatePage.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создание заявки")
    public void ShouldCreateClaimsWithValidData() throws InterruptedException {
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
        Thread.sleep(3000);
        element.saveButton.perform(click());
        Thread.sleep(3000);
        element.filterButton.perform(click());
        element.checkBoxOpen.perform(click());
        Thread.sleep(3000);
        element.okButton.perform(click());
        ViewAfterSwipe(onView(withText(fields.getTitle)), 2, true);
    }

    @Test
    @DisplayName("Редактирование заявки") // БАГ
    public void ShouldEditClaim() throws InterruptedException {
        claims.openClaimsPage();
        element.filterButton.perform(click());
        Thread.sleep(3000);
        element.checkBoxAtWork.perform(click());
        Thread.sleep(1000);
        element.okButton.perform(click());
        Thread.sleep(3000);
        element.openSecondClaims.perform(click());
        Thread.sleep(3000);
        ViewAfterSwipe(onView(withText("7")), 4, true);
        Thread.sleep(3000);
        element.editClaimsButton.perform(click());
        claims.editAndCheckClaimsDate();
    }

    @Test
    @DisplayName("Добавление комментария")
    // перед запуском поменяй комментарий в 2х методах
    public void AddComment() throws InterruptedException {
        claims.openClaimsPage();
        claims.openFirstClaims();
        claims.addComment();
        Thread.sleep(5000);
        claims.checkAddComment();
    }

    @Test
    @DisplayName("Редактирование комменария")
    // перед запуском поменяй комментарий в 3х местах
    public void EditComment() throws InterruptedException {
        claims.openClaimsPage();
        claims.openFirstClaims();
        Thread.sleep(2000);
        claims.editComment();
        Thread.sleep(2000);
        element.firstComment.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Смена статуса заявки") // не кликает по заявке
    public void ChangeStatusClaim() throws InterruptedException {
        String emptyTitle = "no";
        String title = "Для смены статуса";
        String emptyExecutor = "yes";
        String withExecutorChoice = "no";
        String chosenExecutor = "no";
        String executor = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "Убрать мусор в столовой";


//        claims.openClaimsPage();
        // создаём заявку со статусом открыта
        claims.openCreateClaims();
        FillInFieldsForCreateClaims.FillInFieldsClaims(emptyTitle, title, emptyExecutor, withExecutorChoice, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        Thread.sleep(3000);
        element.saveButton.perform(click());
        Thread.sleep(3000);

        // фильтруемся по статусу открыта
        element.filterButton.perform(click());
        element.checkBoxAtWork.perform(click());
        Thread.sleep(3000);
        element.okButton.perform(click());

        // ищем и открываем заявку
        ViewAfterSwipe(onView(withText(fields.getChangeStatusClaim)), 2, true);
        element.claimsForChange.perform(click());
        element.titleClaims.check(matches(isDisplayed()));

        // свайпаем вниз до последнего комментария
        ViewAfterSwipe(onView(withText("Для смены статуса")), 4, true);

        // меняем статус и возвращаемся в список заявок
        claims.changeStatus();
        element.backButton.perform(click());

        // фильтруемся по заявкам В работе
        element.filterButton.perform(click());
        Thread.sleep(3000);
        element.checkBoxAtWork.perform(click());
        element.checkBoxOpen.perform(click());
        Thread.sleep(1000);
        element.okButton.perform(click());
        Thread.sleep(3000);

        // ищем и открываем заявку
        ViewAfterSwipe(onView(withText(fields.getChangeStatusClaim)), 2, true);
        element.claimsForChange.perform(click());
        element.titleClaims.check(matches(isDisplayed()));

        // убеждаемся что заявка имеет статус открыта
        element.statusField.check(matches(withText(data.getAtWorkText)));
    }

    @Test
    @DisplayName("Создание заявки с пустыми полями")
    public void CreateClaimsWithEmptyData() throws InterruptedException {
        claims.openCreateClaims();
        element.saveButton.perform(click());
        Thread.sleep(3000);
        onView(withText(message.getEmptyFieldsText()))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Отмена создания заявки")
    public void CancelCreateClaim() {
        claims.openCreateClaims();
        element.cancelButton.perform(click());
        onView(withText(message.getConfirmationText()))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        claims.waitingButtonToAppear();
        element.claimList.check(matches(isDisplayed()));
    }


}
