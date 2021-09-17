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

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    private val viewModel: DataViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // так делать нельзя. Чтобы запрос был в главном потоке. Передалать. Работает, потому что мало данных
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
            i+= 1
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
        fun newInstance() = MainFragment()
    }
}