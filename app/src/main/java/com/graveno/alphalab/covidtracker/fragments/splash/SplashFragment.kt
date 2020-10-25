package com.graveno.alphalab.covidtracker.fragments.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.graveno.alphalab.covidtracker.BuildConfig
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : Fragment() {

    val TAG : String = "SplashFragment"
    private lateinit var rootView: View
    private lateinit var navController: NavController
    private lateinit var mainActivity: MainActivity
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_splash, container, false)
        viewModel = ViewModelProvider(this)[SplashViewModel :: class.java]
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel.run {
//            img_splash.setOnClickListener { navController.navigate(R.id.action_splashFragment_to_homeFragment) }
            updateGlobal(mainActivity, navController)
            txt_version.text = "V${BuildConfig.VERSION_NAME}"
            obsLoading.observe(viewLifecycleOwner, Observer { message ->
                txt_loading.text = message
            })
            txt_app_name.text = mainActivity.getString(R.string.app_name)
        }
    }
}