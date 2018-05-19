package finom.list.ruf.listcrypt.presentation.crypt_list.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
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
public class ListCryptPresenter extends MvpPresenter<ListCryptView.View> implements LifecycleObserver {
    private static final String TAG = "ListCryptPresenter";
    private final Interactor interactor;
    private List<CryptoCurrency> cryptoCurrencies = new ArrayList<>();
    private CompositeDisposable compositeDisposableForPreparation = new CompositeDisposable();
    private CompositeDisposable compositeDisposableForLoading = new CompositeDisposable();
    private String queryOfSearch = "";
    private SortBy sortBy = SortBy.RANK;
    private boolean isAscendingSort = true;

    public ListCryptPresenter() {
        interactor = Interactor.INTERACTOR_IMPLEMENTATION;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        if (cryptoCurrencies != null && !cryptoCurrencies.isEmpty())
            preparationListCryptoCurrency(cryptoCurrencies);
        else loadListCryptoCurrency();
    }

    private void loadListCryptoCurrency() {
        compositeDisposableForLoading.add(interactor.getListCryptoCurrency()
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(getViewState()::hideLoading)
                .subscribe(cryptoCurrencies -> {
                    this.cryptoCurrencies = cryptoCurrencies;
                    getViewState().showListCryptoCurrency();
                    preparationListCryptoCurrency(cryptoCurrencies);
                }, this::handleError));
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onDestroy() {
        super.onDestroy();
        compositeDisposableForLoading.clear();
        compositeDisposableForPreparation.clear();
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

    private void preparationListCryptoCurrency(List<CryptoCurrency> newCryptoCurrencies) {
        compositeDisposableForPreparation.clear();
        compositeDisposableForPreparation.add(Observable.fromIterable(newCryptoCurrencies)
                .subscribeOn(Schedulers.computation())
                .filter(cryptoCurrency -> cryptoCurrency.contains(queryOfSearch))
                .reduce(new ArrayList<CryptoCurrency>(), (result, cryptoCurrency) -> {
                    result.add(cryptoCurrency);
                    return result;
                })
                .map(cryptoCurrencies -> {
                    Collections.sort(cryptoCurrencies, sortBy.getComparator());
                    if (!isAscendingSort) Collections.reverse(cryptoCurrencies);
                    return cryptoCurrencies;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::updateListCryptoCurrency));
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
