package com.tsybulnik.testofferwall.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.gson.JsonObject
import com.tsybulnik.testofferwall.R
import com.tsybulnik.testofferwall.model.Obj
import com.tsybulnik.testofferwall.network.APIService
import com.tsybulnik.testofferwall.network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_view.*
import org.json.JSONObject
import org.json.JSONTokener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [ViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val retrofit = RetrofitClient.getClient("http://demo3005513.mockable.io/api/v1/").create(
            APIService::class.java
        )
        val objOfView = retrofit.getView(param1!!).execute().body()
         if (objOfView.toString().contains("text")){
             tvView.text = "33"
             Log.d("MyLog","33")
             val s: List<String> = objOfView.toString().split("\"")
             Log.d("MyLog",s.toString())


         }










    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            ViewFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}