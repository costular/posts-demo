package com.costular.postsdemo.presentation.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import com.costular.postsdemo.R

inline fun <reified T : Fragment> onFragment(crossinline function: () -> Unit) {
    launchFragmentInContainer<T>(themeResId = R.style.AppTheme)
    function()
}
