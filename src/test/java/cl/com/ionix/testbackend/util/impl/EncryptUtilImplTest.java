

package cl.com.ionix.testbackend.util.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import cl.com.ionix.testbackend.TestBackendApplication;
import cl.com.ionix.testbackend.util.IEncryptUtil;



@SpringBootTest
@ContextConfiguration(classes = TestBackendApplication.class)
class EncryptUtilImplTest {
	

    @Autowired
    private IEncryptUtil encryptUtil;

	
	@Test
	public  void encryptTest() {
		String param ="1-9";
		String encryptado = encryptUtil.encrypt(param);
		String encryptadoValue = "FyaSTkGi8So=";
		assertTrue(encryptado.equals(encryptadoValue));
	}
	@Test
	public  void encryptNullTest() {
		String param =null;
		String encryptado = encryptUtil.encrypt(param);
		String encryptadoValue = "";
		assertTrue(encryptado.equals(encryptadoValue));
	}
}
