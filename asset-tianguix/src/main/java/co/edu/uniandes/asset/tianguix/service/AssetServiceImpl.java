package co.edu.uniandes.asset.tianguix.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.uniandes.asset.tianguix.AssetRepossitory;
import co.edu.uniandes.asset.tianguix.dto.AssetDto;
import co.edu.uniandes.asset.tianguix.dto.AssetRsDto;
import co.edu.uniandes.asset.tianguix.dto.ValueAssetDto;
import co.edu.uniandes.asset.tianguix.entity.AssetEntity;
import co.edu.uniandes.asset.tianguix.entity.ValueAssetEntity;

@Service
public class AssetServiceImpl implements AssetService {
	
	@Autowired
	private AssetRepossitory repository;
	
	public ResponseEntity<AssetRsDto> saveAsset (AssetDto assetRqDto)  {
		AssetEntity assetEntitySave;
		AssetEntity assetEntity = mapSaveAsset(assetRqDto);
		assetEntitySave=repository.save(assetEntity);
		return mapResponseAsset(assetEntitySave);
	}
	// 
	public ResponseEntity<List<AssetDto>> searchAssetByParams(String type,String currency, Long currency_min,
			Long currency_max, Integer stock_ammount_min, Integer stock_ammount_max) {
		
			    if ((stock_ammount_min!=null)&& (stock_ammount_max!=null) && (currency_min!=null)&& (currency_max!=null)) {
				    return mapSearchResponseAsset(repository.findByAllParam(stock_ammount_min,stock_ammount_max,currency_min,currency_max,currency,type));
				}
			    else if ((stock_ammount_min!=null)&& (stock_ammount_max!=null)) {
					return mapSearchResponseAsset(repository.findByStocksParam(stock_ammount_min,stock_ammount_max,currency,type));
				} else if ((currency_min!=null)&& (currency_max!=null)) {
					return mapSearchResponseAsset(repository.findByCurrencyParam(currency_min,currency_max,currency,type));
				} else {
					//return new ResponseEntity("",HttpStatus.BAD_REQUEST);
					return null;
				}
	}
	
	public ResponseEntity<List<AssetDto>> searchAssetAll() {
		
		return mapSearchResponseAsset(repository.findAll());
	}
	
	private ResponseEntity<List<AssetDto>> mapSearchResponseAsset(List<AssetEntity> listAssetEntitySave) {
		List<AssetDto> listAssetDto= new ArrayList<>();
		for (AssetEntity assetEntitySave : listAssetEntitySave) {
			ValueAssetDto valueAsset = new ValueAssetDto();
			valueAsset.setAmmount(assetEntitySave.getValueAsset().getAmmount());
			valueAsset.setCurrency(assetEntitySave.getValueAsset().getCurrency());
			
			AssetDto assetDto = new AssetDto();
			assetDto.setName(assetEntitySave.getCompanyName());
			assetDto.setStocks(assetEntitySave.getStocks());
			assetDto.setTrader_id(assetEntitySave.getTrader_id());
			assetDto.setType(assetEntitySave.getType());
			assetDto.setValue(valueAsset);
			listAssetDto.add(assetDto);
		}
		return new ResponseEntity<>(listAssetDto,HttpStatus.OK);
	}

	private ResponseEntity<AssetRsDto> mapResponseAsset(AssetEntity assetEntitySave) {
		ValueAssetDto valueAsset = new ValueAssetDto();
		valueAsset.setAmmount(assetEntitySave.getValueAsset().getAmmount());
		valueAsset.setCurrency(assetEntitySave.getValueAsset().getCurrency());
		
		
		AssetRsDto assetRsDto = new AssetRsDto();
		assetRsDto.setName(assetEntitySave.getCompanyName());
		assetRsDto.setState(assetEntitySave.getState());
		assetRsDto.setStocks(assetEntitySave.getStocks());
		assetRsDto.setTrader_id(assetEntitySave.getTrader_id());
		assetRsDto.setType(assetEntitySave.getType());
		assetRsDto.setValue(valueAsset);
		assetRsDto.setId(assetEntitySave.getId());
		
		return new ResponseEntity<>(assetRsDto,HttpStatus.CREATED);
	}

	private AssetEntity mapSaveAsset(AssetDto assetRqDto) {
		String uuid = java.util.UUID.randomUUID().toString();
		long unixTimeStamp= System.currentTimeMillis() / 1000L;
		 
		ValueAssetEntity valueAsset = new ValueAssetEntity();
		valueAsset.setAmmount(assetRqDto.getValue().getAmmount());
		valueAsset.setCurrency(assetRqDto.getValue().getCurrency());
		valueAsset.setIdAsset(uuid);
		
		AssetEntity assetEntity = new AssetEntity();
		assetEntity.setCompanyName(assetRqDto.getName());
		assetEntity.setCreate_at(unixTimeStamp);
		assetEntity.setId(uuid);
		assetEntity.setState("ACTIVE");
		assetEntity.setStocks(assetRqDto.getStocks());
		assetEntity.setTrader_id(assetRqDto.getTrader_id());
		assetEntity.setType(assetRqDto.getType());
		assetEntity.setValueAsset(valueAsset);
		return assetEntity;
	}
}
