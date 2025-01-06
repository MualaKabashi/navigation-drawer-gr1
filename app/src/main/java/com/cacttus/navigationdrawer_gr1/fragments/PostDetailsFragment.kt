package com.cacttus.navigationdrawer_gr1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cacttus.navigationdrawer_gr1.databinding.PostDetailsBinding
import com.cacttus.navigationdrawer_gr1.helpers.Helpers.getIntFromSharedPreferences
import com.cacttus.navigationdrawer_gr1.helpers.Helpers.provideRetrofit
import com.cacttus.navigationdrawer_gr1.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDetailsFragment : Fragment() {
    private lateinit var binding: PostDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PostDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = getIntFromSharedPreferences(requireContext(), "id")
        getPostById(id)
    }

    fun getPostById(id: Int) {
        provideRetrofit().getPostById(id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful && response.body() != null) {
                    binding.userId.text = response.body()!!.userId.toString()
                    binding.id.text = response.body()!!.id.toString()
                    binding.body.text = response.body()!!.body
                    binding.title.text = response.body()!!.title
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}