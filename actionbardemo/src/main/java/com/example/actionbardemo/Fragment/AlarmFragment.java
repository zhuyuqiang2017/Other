package com.example.actionbardemo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.actionbardemo.R;


/**
 * Created by zhuyuqiang on 2017/3/27.
 */

public class AlarmFragment extends Fragment {

    private Button mPopButton;
    public AlarmFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarm_layout,container,false);
//        return super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        mPopButton = (Button) view.findViewById(R.id.btn);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void onAttach(final Context context) {

        super.onAttach(context);
    }

    @Override
    public void onStart() {
//        mPopButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final PopupMenu mMenu = new PopupMenu(getContext(),v){
//                    @Override
//                    public void show() {
//                        super.show();
//                    }
//                };
//                mMenu.inflate(R.menu.test);
//                mMenu.setGravity(Gravity.END);
//                mMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        Toast.makeText(getContext(),""+item.getTitle(),Toast.LENGTH_LONG).show();
//                        return true;
//                    }
//                });
//                mMenu.show();
//            }
//        });
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
