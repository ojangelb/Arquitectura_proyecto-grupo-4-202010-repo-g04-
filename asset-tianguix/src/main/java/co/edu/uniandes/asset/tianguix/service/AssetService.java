package co.edu.uniandes.asset.tianguix.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import co.edu.uniandes.asset.tianguix.dto.AssetDto;
import co.edu.uniandes.asset.tianguix.dto.AssetRsDto;
import co.edu.uniandes.asset.tianguix.entity.AssetEntity;

public interface AssetService {
	ResponseEntity<AssetRsDto> saveAsset (AssetDto assetRqDto);
	
	ResponseEntity<List<AssetDto>> searchAssetByParams(String type,String currency, Long currency_min,
			Long currency_max, Integer stock_ammount_min, Integer stock_ammount_max);
	
	ResponseEntity<List<AssetDto>> searchAssetAll();
}
