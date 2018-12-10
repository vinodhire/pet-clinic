package com.vinod.petclinic.service.map;

import com.vinod.petclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID> {

    protected Map<Long, T> map = new HashMap<>();

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    public T findById(ID id) {
        return map.get(id);
    }

    public T save(T object) {
        if (Objects.isNull(object)) {
            throw new RuntimeException("Cannot save Null Object");
        }
        if (Objects.isNull(object.getId())) {
            object.setId(getNextId());
        }
        map.put(object.getId(), object);
        return object;
    }

    private Long getNextId() {
        Long id = (map.keySet().isEmpty()?1L: (Collections.max(map.keySet()) + 1));
        return id;
    }

    public void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    public void deleteById(ID id) {
        map.remove(id);
    }
}
