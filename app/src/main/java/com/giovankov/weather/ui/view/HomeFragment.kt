package com.giovankov.weather.ui.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.giovankov.weather.R
import com.giovankov.weather.WeatherApplication
import com.giovankov.weather.databinding.FragmentHomeBinding
import com.giovankov.weather.ui.viewModel.HomeViewModel
import com.giovankov.weather.utils.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as WeatherApplication).appComponent.homeComponent()
            .create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    binding.button.visibility = View.GONE
                    getWeatherData()
                }

                else -> {
                    binding.button.visibility = View.VISIBLE
                }
            }
        }

        when {
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                findNavController().navigate(R.id.action_homeFragment_to_askLocationFragment)
            }

            !locationPermissionEnabled() -> {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            }
        }

        getWeatherData()
        homeViewModel.weatherLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    binding.text.text = "${it.data.main.temp.toString()} ${it.data.sys.country}"
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getWeatherData() {
        fusedLocationClient.lastLocation.addOnSuccessListener {
            Log.d("haha", it.latitude.toString())
            homeViewModel.fetchWeatherData(it.latitude, it.longitude)
        }
    }

    private fun locationPermissionEnabled(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}