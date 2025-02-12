package dontworry.app.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import dontworry.app.service.ShipService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the iMONumber value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = ShipIMONumberUnique.ShipIMONumberUniqueValidator.class
)
public @interface ShipIMONumberUnique {

    String message() default "{Exists.ship.iMONumber}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ShipIMONumberUniqueValidator implements ConstraintValidator<ShipIMONumberUnique, String> {

        private final ShipService shipService;
        private final HttpServletRequest request;

        public ShipIMONumberUniqueValidator(final ShipService shipService,
                final HttpServletRequest request) {
            this.shipService = shipService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("shipSeqId");
            if (currentId != null && value.equalsIgnoreCase(shipService.get(Long.parseLong(currentId)).getIMONumber())) {
                // value hasn't changed
                return true;
            }
            return !shipService.iMONumberExists(value);
        }

    }

}
