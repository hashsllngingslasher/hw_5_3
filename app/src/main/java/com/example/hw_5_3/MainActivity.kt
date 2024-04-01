package com.example.hw_5_3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_5_3.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var adapter = PixaAdapter(arrayListOf())
    var oldWord = ""
    var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        initClickers()
        addScrollListener()
    }

    private fun initClickers() {
        binding.btnSearch.setOnClickListener {
            requestByImage(captureSearchWord())
        }
    }

    private fun requestByImage(newWord: String) {
        RetrofitService.api.getImages(searchWord = newWord, page = currentPage)
            .enqueue(object : Callback<PixaModel> {

                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(p0: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        response.body()?.let { model ->
                            if (newWord != oldWord) {
                                adapter.list = model.hits
                            } else {
                                adapter.addList(model.hits)
                                ++currentPage
                            }
                            adapter.notifyDataSetChanged()
                        }
                        oldWord = captureSearchWord()
                    }
                }
                override fun onFailure(p0: Call<PixaModel>, error: Throwable) {
                    Log.e("fail", "onFailure: ${error.message}")
                }
            })
    }

    private fun addScrollListener() {
        binding.rvPhoto.addOnScrollListener(object :
            PaginationScrollListener(binding.rvPhoto.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                ++currentPage
                requestByImage(captureSearchWord())
            }
        })
    }

    private fun initAdapter() {
        binding.rvPhoto.adapter = adapter
    }

    private fun captureSearchWord() = binding.etPhoto.text.toString()
}