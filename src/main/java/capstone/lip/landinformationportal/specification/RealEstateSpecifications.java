package capstone.lip.landinformationportal.specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import capstone.lip.landinformationportal.common.StatusRealEstateConstant;
import capstone.lip.landinformationportal.entity.RealEstate;
public class RealEstateSpecifications implements Specification<RealEstate>{

	
	public RealEstateSpecifications(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}
	private SearchCriteria criteria;
	@Override
	public Predicate toPredicate(Root<RealEstate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (criteria.getOperation().equalsIgnoreCase(">")) {
			return criteriaBuilder.greaterThanOrEqualTo(
					root.get(criteria.getKey()),criteria.getValue().toString());
			
		}
		else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
            	
                return criteriaBuilder.like(
                  criteriaBuilder.lower(root.<String>get(criteria.getKey())), "%" + criteria.getValue() + "%");
                	
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
	}

}
