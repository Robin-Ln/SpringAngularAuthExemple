package fr.louarn.springSecutityExemple.commun.converter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConverter<Entity, Dto> {

    public List<Entity> getEntityList(List<Dto> dtoList) {
        List<Entity> entities = new ArrayList<>();
        dtoList.stream().forEach(dto -> entities.add(this.getEntity(dto)));
        return entities;
    }

    public List<Dto> getDtoList(List<Entity> entities) {
        List<Dto> dtoList = new ArrayList<>();
        entities.stream().forEach(entity -> dtoList.add(this.getDto(entity)));
        return dtoList;
    }

    public abstract Entity getEntity(Dto dto);

    public abstract Dto getDto(Entity entity);
}
