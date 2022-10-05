package com.ecommerce.Fashion.web;


import com.ecommerce.Fashion.entity.Farm;
import com.ecommerce.Fashion.services.FarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin()
public class FarmController {

    private FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping("/fermes")
  public List<Farm> getAll(){
      return farmService.getAllFarm();
  }

  @PostMapping(value = "/create-farm")
   public void create(@RequestBody Farm farm){
       farmService.saveFarm(farm);
      }

    //
    @GetMapping("/ferme/{id}")
    public Farm farmById(@PathVariable Long  id){
        return farmService.getFarmById(id);
    }

    @PutMapping("/ferme/{id}")
    public ResponseEntity<Farm> updateFarm(@PathVariable  Long  id, @RequestBody Farm farm){
        return farmService.updateFarm(id, farm);
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteFarm(@PathVariable  Long  id){
        return farmService.deleteFarm(id);
    }

}
