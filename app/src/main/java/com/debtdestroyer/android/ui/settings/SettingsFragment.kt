package com.debtdestroyer.android.ui.settings

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.debtdestroyer.android.R
import com.debtdestroyer.android.databinding.FragmentSettingsBinding
import com.debtdestroyer.android.model.DataSettings
import com.debtdestroyer.android.model.Setting
import com.debtdestroyer.android.ui.adapter.SettingsAdapter
import com.debtdestroyer.android.ui.base.BaseFragment
import com.debtdestroyer.android.ui.base.BaseFragmentNoAnim
import com.debtdestroyer.android.ui.base.navigateTo
import com.debtdestroyer.android.ui.phonenumber.PhoneNumberFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class SettingsFragment : BaseFragmentNoAnim<FragmentSettingsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding
        get() = FragmentSettingsBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list: ArrayList<DataSettings> = ArrayList()
        list.add(DataSettings(getString(R.string.settings_connected_accounts), R.drawable.ic_settings_connected_account, Setting.CONNECTED_ACCOUNTS))
        list.add(DataSettings(getString(R.string.settings_contact_us), R.drawable.ic_settings_contact_us, Setting.CONTACT_US))
        list.add(DataSettings(getString(R.string.settings_legal_disclosure), R.drawable.ic_settings_disclosure, Setting.LEGAL_DISCLOSURE))
        list.add(DataSettings(getString(R.string.settings_leave_feedback), R.drawable.ic_settings_feedback, Setting.LEAVE_FEEDBACK))
        list.add(DataSettings(getString(R.string.settings_logout), R.drawable.ic_settings_logout, Setting.LOGOUT))
        list.add(DataSettings(getString(R.string.settings_delete_account), R.drawable.ic_settings_delete, Setting.DELETE_ACCOUNT))

        var mAdapter: SettingsAdapter
        binding.recyclerViewSettings.apply {
            layoutManager = LinearLayoutManager(requireContext())
            mAdapter = SettingsAdapter(onItemClick = { data, type ->
                when (data.type) {
                    Setting.CONNECTED_ACCOUNTS -> {
                        navigateTo(SettingsFragmentDirections.actionSettingsFragmentToConnectedAccountsFragment())
                    }
                    Setting.CONTACT_US -> {}
                    Setting.LEGAL_DISCLOSURE -> {}
                    Setting.LEAVE_FEEDBACK -> {}
                    Setting.LOGOUT -> {}
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