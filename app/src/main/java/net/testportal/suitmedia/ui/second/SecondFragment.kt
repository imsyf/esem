package net.testportal.suitmedia.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import net.testportal.suitmedia.EsemApp
import net.testportal.suitmedia.R
import net.testportal.suitmedia.databinding.FragmentSecondBinding
import net.testportal.suitmedia.ui.MainViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels {
        viewModelFactory {
            initializer {
                val app = activity?.application as EsemApp
                MainViewModel(app.service)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.render()
    }

    private fun FragmentSecondBinding.render() {
        name.text = "John Doe"
        selected.text = "Selected User Name"
        nextButton.setOnClickListener {
            findNavController().navigate(R.id.to_thirdFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
