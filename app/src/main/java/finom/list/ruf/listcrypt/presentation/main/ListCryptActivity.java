package finom.list.ruf.listcrypt.presentation.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import finom.list.ruf.listcrypt.R;
import finom.list.ruf.listcrypt.presentation.crypt_list.view.ListCryptFragment;

public class ListCryptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_crypt);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ListCryptFragment cryptFragment = (ListCryptFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_layout);
        if (cryptFragment == null) {
            cryptFragment = ListCryptFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main_fragment_layout, cryptFragment)
                    .commit();
        }
    }
}
