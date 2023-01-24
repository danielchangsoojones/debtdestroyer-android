package com.debtdestroyer.android.ui.trivia.settings.ld

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.debtdestroyer.android.callback.Params.URL_GAME_RULES
import com.debtdestroyer.android.callback.Params.URL_TERMS_AND_SERVICES
import com.debtdestroyer.android.databinding.FragmentLdBinding
import com.debtdestroyer.android.ui.base.BaseFragmentNoAnim
import com.debtdestroyer.android.ui.base.getUrlFromIntent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LDFragment : BaseFragmentNoAnim<FragmentLdBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLdBinding
        get() = FragmentLdBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.privacyPolicyTextview.setOnClickListener {
            getUrlFromIntent("https://www.lavadrop.io")
        }
        binding.termsOfServiceTextview.setOnClickListener {
            getUrlFromIntent(URL_TERMS_AND_SERVICES)
        }
        binding.gameRulesTextview.setOnClickListener {
            getUrlFromIntent(URL_GAME_RULES)
        }
    }
}