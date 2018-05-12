package finom.list.ruf.listcrypt.presentation.crypt_list.view;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import finom.list.ruf.listcrypt.R;
import finom.list.ruf.listcrypt.presentation.crypt_list.presenter.ListCryptPresenter;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ListCryptFragment extends MvpAppCompatFragment implements ListCryptView.View {

    @InjectPresenter
    public ListCryptPresenter presenter;

    @ProvidePresenter
    public ListCryptPresenter providePresenter() {
        return new ListCryptPresenter();
    }

    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefresh;
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
        initToolbar(root);

        progressBar = root.findViewById(R.id.fragment_list_crypt_progress_bar);
        swipeRefresh = root.findViewById(R.id.fragment_list_crypt_swipe_refresh);
        swipeRefresh.setOnRefreshListener(presenter::onSwipeToRefresh);

        RecyclerView recyclerView = root.findViewById(R.id.fragment_list_crypt_recycler_view);
        recyclerView.setLayoutManager(getLayoutManager());
        adapter = new ListCryptRecyclerAdapter(presenter::onClickCryptoCurrency);
        recyclerView.setAdapter(adapter);

        return root;
    }

    private void initToolbar(View root) {
        Toolbar toolbar = root.findViewById(R.id.fragment_list_crypt_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_and_sort, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        initSearchItemMenu(searchView);

        MenuItem sortItem = menu.findItem(R.id.app_bar_sort);
        initSortItemMenu(sortItem);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("CheckResult")
    private void initSearchItemMenu(SearchView searchView) {
        ImageView imageSearchMagIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        ImageView imageSearchButton = searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
        ImageView imageClose = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        imageSearchMagIcon.setImageResource(R.drawable.ic_baseline_search_white_24px);
        imageSearchButton.setImageResource(R.drawable.ic_baseline_search_white_24px);
        imageClose.setImageResource(R.drawable.ic_baseline_close_white_24px);

        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        int colorWhite = getResources().getColor(R.color.colorWhite);
        searchAutoComplete.setTextColor(colorWhite);
        searchAutoComplete.setHintTextColor(colorWhite);
        searchAutoComplete.setLinkTextColor(colorWhite);
        searchAutoComplete.setHighlightColor(colorWhite);

        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(getText(R.string.search));

        RxSearchView.queryTextChanges(searchView)
                .observeOn(AndroidSchedulers.mainThread())
                .throttleLast(1, TimeUnit.SECONDS)
                .map(CharSequence::toString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(presenter::onSearch);
    }

    private void initSortItemMenu(MenuItem sortItem) {
        //TODO кнопка сортировки
    }

    @Override
    public void updateListCryptoCurrency(@NonNull List<CryptoCurrency> cryptoCurrencies) {
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
