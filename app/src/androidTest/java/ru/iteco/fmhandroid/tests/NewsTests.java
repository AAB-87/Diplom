package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.data.NewsData;
import ru.iteco.fmhandroid.page.AuthorizationPage;
import ru.iteco.fmhandroid.page.NewsPage;
import ru.iteco.fmhandroid.utils.FillInFieldsForCreateNews;
import ru.iteco.fmhandroid.utils.StartApp;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class NewsTests extends RunRuleTest {

    static NewsData.DataInNewsList data = new NewsData.DataInNewsList();
    static NewsPage newsPage = new NewsPage();

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
    @DisplayName("Открытие страницы новостей")
    public void shouldOpenNewsPage() {
        newsPage.openNewsPage();
        newsPage.checkOpenListNews();
    }

    @Test
    @DisplayName("Просмотр описания первой новости")
    public void shouldViewDescriptionsFirstNews() {
        newsPage.openNewsPage();
        newsPage.openFirstNews();
        newsPage.checkViewDescriptionFirstNews();
    }

    @Test
    @DisplayName("Просмотр описания шестой новости")
    public void shouldViewDescriptionsSixthNews() {
        newsPage.openNewsPage();
        newsPage.openSixthNews();
        newsPage.checkViewDescriptionSixthNews();
    }

    @Test
    @DisplayName("Сортировка новостей") // БАГ
    // перед запуском проверь что ожидаемая дата самая ранняя в списке новостей
    public void shouldSortNews() throws InterruptedException {
        newsPage.openNewsPage();
        newsPage.clickSortNews();
        newsPage.checkFirstDate();
    }

    @Test
    @DisplayName("Открытие фильтра новостей")
    public void shouldOpenFilterNewsScreen() {
        newsPage.openNewsPage();
        newsPage.clickOpenFilter();
    }

    @Test
    @DisplayName("Фильтрация новостей по категории")
    public void shouldFilterNewsByCategory() {
        newsPage.openNewsPage();
        newsPage.clickOpenFilter();
        newsPage.selectCategory();
//        newsPage.clickFirstNews();
        newsPage.checkCategorySalary();
    }

    @Test
    @DisplayName("Фильтрация новостей по несуществующей категории") // БАГ
    public void shouldFilterNewsByNonExistentCategory() {
        newsPage.openNewsPage();
        newsPage.clickOpenFilter();
        newsPage.selectNonexistentCategory();
        onView(withText(data.getTextNonExistentCategory()))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
    }

    @Test
    @DisplayName("Отмена фильтрации")
    public void shouldCancelFilter() {
        newsPage.openNewsPage();
        newsPage.clickOpenFilter();
        newsPage.selectBirthdayCategory();
        newsPage.checkOpenListNews();
    }

    @Test
    @DisplayName("Фильтрация новостей по дате")
    // перед запуском поменять дату в последней строке на самую позднюю в списске новостей
    public void shouldFilterNewsByDate() {
        newsPage.openNewsPage();
        newsPage.clickOpenFilter();
        newsPage.selectDate();
        newsPage.checkCurrentDate();
    }

    @Test
    @DisplayName("Открытие страницы ПУ")
    public void shouldOpenControlPanelScreen() {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.checkTitleControlPanel();
    }

    @Test
    @DisplayName("Просмотр описания первой новости в ПУ")
    public void shouldViewDescriptionFirstNewsInControlPanel() {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.clickFirstNews();
        newsPage.checkViewDescriptionFirstNews();
    }

    @Test
    @DisplayName("Сортировка новостей в ПУ") // БАГ
    // перед запуском проверь что ожидаемая дата самая ранняя в списке новостей
    public void shouldSortNewsInControlPanel() throws InterruptedException {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.checkTitleControlPanel();
        newsPage.clickSortNews();
        newsPage.checkFirstDateInControlPanel();
    }

    @Test
    @DisplayName("Фильтрация новостей по статусу в ПУ")
    public void shouldFilterNewsInControlPanelByStatus() {
        // перед запуском убедись что статус 1ой новости Активна
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.clickOpenFilter();
        newsPage.clickCheckbox();
        newsPage.checkStatusFirstNews();
    }

    @Test
    @DisplayName("Фильтрация новостей по дате в ПУ")
    // перед запуском поменять дату в последней строке на текущую
    public void shouldFilterNewsInControlPanelByDate() throws InterruptedException {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.clickOpenFilter();
        newsPage.selectCurrentDate();
        Thread.sleep(2000);
        newsPage.checkPublicationDateFirstNews();
    }

    @Test
    @DisplayName("Фильтрация новостей по категории в ПУ")
    // перед запуском необходимо убедиться что после фильтрации по категории, в списке новостей первая новость с заголовком Зарплата
    public void shouldFilterNewsInControlPanelByCategory() throws InterruptedException {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.clickOpenFilter();
        newsPage.selectCategory();
        Thread.sleep(2000);
        newsPage.checkCategorySalary();
    }

    @Test
    @DisplayName("Создание новости")
//    перед запуском необходимо убедиться что после фильтрации по категории, в списке новостей первая БУДЕТ с заголовком Зарплата
    public void shouldCreateNews() throws InterruptedException {
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

        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.сlickCreateNewsButton();
        FillInFieldsForCreateNews.FillInFieldsNews(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description); // заполняем поля окна создания новости
        newsPage.сlickSaveButton();
        newsPage.clickOpenFilter();
        newsPage.selectCategory();
        newsPage.checkCategorySalary();
    }

    @Test
    @DisplayName("Удаление новости")
    public void shouldDeleteNews() throws InterruptedException {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.deleteFirstNews();
        newsPage.checkDeleteFirstNews();
    }

    @Test
    @DisplayName("Редактирование описания новости")
    public void shouldEditNewsDescription() {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.editFirstNews();
        newsPage.clickViewNewsButton();
        newsPage.checkEditText();
    }

    @Test
    @DisplayName("Изменение категории новости")
    // тест работатет, но проверить изменения можно только по иконки. Реализацию проверки смены иконки не нашёл.
    public void shouldChangNewsCategory() {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.editCategory();
        newsPage.сlickSaveButton();
    }

    @Test
    @DisplayName("Изменение категории новости на несуществующую в списке")
    public void shouldChangNewsCategoryToNonExistentOneInList() {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.editCategoryToNonexistent();
        newsPage.сlickSaveButton();
        onView(withText(data.getSaveFailedMessages()))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
    }

    @Test
    @DisplayName("Редактирование статуса новости")
    public void shouldEditNewsStatus() throws InterruptedException {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.changeSwitcher();
        newsPage.сlickSaveButton();
        Thread.sleep(2000);
        newsPage.checkStatusSecondNews();
    }

    @Test
    @DisplayName("Редактирование даты создания новости")
    // перед запуском смени дату на текущую и убедись что фильтруемые категории имеются в списке новостей c нужной датой
    public void shouldEditNewsCreationDate() throws InterruptedException {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.clickOpenFilter();
        newsPage.changeCategory();
        newsPage.checkCategoryText();
        newsPage.editDateFirstNews();
        newsPage.сlickSaveButton();
        Thread.sleep(1000);
        newsPage.checkPublicationDateFirstNews();
    }

}
