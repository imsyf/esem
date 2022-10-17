package net.testportal.suitmedia.ui.first

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
import net.testportal.suitmedia.databinding.FragmentFirstBinding
import net.testportal.suitmedia.ui.MainViewModel

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
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
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.render()
    }

    private fun FragmentFirstBinding.render() {
        checkButton.setOnClickListener {
            findNavController().navigate(R.id.to_palindromeDialogFragment)
        }
        nextButton.setOnClickListener {
            findNavController().navigate(R.id.to_secondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
