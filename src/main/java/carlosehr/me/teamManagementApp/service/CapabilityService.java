package carlosehr.me.teamManagementApp.service;

import carlosehr.me.teamManagementApp.domain.Capability;
import carlosehr.me.teamManagementApp.exceptions.CapabilityException;
import carlosehr.me.teamManagementApp.repositories.CapabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapabilityService {

    private CapabilityRepository capabilityRepository;

    public CapabilityService(CapabilityRepository capabilityRepository) {
        this.capabilityRepository = capabilityRepository;
    }

    public List<Capability> getAllCapabilitier(){
        return capabilityRepository.findAll();
    }
    public Capability findCapById(Long id){

        return  capabilityRepository.findById(id).orElseThrow(()->
                new CapabilityException("Capability with ID: " + id + " Not found"));

    }
}
