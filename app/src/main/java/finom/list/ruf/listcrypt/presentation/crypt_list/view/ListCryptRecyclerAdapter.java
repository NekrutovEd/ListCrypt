package finom.list.ruf.listcrypt.presentation.crypt_list.view;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import finom.list.ruf.listcrypt.R;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;

class ListCryptRecyclerAdapter extends RecyclerView.Adapter<ListCryptRecyclerAdapter.ListCryptViewHolder> {

    private List<CryptoCurrency> cryptoCurrencies = new ArrayList<>();
    private final IOnClickCryptoCurrencyListener onClickCryptoCurrencyListener;

    ListCryptRecyclerAdapter(@NonNull IOnClickCryptoCurrencyListener onClickCryptoCurrencyListener) {
        this.onClickCryptoCurrencyListener = onClickCryptoCurrencyListener;
    }

    void setCryptoCurrencies(@NonNull List<CryptoCurrency> cryptoCurrencies) {
        this.cryptoCurrencies = cryptoCurrencies;
        notifyDataSetChanged();
    }

    @Override
    public ListCryptViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ListCryptViewHolder(inflater.inflate(R.layout.view_holder_list_crypt_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ListCryptViewHolder holder, int position) {
        holder.setCryptoCurrency(cryptoCurrencies.get(position));
    }

    @Override
    public int getItemCount() {
        return cryptoCurrencies.size();
    }

    class ListCryptViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout constraintLayout;
        private TextView symbolTextView;
        private TextView nameTextView;
        private TextView marketCapTextView;
        private TextView volumeTextView;
        private TextView priceTextView;
        private TextView priceChangeTextView;

        private ListCryptViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.view_holder_list_crypt_item_constraint_layout);
            symbolTextView = itemView.findViewById(R.id.view_holder_list_crypt_item_symbol);
            nameTextView = itemView.findViewById(R.id.view_holder_list_crypt_item_name);
            marketCapTextView = itemView.findViewById(R.id.view_holder_list_crypt_item_market_cap);
            volumeTextView = itemView.findViewById(R.id.view_holder_list_crypt_item_volume);
            priceTextView = itemView.findViewById(R.id.view_holder_list_crypt_item_price);
            priceChangeTextView = itemView.findViewById(R.id.view_holder_list_crypt_item_percent_change_24h);
        }

        private void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
            symbolTextView.setText(cryptoCurrency.getSymbol());
            nameTextView.setText(cryptoCurrency.getName());
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
            marketCapTextView.setText(String.format("MC: %s", format.format(cryptoCurrency.getMarketCapUsd())));
            volumeTextView.setText(String.format("V: %s", format.format(cryptoCurrency.getVolumeUsd_24h())));
            priceTextView.setText(format.format(cryptoCurrency.getPriceUsd()));

            String formatPercent = "%s%%";
            if (cryptoCurrency.getPercentChange_24h() > 0) {
                priceChangeTextView.setTextColor(
                        priceChangeTextView.getResources().getColor(R.color.colorGreen));
                formatPercent = "+%s%%";
            } else if (cryptoCurrency.getPercentChange_24h() < 0) {
                priceChangeTextView.setTextColor(
                        priceChangeTextView.getResources().getColor(R.color.colorRed));
            } else {
                priceChangeTextView.setTextColor(
                        priceChangeTextView.getResources().getColor(R.color.colorGreen));
            }
            priceChangeTextView.setText(String.format(formatPercent, cryptoCurrency.getPercentChange_24h()));


            constraintLayout.setOnClickListener(
                    v -> onClickCryptoCurrencyListener.onClickCryptoCurrency(cryptoCurrency));
        }
    }

    interface IOnClickCryptoCurrencyListener {
        void onClickCryptoCurrency(CryptoCurrency cryptoCurrency);
    }
}
