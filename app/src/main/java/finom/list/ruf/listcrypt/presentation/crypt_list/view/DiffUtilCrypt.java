package finom.list.ruf.listcrypt.presentation.crypt_list.view;

import android.support.v7.util.DiffUtil;

import java.util.List;

import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;

/**
 * @author ernekrutov on 14.05.2018.
 */
class DiffUtilCrypt extends DiffUtil.Callback {
    private final List<CryptoCurrency> newCryptoCurrencies;
    private final List<CryptoCurrency> oldCryptoCurrencies;

    DiffUtilCrypt(List<CryptoCurrency> newCryptoCurrencies, List<CryptoCurrency> oldCryptoCurrencies) {
        this.newCryptoCurrencies = newCryptoCurrencies;
        this.oldCryptoCurrencies = oldCryptoCurrencies;
    }

    @Override
    public int getOldListSize() {
        return oldCryptoCurrencies.size();
    }

    @Override
    public int getNewListSize() {
        return newCryptoCurrencies.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newCryptoCurrencies.get(newItemPosition).getId().equals(oldCryptoCurrencies.get(oldItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//        return !newCryptoCurrencies.get(newItemPosition).equals(oldCryptoCurrencies.get(oldItemPosition));
        return true;
    }
}
