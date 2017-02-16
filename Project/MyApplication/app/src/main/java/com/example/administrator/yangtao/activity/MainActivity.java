package com.example.administrator.yangtao.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.administrator.yangtao.R;
import com.example.administrator.yangtao.modules.Message.activity.MessageFragnemet;
import com.example.administrator.yangtao.modules.MyLogin.activity.MyLoginFragnemet;
import com.example.administrator.yangtao.modules.Party.activity.PartyFragnemet;
import com.example.administrator.yangtao.modules.SaleRank.activity.SaleRankFragnemet;
import com.example.administrator.yangtao.modules.shouye.activity.ShouyeFragnemet;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    private RadioGroup radioGroup;
    private Fragment shouyefragmet,rankfragment,partyfragment,messagefragment,minefragment;

    private  int lastSelector=-1;

    Fragment[] fragments = new Fragment[5];
    @Override
    protected void findViews() {
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        shouyefragmet = new ShouyeFragnemet();
        rankfragment=new SaleRankFragnemet();
        partyfragment=new PartyFragnemet();
        messagefragment=new MessageFragnemet();
        minefragment=new MyLoginFragnemet();
        radioGroup.setOnCheckedChangeListener(this);
        addFragment(2);
    }




    @Override
    protected void init() {


    }

    @Override
    protected void initEvent() {

    }
    @Override
    protected void loadData() {

    }

    @Override
    protected int setLatoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void requestNetData() {

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        int position = 0;
        switch (checkedId){
            case R.id.radiobutton_rank:
                position=0;
                break;
            case R.id.radiobutton_party:
                position=1;
                break;
            case R.id.radiobutton_shouye:
                position=2;
                break;
            case R.id.radiobutton_message:
                position=3;
                break;
            case R.id.radiobutton_mine:
                position=4;
                break;

        }
        addFragment(position);

    }
    private void addFragment(int position) {
        if(lastSelector==position){
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(lastSelector!=-1){
            transaction.hide(fragments[lastSelector]);
        }
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[0] = rankfragment;
                    break;
                case 1:
                    fragments[1] = partyfragment;
                    break;
                case 2:
                    fragments[2] = shouyefragmet;
                    break;
                case 3:
                    fragments[3] = messagefragment;
                    break;
                case 4:
                    fragments[4] = minefragment;
                    break;
            }
            transaction.add(R.id.container, fragments[position]);


        }else {
            transaction.show(fragments[position]);
        }
        transaction.commit();
        lastSelector=position;
    }
}
