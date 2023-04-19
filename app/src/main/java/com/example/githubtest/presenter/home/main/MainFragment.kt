package com.example.githubtest.presenter.home.main

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.githubtest.core.animation.ClickAnimationForView
import com.example.githubtest.presenter.home.repos.ReposFragment
import com.example.githubtest.presenter.home.user.UserFragment
import com.example.githubtext.databinding.FragmentMainBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//

        binding.bottomNavView
        binding.bottomNavView.setItemOnTouchListener(
            com.example.githubtext.R.id.nav_repos
        ) { v, event ->

            setPage(ReposFragment())
            binding. bottomNavView.getMenu().getItem(1).isChecked = true;
            true
        }

        binding.bottomNavView.setItemOnTouchListener(
            com.example.githubtext.R.id.nav_user
        ) { v, event ->
            setPage(UserFragment())
            binding. bottomNavView.getMenu().getItem(0).isChecked = true;
            true
        }

        ClickAnimationForView.setClickListener(binding.favoriteIcon) {
            findNavController().navigate(MainFragmentDirections.actionUserFramgentFavoriteFragment())
        }

        setPage(UserFragment())
    }


    fun setPage(fragment: Fragment) {
        val fm: FragmentManager = childFragmentManager
        fm.beginTransaction().replace(binding.content.id, fragment).commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}