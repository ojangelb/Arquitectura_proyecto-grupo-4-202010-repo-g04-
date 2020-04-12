package co.edu.uniandes.asset.tianguix;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.edu.uniandes.asset.tianguix.entity.AssetEntity;

public interface AssetRepossitory extends CrudRepository<AssetEntity, Long> {

	
   @Query("SELECT asset_entity FROM AssetEntity asset_entity JOIN asset_entity.valueAsset value_asset WHERE asset_entity.stocks>= :stocks_min AND asset_entity.stocks<= :stocks_max AND value_asset.currency = :currency AND asset_entity.type = :type")   
   List<AssetEntity> findByStocksParam (@Param("stocks_min")int stocks_min,@Param("stocks_max")int stocks_max, @Param("currency")String currency,@Param("type")String type);
   
   @Query("SELECT asset_entity FROM AssetEntity asset_entity JOIN asset_entity.valueAsset value_asset WHERE value_asset.ammount>= :currency_min AND value_asset.ammount<= :currency_max AND value_asset.currency = :currency AND asset_entity.type = :type")   
   List<AssetEntity> findByCurrencyParam (@Param("currency_min")Long currency_min,@Param("currency_max")Long currency_max, @Param("currency")String currency, @Param("type")String type);
   
   @Query("SELECT asset_entity FROM AssetEntity asset_entity JOIN asset_entity.valueAsset value_asset")   
   List<AssetEntity> findAll ();
   
   @Query("SELECT asset_entity FROM AssetEntity asset_entity JOIN asset_entity.valueAsset value_asset WHERE asset_entity.stocks>= :stocks_min AND asset_entity.stocks<= :stocks_max OR value_asset.ammount>= :currency_min AND value_asset.ammount<= :currency_max AND value_asset.currency = :currency AND asset_entity.type = :type")   
   List<AssetEntity> findByAllParam (@Param("stocks_min")int stocks_min,@Param("stocks_max")int stocks_max,@Param("currency_min")Long currency_min,@Param("currency_max")Long currency_max, @Param("currency")String currency,@Param("type")String type);
   
   @Query("SELECT asset_entity FROM AssetEntity asset_entity JOIN asset_entity.valueAsset value_asset WHERE asset_entity.id = :id")   
   AssetEntity findById (@Param("id") String id);
    
}