package com.tsybulnik.testofferwall.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tsybulnik.testofferwall.R
import com.tsybulnik.testofferwall.network.APIService
import com.tsybulnik.testofferwall.network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_view.*

import android.widget.ImageView

import com.tsybulnik.testofferwall.MainActivity

import android.widget.LinearLayout
import com.bumptech.glide.Glide


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
        return inflater.inflate(com.tsybulnik.testofferwall.R.layout.fragment_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val retrofit = RetrofitClient.getClient("http://demo3005513.mockable.io/api/v1/").create(
            APIService::class.java
        )

        val objOfView = retrofit.getView(param1!!).execute().body()


        Log.d("MyLog",objOfView.toString())
         if (objOfView.toString().contains("text")){
             tvView.text = "33"
             val message: String = objOfView.toString().substring((objOfView.toString().lastIndexOf("=") + 1),objOfView.toString().length-1)
             Log.d("MyLog",message)
         }
        if (objOfView.toString().contains("webview")){
            tvView.text = "33"
            val webview: String = objOfView.toString().substring((objOfView.toString().lastIndexOf("=") + 1),objOfView.toString().length-1)
            Log.d("MyLog",webview)
        }
        if (objOfView.toString().contains("image")){
            val image: String = objOfView.toString().substring((objOfView.toString().lastIndexOf("=") + 1),objOfView.toString().length-1)
            Log.d("MyLog","im"+image)
            val imageView = ImageView(context)
            imageView.setImageResource(com.tsybulnik.testofferwall.R.drawable.ic_launcher_background)
            Glide
                .with(this)
                .load(image)
                .into(imageView);
            val imageViewLayoutParams =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            imageView.setLayoutParams(imageViewLayoutParams)
            mainLayout.addView(imageView)
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