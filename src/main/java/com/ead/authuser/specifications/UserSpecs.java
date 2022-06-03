package com.ead.authuser.specifications;

import com.ead.authuser.filters.UserFilter;
import com.ead.authuser.models.UserModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSpecs {

    public static Specification<UserModel> usandoFiltro(UserFilter filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (Objects.nonNull(filter.getUserType())) {
                predicates.add(builder.equal(root.get("userType"), filter.getUserType()));
            }
            if (Objects.nonNull(filter.getEmail())) {
                predicates.add(builder.like(root.get("email"), "%" + filter.getEmail() + "%"));
            }
            if (Objects.nonNull(filter.getUserStatus())) {
                predicates.add(builder.equal(root.get("userStatus"), filter.getUserStatus()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
