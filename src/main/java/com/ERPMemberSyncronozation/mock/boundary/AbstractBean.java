package com.ERPMemberSyncronozation.mock.boundary;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AbstractBean<T,ID> {
  T create(T entity);
  T edit(T entity);
  boolean remove(T entity);
  T find(ID primaryKey);
  List<T> findAll();
  List<T> findRange(int[] range);
  long count();

    Optional<T> findSingleByNamedQuery(String namedQueryName, Map<String, Object> parameters);

  Optional<T> findSingleByNamedQuery(String namedQueryName, String entityGraph, Map<String, Object> parameters);
}
