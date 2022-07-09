package com.github.williamjbf.moneyapi.repository.posting;

import com.github.williamjbf.moneyapi.model.Posting;
import com.github.williamjbf.moneyapi.repository.filter.PostingFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PostingRepositoryImpl implements PostingRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Posting> filter(PostingFilter postingFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Posting> criteria = builder.createQuery(Posting.class);
        Root<Posting> root = criteria.from(Posting.class);

        //restrictions
        Predicate[] predicates = createRestrictions(postingFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Posting> query = manager.createQuery(criteria);
        addPaginationRestrictions(query,pageable);
        return new PageImpl<>(query.getResultList(),pageable,total(postingFilter)) ;
    }

    private Predicate[] createRestrictions(PostingFilter postingFilter, CriteriaBuilder builder, Root<Posting> root) {

        List<Predicate> predicates = new ArrayList<>();

        // where description like 'example'
        if(!ObjectUtils.isEmpty(postingFilter.getDescription())){
            predicates.add(builder.like(
                    builder.lower(root.get("description")),"%"+postingFilter.getDescription().toLowerCase()+"%"
            ));
        }

        if(postingFilter.getFromDueDate() != null){
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get("dueDate"), postingFilter.getFromDueDate())
            );
        }

        if(postingFilter.getUntilDueDate() != null){
            predicates.add(
                    builder.lessThanOrEqualTo(root.get("dueDate"), postingFilter.getUntilDueDate())
            );
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addPaginationRestrictions(TypedQuery<Posting> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecords = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPageRecords);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Long total(PostingFilter postingFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Posting> root = criteria.from(Posting.class);

        Predicate[] predicates = createRestrictions(postingFilter,builder,root);
        criteria.where(predicates);

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }
}
