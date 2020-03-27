package co.edu.uniandes.asset.tianguix.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AssetEntity {

    @Id
    private String id;
    @Column
    private String companyName;
    @Column
    private int stocks;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "idAsset")
    private ValueAssetEntity valueAsset;
    @Column
    private String type;
    @Column
    private String trader_id;
    @Column
    private String state;
    @Column
    private Long create_at;

    public AssetEntity() {}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getStocks() {
		return stocks;
	}

	public void setStocks(int stocks) {
		this.stocks = stocks;
	}

	public ValueAssetEntity getValueAsset() {
		return valueAsset;
	}

	public void setValueAsset(ValueAssetEntity valueAsset) {
		this.valueAsset = valueAsset;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTrader_id() {
		return trader_id;
	}

	public void setTrader_id(String trader_id) {
		this.trader_id = trader_id;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Long getCreate_at() {
		return create_at;
	}


	public void setCreate_at(Long create_at) {
		this.create_at = create_at;
	}
	

}