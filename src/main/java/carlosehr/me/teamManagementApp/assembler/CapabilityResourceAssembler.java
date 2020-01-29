package carlosehr.me.teamManagementApp.assembler;

import carlosehr.me.teamManagementApp.domain.Capability;
import carlosehr.me.teamManagementApp.resources.CapabilityController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CapabilityResourceAssembler implements RepresentationModelAssembler<Capability, EntityModel<Capability> > {


    @Override
    public EntityModel<Capability> toModel(Capability entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(CapabilityController.class).getCapability(entity.getId())).withRel("getThisCapability"),
                linkTo(methodOn(CapabilityController.class).deleteCapability(entity.getId())).withRel("deleteThisCapability"),
                linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel("getAllCapabilities"),
                new Link("http://localhost:8080/dashboard/"+entity.getId()).withRel("updateThisCapability")

                );
    }

    @Override
    public CollectionModel<EntityModel<Capability>> toCollectionModel(Iterable<? extends Capability> entities) {
        return null;
    }
}
