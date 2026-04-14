package com.tarunkumar.practice.Controller;

import com.tarunkumar.practice.Dto.AssetRequestDTO;
import com.tarunkumar.practice.Entity.Asset;
import com.tarunkumar.practice.Service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/asset")
public class AssetController {

    private final AssetService assetService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Asset create(@RequestBody AssetRequestDTO requestDTO ){
           Asset asset=assetService.saveAs(requestDTO);
                return  asset;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleted(@PathVariable String id){

      return   assetService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/findById/{id}")
    public Asset assetById(@PathVariable String id ){
        return assetService.findById(id);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/find")
    public List<Asset> assetAll(){
        return assetService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{itemId}")
    public Asset update(@PathVariable String itemId,
                        @RequestBody AssetRequestDTO dto){

        return assetService.updatePartial(itemId, dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/search")
    public List<Asset> find(@RequestParam String itemId,
                            @RequestParam String location){

        return assetService.findByItemIdAndLocation(itemId, location);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/filter")
    public List<Asset> filter(@RequestParam String status,
                              @RequestParam String department){

        return assetService.filter(status, department);
    }
}
