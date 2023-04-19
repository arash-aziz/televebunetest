package com.example.githubtest.presenter.home.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubtest.core.entity.User
import com.example.githubtest.core.extentions.displayCenterCropImage
import com.example.githubtest.core.extentions.displayCircleImage
import com.example.githubtext.databinding.FragmentUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    val viewModel: UserViewModel by viewModel()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observe()
        viewModel.getUser()
    }

    fun observe() {
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            setup(it)
        }
    }

    fun setup(user: User) {
        binding.userName.text = user.name
        binding.userEmail.text = user.email
        binding.userImage.displayCircleImage(user.avatarUrl)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}