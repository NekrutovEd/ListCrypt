package finom.list.ruf.listcrypt.presentation.crypt_list.view;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import finom.list.ruf.listcrypt.R;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;
import finom.list.ruf.listcrypt.repository.dto.CryptoCurrencyDTO;

class ListCryptRecyclerAdapter extends RecyclerView.Adapter<ListCryptRecyclerAdapter.ListCryptViewHolder> {

    private List<CryptoCurrency> cryptoCurrencies = new ArrayList<>();
    private final IOnClickCryptoCurrencyListener onClickCryptoCurrencyListener;

    ListCryptRecyclerAdapter(@NonNull IOnClickCryptoCurrencyListener onClickCryptoCurrencyListener) {
        this.onClickCryptoCurrencyListener = onClickCryptoCurrencyListener;
    }

    void setCryptoCurrencies(List<CryptoCurrency> cryptoCurrencies) {
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
        private TextView nameTextView;

        private ListCryptViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.view_holder_list_crypt_item_constraint_layout);
            nameTextView = itemView.findViewById(R.id.view_holder_list_crypt_item_name);
        }

        private void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
            nameTextView.setText(cryptoCurrency.getName());
            constraintLayout.setOnClickListener(
                    v -> onClickCryptoCurrencyListener.onClickCryptoCurrency(cryptoCurrency));
        }
    }

    interface IOnClickCryptoCurrencyListener {
        void onClickCryptoCurrency(CryptoCurrency cryptoCurrency);
    }
}
