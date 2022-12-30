package ru.iteco.fmhandroid.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import ru.iteco.fmhandroid.R;

import androidx.test.espresso.ViewInteraction;

public class ClaimsPageElements {

    // страницы
    public static ViewInteraction claimsPage = onView(withId(R.id.claim_list_recycler_view)); // страница заявок
    public static ViewInteraction filterPage = onView(withId(R.id.claim_filter_dialog_title)); // окно Фильтрация отображается
    public static ViewInteraction titleCreatePage = onView(withId(R.id.title_edit_text)); // заголовок новой заявки

    // список заявок
    public static ViewInteraction firstClaims = onView(withIndex(withId(R.id.plan_date_material_text_view), 0)); // с помощью утилиты находим в списке 1ую заявку

    // поля заявки
    public static ViewInteraction claimsAuthor = onView(withId(R.id.executor_name_label_text_view)); // автор заявки
    public static ViewInteraction claimsStatus = onView(withId(R.id.status_label_text_view)); // статус заявки
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button)); // кнопка Отменить

    // сообщения
    public static ViewInteraction messageNothing = onView(withId(R.id.empty_claim_list_text_view)); // сообщение Здесь ничего нет

    // чек-бокс
    public static ViewInteraction checkBoxOpen = onView(withId(R.id.item_filter_open)); // чек-бокс Открыта
    public static ViewInteraction checkBoxAtWork = onView(withId(R.id.item_filter_in_progress)); // чек-бокс В работе

    // кнопки
    public static ViewInteraction filterButton = onView(withId(R.id.filters_material_button)); // кнопка открытия фильтра
    public static ViewInteraction okButton = onView(withId(R.id.claim_list_filter_ok_material_button)); // кнопка ОК
    public static ViewInteraction saveButton = onView(withId(R.id.save_button)); // кнопка Сохранить
    public static ViewInteraction editClaimsButton = onView(withId(R.id.edit_processing_image_button)); // кнопка редактирования заявки
    public static ViewInteraction openSecondClaims = onView(withIndex(withId(R.id.plan_date_material_text_view), 1)); // кнопка открытия 2ой заявкиой
    public static ViewInteraction firstComment = onView(withText("Отредактированный")); // текст комментария

}
