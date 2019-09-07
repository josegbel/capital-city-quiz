package com.example.capitalcityquizktx.UI


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.databinding.TitleFragmentBinding
import kotlinx.android.synthetic.main.title_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : TitleFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.title_fragment, container, false)

        binding.playBtn.setOnClickListener { v : View -> Navigation.findNavController(v)
            .navigate(TitleFragmentDirections.actionTitleFragmentToGameModeSelectionFragment()) }

        return binding.root
    }


}
