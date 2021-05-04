package com.example.multiapp.androidApp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.multiapp.shared.SpaceXSDK
import com.example.multiapp.shared.cache.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SpaceXFragment : Fragment() {

    private lateinit var rcAdapter: LaunchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var sdk: SpaceXSDK
    private lateinit var databaseDriver: DatabaseDriverFactory
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rcAdapter = LaunchAdapter()
        databaseDriver = DatabaseDriverFactory(requireContext())
        sdk = SpaceXSDK(databaseDriver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_space_x, container, false)
        recyclerView = view.findViewById(R.id.rc_container)!!
        recyclerView.adapter = rcAdapter
        return view
    }

    override fun onResume() {
        super.onResume()
        mainScope.launch {
           val list = sdk.getLaunches(true)
            Log.v("TAG", "$list")
            rcAdapter.setLaunchData(list)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = SpaceXFragment()
    }
}