package com.imaginato.randomusers.ui.userdetails

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.imaginato.randomusers.R
import com.imaginato.randomusers.databinding.FragmentUserDetailsBinding
import com.imaginato.randomusers.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 *  To showcase the user details
 */
@AndroidEntryPoint
class UserDetailsFragment :
    BaseFragment<FragmentUserDetailsBinding>(R.layout.fragment_user_details) {

    private val args by navArgs<UserDetailsFragmentArgs>()

    override fun getClassName(): String {
        return this::class.java.simpleName
    }

    /**
     * Init toolbar and user data
     */
    override fun initViews() {
        super.initViews()
        setupToolbar()
        initUI()
    }

    /**
     * to init user data and UI
     */
    @SuppressLint("SetTextI18n")
    private fun initUI() {
        val user = args.user
        Glide.with(mBinding.ivUserThumb)
            .load(user.picture?.large)
            .transform(
                RoundedCorners(
                    mBinding.ivUserThumb.context.resources.getDimensionPixelSize(
                        R.dimen._50sdp
                    )
                )
            )
            .into(mBinding.ivUserThumb)

        mBinding.tvName.text = "${user.name?.title} ${user.name?.first} ${user.name?.last}"
        mBinding.tvEmail.text = user.email
        mBinding.tvGender.text = String.format(getString(R.string.text_gender_value), user.gender)
        mBinding.tvPhone.text = String.format(getString(R.string.text_phone_value), user.phone)
    }

    /**
     * to setup toolbar
     */
    @SuppressLint("RestrictedApi")
    private fun setupToolbar() {
        (activity as AppCompatActivity?)?.apply {
            setSupportActionBar(mBinding.toolbar)
            supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        }
        mBinding.toolbar.title = getString(R.string.text_title_user_details)
        mBinding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

}
