package org.code.math.entity;

import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<Operation, Integer> {
    Operation save(Operation operation);

    Operation deleteById(int id);
}