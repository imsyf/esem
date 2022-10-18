package net.testportal.suitmedia.ui.third

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
import net.testportal.suitmedia.databinding.LayoutEpoxyBinding
import net.testportal.suitmedia.loading
import net.testportal.suitmedia.text
import net.testportal.suitmedia.ui.MainViewModel
import net.testportal.suitmedia.userCard

class ThirdFragment : Fragment() {

    private var _binding: LayoutEpoxyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutEpoxyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.resetPage()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    binding.swipeToRefresh.isRefreshing = state.isRefreshing

                    binding.epoxy.withModels {
                        for (user in state.users) {
                            userCard {
                                id("user/${user.id}")
                                user(user)
                                onClick { _, _, _, _ ->
                                    viewModel.setSelected(user.name)
                                    findNavController().popBackStack()
                                }
                            }
                        }

                        if (!state.isLastPage) {
                            loading {
                                id("loading/${state.users.size}")
                                onBind { _, _, _ -> viewModel.fetch() }
                            }
                        } else {
                            text {
                                id("footer")
                                text("You've reached the end")
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        viewModel.resetPage()
        super.onDestroy()
    }
}
