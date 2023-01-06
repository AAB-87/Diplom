package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.data.NewsData;
import ru.iteco.fmhandroid.elements.NewsPageElements;
import ru.iteco.fmhandroid.page.AuthorizationPage;
import ru.iteco.fmhandroid.page.NewsPage;
import ru.iteco.fmhandroid.utils.FillInFieldsForCreateNews;
import ru.iteco.fmhandroid.utils.StartApp;
import ru.iteco.fmhandroid.utils.ViewActions;

@RunWith(AllureAndroidJUnit4.class) // чтобы класс запускался как набор тестов

public class NewsTests extends RunRuleTest {

    static NewsData.DataInNewsList data = new NewsData.DataInNewsList();
    static NewsPageElements newsPageElements = new NewsPageElements();
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
        newsPageElements.listNews.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Просмотр описания первой новости")
    public void shouldViewDescriptionsFirstNews() {
        newsPage.openNewsPage();
        newsPage.openFirstNews();
        newsPageElements.descriptionFirstNews1.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Просмотр описания шестой новости")
    public void shouldViewDescriptionsSixthNews() {
        newsPage.openNewsPage();
        newsPage.openSixthNews();
        newsPageElements.descriptionSixthNews.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Сортировка новостей") // БАГ
    // перед запуском проверь что ожидаемая дата самая ранняя в списке новостей
    public void shouldSortNews() {
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
        newsPageElements.filterButton.perform(click());
        newsPageElements.titleFirstNews.perform(click());
        newsPageElements.descriptionFirstNews.check(matches(isDisplayed()));
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
        newsPage.selectCategory();
        newsPageElements.clickCancelFilter.perform(click());
        newsPageElements.listNews.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Фильтрация новостей по дате")
    // перед запуском поменять дату в последней строке на самую позднюю в списске новостей
    public void shouldFilterNewsByDate() {
        newsPage.openNewsPage();
        newsPage.clickOpenFilter();
        newsPage.selectDate();
        newsPageElements.filterButton.perform(click());
        newsPage.checkCurrentDate();
    }

    @Test
    @DisplayName("Открытие страницы ПУ")
    public void shouldOpenControlPanelScreen() {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPageElements.textControlPanel.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Просмотр описания первой новости в ПУ")
    public void shouldViewDescriptionFirstNewsInControlPanel() {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPageElements.titleFirstNews1.perform(click());
        newsPageElements.descriptionFirstNews3.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Сортировка новостей в ПУ") // БАГ
    // перед запуском проверь что ожидаемая дата самая ранняя в списке новостей
    public void shouldSortNewsInControlPanel() {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPageElements.textControlPanel.check(matches(isDisplayed()));
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
        newsPageElements.checkboxActive.perform(click());
        newsPageElements.filterButton.perform(click());
        newsPageElements.statusFirstNews.check(matches(withText(data.getStatusNotActive())));
    }

    @Test
    @DisplayName("Фильтрация новостей по дате в ПУ")
    // перед запуском поменять дату в последней строке на текущую
    public void shouldFilterNewsInControlPanelByDate() throws InterruptedException {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.clickOpenFilter();
        newsPage.selectCurrentDate();
        newsPageElements.filterButton.perform(click());
        Thread.sleep(2000);
        newsPageElements.publicationDate1News.check(matches(withText(containsString(data.getToday()))));
    }

    @Test
    @DisplayName("Фильтрация новостей по категории в ПУ")
    // перед запуском необходимо убедиться что после фильтрации по категории, в списке новостей вторая новость с заголовком Зарплата
    public void shouldFilterNewsInControlPanelByCategory() throws InterruptedException {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.clickOpenFilter();
        newsPage.selectCategory();
        newsPageElements.filterButton.perform(click());
        Thread.sleep(2000);
        newsPageElements.titleSecondNews.check(matches(withText(containsString(data.getTitleTextSalary()))));
    }

    @Test
    @DisplayName("Создание новости")
//    перед запуском необходимо убедиться что после фильтрации по категории, в списке новостей вторая новость БУДЕТ с заголовком Зарплата
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
        newsPageElements.createNewsButton.perform(click());
        FillInFieldsForCreateNews.FillInFieldsNews(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description); // заполняем поля окна создания новости
        newsPageElements.saveNewsButton.perform(click());
        newsPage.clickOpenFilter();
        newsPage.selectCategory();
        newsPageElements.filterButton.perform(click());
        newsPageElements.titleSecondNews.check(matches(withText(containsString(data.getTitleTextSalary()))));
    }

    @Test
    @DisplayName("Удаление новости")
    public void shouldDeleteNews() {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPageElements.deleteFirstNewsButton.perform(click());
        newsPageElements.selectionConfirmationButton.perform(click());
        newsPageElements.textSalary.check(doesNotExist());
    }

    @Test
    @DisplayName("Редактирование описания новости")
    public void shouldEditNewsDescription() {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPageElements.editFirstNews.perform(click());
        newsPageElements.descriptionField.perform(replaceText(data.getEditText()));
        newsPageElements.saveNewsButton.perform(click());
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.view_news_item_image_view), 10000)); // ожидаем появление нужного элемента
        newsPageElements.viewNewsButton.perform(click());
        newsPageElements.descriptionFirstNews2.check(matches(withText(data.getEditText())));
    }

    @Test
    @DisplayName("Изменение категории новости")
    // тест работатет, но проверить изменения можно только по иконки. Реализацию проверки смены иконки не нашёл.
    public void shouldChangNewsCategory() {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.editCategory();
        newsPageElements.saveNewsButton.perform(click());
    }

    @Test
    @DisplayName("Изменение категории новости на несуществующую в списке")
    public void shouldChangNewsCategoryToNonExistentOneInList() {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.editCategoryToNonexistent();
        newsPageElements.saveNewsButton.perform(click());
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
        newsPageElements.saveNewsButton.perform(click());
        Thread.sleep(2000);
        newsPageElements.statusSecondNews.check(matches(withText(data.getStatusNotActive())));
    }

    @Test
    @DisplayName("Редактирование даты создания новости")
    // перед запуском смени дату на текущую и убедись что фильтруемые категории имеются в списке новостей c нужной датой
    public void shouldEditNewsCreationDate() throws InterruptedException {
        newsPage.openNewsPage();
        newsPage.openControlPanel();
        newsPage.clickOpenFilter();
        newsPage.changeCategory();
        newsPageElements.filterButton.perform(click());
        newsPageElements.titleFirstNews4.check(matches(withText(data.getTitleAdvertisement())));
        newsPageElements.editFirstNews1.perform(click());
        newsPage.changeFirstDate();
        newsPageElements.saveNewsButton.perform(click());
        Thread.sleep(1000);
        newsPageElements.publicationDate1News.check(matches(withText(containsString(data.getToday()))));
    }

}
