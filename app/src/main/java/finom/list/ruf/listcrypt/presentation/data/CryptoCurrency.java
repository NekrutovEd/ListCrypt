package finom.list.ruf.listcrypt.presentation.data;

public class CryptoCurrency {

    private String id;
    private String name;
    private String symbol;
    private Integer rank;
    private Float priceUsd;
    private Float priceBtc;
    private Long volumeUsd_24h;
    private Long marketCapUsd;
    private Long availableSupply;
    private Long totalSupply;
    private Long maxSupply;
    private Float percentChange_1h;
    private Float percentChange_24h;
    private Float percentChange_7d;
    private Long lastUpdated;


    /**
     * Returns true if and only if {@code CryptoCurrency.name} or {@code CryptoCurrency.symbol} contains string.
     * Without the register
     *
     * @param textSearch the string to search for
     */
    public boolean contains(String textSearch) {
        return name.toLowerCase().contains(textSearch.toLowerCase())
                || symbol.toLowerCase().contains(textSearch.toLowerCase());
    }

    public int compareRank(CryptoCurrency cryptoCurrency) {
        Integer rank = cryptoCurrency.getRank();
        if (this.rank > rank) return 1;
        else if (this.rank < rank) return -1;
        else return 0;
    }

    public int comparePriceUsd(CryptoCurrency cryptoCurrency) {
        Float priceUsd = cryptoCurrency.getPriceUsd();
        if (this.priceUsd > priceUsd) return 1;
        else if (this.priceUsd < priceUsd) return -1;
        else return 0;
    }

    public int compareMarketCapUsd(CryptoCurrency cryptoCurrency) {
        Long marketCapUsd = cryptoCurrency.getMarketCapUsd();
        if (this.marketCapUsd > marketCapUsd) return 1;
        else if (this.marketCapUsd < marketCapUsd) return -1;
        else return 0;
    }

    public int compareVolumeUsd(CryptoCurrency cryptoCurrency) {
        Long volumeUsd_24h = cryptoCurrency.getVolumeUsd_24h();
        if (this.volumeUsd_24h > volumeUsd_24h) return 1;
        else if (this.volumeUsd_24h < volumeUsd_24h) return -1;
        else return 0;
    }

    public int comparePercentChange_24h(CryptoCurrency cryptoCurrency) {
        Float percentChange_24h = cryptoCurrency.getPercentChange_24h();
        if (this.percentChange_24h > percentChange_24h) return 1;
        else if (this.percentChange_24h < percentChange_24h) return -1;
        else return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CryptoCurrency that = (CryptoCurrency) o;

        if (!id.equals(that.id)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (symbol != null ? !symbol.equals(that.symbol) : that.symbol != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;
        if (priceUsd != null ? !priceUsd.equals(that.priceUsd) : that.priceUsd != null)
            return false;
        if (priceBtc != null ? !priceBtc.equals(that.priceBtc) : that.priceBtc != null)
            return false;
        if (volumeUsd_24h != null ? !volumeUsd_24h.equals(that.volumeUsd_24h) : that.volumeUsd_24h != null)
            return false;
        if (marketCapUsd != null ? !marketCapUsd.equals(that.marketCapUsd) : that.marketCapUsd != null)
            return false;
        if (availableSupply != null ? !availableSupply.equals(that.availableSupply) : that.availableSupply != null)
            return false;
        if (totalSupply != null ? !totalSupply.equals(that.totalSupply) : that.totalSupply != null)
            return false;
        if (maxSupply != null ? !maxSupply.equals(that.maxSupply) : that.maxSupply != null)
            return false;
        if (percentChange_1h != null ? !percentChange_1h.equals(that.percentChange_1h) : that.percentChange_1h != null)
            return false;
        if (percentChange_24h != null ? !percentChange_24h.equals(that.percentChange_24h) : that.percentChange_24h != null)
            return false;
        if (percentChange_7d != null ? !percentChange_7d.equals(that.percentChange_7d) : that.percentChange_7d != null)
            return false;
        return lastUpdated != null ? lastUpdated.equals(that.lastUpdated) : that.lastUpdated == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (priceUsd != null ? priceUsd.hashCode() : 0);
        result = 31 * result + (priceBtc != null ? priceBtc.hashCode() : 0);
        result = 31 * result + (volumeUsd_24h != null ? volumeUsd_24h.hashCode() : 0);
        result = 31 * result + (marketCapUsd != null ? marketCapUsd.hashCode() : 0);
        result = 31 * result + (availableSupply != null ? availableSupply.hashCode() : 0);
        result = 31 * result + (totalSupply != null ? totalSupply.hashCode() : 0);
        result = 31 * result + (maxSupply != null ? maxSupply.hashCode() : 0);
        result = 31 * result + (percentChange_1h != null ? percentChange_1h.hashCode() : 0);
        result = 31 * result + (percentChange_24h != null ? percentChange_24h.hashCode() : 0);
        result = 31 * result + (percentChange_7d != null ? percentChange_7d.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        return result;
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
