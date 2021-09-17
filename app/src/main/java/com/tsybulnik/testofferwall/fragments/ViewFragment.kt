package com.tsybulnik.testofferwall.fragments

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
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
        var str = ""
        var str1 = ""
        viewModel.str.observe(activity as LifecycleOwner, {
            str = it
//             текствью
            if (str.contains("text")) {
                str1 = str.substring(
                    (str.lastIndexOf("=") + 1),
                    str.length - 1
                )
                webView.visibility = View.GONE
                imageView.visibility = View.GONE
                textview.visibility = View.VISIBLE
                textview.text = str1
            }
//            // вебвью
            if (str.contains("webview")) {
                str1 = str.substring(
                    (str.lastIndexOf("=") + 1),
                    str.length - 1
                )
                textview.visibility = View.GONE
                webView.visibility = View.VISIBLE
                webView.loadUrl(str1)
            }
            // Картинка
            if (str.contains("image")) {
                str1 = str.substring(
                    (str.lastIndexOf("=") + 1),
                    str.length - 1
                )
                webView.visibility = View.GONE
                imageView.visibility = View.VISIBLE
                Glide
                    .with(this)
                    .load(str1)
                    .into(imageView);
            }
            if (str.contains("game")) {
                str1 = str.substring(
                    (str.lastIndexOf("=") + 1),
                    str.length - 1
                )
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
}