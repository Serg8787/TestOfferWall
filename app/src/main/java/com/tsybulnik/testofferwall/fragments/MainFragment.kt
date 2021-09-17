package com.tsybulnik.testofferwall.fragments

import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tsybulnik.testofferwall.DataViewModel
import com.tsybulnik.testofferwall.R
import com.tsybulnik.testofferwall.network.APIService
import com.tsybulnik.testofferwall.network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val viewModel: DataViewModel by activityViewModels()
    private var isFirst: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // так делать нельзя. Чтобы запрос был в главном потоке. Передалать
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


        val retrofit = RetrofitClient.getClient("http://demo3005513.mockable.io/api/v1/").create(
            APIService::class.java
        )

        val list = retrofit.getDataList().execute().body()?.data
        val idList: ArrayList<Int> = arrayListOf()

        for (number in list!!.indices) {
            idList.add(list[number].id)
        }

        var objOfView = retrofit.getView(idList[0]).execute().body()
        viewModel.str.value = objOfView.toString()

        var i = 0

        btOnWard.setOnClickListener {

            i += 1
            if (i < idList.size) {
                objOfView = retrofit.getView(idList[i]).execute().body()
                viewModel.str.value = objOfView.toString()
            } else {
                i = 0
                objOfView = retrofit.getView(idList[i]).execute().body()
                viewModel.str.value = objOfView.toString()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}