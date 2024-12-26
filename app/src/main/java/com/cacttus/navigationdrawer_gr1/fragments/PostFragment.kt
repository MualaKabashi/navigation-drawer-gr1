package com.cacttus.navigationdrawer_gr1.fragments

import android.os.Bundle
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
    private lateinit var list: List<Post>
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
            getPosts()
        }
    }


    fun getPosts() {
        list = listOf(Post(1, 2, "123", "123"))

        provideRetrofit().getPost().enqueue(object: Callback<List<Post>> {
            override fun onResponse(
                call: Call<List<Post>?>,
                response: Response<List<Post>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    list = response.body()!!
                    val postAdapter = PostAdapter(requireContext(), list)
                    binding.listView.adapter = postAdapter
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Response is not successful",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Post>>, t: Throwable) {
                Toast.makeText(
                    requireContext(),
                    "Failure due to internet connection / server timeout / etc",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        )
    }
}