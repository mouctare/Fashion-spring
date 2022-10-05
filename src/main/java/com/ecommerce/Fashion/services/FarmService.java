package com.ecommerce.Fashion.services;

import com.ecommerce.Fashion.entity.Farm;
import com.ecommerce.Fashion.exceptions.ResourceNotFoundException;
import com.ecommerce.Fashion.repositories.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    public FarmService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }
    public void saveFarm(Farm farm){
        farmRepository.save(farm);
    }

    public List<Farm> getAllFarm(){
      return farmRepository.findAll();
    }

    public Farm getFarmById(Long  id){
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not exist with id:" + id));
        return farm;
    }

    public ResponseEntity<Farm> updateFarm(Long id, Farm farmDetails){
        Farm farm = farmRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Farm not exist with id:" + id));
        farm.setName(farmDetails.getName());
        farm.setDescription(farmDetails.getDescription());
        farm.setLatitude(farmDetails.getLatitude());
        farm.setLongitude(farmDetails.getLongitude());
        farm.setPhoneNumber(farmDetails.getPhoneNumber());

       farmRepository.save(farmDetails);
        return  ResponseEntity.ok(farm);
    }

    public ResponseEntity<HttpStatus> deleteFarm(Long  id){
        Farm farm = farmRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Farm not exist with id:" + id));
        farmRepository.delete(farm);

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
