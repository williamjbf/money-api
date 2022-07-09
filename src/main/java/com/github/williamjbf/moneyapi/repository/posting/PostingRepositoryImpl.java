package com.github.williamjbf.moneyapi.repository.posting;

import com.github.williamjbf.moneyapi.model.Posting;
import com.github.williamjbf.moneyapi.repository.filter.PostingFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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
    public List<Posting> filter(PostingFilter postingFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Posting> criteria = builder.createQuery(Posting.class);
        Root<Posting> root = criteria.from(Posting.class);

        //restrictions
        Predicate[] predicates = createRestrictions(postingFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Posting> query = manager.createQuery(criteria);

        return query.getResultList();
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
}
