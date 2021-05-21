package com.cogeek.tncoffee.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models_old.Notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment {

    private static final boolean IS_DEBUG = true;

    private ListView listView;
    private NotificationAdapter adapter;
    private ArrayList<Notification> notificationArrayList = new ArrayList<Notification>();;
    //    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;
    private Handler slideHandler;
    private CircleIndicator3 indicator;
    private View header;

    private DatabaseReference databaseReference;

    String[] imageUrls = new String[]{
            "https://bratus.co/wp-content/uploads/2019/03/logo-zcafe-cafe-coffee-logo-logotype-vietnam.jpg",
            "https://bratus.co/wp-content/uploads/2019/03/logo-zcafe-cafe-coffee-logo-logotype-vietnam.jpg",
            "https://bratus.co/wp-content/uploads/2019/03/logo-zcafe-cafe-coffee-logo-logotype-vietnam.jpg"
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (IS_DEBUG) {
            fakeData();
        }
        else {
            databaseReference = FirebaseDatabase.getInstance().getReference("notification");
            databaseReference.addValueEventListener(notificationValueEventListner);
        }

        listView = view.findViewById(R.id.lvNotification);
        adapter = new NotificationAdapter(getActivity(), R.layout.notification, notificationArrayList);
        this.header = getLayoutInflater().inflate(R.layout.header_home_listview, null, false);
        listView.addHeaderView(header);
        listView.setAdapter(adapter);

        setUpSlider();
        addEvent();
    }

    private void addEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NotificationBottomSheetDialogFragment bottomSheetDialog = new NotificationBottomSheetDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("imageUrl", notificationArrayList.get(position - 1).getImage());
                bundle.putString("title", notificationArrayList.get(position - 1).getTitle());
                bundle.putString("content", notificationArrayList.get(position - 1).getContent());
                bottomSheetDialog.setArguments(bundle);
                bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "Detail Notification");
            }
        });
    }

    private void setUpSlider() {
        viewPager2 = header.findViewById(R.id.viewPagerInageSlider);
        indicator = header.findViewById(R.id.indicator);


        SliderAdapter sliderAdapter = new SliderAdapter(imageUrls, viewPager2);
        viewPager2.setAdapter(sliderAdapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(1);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        // Transformer
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        slideHandler = new Handler();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable, 4000);
            }
        });

        indicator.setViewPager(viewPager2);
        sliderAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());

    }

    private ValueEventListener notificationValueEventListner = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            notificationArrayList.clear();
            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                Notification post = postSnapshot.getValue(Notification.class);
                Log.e("Data:::::", post.getContent());
                notificationArrayList.add(post);
            }
            Collections.sort(notificationArrayList, (a,b) -> Long.valueOf(b.getTime()).compareTo(a.getTime()));
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            int target = (viewPager2.getCurrentItem() + 1) % imageUrls.length;
            viewPager2.setCurrentItem(target);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 4000);
    }

    private void fakeData() {
//        notificationArrayList = new ArrayList<Notification>();
        notificationArrayList.add(new Notification("Đang giảm giá nè",
                "Đang giảm giá nè, mại zô mại zô giảm 40k cho đơn hàng từ 160k đâyyyyyy",
                "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2",
                (int) (new Date().getTime() / 1000)));

        notificationArrayList.add(new Notification("non",
                "",
                "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2",
                (int) (new Date().getTime() / 1000)));
        notificationArrayList.add(new Notification("non",
                "",
                "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2",
                (int) (new Date().getTime() / 1000)));
    }
}
