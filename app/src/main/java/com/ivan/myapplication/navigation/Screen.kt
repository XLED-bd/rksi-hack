package com.ivan.myapplication.navigation


sealed class Screen(val route: String) {
    object MainPage : Screen("mainPage")
    object ListInvestmentsPage : Screen("investmentsPage")
    object ProfilePage: Screen("profilePage")
    object SettingPage: Screen("settingPage")
}