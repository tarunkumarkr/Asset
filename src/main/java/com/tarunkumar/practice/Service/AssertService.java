package com.tarunkumar.practice.Service;

import com.tarunkumar.practice.Dto.AssetRequestDTO;
import com.tarunkumar.practice.Entity.Asset;
import com.tarunkumar.practice.Enum.AssetCondition;
import com.tarunkumar.practice.Enum.AssetStatus;
import com.tarunkumar.practice.Repo.AssertRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AssertService {

    private  final AssertRepo asserRepo;
    public Asset saveAs(AssetRequestDTO asset){



        AssetStatus status = AssetStatus.valueOf(asset.getStatus().toUpperCase());
        AssetCondition condition = AssetCondition.valueOf(asset.getCondition().toUpperCase());


        Asset asset1= Asset.builder()
                .bu(asset.getBu())
                .itemId(asset.getItemId())
                .assetDisplayName(asset.getAssetDisplayName())
                .condition(condition)
                .department(asset.getDepartment())
                .location(asset.getLocation())
                .manufactureDate(asset.getManufactureDate())
                .purchaseCost(asset.getPurchaseCost())
                .purchaseDate(asset.getPurchaseDate())
                .serialNumber(asset.getSerialNumber())
                .status(status)
                .warrantyExpiry(asset.getWarrantyExpiry())
                .description(asset.getDescription())
                .build();
         Asset asset2=asserRepo.save(asset1);
         return asset2;
    }

    public String delete(String s){

        if(!asserRepo.existsByItemId(s)){
            return "there is no item present ";
        }
        asserRepo.deleteByItemId(s);
        return "item deleted successfully ";
    }
    public Asset findById(String id){
        return asserRepo.findByItemId(id)
                .orElseThrow(()->new RuntimeException("there is no item"));
    }

    public List<Asset> findAll(){

        return asserRepo.findAll();
    }

    public Asset updatePartial(String itemId, AssetRequestDTO dto){

        Asset existing = asserRepo.findByItemId(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (dto.getDepartment() != null) {
            existing.setDepartment(dto.getDepartment());
        }

        if (dto.getAssetDisplayName() != null) {
            existing.setAssetDisplayName(dto.getAssetDisplayName());
        }

        if (dto.getLocation() != null) {
            existing.setLocation(dto.getLocation());
        }

        if (dto.getDescription() != null) {
            existing.setDescription(dto.getDescription());
        }

        if (dto.getStatus() != null) {
            try {
                existing.setStatus(AssetStatus.valueOf(dto.getStatus().toUpperCase()));
            } catch (Exception e) {
                throw new RuntimeException("Invalid status");
            }
        }

        if (dto.getCondition() != null) {
            try {
                existing.setCondition(AssetCondition.valueOf(dto.getCondition().toUpperCase()));
            } catch (Exception e) {
                throw new RuntimeException("Invalid condition");
            }
        }

        return asserRepo.save(existing);
    }

    public List<Asset> findByItemIdAndLocation(String itemId, String location){

        List<Asset> assets = asserRepo.findByItemIdAndLocation(itemId, location);

        if (assets.isEmpty()) {
            throw new RuntimeException("No assets found");
        }

        return assets;
    }

    public List<Asset> filter(String status, String department){

        return asserRepo.findByStatusAndDepartment(
                AssetStatus.valueOf(status.toUpperCase()),
                department
        );
    }
}
