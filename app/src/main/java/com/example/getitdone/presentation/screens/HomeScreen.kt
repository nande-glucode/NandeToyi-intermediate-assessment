package com.example.getitdone.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.getitdone.presentation.components.TodoItem
import com.example.getitdone.presentation.components.WeatherDisplay
import com.example.getitdone.presentation.viewmodels.TodoViewModel
import com.example.getitdone.presentation.viewmodels.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    viewModel: TodoViewModel,
    weatherViewModel: WeatherViewModel,
    onRefreshWeather: () -> Unit
) {
    val todos by viewModel.todos.collectAsState()
    val weatherState by weatherViewModel.weatherState.collectAsStateWithLifecycle()
    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val tabs = listOf("All", "In Progress", "Completed")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    var selectedTab by remember { mutableStateOf(pagerState.currentPage) }

    LaunchedEffect(selectedTab) {
        pagerState.scrollToPage(selectedTab)
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTab = pagerState.currentPage
    }

    Column(
        Modifier
            .padding(16.dp)
            .nestedScroll(scrollBehaviour.nestedScrollConnection)
    ) {
        CenterAlignedTopAppBar(
            title = { Text("Let's get it done") }
        )
        WeatherDisplay(
            weatherState,
            onRefresh = onRefreshWeather
        )
        Spacer(modifier = Modifier.padding(20.dp))
        TabRow(selectedTabIndex = selectedTab) {
            for (index in 0 until pagerState.pageCount) {
                Tab(
                    selected = index == selectedTab,
                    onClick = {
                        selectedTab = index
                    }
                ) {
                    Text(
                        text = tabs[index],
                        modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { currentPage ->
            val filteredTodos = when (currentPage) {
                0 -> todos
                1 -> todos.filter { !it.isCompleted }
                2 -> todos.filter { it.isCompleted }
                else -> todos
            }
            LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
                items(filteredTodos) { todo ->
                    TodoItem(
                        todo = todo,
                        onDelete = { viewModel.deleteTodo(todo) },
                        onToggle = { viewModel.toggleDone(todo) }
                    )
                }
            }
        }
    }
}

