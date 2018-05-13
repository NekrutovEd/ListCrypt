package finom.list.ruf.listcrypt.presentation.crypt_list.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import finom.list.ruf.listcrypt.busines.interactor.Interactor;
import finom.list.ruf.listcrypt.presentation.crypt_list.view.ListCryptView;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ListCryptPresenter extends MvpPresenter<ListCryptView.View> {
    private static final String TAG = "ListCryptPresenter";
    private final Interactor interactor;
    private List<CryptoCurrency> cryptoCurrencies = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String queryOfSearch = "";
    private SortBy sortBy = SortBy.RANK;
    private boolean isAscendingSort = true;

    public ListCryptPresenter() {
        interactor = Interactor.INTERACTOR_IMPLEMENTATION;
    }

    public void start() {
        if (cryptoCurrencies != null && !cryptoCurrencies.isEmpty())
            preparationListCryptoCurrency(cryptoCurrencies);
        else loadListCryptoCurrency();
    }

    @SuppressLint("CheckResult")
    private void loadListCryptoCurrency() {
        interactor.getListCryptoCurrency()
                .map(cryptoCurrencies -> this.cryptoCurrencies = cryptoCurrencies)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(cryptoCurrencies -> getViewState().showListCryptoCurrency())
                .doFinally(getViewState()::hideLoading)
                .subscribe(this::preparationListCryptoCurrency, this::handleError);
    }

    public void onSwipeToRefresh() {
        loadListCryptoCurrency();
    }

    public void onClickCryptoCurrency(CryptoCurrency cryptoCurrency) {
        getViewState().showCryptDetails(cryptoCurrency);
    }

    private void handleError(Throwable throwable) {
        //TODO обработка ошибок
        Log.e(TAG, "handleError: " + throwable.getMessage(), throwable);
        getViewState().showErrorMessage("Произошла ошибка");
    }

    public void onSearch(String queryOfSearch) {
        this.queryOfSearch = queryOfSearch;
        preparationListCryptoCurrency(cryptoCurrencies);
    }

    private void preparationListCryptoCurrency(List<CryptoCurrency> cryptoCurrencies) {
        compositeDisposable.clear();
        Collections.sort(cryptoCurrencies, sortBy.getComparator());
        if (!isAscendingSort) Collections.reverse(cryptoCurrencies);
        if (queryOfSearch.isEmpty()) {
            getViewState().updateListCryptoCurrency(cryptoCurrencies);
        } else {
            List<CryptoCurrency> result = new ArrayList<>();
            compositeDisposable.add(Observable.fromIterable(cryptoCurrencies)
                    .subscribeOn(Schedulers.computation())
                    .filter(cryptoCurrency -> cryptoCurrency.contains(queryOfSearch))
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> getViewState().updateListCryptoCurrency(result))
                    .subscribe(result::add));
        }
    }

    public void onMenuItemSortClick(SortBy sortBy) {
        if (this.sortBy == sortBy) {
            isAscendingSort = !isAscendingSort;
        } else {
            this.sortBy = sortBy;
            isAscendingSort = true;
        }
        preparationListCryptoCurrency(cryptoCurrencies);
    }

    public boolean isAscendingSort() {
        return isAscendingSort;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public String getQueryOfSearch() {
        return queryOfSearch;
    }
}
