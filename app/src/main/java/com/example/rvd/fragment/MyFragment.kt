package com.example.rvd.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rvd.GameDetailActivity
import com.example.rvd.R
import com.example.rvd.adapter.MyAdapter
import com.example.rvd.model.GameNFT
import com.example.rvd.service.MyRetrofitHelper
import kotlinx.android.synthetic.main.recycler_view_fragment.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val NAME = "com.example.RVD.NAME"
const val DESCRIPTION = "com.example.RVD.DESCRIPTION"
const val IMAGE = "com.example.RVD.IMAGE"
const val USER_NAME = "com.example.RVD.USER_NAME"
const val USER_IMAGE = "com.example.RVD.USER_IMAGE"

class MyFragment : Fragment() {
    lateinit var myAdapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchGameNFT()
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            fetchGameNFT()
            Toast.makeText(activity, "Data Refreshed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchGameNFT() {
        val helper = MyRetrofitHelper.apiService
        recyclerView.apply {
            // Create a layout manager type of RecycleView: Vertical | Horizontal
            recyclerView.layoutManager = LinearLayoutManager(activity)
            myAdapter = MyAdapter()
        }
        helper.getGame().enqueue(object : Callback<GameNFT> {
            override fun onResponse(call: Call<GameNFT>, response: Response<GameNFT>) {
                recyclerView.apply {
                    // Create a layout manager type of RecycleView: Vertical | Horizontal
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    if (response.body() != null) {
                        swipeRefresh.isRefreshing = false
                        myAdapter.games = response.body()!!
                        recyclerView.adapter = myAdapter
                        myAdapter.setOnItemClickListener(object : MyAdapter.OnItemClickListener {
                            override fun onItemClick(position: Int) {
                                val intent =
                                    Intent(activity, GameDetailActivity::class.java).apply {
                                        putExtra(
                                            NAME,
                                            myAdapter.games.assets[position].collection.name
                                                ?: "No name"
                                        )
                                        putExtra(
                                            DESCRIPTION,
                                            myAdapter.games.assets[position].collection.description
                                                ?: "No description"
                                        )
                                        putExtra(
                                            IMAGE,
                                            myAdapter.games.assets[position].collection.banner_image_url
                                                ?: "https://c.tenor.com/T1ehudBJ0EYAAAAd/nfts-nft.gif"
                                        )
                                        putExtra(
                                            USER_NAME,
                                            myAdapter.games.assets[position].creator.user.username
                                                ?: "No username"
                                        )
                                        putExtra(
                                            USER_IMAGE,
                                            myAdapter.games.assets[position].creator.profile_img_url
                                                ?: "https://c.tenor.com/T1ehudBJ0EYAAAAd/nfts-nft.gif"
                                        )
                                    }
                                activity?.startActivity(intent)
                            }

                        })
                    }
                }
            }

            override fun onFailure(call: Call<GameNFT>, t: Throwable) {
                swipeRefresh.isRefreshing = false
            }
        })
    }
}

