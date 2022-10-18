package net.testportal.suitmedia.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import net.testportal.suitmedia.R
import net.testportal.suitmedia.databinding.FragmentSecondBinding
import net.testportal.suitmedia.ui.MainViewModel
import net.testportal.suitmedia.ui.MainViewModel.State

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

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

        binding.setup()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    binding.render(it)
                }
            }
        }
    }

    private fun FragmentSecondBinding.setup() {
        nextButton.setOnClickListener {
            findNavController().navigate(R.id.to_thirdFragment)
        }
    }

    private fun FragmentSecondBinding.render(state: State) {
        name.text = state.name
        selected.text = state.selected
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
