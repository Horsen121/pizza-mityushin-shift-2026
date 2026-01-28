package com.example.pizza_mityushin_shift_2026.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.toRoute
import com.example.basket.BasketRoute
import com.example.basket.presentation.BasketScreen
import com.example.card.PizzaCardRoute
import com.example.card.presentation.PizzaCardScreen
import com.example.main.MainRoute
import com.example.main.presentation.MainScreen
import com.example.orders.OrdersRoute
import com.example.orders.presentation.OrdersScreen
import com.example.pizza_mityushin_shift_2026.R
import com.example.profile.ProfileRoute
import com.example.profile.presentation.ProfileScreen
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
                    Column {
                        NavHost(
                            modifier = Modifier
                                .weight(1f)
                                .padding(
                                    top = innerPadding.calculateTopPadding(),
                                    bottom = innerPadding.calculateBottomPadding(),
                                    start = 16.dp,
                                    end = 16.dp
                                )
                            ,
                            navController = navController,
                            startDestination = MainRoute
                        ) {
                            animatedComposable<MainRoute> {
                                MainScreen(
//                                    mainViewModel = koinViewModel(),
                                    onItemClick = { pizzaId ->
                                        navController.navigate(PizzaCardRoute(pizzaId))
                                    }
                                )
                            }
                            animatedComposable<PizzaCardRoute> {
                                val destination = it.toRoute<PizzaCardRoute>()

                                PizzaCardScreen(
//                                    cardViewModel = koinViewModel { parametersOf(destination.pizzaId) },
                                    onBackClick = { navController.navigateUp() }
                                )
                            }

                            animatedComposable<OrdersRoute> {
                                OrdersScreen()
                            }
                            animatedComposable<BasketRoute> {
                                BasketScreen()
                            }
                            animatedComposable<ProfileRoute> {
                                ProfileScreen()
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
    HorizontalDivider(
        modifier = Modifier.height(1.dp),
        color = MaterialTheme.colorScheme.outline,
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        for (option in navigationOptions) {
            val isSelected = selectedNavigationOption == option
            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClicked(option) },
                icon = {
                    Icon(
                        getIcon(option), "",
                        tint = if(isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onTertiary
                    )
                },
                label = {
                    Text(
                        text = getLabel(option),
                        color = if(isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onTertiary
                    )
                }
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