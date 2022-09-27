package com.ead.authuser.specifications;

import com.ead.authuser.filters.UserFilter;
import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSpecs {

    public static Specification<UserModel> usandoFiltro(UserFilter filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (Objects.nonNull(filter.getUserType())) {
                predicates.add(builder.equal(root.get("userType"), filter.getUserType()));
            }
            if (StringUtils.hasText(filter.getEmail())) {
                predicates.add(builder.like(root.get("email"), "%" + filter.getEmail() + "%"));
            }
            if (Objects.nonNull(filter.getUserStatus())) {
                predicates.add(builder.equal(root.get("userStatus"), filter.getUserStatus()));
            }
            if (StringUtils.hasText(filter.getFullName())) {
                predicates.add(builder.like(root.get("fullName"), "%" + filter.getFullName() + "%"));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<UserModel> userCourseId(final UUID courseId) {
        return (root, query, builder) -> {
            query.distinct(true);
            Join<UserModel, UserCourseModel> join = root.join("usersCourses");
            return builder.equal(join.get("courseId"), courseId);
        };
    }
}
