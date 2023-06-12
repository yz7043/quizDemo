package com.andy.project1.util;

import org.springframework.data.relational.core.sql.In;

public class Constant {
    public static final String LOGIN_SESSION_KEY = "user";

    public static final String ALERT_MSG = "alertMsg";
    // get login info
    public static final String IS_LOGIN = "isLogin";
    public static final String IS_ADMIN = "isAdmin";
    public static final String LOGGED_USER = "loggedUser";

    // home page quiz
    public static final String ONGOING_QUIZ = "ongoingQuiz";
    public static final String HISTORY_QUIZ = "historyQuiz";
    public static final String ALL_CATEGORIES = "allCategories";

    // quiz result page
    public static final String QUIZ_RES = "quizRes";
    public static final String QUIZ_RES_END_TIME = "quizResEndTime";

    public static final String QUIZ_RES_SCORE = "quizResScore";

    // quiz page
    public static final String QUIZ_QUESTION = "quizQuestion";

    public static final String QUIZ_SUBMIT_QUESTION_PREFIX = "questionId"; // 0 based

    public static final String QUIZ_SUBMIT_CHOICE_PREFIX = "question"; // 1 based
    public static final Integer QUIZ_POOL_SIZE = 5;
    public static final String QUIZ_CATEGORY = "quizCategory";
    public static final String QUIZ_START_TIME = "quizStartTime";

    // Contact admin management
    public static final String ADMIN_CONTACT_LIST = "adminContactList";

    // User admin management
    public static final String ADMIN_USER_LIST = "adminUserList";

    // Quiz result admin management
    public static final String ADMIN_QUIZ_RESULT = "adminQuizList";
    public static final String ADMIN_QUIZ_RESULT_BY_CATEGORY = "adminQuizResultByCategory";
    public static final String ADMIN_QUIZ_RESULT_BY_USER = "adminQuizResultByUser";

    // Question admin management
    public static final String ADMIN_QUESTION_MGMT_QUESTIONS = "adminQuestionMgmtQuestions";
    public static final String ADMIN_QUESTION_MGMT_CATEGORIES = "adminQuestionMgmtCategories";

    public static final Integer ADMIN_ADD_QUESTION_NUMBER = 4;
    public static final String ADMIN_ADD_QUESTION_NUMBER_STR = "adminAddQuestionNumber";

    // Question modification admin
    public static final String ADMIN_MODIFY_QUESTION = "adminModifyQuestion";
    // Contact detail
    public static final String ADMIN_CONTACT_DETAIL = "adminContactDetail";
}
