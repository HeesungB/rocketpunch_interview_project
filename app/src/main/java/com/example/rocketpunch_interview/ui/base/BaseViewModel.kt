package com.example.rocketpunch_interview.ui.base

import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    open fun onViewLoaded() {}
}