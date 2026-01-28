package com.example.pizza_mityushin_shift_2026.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pizza_mityushin_shift_2026.R
import com.example.theme.theme.Pizzamityushinshift2026Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pizzamityushinshift2026Theme {
                val navController = rememberNavController()
                val currentRoute = rememberSaveable { mutableStateOf(NavigationOption.MAIN) }

                LaunchedEffect(key1 = Unit) {
                    navController.addOnDestinationChangedListener { _, destination, _ ->
                        val openedOption = NavigationOption.entries.firstOrNull { destination.hasRoute(it.route) }

                        if (openedOption != null) {
                            currentRoute.value = openedOption
                        }
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(
                                top = innerPadding.calculateTopPadding(),
                                bottom = innerPadding.calculateBottomPadding(),
                                start = 16.dp,
                                end = 16.dp
                            )
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = MainRoute
                        ) {
                            animatedComposable<MainRoute> {
                                MainScreen(
                                    mainViewModel = koinViewModel(),
                                    onItemClick = { pizzaId ->
                                        navController.navigate(CardRoute(pizzaId))
                                    }
                                )
                            }
                            animatedComposable<CardRoute> {
                                CardScreen(
                                    cardViewModel = koinViewModel(),
                                    onBackClick = {
                                        navController.navigateUp())
                                    }
                                )
                            }
                        }
                    }
                    BottomNavigation(
                        navigationOptions = NavigationOption.entries,
                        selectedNavigationOption = currentRoute.value,
                        onItemClicked = { navOption ->
                            when (navOption) {
                                NavigationOption.MAIN -> navController.openPoppingAllPrevious(MainRoute)
                                NavigationOption.ORDERS -> navController.openPoppingAllPrevious(OrdersRoute)
                                NavigationOption.BASKET -> navController.openPoppingAllPrevious(BasketRoute)
                                NavigationOption.PROFILE -> navController.openPoppingAllPrevious(ProfileRoute)
                            }

                            currentRoute.value = navOption
                        }
                    )
                }
            }
        }
    }
}

inline fun <reified T : Any> NavGraphBuilder.animatedComposable(noinline block: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit) {
    composable<T>(
        enterTransition = ENTER_TRANSITION,
        exitTransition = EXIT_TRANSITION,
        popEnterTransition = POP_ENTER_TRANSITION,
        popExitTransition = POP_EXIT_TRANSITION,
        content = block
    )
}

@Composable
private fun BottomNavigation(
    navigationOptions: List<NavigationOption>,
    selectedNavigationOption: NavigationOption,
    onItemClicked: (NavigationOption) -> Unit,
) {
    NavigationBar {
        for (option in navigationOptions) {
            NavigationBarItem(
                selected = selectedNavigationOption == option,
                onClick = { onItemClicked(option) },
                icon = { Icon(getIcon(option), "") },
                label = { Text(text = getLabel(option)) }
            )
        }
    }
}

@Composable
private fun getIcon(option: NavigationOption): Painter =
    when (option) {
        NavigationOption.MAIN    -> painterResource(R.drawable.pizza)
        NavigationOption.ORDERS  -> painterResource(R.drawable.orders)
        NavigationOption.BASKET  -> painterResource(R.drawable.basket)
        NavigationOption.PROFILE -> painterResource(R.drawable.profile)
    }

@Composable
private fun getLabel(option: NavigationOption): String = stringResource(
    when (option) {
        NavigationOption.MAIN    -> R.string.app_bottom_bar_main
        NavigationOption.ORDERS -> R.string.app_bottom_bar_orders
        NavigationOption.BASKET    -> R.string.app_bottom_bar_basket
        NavigationOption.PROFILE -> R.string.app_bottom_bar_profile
    }
)

fun NavController.openPoppingAllPrevious(route: Any) {
    this.navigate(route) {
        popUpTo(graph.startDestinationId)
        launchSingleTop = true
    }
}