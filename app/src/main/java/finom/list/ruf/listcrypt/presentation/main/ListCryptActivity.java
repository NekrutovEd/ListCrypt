package finom.list.ruf.listcrypt.presentation.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import finom.list.ruf.listcrypt.R;
import finom.list.ruf.listcrypt.presentation.crypt_details.view.CryptDetailsFragment;
import finom.list.ruf.listcrypt.presentation.crypt_list.view.ListCryptFragment;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;

public class ListCryptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_crypt);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initListCryptFragment();
    }

    private void initListCryptFragment() {
        Fragment fragment =  getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_layout);
        if (fragment == null) {
            fragment = ListCryptFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main_fragment_layout, fragment)
                    .commit();
        }
    }

    public void showCryptDetailsFragment(CryptoCurrency cryptoCurrency) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.right_to_left_enter, R.anim.right_to_left_exit,
                        R.anim.left_to_right_enter, R.anim.left_to_right_exit)
                .replace(R.id.activity_main_fragment_layout, CryptDetailsFragment.newInstance(cryptoCurrency))
                .addToBackStack(null)
                .commit();
    }
}
