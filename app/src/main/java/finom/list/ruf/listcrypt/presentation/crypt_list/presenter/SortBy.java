package finom.list.ruf.listcrypt.presentation.crypt_list.presenter;

import android.support.annotation.StringRes;

import java.util.Comparator;

import finom.list.ruf.listcrypt.R;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;

public enum SortBy {

    RANK(R.string.sort_by_rank, CryptoCurrency::compareRank),
    PRICE(R.string.sort_by_price, CryptoCurrency::comparePriceUsd),
    VOLUME(R.string.sort_by_volume, CryptoCurrency::compareVolumeUsd),
    MARKET_CAP(R.string.sort_by_market_cap, CryptoCurrency::compareMarketCapUsd),
    PERCENT_CHANGE(R.string.sort_by_percent_change, CryptoCurrency::comparePercentChange_24h);

    @StringRes
    private int title;
    private Comparator<CryptoCurrency> comparator;

    SortBy(@StringRes int title, Comparator<CryptoCurrency> comparator) {
        this.title = title;
        this.comparator = comparator;
    }

    @StringRes
    public int getTitle() {
        return title;
    }

    public Comparator<CryptoCurrency> getComparator() {
        return comparator;
    }
}
