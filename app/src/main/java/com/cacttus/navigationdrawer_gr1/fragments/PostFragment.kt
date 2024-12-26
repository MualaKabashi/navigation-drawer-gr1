package com.cacttus.navigationdrawer_gr1.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cacttus.navigationdrawer_gr1.adapters.PostAdapter
import com.cacttus.navigationdrawer_gr1.databinding.PostsFragmentBinding
import com.cacttus.navigationdrawer_gr1.helpers.Helpers.provideRetrofit
import com.cacttus.navigationdrawer_gr1.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostFragment : Fragment() {
    private lateinit var binding: PostsFragmentBinding
    private var list: List<Post> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PostsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGetPosts.setOnClickListener {
            Log.d("TAG", "onViewCreated:  button clicked ")
            getPosts()
        }
    }

    fun getPosts() {
        //if the data is not shown in the list restart the emulator wifi(turn it off and on again)
        provideRetrofit().getPost().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d("TAG", "onResponse: $response")
                    list = response.body()!!
                    var adapter = PostAdapter(requireContext(), list)
                    binding.listView.adapter = adapter
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}