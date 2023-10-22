package com.giovankov.weather.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.giovankov.weather.data.model.Coord
import com.giovankov.weather.data.model.WeatherModel
import com.giovankov.weather.domain.GetWeatherUseCase
import com.giovankov.weather.domain.WeatherUseCase
import com.giovankov.weather.getOrAwaitValue
import com.giovankov.weather.utils.Resource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var getWeatherUseCase: GetWeatherUseCase

    @Mock
    private lateinit var weatherUseCase: WeatherUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)

        // Initialize your ViewModel with the mocked UseCase
        homeViewModel = HomeViewModel(weatherUseCase)
    }

//    @Test
//    fun `fetchWeatherData success`() {
//        val mockWeatherData = WeatherModel(
//            coord = Coord(lat = 0.0, lon = 0.0)
//        )
//        val successResource = Resource.success(mockWeatherData)
//
//        // Mock the behavior of the UseCase
//        runBlocking {
//            `when`(getWeatherUseCase(anyDouble(), anyDouble()))
//                .thenReturn(successResource)
//        }
//
//        // Trigger the fetchWeatherData method
//        homeViewModel.fetchWeatherData(0.0, 0.0)
//
//        // Verify that the LiveData has received the success result
//        val result =
//            homeViewModel.weatherLiveData.getOrAwaitValue() // You need a custom test utility for this
//        assert(result is Resource.Success)
//        assertEquals(mockWeatherData, (result as Resource.Success).data)
//    }

    @Test
    fun `fetchWeatherData error`() {
        testDispatcher.runBlockingTest {
            val errorResource = Resource.error(12, "dw")

            // Mock the behavior of the UseCase
            runBlocking {
                `when`(getWeatherUseCase(anyDouble(), anyDouble()))
                    .thenReturn(errorResource)
            }

            // Trigger the fetchWeatherData method
            homeViewModel.fetchWeatherData(0.0, 0.0)

            // Verify that the LiveData has received the error result
            val result =
                homeViewModel.weatherLiveData.getOrAwaitValue() // You need a custom test utility for this
            assert(result is Resource.Error)
//        assertEquals("An error occurred", (result as Resource.Error).message)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher to its original implementation
        testDispatcher.cleanupTestCoroutines()
    }
}