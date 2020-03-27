package co.edu.uniandes.asset.tianguix;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.uniandes.asset.tianguix.dto.AssetDto;
import co.edu.uniandes.asset.tianguix.dto.AssetRsDto;
import co.edu.uniandes.asset.tianguix.entity.AssetEntity;
import co.edu.uniandes.asset.tianguix.service.AssetService;

@Controller
@RequestMapping(path="/")
public class AssetController {
	
   @Autowired
   private AssetService assetService;
   

	@PostMapping("/asset")
    public @ResponseBody ResponseEntity<AssetRsDto> addNewAsset (@RequestBody AssetDto c) {
		
        return assetService.saveAsset(c);
    }
	
	@GetMapping("/asset")
	
    public @ResponseBody ResponseEntity<List<AssetDto>> searchAsset (@RequestParam String type,
    				 @RequestParam String currency,
    				 @RequestParam  (required = false) Long currency_min,
    				 @RequestParam  (required = false) Long currency_max,
    				 @RequestParam  (required = false) Integer stock_ammount_min,
    				 @RequestParam  (required = false) Integer stock_ammount_max) {
		
        return assetService.searchAssetByParams(type,currency,currency_min,
        		currency_max,stock_ammount_min,stock_ammount_max);
    }
	
	
@GetMapping("/asset-all")
	
    public @ResponseBody ResponseEntity<List<AssetDto>> searchAssetAll () {
		
        return assetService.searchAssetAll();
    }
	
/*
    @GetMapping
    public @ResponseBody Iterable<AssetEntity> getAllUsers() {
        return "repository.findAll()";
    }
    *
    */
}