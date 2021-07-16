package com.imaginato.randomusers.ui.main

import com.imaginato.randomusers.R
import com.imaginato.randomusers.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity used to showcase the Map and User details fragment
 * both fragments are display using navGraph
 *
 * Here BaseActivity extended instead of BaseViewModelActivity because no need to create viewModel
 * for MainActivity.
 */
@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main)