package com.example.getitdone.navigation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.getitdone.data.models.ToDo
import com.example.getitdone.navigation.mainnavgraph.BottomNav
import com.example.getitdone.navigation.mainnavgraph.MainNavGraph
import com.example.getitdone.presentation.viewmodels.TodoViewModel


@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreenBottomNavBar(
    todo: ToDo,
    viewModel: TodoViewModel,
    navController: NavController
) {

    val isImeVisible = WindowInsets.isImeVisible
    val bottomNavController = rememberNavController()
    val bottomNavBarBgColor = Color.Black

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .clip(
                        RoundedCornerShape(40.dp)
                    )
                    .background(bottomNavBarBgColor)
            ) {
                BottomBar(
                    navController = bottomNavController,
                    viewModel
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    bottomNavController.navigate("add_todo")
                },
                shape = CircleShape
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier = Modifier
            .background(color = bottomNavBarBgColor)
            .systemBarsPadding()
    )
    { innerPadding ->
        Log.d("using it to avoid error: will fix", "innerPadding : $innerPadding")
        val contentPadding = remember(isImeVisible) {
            if (isImeVisible)
                PaddingValues(start = 0.0.dp, top = 0.0.dp, end = 0.0.dp, bottom = 56.0.dp)
            else
                PaddingValues(start = 0.0.dp, top = 0.0.dp, end = 0.0.dp, bottom = 0.0.dp)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            MainNavGraph(
                bottomNavController,
                todo,
                viewModel
            )
        }
    }
}

    @ExperimentalFoundationApi
    @Composable
    fun BoxScope.BottomBar(
        navController: NavHostController,
        viewModel: TodoViewModel
    ) {
        val screens = listOf(
            BottomNav.Home,
            BottomNav.CompletedTodos,
            BottomNav.Calendar,
            BottomNav.Weather,
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Box {
            BottomNavigation(
                backgroundColor = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                screens.forEachIndexed { _, screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }
    }

    @ExperimentalFoundationApi
    @Composable
    fun RowScope.AddItem(
        screen: BottomNav,
        currentDestination: NavDestination?,
        navController: NavHostController,
    ) {
        val selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true

        BottomNavigationItem(
            modifier = Modifier.align(Alignment.CenterVertically),
            icon = {
                Icon(
                    screen.icon.asPainterResource(),
                    contentDescription = null,
                    tint = if (selected) Color.Green else Color.Black
                )
            },
            selected = selected,
            onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            interactionSource = remember { MutableInteractionSource() },
            selectedContentColor = Color.Unspecified,
            unselectedContentColor = Color.Unspecified
        )
    }