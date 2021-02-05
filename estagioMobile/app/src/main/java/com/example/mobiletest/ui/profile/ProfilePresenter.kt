package com.example.mobiletest.ui.profile

import com.example.mobiletest.api.ClientAPI
import com.example.mobiletest.data.Feed
import com.example.mobiletest.data.Profile
import com.example.mobiletest.data.ProfileDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePresenter {
    private var p = 0 //Variável que controla a página atual para requisição
    private var t = 0L //Variável que controla a timestamp para requisição
    private var id = 0L
    private var requesting = false //Flag que controla se existe uma requisição em andamento

    fun getProfileDetail(onSuccess: (profileDetail: ProfileDetail) -> Unit, onError: () -> Unit, id:Long, page: Int? = null, timestamp: Long? = null) {
        if (!requesting) {
            requesting = true

            this.id = id

            //Chamada para a API
            ClientAPI.getService().getProfileDetail(id, page, timestamp).enqueue(object : Callback<ProfileDetail> {
                override fun onResponse(call: Call<ProfileDetail>, response: Response<ProfileDetail>) {
                    val profile = response.body()
                    if (response.isSuccessful && profile != null) {
                        p = profile.page
                        t = profile.timestamp
                        requesting = false
                        onSuccess(profile)
                    } else {
                        requesting = false
                        onError()
                    }
                }

                override fun onFailure(call: Call<ProfileDetail>, t: Throwable) {
                    requesting = false
                    onError()
                }

            })
        }
    }

    fun getMorePosts(onSuccess: (profileDetail: ProfileDetail) -> Unit, onError: () -> Unit) {
        p++ //incremento na variável para buscar a próxima página de posts
        getProfileDetail(onSuccess, onError, id, p, t)
    }
}