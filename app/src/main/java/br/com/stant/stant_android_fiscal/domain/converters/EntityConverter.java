package br.com.stant.stant_android_fiscal.domain.converters;

import br.com.stant.stant_android_fiscal.domain.entity.user.Entity;
import br.com.stant.stant_android_fiscal.services.auth.dto.EntityResponse;
import br.com.stant.stant_android_fiscal.services.session.dto.EntityEntry;

/**
 * Created by denisvieira on 18/05/17.
 */

public class EntityConverter {


    public static Entity convertFromResponse(EntityResponse entityResponse) {
        if (entityResponse == null) return null;

        int id        = entityResponse.getId();
        String name   = entityResponse.getName();
        String state  = entityResponse.getState();

        return new Entity(id, name, state);
    }

    public static Entity convertFromEntry(EntityEntry entry) {
        if (entry == null) return null;

        return new Entity(entry.id, entry.name, entry.state);
    }

    public static EntityEntry convertEntityToEntry(Entity entity) {
        if (entity == null) return null;


        EntityEntry entityEntry = new EntityEntry();

        entityEntry.id = entity.getId();
        entityEntry.name = entity.getName();
        entityEntry.state = entity.getState();

        return entityEntry;
    }


}
