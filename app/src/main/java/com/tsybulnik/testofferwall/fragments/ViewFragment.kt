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
        var messageOrUrl:String = "fdsf"
        viewModel.str.observe(activity as LifecycleOwner, {
            val queryString = it
//             TextView
            if (queryString.contains("text")) {
                messageOrUrl = getStr(queryString)
                webView.visibility = View.GONE
                imageView.visibility = View.GONE
                textview.visibility = View.VISIBLE
                textview.text = messageOrUrl
            }
//            WebView
            if (queryString.contains("webview")) {
                messageOrUrl = getStr(queryString)
                textview.visibility = View.GONE
                webView.visibility = View.VISIBLE
                webView.loadUrl(messageOrUrl)
            }
            // ImageView
            if (queryString.contains("image")) {
                messageOrUrl = getStr(queryString)
                webView.visibility = View.GONE
                imageView.visibility = View.VISIBLE
                Glide
                    .with(this)
                    .load(messageOrUrl)
                    .into(imageView)
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
        val  messageOrUrl =   queryString.substring(
            (queryString.lastIndexOf("=") + 1),
            queryString.length - 1
        )
        return  messageOrUrl
    }
}