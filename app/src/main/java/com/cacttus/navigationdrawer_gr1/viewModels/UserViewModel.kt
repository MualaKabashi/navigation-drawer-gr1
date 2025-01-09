package com.cacttus.navigationdrawer_gr1.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cacttus.navigationdrawer_gr1.helpers.Helpers.provideRetrofit
import com.cacttus.navigationdrawer_gr1.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    private var _userList: MutableLiveData<List<User>> =
        MutableLiveData(mutableListOf())

    val userList: MutableLiveData<List<User>>
        get() = _userList

    var loading = MutableLiveData<Boolean>()

    fun getUsers() {
        loading.value = true
        provideRetrofit().getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful && response.body() != null) {
                    _userList.value = response.body()
                }
                loading.value = false
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                loading.value = false
            }
        })
    }
}