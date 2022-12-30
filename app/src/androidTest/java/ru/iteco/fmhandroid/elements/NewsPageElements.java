package ru.iteco.fmhandroid.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;

import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.utils.WithIndex.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsPageElements {

    // кнопки
    public static ViewInteraction filterButton = onView(withId(R.id.filter_button)); // кнопка фильтрации
    public static ViewInteraction clickCancelFilter = onView(withId(R.id.cancel_button)); // кнопка отмены фильтрации
    public static ViewInteraction createNewsButton = onView(withId(R.id.add_news_image_view)); // кнопка создания новости
    public static ViewInteraction saveNewsButton = onView(withId(R.id.save_button)); // кнопка сохранения новости
    public static ViewInteraction selectionConfirmationButton = onView(withId(android.R.id.button1)); // кнопка подтверждения ОК


    public static ViewInteraction deleteFirstNewsButton = onView(withIndex(withId(R.id.delete_news_item_image_view), 0)); // кнопка удаления 1ой новости
    public static ViewInteraction listNews = onView(withId(R.id.container_list_news_include)); // главная страница новостей
    public static ViewInteraction titleFirstNews = onView(withIndex(withId(R.id.news_item_title_text_view), 0)); // с помощью утилиты находим 1ую новость
    public static ViewInteraction textControlPanel = onView(withId(R.id.container_custom_app_bar_include_on_fragment_news_control_panel));  // текст Панель управления
    public static ViewInteraction checkboxActive = onView(withId(R.id.filter_news_active_material_check_box)); // чек-бокс Активна
    public static ViewInteraction textSalary = onView(allOf(withHint("Зарплата"))); // текст Заголовка - Зарплата
    public static ViewInteraction editFirstNews = onView(withIndex(withId(R.id.edit_news_item_image_view), 0)); // кнопка редактирования 1ой новости
    public static ViewInteraction viewNewsButton = onView(withIndex(withId(R.id.view_news_item_image_view), 0)); // с помощью утилиты находим в списке 1ую кнопку раскрытия новости
    public static ViewInteraction statusFirstNews = onView(withIndex(withId(R.id.news_item_published_text_view), 0)); // с помощью утилиты находим статус в 1ой новости
    public static ViewInteraction statusSecondNews = onView(withIndex(withId(R.id.news_item_published_text_view), 1)); // с помощью утилиты находим статус в 2ой новости
    public static ViewInteraction editFirstNews1 = onView(withIndex(withId(R.id.edit_news_item_image_view), 0)); // кнопка редактирования 1ой новости
    public static ViewInteraction titleFirstNews1 = onView(withIndex(withId(R.id.news_item_title_text_view), 0)); // с помощью утилиты находим 1ую новость
    public static ViewInteraction titleSecondNews = onView(withIndex(withId(R.id.news_item_title_text_view), 2)); // с помощью утилиты находим 2ую новость
    public static ViewInteraction titleFirstNews4 = onView(withIndex(withId(R.id.news_item_title_text_view), 0)); // с помощью утилиты находим 1ую новость
    public static ViewInteraction descriptionFirstNews1 = onView(withIndex(withId(R.id.news_item_description_text_view), 0)); // с помощью утилиты находим описание в 1ой новости в списке
    public static ViewInteraction descriptionFirstNews2 = onView(withIndex(withId(R.id.news_item_description_text_view), 0)); // с помощью утилиты находим описание в 1ой новости в списке
    public static ViewInteraction descriptionFirstNews3 = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)); // с помощью утилиты находим описание в 2ой новости в списке
    public static ViewInteraction descriptionField = onView(withId(R.id.news_item_description_text_input_edit_text)); // поле описания Новости
    public static ViewInteraction descriptionFirstNews = onView(withIndex(withId(R.id.news_item_description_text_view), 0)); // с помощью утилиты находим описание в 1ой новости в списке
    public static ViewInteraction descriptionSixthNews = onView(withIndex(withId(R.id.news_item_description_text_view), 5)); // с помощью утилиты находим описание в 6ой новости в списке
    public static ViewInteraction publicationDate1News = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)); // с помощью утилиты находим описание в 2ой новости в списке


}