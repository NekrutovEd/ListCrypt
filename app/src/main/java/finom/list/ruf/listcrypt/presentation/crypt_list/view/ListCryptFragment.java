package finom.list.ruf.listcrypt.presentation.crypt_list.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import finom.list.ruf.listcrypt.R;
import finom.list.ruf.listcrypt.presentation.crypt_list.presenter.ListCryptPresenter;
import finom.list.ruf.listcrypt.presentation.crypt_list.presenter.SortBy;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;
import finom.list.ruf.listcrypt.presentation.main.ListCryptActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ListCryptFragment extends MvpAppCompatFragment implements ListCryptView.View {

    @InjectPresenter
    public ListCryptPresenter presenter;

    @ProvidePresenter
    public ListCryptPresenter providePresenter() {
        return new ListCryptPresenter();
    }

    private ListCryptActivity activity;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private ListCryptRecyclerAdapter adapter;

    public static ListCryptFragment newInstance() {
        Bundle args = new Bundle();
        ListCryptFragment fragment = new ListCryptFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof ListCryptActivity) {
            activity = (ListCryptActivity) getActivity();
        } else {
            throw new ClassCastException(getActivity().getLocalClassName() + " should extends ListCryptActivity");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_crypt, container, false);
        initToolbar(root);

        progressBar = root.findViewById(R.id.fragment_list_crypt_progress_bar);
        swipeRefresh = root.findViewById(R.id.fragment_list_crypt_swipe_refresh);
        swipeRefresh.setOnRefreshListener(presenter::onSwipeToRefresh);

        recyclerView = root.findViewById(R.id.fragment_list_crypt_recycler_view);
        recyclerView.setLayoutManager(getLayoutManager());
        adapter = new ListCryptRecyclerAdapter(presenter::onClickCryptoCurrency);
        recyclerView.setAdapter(adapter);

        return root;
    }

    private void initToolbar(View root) {
        Toolbar toolbar = root.findViewById(R.id.fragment_list_crypt_toolbar);
        activity.setSupportActionBar(toolbar);

        SearchView searchView = toolbar.findViewById(R.id.fragment_list_crypt_toolbar_search);
        initSearchView(searchView);

        ImageView sortImageView = toolbar.findViewById(R.id.fragment_list_crypt_toolbar_sort);
        sortImageView.setOnClickListener(this::initSortMenu);

        setHasOptionsMenu(true);
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {
            return new GridLayoutManager(getContext(), 2);
        } else {
            return new LinearLayoutManager(getContext());
        }
    }

    @SuppressLint("RestrictedApi")
    private void initSortMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view, Gravity.BOTTOM);

        Menu menu = popupMenu.getMenu();
        if (menu instanceof MenuBuilder) {
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }

        SortBy sortBy = presenter.getSortBy();
        boolean isAscendingSort = presenter.isAscendingSort();

        for (SortBy sortByItem : SortBy.values()) {
            MenuItem menuItem = menu.add(Menu.NONE, Menu.NONE, Menu.NONE, sortByItem.getTitle());
            if (sortByItem == sortBy) {
                menuItem.setIcon(isAscendingSort
                        ? R.drawable.ic_baseline_expand_more_black_24px
                        : R.drawable.ic_baseline_expand_less_black_24px);
            }
            menuItem.setOnMenuItemClickListener(item -> {
                presenter.onMenuItemSortClick(sortByItem);
                popupMenu.dismiss();
                return true;
            });
        }
        popupMenu.show();
    }

    @SuppressLint("CheckResult")
    private void initSearchView(SearchView searchView) {
        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        int colorWhite = getResources().getColor(R.color.colorWhite);
        searchAutoComplete.setTextColor(colorWhite);
        searchAutoComplete.setHintTextColor(colorWhite);
        searchAutoComplete.setLinkTextColor(colorWhite);
        searchAutoComplete.setHighlightColor(colorWhite);

        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(getText(R.string.search));
        searchView.setQuery(presenter.getQueryOfSearch(), false);

        RxSearchView.queryTextChanges(searchView)
                .observeOn(AndroidSchedulers.mainThread())
                .throttleLast(1, TimeUnit.SECONDS)
                .map(CharSequence::toString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(presenter::onSearch);
    }

    @Override
    public void updateListCryptoCurrency(@NonNull List<CryptoCurrency> cryptoCurrencies) {
        Parcelable instanceState = recyclerView.getLayoutManager().onSaveInstanceState();
        adapter.setCryptoCurrencies(cryptoCurrencies);
        recyclerView.getLayoutManager().onRestoreInstanceState(instanceState);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showListCryptoCurrency() {
        swipeRefresh.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCryptDetails(CryptoCurrency cryptoCurrency) {
        activity.showCryptDetailsFragment(cryptoCurrency);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
