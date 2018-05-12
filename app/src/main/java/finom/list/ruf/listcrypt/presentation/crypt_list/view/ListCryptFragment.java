package finom.list.ruf.listcrypt.presentation.crypt_list.view;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
    private SwipeRefreshLayout swipeRefresh;

    @ProvidePresenter
    public ListCryptPresenter providePresenter() {
        return new ListCryptPresenter();
    }

    private ProgressBar progressBar;
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
        progressBar = root.findViewById(R.id.fragment_list_crypt_progress_bar);
        swipeRefresh = root.findViewById(R.id.fragment_list_crypt_swipe_refresh);
        swipeRefresh.setOnRefreshListener(presenter::onSwipeToRefresh);

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

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showListCryptoCurrency() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionManager.beginDelayedTransition(swipeRefresh, new Slide(Gravity.END));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(swipeRefresh);
        }
        swipeRefresh.setVisibility(View.VISIBLE);
    }
}
