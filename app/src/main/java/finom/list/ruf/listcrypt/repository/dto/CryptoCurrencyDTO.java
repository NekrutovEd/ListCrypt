package finom.list.ruf.listcrypt.repository.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoCurrencyDTO {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("symbol")
    @Expose
    private String symbol;

    @SerializedName("rank")
    @Expose
    private Integer rank;

    @SerializedName("price_usd")
    @Expose
    private Float priceUsd;

    @SerializedName("price_btc")
    @Expose
    private Float priceBtc;

    @SerializedName("24h_volume_usd")
    @Expose
    private Long volumeUsd_24h;

    @SerializedName("market_cap_usd")
    @Expose
    private Long marketCapUsd;

    @SerializedName("available_supply")
    @Expose
    private Long availableSupply;

    @SerializedName("total_supply")
    @Expose
    private Long totalSupply;

    @SerializedName("max_supply")
    @Expose
    private Long maxSupply;

    @SerializedName("percent_change_1h")
    @Expose
    private Float percentChange_1h;

    @SerializedName("percent_change_24h")
    @Expose
    private Float percentChange_24h;

    @SerializedName("percent_change_7d")
    @Expose
    private Float percentChange_7d;

    @SerializedName("last_updated")
    @Expose
    private Long lastUpdated;

    public CryptoCurrencyDTO(String id,
                             String name,
                             String symbol,
                             Integer rank,
                             Float priceUsd,
                             Float priceBtc,
                             Long volumeUsd_24h,
                             Long marketCapUsd,
                             Long availableSupply,
                             Long totalSupply,
                             Long maxSupply,
                             Float percentChange_1h,
                             Float percentChange_24h,
                             Float percentChange_7d,
                             Long lastUpdated) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.priceUsd = priceUsd;
        this.priceBtc = priceBtc;
        this.volumeUsd_24h = volumeUsd_24h;
        this.marketCapUsd = marketCapUsd;
        this.availableSupply = availableSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.percentChange_1h = percentChange_1h;
        this.percentChange_24h = percentChange_24h;
        this.percentChange_7d = percentChange_7d;
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Float getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Float priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Float getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(Float priceBtc) {
        this.priceBtc = priceBtc;
    }

    public Long getVolumeUsd_24h() {
        return volumeUsd_24h;
    }

    public void setVolumeUsd_24h(Long volumeUsd_24h) {
        this.volumeUsd_24h = volumeUsd_24h;
    }

    public Long getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(Long marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public Long getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(Long availableSupply) {
        this.availableSupply = availableSupply;
    }

    public Long getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Long totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Long getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(Long maxSupply) {
        this.maxSupply = maxSupply;
    }

    public Float getPercentChange_1h() {
        return percentChange_1h;
    }

    public void setPercentChange_1h(Float percentChange_1h) {
        this.percentChange_1h = percentChange_1h;
    }

    public Float getPercentChange_24h() {
        return percentChange_24h;
    }

    public void setPercentChange_24h(Float percentChange_24h) {
        this.percentChange_24h = percentChange_24h;
    }

    public Float getPercentChange_7d() {
        return percentChange_7d;
    }

    public void setPercentChange_7d(Float percentChange_7d) {
        this.percentChange_7d = percentChange_7d;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
