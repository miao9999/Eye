package com.example.dllo.eyepetzier.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.dllo.eyepetzier.mode.bean.DiscoveryFragmentBean;
import com.example.dllo.eyepetzier.ui.activity.ImageDetailsActivity;
import com.example.dllo.eyepetzier.R;

import com.example.dllo.eyepetzier.mode.bean.DisconveryDetailBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.ui.adapter.rv.CommonRvAdapter;
import com.example.dllo.eyepetzier.ui.adapter.rv.RvViewHolder;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/18.
 * 发现二级界面的时间排序界面
 */
public class DiscoveryDetailTimeFragment extends AbaBaseFragment  {


    /**
     * *******************************************
     */
    /**
     * image_details_image_model
     */

    private ArrayList<DisconveryDetailBean.ItemListBean> mDetailList = new ArrayList<>();

    private RecyclerView mRecyclerView;



    private String str;

    public static DiscoveryDetailTimeFragment getDiscoveryDetailAllFragment(String str) {
        DiscoveryDetailTimeFragment discoveryDetailTimeFragment = new DiscoveryDetailTimeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        discoveryDetailTimeFragment.setArguments(bundle);
        return discoveryDetailTimeFragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_discovery_detail_time;
    }

    @Override
    protected void initView() {
        mRecyclerView = bindView(R.id.fragment_discoverydetail_rv);
    }

    @Override
    protected void initData() {
        initializeImages();
    }




    private void initializeImages() {

        Bundle bundle = getArguments();
        this.str = bundle.getString("url");
        NetRequestSingleton.getInstance().startRequest(str, DisconveryDetailBean.class, new IOnHttpCallback<DisconveryDetailBean>() {
            @Override
            public void onSuccess(DisconveryDetailBean response) {
                List<DisconveryDetailBean.ItemListBean> listBeen = response.getItemList();
                CommonRvAdapter<DisconveryDetailBean.ItemListBean> adapter = new CommonRvAdapter<DisconveryDetailBean.ItemListBean>(context, listBeen, R.layout.item_discovery_detail) {
                    @Override
                    protected void convert(RvViewHolder holder, DisconveryDetailBean.ItemListBean itemListBean, int pos) {
                        holder.setImgUrl(R.id.item_discovery_detail_iv, itemListBean.getData().getCover().getFeed());
                    }
                };
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex) {

            }
        });

    }









}
