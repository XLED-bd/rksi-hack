package com.ivan.myapplication.viewModel

import androidx.lifecycle.ViewModel
import com.ivan.myapplication.ui.bottombar.BottomNavItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _selectedItem = MutableStateFlow<BottomNavItem>(BottomNavItem.Home)
    val selectedItem: StateFlow<BottomNavItem> = _selectedItem.asStateFlow()

    fun onItemSelected(item: BottomNavItem) {
        _selectedItem.value = item
    }
}