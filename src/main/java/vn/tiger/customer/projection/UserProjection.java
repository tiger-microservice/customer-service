package vn.tiger.customer.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import vn.tiger.sagacommon.model.CardDetails;
import vn.tiger.sagacommon.model.User;
import vn.tiger.sagacommon.queries.GetUserPaymentDetailsQuery;

@Component
public class UserProjection {

    @QueryHandler
    public User getUserPaymentDetails(GetUserPaymentDetailsQuery query) {
        CardDetails cardDetails = CardDetails.builder()
                .name("Ta Duy Hoang")
                .validUntilMonth(01)
                .validUntilYear(2030)
                .cardNumber("123456789")
                .cvv(123)
                .build();

        return User.builder()
                .userId("12345")
                .firstName("Hoang")
                .lastName("Ta Duy")
                .cardDetails(cardDetails)
                .build();
    }

}
