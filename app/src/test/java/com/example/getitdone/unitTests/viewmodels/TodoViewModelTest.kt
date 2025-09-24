package com.example.getitdone.unitTests.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.getitdone.data.models.ToDo
import com.example.getitdone.data.persistance.TodoRepository
import com.example.getitdone.presentation.viewmodels.TodoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify

class TodoViewModelTest {
    private lateinit var viewModel: TodoViewModel

    @Mock
    private lateinit var mockRepository: TodoRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setupViewModel() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        `when`(mockRepository.allTodos).thenReturn(flowOf(emptyList()))
        viewModel = TodoViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `addTodo adds todo`() = runTest {
        val title = "Test1"
        val description = "This is a test"
        val isCompleted = false

        viewModel.addTodo(title, description, isCompleted)

        verify(mockRepository).addTodo(any())
    }

    @Test
    fun `deleteTodo calls repository deleteTodo`() = runTest {
        val todo = ToDo(id = 1, title = "Test", description = "Test", isCompleted = false)

        viewModel.deleteTodo(todo)

        verify(mockRepository).deleteTodo(todo)
    }

    @Test
    fun `toggleDone calls repository updateTodo with toggled completion status`() = runTest {
        val todo = ToDo(id = 1, title = "Test", description = "Test", isCompleted = false)

        viewModel.toggleDone(todo)

        verify(mockRepository).updateTodo(argThat { updatedTodo ->
            updatedTodo.id == todo.id &&
                    updatedTodo.title == todo.title &&
                    updatedTodo.description == todo.description &&
                    updatedTodo.isCompleted == !todo.isCompleted // Should be toggled
        })
    }

}