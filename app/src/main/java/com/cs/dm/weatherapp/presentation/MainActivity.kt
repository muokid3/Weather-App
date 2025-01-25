package com.cs.dm.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.cs.dm.weatherapp.presentation.ui.theme.WeatherAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = getViewModel<WeatherViewModel>()

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadCurrentWeatherData()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )


        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {

                val state = viewModel.weatherState.collectAsStateWithLifecycle().value
                val snackBarHostState = remember { SnackbarHostState() }
                val localLifecycleOwner = LocalLifecycleOwner.current

                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    LaunchedEffect(localLifecycleOwner) {
                        repeatOnLifecycle(Lifecycle.State.STARTED) {
                            withContext(Dispatchers.Main.immediate) {
                                viewModel.channelEvents.collect { event ->
                                    when (event) {
                                        is WeatherEvents.ShowSnack -> {
                                            snackBarHostState.showSnackbar(message = event.message, withDismissAction = true)
                                        }
                                    }
                                }
                            }
                        }

                    }
                    HomeScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        state = state,
                    )
                }
            }
        }
    }
}