package com.example.mobiletest.ui.profile

import com.example.mobiletest.api.ClientAPI
import com.example.mobiletest.data.ProfileDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//Classe repsonsável por fornecer os dados e controlar a lógica para a ProfileActivity
class ProfilePresenter {

    private var p = 0 //Variável que controla a página atual para requisição
    private var t = 0L //Variável que controla a timestamp para requisição
    private var requesting = false //Flag que controla se existe uma requisição em andamento

    fun getPosts(
        id: Long,
        onSuccess: (profileDetails: ProfileDetails) -> Unit,
        onError: () -> Unit,
        page: Int? = null,
        timestamp: Long? = null
    ) {
        if (!requesting) {
            requesting = true

            //Chamada para a API
            ClientAPI.getService().getProfile(id, page, timestamp)
                .enqueue(object : Callback<ProfileDetails> {
                    override fun onResponse(
                        call: Call<ProfileDetails>,
                        response: Response<ProfileDetails>
                    ) {
                        val profileDetails = response.body()
                        if (response.isSuccessful && profileDetails != null) {
                            p = profileDetails.page
                            t = profileDetails.timestamp
                            requesting = false
                            onSuccess(profileDetails)
                        } else {
                            requesting = false
                            onError()
                        }
                    }

                    override fun onFailure(call: Call<ProfileDetails>, t: Throwable) {
                        requesting = false
                        onError()
                    }
                })
        }
    }

    fun getMorePosts(
        id: Long,
        onSuccess: (profileDetails: ProfileDetails) -> Unit,
        onError: () -> Unit
    ) {
        p++ //incremento na variável para buscar a próxima página de posts
        getPosts(id, onSuccess, onError, p, t)
    }

}