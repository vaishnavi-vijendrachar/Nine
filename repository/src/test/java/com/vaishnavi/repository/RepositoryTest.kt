package com.vaishnavi.repository

import com.vaishnavi.model.Assets
import com.vaishnavi.model.Response
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.context.GlobalContext.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub
import org.mockito.kotlin.whenever

class RepositoryTest {

    private var mockClient: RemoteService = mock()

    @Mock
    private lateinit var response: Response

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

        response = Response(
            listOf(
                Assets(
                    "https://www.afr.com/property/residential/got-a-spare-5m-how-to-renovate-your-paris-apartment-20231031-p5egce",
                    "Michael Bleby",
                    "Got a spare \$5m? How to renovate your Paris apartment",
                    emptyList(),
                    "You think your reno is tricky? Try doing up a heritage pad in the 4th Arrondissement in lockdown. From Melbourne."
                )
            ),
            1698802143
        )

        mockClient.stub {
            onBlocking { getDataFromRemote() }.doReturn(
                response
            )
        }
    }

    @After
    fun tearDown() {
        unloadKoinModules(modules)
        stopKoin()
    }

    @Test
    fun `given RemoteService when response for byLine is successful`() {
        runTest {
            //given
            whenever(mockClient.getDataFromRemote()).thenReturn(response)
            //when
            val response = mockClient.getDataFromRemote()
            //then
            assertEquals(response.assets.first().byLine, "Michael Bleby")
        }
    }

    @Test
    fun `given RemoteService when response for headLine is successful`() {
        runTest {
            //given
            whenever(mockClient.getDataFromRemote()).thenReturn(response)
            //when
            val response = mockClient.getDataFromRemote()
            //then
            assertEquals(
                response.assets.first().headline,
                "Got a spare \$5m? How to renovate your Paris apartment"
            )
        }
    }

    @Test
    fun `given RemoteService when response for theAbstract is successful`() {
        runTest {
            //given
            whenever(mockClient.getDataFromRemote()).thenReturn(response)
            //when
            val response = mockClient.getDataFromRemote()
            //then
            assertEquals(
                response.assets.first().theAbstract,
                "You think your reno is tricky? Try doing up a heritage pad in the 4th Arrondissement in lockdown. From Melbourne."
            )
        }
    }

    @Test
    fun `given RemoteService when response for timestamp is successful`() {
        runTest {
            //given
            whenever(mockClient.getDataFromRemote()).thenReturn(response)
            //when
            val response = mockClient.getDataFromRemote()
            //then
            assertEquals(
                response.timeStamp,
                1698802143
            )
        }
    }
}
