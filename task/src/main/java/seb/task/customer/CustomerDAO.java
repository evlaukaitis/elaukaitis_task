package seb.task.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seb.task.generic.ObjectMapperException;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerDAO {

    private final EntityManager entityManager;

    private final ObjectMapper objectMapper;

    public CustomerSearchResultDTO search(String query, Long pageNo, Long pageSize) {
        return getFullJpaQuery(query, pageNo, pageSize);
    }

    private CustomerSearchResultDTO getFullJpaQuery(String query, Long pageNo, Long pageSize) {
        final JPAQuery<CustomerListDTO> jpaQuery = getJpaQuery();

        BooleanBuilder booleanBuilder = getWhereExpression(getQuery(query));

        jpaQuery.where(booleanBuilder);
        final long realPageNo = pageNo != null ? pageNo : 0;
        final long realPageSize = pageSize != null ? pageSize : 5;

        final long numFound = jpaQuery.fetchCount();
        jpaQuery.offset(realPageNo * realPageSize);
        jpaQuery.limit(realPageSize);

        List<CustomerListDTO> resultList = jpaQuery.fetch();
        return new CustomerSearchResultDTO(
                resultList,
                numFound);
    }

    private JPAQuery<CustomerListDTO> getJpaQuery() {
        QCustomerEntity qCustomer = QCustomerEntity.customerEntity;

        return new JPAQueryFactory(entityManager).select((Projections.constructor(CustomerListDTO.class,
                        qCustomer.id,
                        qCustomer.name,
                        qCustomer.surname,
                        qCustomer.birthDate)))
                .from(qCustomer);
    }

    private CustomerSearchQuery getQuery(String query) {
        try {
            return objectMapper.readValue(query, CustomerSearchQuery.class);
        } catch (IOException e) {
            throw new ObjectMapperException("Could not parse query: " + query);
        }
    }

    private BooleanBuilder getWhereExpression(CustomerSearchQuery customerSearchQuery) {
        BooleanBuilder whereExpression = new BooleanBuilder();

        if (customerSearchQuery == null) {
            return whereExpression;
        }

        QCustomerEntity qCustomer = QCustomerEntity.customerEntity;
        if (customerSearchQuery.getName() != null) {
            whereExpression.and(qCustomer.name.eq(customerSearchQuery.getName()));
        }
        if (customerSearchQuery.getSurname() != null) {
            whereExpression.and(qCustomer.surname.eq(customerSearchQuery.getSurname()));
        }
        if (customerSearchQuery.getBirthDate() != null) {
            whereExpression.and(qCustomer.birthDate.eq(customerSearchQuery.getBirthDate()));
        }

        return whereExpression;

    }
}
