package com.example.zenturaserver.service;

import com.example.zenturaserver.model.PharmacyDTO;
import com.example.zenturaserver.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PharmacyServiceImpl implements PharmacyService{

    @Autowired
    private PharmacyRepository pharmacyRepo;

    @Override
    public List<PharmacyDTO> getAllPharmacy() {
        List<PharmacyDTO> pharmacies = pharmacyRepo.findAll();
        if (pharmacies.size() > 0) {
            return pharmacies;
        } else {
            return new ArrayList<PharmacyDTO>();
        }
    }

    @Override
    public PharmacyDTO getAPharmacy(String id) throws Exception {
        Optional<PharmacyDTO> optionalPharmacy = pharmacyRepo.findById(id);
        if (!optionalPharmacy.isPresent()) {
            throw new Exception("Pharmacy not found!");
        } else {
            return optionalPharmacy.get();
        }
    }

    @Override
    public void createPharmacy(PharmacyDTO patient) throws Exception {

    }

    @Override
    public void updatePharmacy(String id, PharmacyDTO pharmacy) throws Exception {

    }

    @Override
    public void deletePharmacy(String id) throws Exception {
        Optional<PharmacyDTO> pharmacyOptional = pharmacyRepo.findById(id);

        if(!pharmacyOptional.isPresent()){
            throw new Exception("Pharmacy not found!");
        }else {
            pharmacyRepo.deleteById(id);
        }
    }
}
