package finom.list.ruf.listcrypt.presentation.crypt_list.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import finom.list.ruf.listcrypt.R;
import finom.list.ruf.listcrypt.presentation.crypt_list.presenter.ListCryptPresenter;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;

public class ListCryptFragment extends MvpAppCompatFragment implements ListCryptView.View {

    @InjectPresenter
    public ListCryptPresenter presenter;

    @ProvidePresenter
    public ListCryptPresenter providePresenter() {
        return new ListCryptPresenter();
    }

    private ListCryptRecyclerAdapter adapter;

    public static ListCryptFragment newInstance() {
        Bundle args = new Bundle();
        ListCryptFragment fragment = new ListCryptFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_crypt, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.fragment_list_crypt_recycler_view);
        recyclerView.setLayoutManager(getLayoutManager());
        adapter = new ListCryptRecyclerAdapter(presenter::onClickCryptoCurrency);
        recyclerView.setAdapter(adapter);

        return root;
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {
            return new GridLayoutManager(getContext(), 2);
        } else {
            return new LinearLayoutManager(getContext());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void updateListCryptoCurrency(List<CryptoCurrency> cryptoCurrencies) {
        adapter.setCryptoCurrencies(cryptoCurrencies);
    }
}
