package net.testportal.suitmedia.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import net.testportal.suitmedia.R
import net.testportal.suitmedia.databinding.PlaceholderLayoutBinding
import net.testportal.suitmedia.ui.MainViewModel

class SecondFragment : Fragment() {

    private var _binding: PlaceholderLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PlaceholderLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.placeholderText) {
            text = viewModel.getString(2)
            setOnClickListener {
                findNavController().navigate(R.id.to_thirdFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
