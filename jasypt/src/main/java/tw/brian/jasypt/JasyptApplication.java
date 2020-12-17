package tw.brian.jasypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootApplication
public class JasyptApplication {

	public static void main(String[] args) {
		TransactionSynchronizationManager.setActualTransactionActive(true);
		SpringApplication.run(JasyptApplication.class, args);
	}

}
