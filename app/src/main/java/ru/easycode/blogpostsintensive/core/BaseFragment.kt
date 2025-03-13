package ru.easycode.blogpostsintensive.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding, VM : ViewModel> : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!
    protected lateinit var viewModel: VM
    protected abstract val viewModelClass: Class<VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(this, viewModelClass)
    }

    protected abstract fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): T

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun handleBackPress(action: () -> Unit) {

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = action.invoke()
            })
    }

    protected fun closeKeyboard() =
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.hideSoftInputFromWindow(requireView().windowToken, 0)
}