package com.vaishnavi.nine

import com.vaishnavi.model.Response
import com.vaishnavi.nine.main.MainViewModel
import com.vaishnavi.repository.ApiClient
import com.vaishnavi.repository.Repository
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class MainViewModelTest {

    @Mock
    private lateinit var mockPage: Response

    @Mock
    private lateinit var mockRepository: Repository

    private lateinit var mainViewModel: MainViewModel

    private val modules: Module
            by lazy {
                module {
                    single { Repository() }
                    single { ApiClient().buildService }
                }
            }


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        startKoin {
            modules(emptyList())
        }
        loadKoinModules(modules)

        mainViewModel = MainViewModel()

    }

    @After
    fun tearDown() {
        unloadKoinModules(modules)
        stopKoin()
    }

    @Test
    fun `given MainViewModel when successful`() {
        runTest {
            val flowResponse = flow {
                emit(mockPage)
            }
            whenever(mockRepository.getUserDataFromRemote()).thenReturn(flowResponse)
            val response = mainViewModel.getDataFromServer()
            assertNotNull(response)

        }
    }

    @Test
    fun `given MainViewModel when response is not null`() {
        runTest {
            whenever(mockRepository.getUserDataFromRemote()).thenReturn(emptyFlow())
            val response = mainViewModel.getDataFromServer()
            assertNotNull(response)
        }
    }
}
