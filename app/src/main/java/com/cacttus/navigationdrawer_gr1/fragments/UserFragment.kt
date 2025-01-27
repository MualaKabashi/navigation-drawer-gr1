package com.cacttus.navigationdrawer_gr1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cacttus.navigationdrawer_gr1.adapters.UserAdapter
import com.cacttus.navigationdrawer_gr1.databinding.UsersFragmentBinding
import com.cacttus.navigationdrawer_gr1.viewModels.UserViewModel

class UserFragment : Fragment() {
    private lateinit var binding: UsersFragmentBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UsersFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.getUsers()

        observeData()
    }


    fun observeData() {
        viewModel.userList.observe(viewLifecycleOwner) { userList ->
            binding.recycleView.adapter = UserAdapter(userList)
            binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }
}