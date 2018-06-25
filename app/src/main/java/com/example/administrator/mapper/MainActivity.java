package com.example.administrator.mapper;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.administrator.mapper.view.FragmentLike;
import com.example.administrator.mapper.view.FragmentMainPage;
import com.example.administrator.mapper.view.FragmentMyUsage;
import com.example.administrator.mapper.view.FragmentSetting;
import com.example.administrator.mapper.view.FragmentWrite;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btn_mainpage)
    FloatingActionButton btnMainpage;
    @BindView(R.id.btn_myusage)
    FloatingActionButton btnMyusage;
    @BindView(R.id.btn_collection)
    FloatingActionButton btnCollection;
    @BindView(R.id.btn_write)
    FloatingActionButton btnWrite;
    @BindView(R.id.btn_setting)
    FloatingActionButton btnSetting;
    @BindView(R.id.multiple_actions)
    FloatingActionsMenu multipleActions;
    @BindView(R.id.contentContainer)
    FrameLayout contentContainer;
    private FragmentMainPage fragment1;
    private FragmentMyUsage fragment2;
    private FragmentLike fragment3;
    private FragmentWrite fragment4;
    private FragmentSetting fragment5;
    private static final int FRAGMENT_ONE = 0;
    private static final int FRAGMENT_TWO = 1;
    private static final int FRAGMENT_THREE = 2;
    public static final int FRAGMENT_FOURTH = 3;
    public static final int FRAGMENT_FIFTH = 4;
    private FloatingActionButton btn_mpage, btn_musage, btn_col, btn_write, btn_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showFragment(0);
        btn_mpage = (FloatingActionButton) findViewById(R.id.btn_mainpage);
        btn_musage = (FloatingActionButton) findViewById(R.id.btn_collection);
        btn_col = (FloatingActionButton) findViewById(R.id.btn_myusage);
        btn_write = (FloatingActionButton) findViewById(R.id.btn_write);
        multipleActions= (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        btn_set = (FloatingActionButton) findViewById(R.id.btn_setting);
        btn_mpage.setOnClickListener(this);
        btn_musage.setOnClickListener(this);
        btn_col.setOnClickListener(this);
        btn_write.setOnClickListener(this);
        btn_set.setOnClickListener(this);

    }

    public void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case FRAGMENT_ONE:
                if (fragment1 == null) {
                    fragment1 = new FragmentMainPage();
                    ft.add(R.id.contentContainer, fragment1);
                } else {
                    ft.show(fragment1);
                }
                break;
            case FRAGMENT_TWO:
                if (fragment2 == null) {
                    fragment2 = new FragmentMyUsage();
                    ft.add(R.id.contentContainer, fragment2);
                } else {
                    ft.show(fragment2);
                }
                break;
            case FRAGMENT_THREE:
                if (fragment3 == null) {
                    fragment3 = new FragmentLike();
                    ft.add(R.id.contentContainer, fragment3);
                } else {
                    ft.show(fragment3);
                }
                break;
            case FRAGMENT_FOURTH:
                if (fragment4 == null) {
                    fragment4 = new FragmentWrite();
                    ft.add(R.id.contentContainer, fragment4);
                } else {
                    ft.show(fragment4);
                }
                break;
            case FRAGMENT_FIFTH:
                if (fragment5 == null) {
                    fragment5 = new FragmentSetting();
                    ft.add(R.id.contentContainer, fragment5);
                } else {
                    ft.show(fragment5);
                }
                break;
            default:
                break;
        }
        ft.commit();
    }

    /**
     * 闅愯棌Fragment
     *
     * @param ft
     */
    public void hideFragment(FragmentTransaction ft) {

        if (fragment1 != null) {
            ft.hide(fragment1);
        }
        if (fragment2 != null) {
            ft.hide(fragment2);
        }
        if (fragment3 != null) {
            ft.hide(fragment3);
        }
        if (fragment4 != null) {
            ft.hide(fragment4);
        }
        if (fragment5 != null) {
            ft.hide(fragment5);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mainpage:
                showFragment(0);
                break;
            case R.id.btn_myusage:
                showFragment(1);
                break;
            case R.id.btn_collection:
                showFragment(2);
                break;
            case R.id.btn_write:
                showFragment(3);
                break;
            case R.id.btn_setting:
                showFragment(4);
                break;
        }
        multipleActions.collapse();
    }
}
