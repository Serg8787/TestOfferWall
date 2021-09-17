package com.tsybulnik.testofferwall.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.tsybulnik.testofferwall.DataViewModel
import kotlinx.android.synthetic.main.fragment_view.*


class ViewFragment : Fragment() {
    private val viewModel: DataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            com.tsybulnik.testofferwall.R.layout.fragment_view,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var str1 = ""
        viewModel.str.observe(activity as LifecycleOwner, {
            val queryString = it
//             TextView
            if (queryString.contains("text")) {
                str1 = getStr(queryString)
                webView.visibility = View.GONE
                imageView.visibility = View.GONE
                textview.visibility = View.VISIBLE
                textview.text = str1
            }
//            WebView
            if (queryString.contains("webview")) {
                str1 = getStr(queryString)
                textview.visibility = View.GONE
                webView.visibility = View.VISIBLE
                webView.loadUrl(str1)
            }
            // ImageView
            if (queryString.contains("image")) {
                str1 = getStr(queryString)
                webView.visibility = View.GONE
                imageView.visibility = View.VISIBLE
                Glide
                    .with(this)
                    .load(str1)
                    .into(imageView);
            }
            // Nothing
            if (queryString.contains("game")) {
                webView.visibility = View.GONE
                textview.visibility = View.GONE
                imageView.visibility = View.GONE
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ViewFragment()

    }
    private fun getStr(queryString:String):String{
        val ss =   queryString.substring(
            (queryString.lastIndexOf("=") + 1),
            queryString.length - 1
        )
        return ss
    }
}