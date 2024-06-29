package com.taxicar.views.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.taxicar.views.screens.MainScreen
import com.taxicar.views.screens.carsearchscreens.CarSearchProgressScreen
import com.taxicar.views.screens.carsearchscreens.CurrentTripScreen
import com.taxicar.views.screens.carsearchscreens.TripSuggestedScreen
import com.taxicar.views.screens.online.ChatFeedSwipeContainerToRevealUnderneath
import com.taxicar.views.screens.online.ChatListScreen
import com.taxicar.views.screens.online.DatingSettingsScreen
import com.taxicar.views.screens.online.MessengerSettingsScreen
import com.taxicar.views.screens.personaldata.PersonalDataScreen
import com.taxicar.views.screens.profilesetting.BlackListScreen
import com.taxicar.views.screens.profilesetting.ChangeCityScreen
import com.taxicar.views.screens.profilesetting.ChangePasswordScreen
import com.taxicar.views.screens.profilesetting.ProfileSettingScreen
import com.taxicar.views.screens.ridehistoryscreens.RideHistoryScreen
import com.taxicar.views.screens.routebuildscreens.LastDestinationPointScreen
import com.taxicar.views.screens.routebuildscreens.PickLocationOnMapScreen
import com.taxicar.views.screens.routebuildscreens.RouteCreateScreen

val LocalNavController = compositionLocalOf<NavController> { error("No NavController provided") }
val isSwipeHandled = compositionLocalOf { false }

@Composable
fun Navigation() {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalNavController provides navController,
    ) {
        NavHost(navController = navController, startDestination = "start_page") {
            composable("start_page") {

                val mainScreenViewModel = viewModel<com.taxicar.view.models.MainScreenViewModel>()
                MainScreen(mainScreenViewModel = mainScreenViewModel)
            }

            composable("last_destination_point_screen") {

                LastDestinationPointScreen()
            }

            composable(
                "pick_location_point_screen/{searchLabel}",
                arguments = listOf(navArgument("searchLabel") {
                    type = NavType.StringType
                })
            ) { entry ->
                PickLocationOnMapScreen(searchLabel = entry.arguments?.getString("searchLabel"))
            }

            composable("create_route_screen") {
                RouteCreateScreen()
            }

            composable("car_search_progress_screen") {
                val carSearchViewModel = viewModel<com.taxicar.view.models.CarSearchViewModel>()
                CarSearchProgressScreen(carSearchViewModel = carSearchViewModel)
            }

            composable("trip_suggested_screen") {
                TripSuggestedScreen()
            }

            composable("current_trip_screen") {
                CurrentTripScreen()
            }

            composable("personal_data_screen") {
                PersonalDataScreen()
            }

            composable("profile_setting_screen") {
                ProfileSettingScreen()
            }

            composable("black_list_screen") {
                BlackListScreen()
            }

            composable("change_city_screen") {
                ChangeCityScreen()
            }

            composable("change_password_screen") {
                ChangePasswordScreen()
            }

            composable("dating_settings_screen") {
                DatingSettingsScreen()
            }

            composable("chat_list_screen") {
                ChatListScreen()
            }

            composable("chat_feed_screen") {
                ChatFeedSwipeContainerToRevealUnderneath()
            }

            composable("messenger_setting_screen") {
                MessengerSettingsScreen()
            }

            composable("ride_history_screen") {
                RideHistoryScreen()
            }
        }
    }
}