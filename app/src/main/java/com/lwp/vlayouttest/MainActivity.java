package com.lwp.vlayouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.ScrollView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.lwp.vlayouttest.adapter.LinearTestAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv_main_view);

        VirtualLayoutManager vlmanger = new VirtualLayoutManager(this);

        DelegateAdapter delegateAdapter = new DelegateAdapter(vlmanger);

        List<DelegateAdapter.Adapter> adapterList = new ArrayList<>();

        adapterList.add(new LinearTestAdapter(new LinearLayoutHelper(),16));
        adapterList.add(new LinearTestAdapter(new GridLayoutHelper(6),36));

        adapterList.add(new LinearTestAdapter(new FixLayoutHelper(10,10),1));
        adapterList.add(new LinearTestAdapter(new FixLayoutHelper(FixLayoutHelper.TOP_RIGHT,10,10),1));
        adapterList.add(new LinearTestAdapter(new FixLayoutHelper(FixLayoutHelper.BOTTOM_LEFT,10,10),1));
        adapterList.add(new LinearTestAdapter(new FixLayoutHelper(FixLayoutHelper.BOTTOM_RIGHT,10,10),1));

        ScrollFixLayoutHelper scrollFixLayoutHelper1 = new ScrollFixLayoutHelper(FixLayoutHelper.TOP_RIGHT,160,160);
        scrollFixLayoutHelper1.setShowType(ScrollFixLayoutHelper.SHOW_ALWAYS);
        ScrollFixLayoutHelper scrollFixLayoutHelper2 = new ScrollFixLayoutHelper(FixLayoutHelper.BOTTOM_LEFT,160,160);
        scrollFixLayoutHelper2.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);
        ScrollFixLayoutHelper scrollFixLayoutHelper3 = new ScrollFixLayoutHelper(FixLayoutHelper.BOTTOM_RIGHT,160,160);
        scrollFixLayoutHelper3.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);

        adapterList.add(new LinearTestAdapter(scrollFixLayoutHelper1,1));
        adapterList.add(new LinearTestAdapter(scrollFixLayoutHelper2,1));
        adapterList.add(new LinearTestAdapter(scrollFixLayoutHelper3,1));

        adapterList.add(new LinearTestAdapter(new FloatLayoutHelper(),1));

        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        columnLayoutHelper.setWeights(new float[]{15.0f,25.0f,60.0f});
        adapterList.add(new LinearTestAdapter(columnLayoutHelper,3));

        adapterList.add(new LinearTestAdapter(new SingleLayoutHelper(),1));

        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();
        stickyLayoutHelper.setOffset(180);
        adapterList.add(new LinearTestAdapter(stickyLayoutHelper,1));
        adapterList.add(new LinearTestAdapter(new StickyLayoutHelper(false),1));

        adapterList.add(new LinearTestAdapter(new StaggeredGridLayoutHelper(2),32));

        delegateAdapter.addAdapters(adapterList);

        recyclerView.setLayoutManager(vlmanger);
        recyclerView.setAdapter(delegateAdapter);
    }
}
