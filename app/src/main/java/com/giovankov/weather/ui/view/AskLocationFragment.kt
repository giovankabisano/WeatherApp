package com.giovankov.weather.ui.view

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.giovankov.weather.databinding.FragmentAskLocationBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AskLocationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AskLocationFragment : Fragment() {

    private lateinit var binding: FragmentAskLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAskLocationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                val a = permissions
//                when {
//                    permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
//                        findNavController().navigateUp()
//                    }
//
//                    permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
//                        findNavController().navigateUp()
//                    }
//                }
            }

        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )

        binding.buttonPermission.setOnClickListener {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
        }

    }
}