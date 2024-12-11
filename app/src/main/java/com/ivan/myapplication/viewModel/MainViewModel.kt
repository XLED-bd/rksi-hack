package com.ivan.myapplication.viewModel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ivan.myapplication.model.BuyStock
import com.ivan.myapplication.model.Portfolio
import com.ivan.myapplication.model.Stock
import com.ivan.myapplication.model.StockHistory
import com.ivan.myapplication.model.User
import com.ivan.myapplication.network.ApiService
import com.ivan.myapplication.ui.bottombar.BottomNavItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences
): ViewModel() {
    private val _selectedItem = MutableStateFlow<BottomNavItem>(BottomNavItem.Home)
    val selectedItem: StateFlow<BottomNavItem> = _selectedItem.asStateFlow()

    private val _stocks = MutableStateFlow<List<Stock>>(emptyList())
    val stocks: StateFlow<List<Stock>> = _stocks.asStateFlow()

    private val _stock = MutableStateFlow<Stock>(Stock("-1", "-1", "-1", 0.0, "-1"))
    val stock: StateFlow<Stock> = _stock.asStateFlow()

    private val _user = MutableStateFlow<User>(User("-1", "-1", "01-01-1970"))
    val user: StateFlow<User> = _user.asStateFlow()

    private val _history = MutableStateFlow<List<StockHistory>>(emptyList())
    val history: StateFlow<List<StockHistory>> = _history.asStateFlow()

    private val _portfolios = MutableStateFlow<List<Portfolio>>(emptyList())
    val portfolios: StateFlow<List<Portfolio>> = _portfolios.asStateFlow()

    private val _balance = MutableStateFlow<Double>(0.0)
    val balance: StateFlow<Double> = _balance.asStateFlow()


    companion object {
        private const val TOKEN_KEY = "jwt_token"
    }


    fun onItemSelected(item: BottomNavItem) {
        _selectedItem.value = item
    }

    fun getStock(id: String) {
        viewModelScope.launch {
            Log.e("?", apiService.getStock(id).toString())
            _stock.value = apiService.getStock(id)
        }
    }

    fun getStocks() {
        viewModelScope.launch {
            Log.e("?", apiService.getStocks().toString())
            _stocks.value = apiService.getStocks()
        }
    }

    fun getUser(){
        viewModelScope.launch {
            Log.e("???", getToken())
            _user.value = apiService.getUser("Bearer " + getToken())
        }
    }

    private fun getToken(): String {
        return if (sharedPreferences.getString(TOKEN_KEY, null) == null) "null" else sharedPreferences.getString(TOKEN_KEY, "null")!!
    }

    fun getHistory() {
        viewModelScope.launch {
            _history.value = apiService.getHistory("Bearer " + getToken())
        }
    }

    fun getPortfolios() {
        viewModelScope.launch {
            _portfolios.value = apiService.getPortfolios("Bearer " + getToken())
        }
    }

    fun buyStock(id: String, amount: Int, price: Double){
        viewModelScope.launch {
            apiService.buyStocks("Bearer " + getToken(), BuyStock(id, amount, price, "BUY"))
        }
    }

    fun sellStock(id: String, amount: Int, price: Double){
        viewModelScope.launch {
            apiService.sellStocks("Bearer " + getToken(), BuyStock(id, amount, price, "SELL"))
        }
    }

    fun getBalance(){
        viewModelScope.launch {
            _balance.value = apiService.getBalance("Bearer " + getToken()).balance
        }
    }

}