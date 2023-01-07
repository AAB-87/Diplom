package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.utils.SwipeActions.ViewAfterSwipe;
import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import androidx.test.rule.ActivityTestRule;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.data.ClaimsData;
import ru.iteco.fmhandroid.tests.RunRuleTest;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.utils.ViewActions;

public class ClaimsPage extends RunRuleTest {

    static ClaimsData.StatusData data = new ClaimsData.StatusData();

    public static void openClaimsPage() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Заявки")).perform(click()); // кликаем по Заявки
        Thread.sleep(2000);
    }

    public static void checkOpenClaimsPage() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_recycler_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed())); // страница заявок отображается
    }

    public static void viewFirstClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.plan_date_material_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.plan_date_material_text_view), 0)).perform(click()); // с помощью утилиты находим в списке 1ую заявку и кликаем по ней
    }

    public static void checkAuthorClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.executor_name_label_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.executor_name_label_text_view)).check(matches(isDisplayed())); // проверяем что автор заявки отображается
    }

    public static void clickFilterButton() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filters_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.filters_material_button)).perform(click()); // кликаем по кнопке открытия фильтра
    }

    public static void checkFilterWindow() {
        onView(withId(R.id.claim_filter_dialog_title)).check(matches(isDisplayed())); // проверяем что окно Фильтрация отображается
    }

    public static void filterByOpenClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.item_filter_in_progress), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.item_filter_in_progress)).perform(click()); // кликаем по чек-боксу В работе
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_filter_ok_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.claim_list_filter_ok_material_button)).perform(click()); // кликаем по кнопке ОК
    }

    public static void clickOkButton() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_filter_ok_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.claim_list_filter_ok_material_button)).perform(click()); // кликаем по кнопке ОК
    }

    public static void checkByInProgressClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.status_label_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.status_label_text_view)).check(matches(withText(data.getAtWorkText()))); // проверяем статус заявки
    }

    public static void swipeAndCheckStatus() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_comments_list_recycler_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.claim_comments_list_recycler_view)).perform(swipeDown()); // свайпаем вверх
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.status_label_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.status_label_text_view)).check(matches(withText(data.getAtWorkText()))); // проверяем статус заявки
    }

    public static void filterByInProgressClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.item_filter_open), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.item_filter_open)).perform(click()); // кликаем по чек-боксу Открыта
    }

    public static void checkMessageNothing() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.empty_claim_list_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.empty_claim_list_text_view)).check(matches(isDisplayed())); // отображается сообщение Здесь ничего нет
    }

    public static void viewTitleCreateText() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.title_edit_text), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.title_edit_text)).check(matches(isDisplayed())); // отображается поле категории заявки
    }

    public static void clickSaveButton() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.save_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.save_button)).perform(click()); // кликаем по кнопке Сохранить
    }

    public static void openSecondClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.plan_date_material_text_view), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.plan_date_material_text_view), 1)).perform(click()); // кнопка открытия 2ой заявкиой
    }

    public static void editClaimsButton() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.edit_processing_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.edit_processing_image_button)).perform(click()); // кнопка редактирования заявки
    }

    public static void openCreateClaims() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.main_menu_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.main_menu_image_button)).perform(click()); // кликаем по кнопке Меню
        onView(withText("Заявки")).perform(click()); // кликаем по Заявки
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed())); // проверяем что страница заявок отображается
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.filters_material_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_new_claim_material_button)).perform(click()); // кликаем по кнопке создания заявки
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.title_edit_text), 10000)); // ожидаем появление нужного элемента
    }

    public static void expectationSecondClaims() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_recycler_view), 10000)); // проверяем что отображается список заявок
        Thread.sleep(2000);
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_card), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.claim_list_card), 1)).perform(click()); // с помощью утилиты находим 1ую заявку в списке и кликаем по ней
        Thread.sleep(2000);
    }

    public static void editAndCheckClaimsDate() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.date_in_plan_text_input_edit_text), 5000)); // находим поле описания
        onView(withId(R.id.date_in_plan_text_input_edit_text)).perform(click()); // кликаем по полю даты
        onView(withText("ОК")).perform(click()); // кликаем по кнопке ОК
        onView(withId(R.id.save_button)).perform(click()); // кликаем по кнопке сохранить
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.plane_date_text_view), 5000)).check(matches(withText("27.12.2022"))); // проверяем что описание соответствует
    }

    public static void addComment() throws InterruptedException {
        ViewAfterSwipe(onView(withText("Новый")), 4, true); // свайпаем вниз до последнего комментария
        Thread.sleep(5000);
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.add_comment_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.add_comment_image_button)).perform(click()); // кликаем по кнопке добавления комментария
        onView(allOf(withHint("Комментарий"))).perform(replaceText("000")); // вписываем комментарий
        onView(withId(R.id.save_button)).perform(click()); // кликаем по кнопке Сохранить
        Thread.sleep(5000);
    }

    public static void checkAddComment() {
        ViewAfterSwipe(onView(withText("Новый")), 4, true); // свайпаем вниз до последнего комментария
        onView(withId(R.id.add_comment_image_button)).check(matches(isDisplayed())); // убеждаемся что кнопка добавления комментария видна
        onView(withText("000")).check(matches(isDisplayed())); // проверяем что комментарий отображается
    }

    public static void openFirstClaims() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_swipe_refresh), 10000)); // проверяем что отображается список заявок
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.claim_list_card), 10000)); // ожидаем появление нужного элемента
        onView(withIndex(withId(R.id.claim_list_card), 0)).perform(click()); // с помощью утилиты находим 1ю заявку в списке и кликаем по ней
        Thread.sleep(3000);
    }

    public static void editComment() throws InterruptedException {
        onView(withIndex(withId(R.id.edit_comment_image_button), 0)).perform(click()); // с помощью утилиты находим кнопку редактирования для 1го комментария в списке и кликаем по ней
        Thread.sleep(5000);
        onView(allOf(withText("Редакция1"))).perform(replaceText("Редакция2")); // редактируем комментарий
        onView(withId(R.id.save_button)).check(matches(isDisplayed())); // убеждаемся что кнопка Сохранить отображается
        onView(withId(R.id.save_button)).perform(click()); // кликаем по кнопке Сохранить
        Thread.sleep(3000);
    }

    public static void checkComment() {
        onView(withText("Редакция2")).check(matches(isDisplayed())); // текст комментария
    }

    public static void changeStatus() throws InterruptedException {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.status_processing_image_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.status_processing_image_button)).perform(click()); // кликаем по кнопке смены статуса
        Thread.sleep(3000);
        onView(allOf(withText("В работу"))).perform(click()); // меняем статус на В работу
        Thread.sleep(3000);
    }

    public static void waitingButtonToAppear() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(android.R.id.button1), 10000)); // ожидаем появление нужного элемента
        onView(withId(android.R.id.button1)).perform(click()); // кликаем по кнопке OK
    }

    public static void clickCancelButton() {
        onView(isRoot()).perform(ViewActions.waitElement(withId(R.id.cancel_button), 10000)); // ожидаем появление нужного элемента
        onView(withId(R.id.cancel_button)).perform(click()); // кликаем по кнопке ОТМЕНИТЬ
    }

    public static void messageEmptyFields(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("Заполните пустые поля"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    public static void messageChangesNotSaved(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("Изменения не будут сохранены. Вы действительно хотите выйти?"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }


}
