package com.artemissoftware.orionstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.artemissoftware.orionstore.databinding.ActivityMainBinding;
import com.artemissoftware.orionstore.models.Product;


public class MainActivity extends AppCompatActivity implements IMainActivity{

    private static final String TAG = "MainActivity";

    //data binding
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }



    private void init(){
        /*
        ViewProductFragment fragment = new ViewProductFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment, getString(R.string.fragment_view_product));
        transaction.commit();
        */

        MainFragment fragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment, getString(R.string.fragment_main));
        transaction.commit();
    }

    @Override
    public void inflateViewProductFragment(Product product) {

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.intent_product), product);


        ViewProductFragment fragment = new ViewProductFragment();
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment, getString(R.string.fragment_view_product));
        transaction.addToBackStack(getString(R.string.fragment_view_product));
        transaction.commit();
    }

    @Override
    public void showQuantityDialog() {

        Log.d(TAG, "showQuantityDialog: showing Quantity Dialog.");
        ChooseQuantityDialog dialog = new ChooseQuantityDialog();
        dialog.show(getSupportFragmentManager(), getString(R.string.dialog_choose_quantity));
    }

    @Override
    public void setQuantity(int quantity) {

        Log.d(TAG, "selectQuantity: selected quantity: " + quantity);

        ViewProductFragment fragment = (ViewProductFragment)getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_view_product));
        if(fragment != null){
            fragment.mBinding.getProductView().setQuantity(quantity);
        }
    }
}
