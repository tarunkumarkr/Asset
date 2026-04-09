package com.tarunkumar.practice.Controller;

import com.tarunkumar.practice.Dto.AssetRequestDTO;
import com.tarunkumar.practice.Entity.Asset;
import com.tarunkumar.practice.Service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/asset")
public class AssetController {

    private final AssetService assetService;
    @PostMapping("/create")
    public Asset create(@RequestBody AssetRequestDTO requestDTO ){
           Asset asset=assetService.saveAs(requestDTO);
                return  asset;
    }

    @DeleteMapping("/delete/{id}")
    public String deleted(@PathVariable String id){

      return   assetService.delete(id);
    }

    @GetMapping("/findById/{id}")
    public Asset assetById(@PathVariable String id ){
        return assetService.findById(id);
    }


    @GetMapping("/find")
    public List<Asset> assetAll(){
        return assetService.findAll();
    }
    @PatchMapping("/{itemId}")
    public Asset update(@PathVariable String itemId,
                        @RequestBody AssetRequestDTO dto){

        return assetService.updatePartial(itemId, dto);
    }
}
