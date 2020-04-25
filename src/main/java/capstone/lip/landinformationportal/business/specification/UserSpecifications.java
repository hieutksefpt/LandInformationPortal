package capstone.lip.landinformationportal.business.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import capstone.lip.landinformationportal.common.entity.User;

public class UserSpecifications implements Specification<User> {

	private static final long serialVersionUID = 1L;

	private SearchCriteria criteria;
	
	public UserSpecifications(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.<String>get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        } else if (criteria.getOperation().equalsIgnoreCase(":=")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.<String>get(criteria.getKey())), criteria.getValue().toString().toLowerCase());
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        } else if (criteria.getOperation().equalsIgnoreCase("!=")) {
        	if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.notEqual(
                        criteriaBuilder.lower(root.<String>get(criteria.getKey())), criteria.getValue().toString().toLowerCase());
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
	}

}
