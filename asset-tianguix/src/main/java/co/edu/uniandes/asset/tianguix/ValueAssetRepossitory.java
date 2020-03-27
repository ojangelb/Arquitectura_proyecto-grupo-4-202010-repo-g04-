package co.edu.uniandes.asset.tianguix;

import org.springframework.data.repository.CrudRepository;

import co.edu.uniandes.asset.tianguix.entity.AssetEntity;

public interface ValueAssetRepossitory extends CrudRepository<AssetEntity, Long> {
	

}
