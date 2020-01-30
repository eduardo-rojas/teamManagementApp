package carlosehr.me.teamManagementApp.resources;

import carlosehr.me.teamManagementApp.assembler.CapabilityResourceAssembler;
import carlosehr.me.teamManagementApp.domain.Capability;
import carlosehr.me.teamManagementApp.service.CapabilityService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/dashboard")
@CrossOrigin
public class CapabilityController {

    private CapabilityService capabilityService;

    private CapabilityResourceAssembler assembler;

    public CapabilityController(CapabilityService capabilityService, CapabilityResourceAssembler assembler) {
        this.capabilityService = capabilityService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Capability>> getAllCapabilities(){

        return new CollectionModel<>(capabilityService.getAllCapabilities().stream()
                .map(capability -> assembler.toModel(capability)).collect(Collectors.toList()),
                new Link("http://localhost:8080/dashboard").withRel("createCapability")
        );
    }

    @GetMapping("/{id}")
    public EntityModel<?> getCapability(@PathVariable Long id){

        return assembler.toModel(capabilityService.findCapById(id));
    }

    @PostMapping
    public Object createCapability(@Valid @RequestBody Capability capability, BindingResult result){

        if(result.hasErrors()) return capabilityService.errorMap(result);

        return new EntityModel<>(assembler.toModel(capabilityService.saveCapability(capability)));

    }

    @PutMapping("/{id}")
    public Object updateCapability(@PathVariable Long id, @Valid @RequestBody Capability capability, BindingResult result){

        if(result.hasErrors()) return capabilityService.errorMap(result);

        return new EntityModel<>(assembler.toModel(capabilityService.updateCapability(id,capability)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCapability(@PathVariable Long id){

        capabilityService.deleteCapability(id);

        return new ResponseEntity<String>("Capability Deleted", HttpStatus.OK);
    }

}