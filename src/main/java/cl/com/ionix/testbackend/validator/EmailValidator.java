package cl.com.ionix.testbackend.validator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The Class EmailValidator.
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    
    /** The Constant EMAIL_PATTERN. */
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Checks if is valid.
     *
     * @param email the email
     * @param context the context
     * @return true, if is valid
     */
    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
    	  return (validateEmail(email, context));
    }

    /**
     * Validate email.
     *
     * @param email the email
     * @param context the context
     * @return true, if successful
     */
    private boolean validateEmail(final String email, ConstraintValidatorContext context) {
    	Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        String m = "";
        if(Objects.isNull(email))
        	m= "Email_Required";
        if(Objects.isNull(email))
        	m="Email_Not_Empty";
        
        if(!m.isEmpty()) {
        	context.buildConstraintViolationWithTemplate(m).addConstraintViolation()
            .disableDefaultConstraintViolation();
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
