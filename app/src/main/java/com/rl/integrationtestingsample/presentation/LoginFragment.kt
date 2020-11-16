package com.rl.integrationtestingsample.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.rl.integrationtestingsample.R
import com.rl.integrationtestingsample.databinding.MainFragmentBinding
import com.rl.integrationtestingsample.di.DaggerApplicationComponent
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    @Inject lateinit var viewModel: LoginViewModel

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerApplicationComponent.builder().application(requireActivity().application).build().inject(this)

        val binding = MainFragmentBinding.bind(view)
        with (binding) {
            username.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    viewModel.usernameText = s.toString()
                }
            })
            password.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    viewModel.passwordText = s.toString()
                }
            })
            login.setOnClickListener {
                viewModel.onLoginClicked()
            }

            viewModel.viewStateObservable.observe(viewLifecycleOwner, { state ->
                validationError.visibility = View.GONE
                when(state) {
                    LoginViewState.InvalidInputData -> {
                        validationError.visibility = View.VISIBLE
                    }
                    LoginViewState.LoginSuccess -> {
                        requireActivity().finish()
                    }
                    LoginViewState.LoginFailed -> {
                        AlertDialog.Builder(requireActivity())
                            .setMessage("Login credentials incorrect!")
                            .setPositiveButton("Ok") { _, _ -> }
                            .create().show()
                    }
            }
            })
        }
    }
}
