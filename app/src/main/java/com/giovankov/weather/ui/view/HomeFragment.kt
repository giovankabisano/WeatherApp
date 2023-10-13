package com.giovankov.weather.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.giovankov.weather.R
import com.giovankov.weather.WeatherApplication
import com.giovankov.weather.databinding.FragmentHomeBinding
import com.giovankov.weather.ui.viewModel.HomeViewModel
import com.giovankov.weather.utils.Resource
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as WeatherApplication).appComponent.homeComponent()
            .create()
            .inject(this)
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
        homeViewModel.onCreate(-6.200000, 106.816666)
        homeViewModel.quoteModel.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    binding.text.text = it.data.timezone.toString()
                }
            }
        }
    }
}