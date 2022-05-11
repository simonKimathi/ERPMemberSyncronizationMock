package com.ERPMemberSyncronozation.mock.boundary.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ERPMemberSyncronozation.mock.boundary.AbstractBean;

public abstract class AbstractBeanImpl<T, V> implements AbstractBean<T, V> {
  private final Class<T> entityClass;

  protected abstract EntityManager getEntityManager();

  public AbstractBeanImpl(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  @Override
  public T create(T entity) {
    getEntityManager().persist(entity);
    return entity;
  }

  @Override
  public T edit(T entity) {
    getEntityManager().merge(entity);
    return entity;
  }

  @Override
  public boolean remove(T entity) {
    getEntityManager().remove(getEntityManager().merge(entity));
    return true;
  }

  @Override
  public T find(V id) {
    return getEntityManager().find(entityClass, id);
  }

  @Override
  public List<T> findAll() {
    CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
    cq.select(cq.from(entityClass));
    return getEntityManager().createQuery(cq).getResultList();
  }

  @Override
  public List<T> findRange(int[] range) {
    CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
    cq.select(cq.from(entityClass));
    TypedQuery<T> q = getEntityManager().createQuery(cq);
    q.setMaxResults(range[1] - range[0] + 1);
    q.setFirstResult(range[0]);
    return q.getResultList();
  }

  @Override
  public long count() {
    CriteriaQuery<Long> cq =
            getEntityManager().getCriteriaBuilder().createQuery(Long.class);
    Root<T> rt = cq.from(entityClass);
    cq.select(getEntityManager().getCriteriaBuilder().count(rt));
    TypedQuery<Long> q = getEntityManager().createQuery(cq);
    return q.getSingleResult();
  }

  @Override
  public Optional<T> findSingleByNamedQuery(String namedQueryName, Map<String, Object> parameters) {
    return findSingleByNamedQuery(namedQueryName, null, parameters);

  }
  @Override
  public Optional<T> findSingleByNamedQuery(String namedQueryName, String entityGraph, Map<String, Object> parameters) {
    Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
    TypedQuery<T> query = getEntityManager().createNamedQuery(namedQueryName, entityClass);
    rawParameters.forEach(entry -> query.setParameter(entry.getKey(), entry.getValue()));
    if (entityGraph != null) {
      query.setHint("javax.persistence.loadgraph", getEntityManager().getEntityGraph(entityGraph));
    }
    return findOrEmpty(query::getSingleResult);
  }
  public static <T> Optional<T> findOrEmpty(final DaoRetriever<T> retriever) {
    try {
      return Optional.of(retriever.retrieve());
    } catch (NoResultException ex) {
      //log
    }
    return Optional.empty();
  }

  @FunctionalInterface
  public interface DaoRetriever<T> {
    T retrieve() throws NoResultException;
  }

}
