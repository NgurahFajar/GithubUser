package com.ngurah.githubuser.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngurah.githubuser.adapter.UserAdapter
import com.ngurah.githubuser.databinding.FragmentFollowBinding
import com.ngurah.githubuser.viewmodel.FollowViewModel

class FollowFragment : Fragment() {
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FollowViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.get(EXTRA_POSISITION)
        val name = arguments?.get(EXTRA_NAME)
        adapter = UserAdapter()

        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
            FollowViewModel::class.java)
        if (position == 1){
            binding.rvFollow.adapter = adapter
            binding.rvFollow.layoutManager = LinearLayoutManager(requireActivity())
            binding.rvFollow.setHasFixedSize(true)
            viewModel.listfollowers.observe(viewLifecycleOwner) {
                adapter.setListUser(it)
            }
            viewModel.setFollowers(name.toString())
        }else{
            binding.rvFollow.adapter = adapter
            binding.rvFollow.layoutManager = LinearLayoutManager(requireActivity())
            binding.rvFollow.setHasFixedSize(true)
            viewModel.listfollowing.observe(viewLifecycleOwner) {
                adapter.setListUser(it)
            }

            viewModel.setFollowing(name.toString())
        }

        viewModel.isLoading.observe(requireActivity()) {
            showLoading(it)
        }
    }


    companion object {
        const val EXTRA_POSISITION = "extraposisi"
        const val EXTRA_NAME = "extraname"

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar4.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}