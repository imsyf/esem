package net.testportal.suitmedia.ui.first

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import net.testportal.suitmedia.EsemApp
import net.testportal.suitmedia.R
import net.testportal.suitmedia.ui.MainViewModel

class PalindromeDialogFragment : DialogFragment() {

    private val viewModel: MainViewModel by activityViewModels {
        viewModelFactory {
            initializer {
                val app = activity?.application as EsemApp
                MainViewModel(app.service)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.result))
            .setMessage(viewModel.getString(-1))
            .create()
    }
}
