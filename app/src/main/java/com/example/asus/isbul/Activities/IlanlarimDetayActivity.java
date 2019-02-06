package com.example.asus.isbul.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.asus.isbul.Fragments.BasvurularFragment;
import com.example.asus.isbul.Fragments.IlanimDetayFragment;
import com.example.asus.isbul.R;
import com.example.asus.isbul.Utils.ChangeFragments;

public class IlanlarimDetayActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ChangeFragments changeFragments;
    String id;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    changeFragments.changeDetayFragments(new IlanimDetayFragment(), id);

                    return true;
                case R.id.navigation_dashboard:
                    changeFragments.changeDetayFragments(new BasvurularFragment(), id);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim_detay);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("ilanid");
        changeFragments = new ChangeFragments(IlanlarimDetayActivity.this);
        changeFragments.changeDetayFragments(new IlanimDetayFragment(), id);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
