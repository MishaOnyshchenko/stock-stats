package com.github.onyshchenko.stock.data.repository.impl;

import com.github.onyshchenko.stock.data.domain.Stock;
import com.github.onyshchenko.stock.data.repository.CustomStockRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomStockStockRepositoryImpl implements CustomStockRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Stock> findGreatestValueOrderedByCloseAndCompanyName(int limit) {
        return entityManager.createQuery("SELECT s FROM Stock s ORDER BY s.close DESC, s.companyName",
                Stock.class).setMaxResults(limit).getResultList();
    }

    @Override
    public List<Stock> findOrderedByGreatestChange(int limit) {
        return entityManager.createQuery("SELECT s FROM Stock s ORDER BY s.changePercent DESC",
                Stock.class).setMaxResults(limit).getResultList();
    }
}
