package carlosehr.me.teamManagementApp.resources;

import carlosehr.me.teamManagementApp.domain.Capability;
import carlosehr.me.teamManagementApp.service.CapabilityService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/dashboard")
@CrossOrigin
public class CapabilityController {

    private CapabilityService capabilityService;

    public CapabilityController(CapabilityService capabilityService) {
        this.capabilityService = capabilityService;
    }

    @GetMapping
    public CollectionModel<EntityModel<Capability>> getAllCapabilities(){

        List<EntityModel<Capability>> capabilities = capabilityService.getAllCapabilities().stream()
                .map(capability -> new EntityModel<>(capability,
                        linkTo(methodOn(CapabilityController.class).getCapability(capability.getId())).withRel("getThisCapability"),
                        linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel("getAllCapabilities")
                )).collect(Collectors.toList());

        return new CollectionModel<>(capabilities);

    }

    @GetMapping("/{id}")
    public EntityModel<?> getCapability(@PathVariable Long id){
        Capability capability = capabilityService.findCapById(id);

        return new EntityModel<>(capability,
                linkTo(methodOn(CapabilityController.class).getCapability(id)).withRel("getThisCapability"));
    }
}