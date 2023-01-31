package com.debtdestroyer.android.ui.trivia.settings.connectedaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.debtdestroyer.android.R
import com.debtdestroyer.android.databinding.FragmentListBinding
import com.debtdestroyer.android.model.Accounts
import com.debtdestroyer.android.ui.trivia.adapter.AccountsAdapter
import com.debtdestroyer.android.ui.base.BaseFragmentNoAnim
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConnectedAccountsFragment : BaseFragmentNoAnim<FragmentListBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListBinding
        get() = FragmentListBinding::inflate


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list: ArrayList<Accounts> = ArrayList()
        list.add(Accounts("Loan 1", R.drawable.ic_settings_connected_account,"$2300.3232"))
        list.add(Accounts("Loan 2", R.drawable.ic_settings_connected_account, "$200.3232"))
        list.add(Accounts("Loan 3", R.drawable.ic_settings_connected_account, "$1000.3232"))
        list.add(Accounts("Loan 4", R.drawable.ic_settings_connected_account, "$6300.3232"))
        list.add(Accounts("Connect Another Student Loan Account", R.drawable.ic_plus, ""))

        var mAdapter: AccountsAdapter
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            mAdapter = AccountsAdapter(onItemClick = { data, type ->
            })
            adapter = mAdapter
        }

        mAdapter.submitList(list.toMutableList())
    }
}