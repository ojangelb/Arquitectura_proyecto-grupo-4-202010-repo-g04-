package co.edu.uniandes.asset.tianguix.dto;

public class AssetRsDto {
	String id;
	String name;
	int stocks;
	ValueAssetDto value;
	String type;
	String state;
	String trader_id;
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
	public int getStocks() {
		return stocks;
	}
	public void setStocks(int stocks) {
		this.stocks = stocks;
	}
	public ValueAssetDto getValue() {
		return value;
	}
	public void setValue(ValueAssetDto value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTrader_id() {
		return trader_id;
	}
	public void setTrader_id(String trader_id) {
		this.trader_id = trader_id;
	}
	
}
