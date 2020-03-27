package co.edu.uniandes.asset.tianguix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ValueAssetEntity {
	
	 @Id
	 @Column
	 private String idAsset;
	 
	 @Column
	 private String currency;
	 
	 @Column
	 private Long ammount;


	public String getIdAsset() {
		return idAsset;
	}

	public void setIdAsset(String idAsset) {
		this.idAsset = idAsset;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getAmmount() {
		return ammount;
	}

	public void setAmmount(Long ammount) {
		this.ammount = ammount;
	}
	 
	 

}
