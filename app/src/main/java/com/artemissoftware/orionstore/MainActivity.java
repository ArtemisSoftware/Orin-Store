package com.artemissoftware.orionstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.artemissoftware.orionstore.databinding.ActivityMainBinding;
import com.artemissoftware.orionstore.models.CartItem;
import com.artemissoftware.orionstore.models.CartViewModel;
import com.artemissoftware.orionstore.models.Product;
import com.artemissoftware.orionstore.util.PreferenceKeys;
import com.artemissoftware.orionstore.util.Products;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements IMainActivity{

    private static final String TAG = "MainActivity";

    //data binding
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.cart.setOnTouchListener(new CartTouchListener());

        getShoppingCart();
        init();
    }



    private void init(){

        MainFragment fragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment, getString(R.string.fragment_main));
        transaction.commit();
    }


    private void getShoppingCart(){

        SharedPreferences preferences = this.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        Set<String> serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart, new HashSet<String>());

        Products products = new Products();
        List<CartItem> cartItems = new ArrayList<>();

        for(String serialNumber : serialNumbers){
            int quantity = preferences.getInt(serialNumber, 0);

            cartItems.add(new CartItem(products.PRODUCT_MAP.get(serialNumber), quantity));
        }

        CartViewModel viewModel = new CartViewModel();
        viewModel.setCart(cartItems);

        try {

            viewModel.setCartVisible(mainBinding.getCartView().isCartVisible());
        }
        catch (NullPointerException e){
            Log.e(TAG, "getShoppingCart: " + e.getMessage());
        }

        mainBinding.setCartView(viewModel);
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

    @Override
    public void addToCart(Product product, int quantity) {

        SharedPreferences preferences = this.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart, new HashSet<String>());
        serialNumbers.add(String.valueOf(product.getSerial_number()));

        editor.putStringSet(PreferenceKeys.shopping_cart, serialNumbers);
        editor.commit();

        int currentQuantity = preferences.getInt(String.valueOf(product.getSerial_number()), 0);

        editor.putInt(String.valueOf(product.getSerial_number()), (currentQuantity + quantity));
        editor.commit();

        setQuantity(1);

        Toast.makeText(this, "added to cart", Toast.LENGTH_SHORT).show();

        getShoppingCart();
    }

    @Override
    public void inflateViewCartFragment() {
        ViewCartFragment fragment = (ViewCartFragment)getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_view_cart));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(fragment == null){

            fragment = new ViewCartFragment();
            transaction.replace(R.id.main_container, fragment, getString(R.string.fragment_view_cart));
            transaction.addToBackStack(getString(R.string.fragment_view_cart));
            transaction.commit();
        }
    }

    @Override
    public void setCartVisibility(boolean visibility) {
        mainBinding.getCartView().setCartVisible(visibility);
    }

    @Override
    public void updateQuantity(Product product, int quantity) {

        SharedPreferences preferences = this.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        int currentQuantity = preferences.getInt(String.valueOf(product.getSerial_number()), 0);
        editor.putInt(String.valueOf(product.getSerial_number()), (currentQuantity + quantity));
        editor.commit();

        getShoppingCart();
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        SharedPreferences preferences = this.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.remove(String.valueOf(cartItem.getProduct().getSerial_number()));
        editor.commit();

        Set<String> serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart, new HashSet<String>());

        if(serialNumbers.size() == 1){
            editor.remove(PreferenceKeys.shopping_cart);
            editor.commit();
        }
        else{
            serialNumbers.remove(String.valueOf(cartItem.getProduct().getSerial_number()));
            editor.putStringSet(PreferenceKeys.shopping_cart, serialNumbers);
            editor.commit();
        }

        getShoppingCart();

        ViewCartFragment fragment = (ViewCartFragment)getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_view_cart));

        if(fragment != null){
            fragment.updateCartItems();
        }
    }


    public static class CartTouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if(event.getAction() == MotionEvent.ACTION_UP){
                v.setBackgroundColor(v.getContext().getResources().getColor(R.color.blue4));
                v.performClick();

                IMainActivity iMainActivity = (IMainActivity) v.getContext();
                iMainActivity.inflateViewCartFragment();
            }
            else if(event.getAction() == MotionEvent.ACTION_DOWN){
                v.setBackgroundColor(v.getContext().getResources().getColor(R.color.blue6));
            }

            return true;
        }
    }



}
