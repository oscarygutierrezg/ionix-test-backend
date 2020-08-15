package cl.com.ionix.testbackend.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// TODO: Auto-generated Javadoc
/**
 * The Class RutValidator.
 */
public class RutValidator implements ConstraintValidator<ValidRut, String> {

    /**
     * Checks if is valid.
     *
     * @param rut the rut
     * @param context the context
     * @return true, if is valid
     */
    @Override
    public boolean isValid(final String rut, final ConstraintValidatorContext context) {
    	  return validateRut(rut);
    }

    /**
     * Validate rut.
     *
     * @param r the r
     * @return true, if successful
     */
    private boolean validateRut(final String r) {
    	String rut = r;
    	boolean validacion = false;
		try {
			rut =  rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
			char dv = rut.charAt(rut.length() - 1);
			int m = 0;
			int s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {
				validacion = true;
			}

		} catch (Exception e) {
		}
        return validacion;
    }
}
