package com.debtdestroyer.android.ui.trivia.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.debtdestroyer.android.R
import com.debtdestroyer.android.callback.Params.URL_WINNERS_INFORMATION
import com.debtdestroyer.android.callback.Status
import com.debtdestroyer.android.databinding.FragmentSettingsBinding
import com.debtdestroyer.android.model.DataSettings
import com.debtdestroyer.android.model.Setting
import com.debtdestroyer.android.ui.auth.AuthActivity
import com.debtdestroyer.android.ui.base.*
import com.debtdestroyer.android.ui.trivia.TriviaActivity
import com.debtdestroyer.android.ui.trivia.adapter.SettingsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragmentNoAnim<FragmentSettingsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding
        get() = FragmentSettingsBinding::inflate

    private val viewModel: SettingsVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.res.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                    activity?.finish()
                }
                Status.ERROR -> {
                    showToast(getString(R.string.invalid_input), it.message)
                    hideProgressBar()
                }
                Status.LOADING -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun setupUI() {

        val list: ArrayList<DataSettings> = ArrayList()
        list.add(DataSettings(getString(R.string.settings_enter_winner_information), R.drawable.ic_settings_disclosure, Setting.WINNER_INFORMATION))
        list.add(DataSettings(getString(R.string.settings_connected_accounts), R.drawable.ic_settings_connected_account, Setting.CONNECTED_ACCOUNTS))
        list.add(DataSettings(getString(R.string.settings_contact_us), R.drawable.ic_settings_contact_us, Setting.CONTACT_US))
        list.add(DataSettings(getString(R.string.settings_legal_disclosure), R.drawable.ic_settings_disclosure, Setting.LEGAL_DISCLOSURE))
        list.add(DataSettings(getString(R.string.settings_leave_feedback), R.drawable.ic_settings_feedback, Setting.LEAVE_FEEDBACK))
        //list.add(DataSettings(getString(R.string.settings_delete_account), R.drawable.ic_settings_delete, Setting.DELETE_ACCOUNT))
        list.add(DataSettings(getString(R.string.settings_logout), R.drawable.ic_settings_logout, Setting.LOGOUT))

        var mAdapter: SettingsAdapter
        binding.recyclerViewSettings.apply {
            layoutManager = LinearLayoutManager(requireContext())
            mAdapter = SettingsAdapter(onItemClick = { data, type ->
                when (data.type) {
                    Setting.WINNER_INFORMATION -> {
                        getUrlFromIntent(URL_WINNERS_INFORMATION)
                    }
                    Setting.CONNECTED_ACCOUNTS -> {
                        navigateTo(SettingsFragmentDirections.actionSettingsFragmentToConnectedAccountsFragment())
                    }
                    Setting.CONTACT_US -> {
                        getSmsIntent()
                    }
                    Setting.LEGAL_DISCLOSURE -> {
                        navigateTo(SettingsFragmentDirections.actionSettingsFragmentToLDFragment())
                    }
                    Setting.LEAVE_FEEDBACK -> {}
                    Setting.LOGOUT -> {
                        viewModel.onLogoutClicked()
                    }
                    Setting.DELETE_ACCOUNT -> {}
                    else -> {
                        return@SettingsAdapter
                    }
                }

            })
            adapter = mAdapter
        }

        mAdapter.submitList(list.toMutableList())
    }
}