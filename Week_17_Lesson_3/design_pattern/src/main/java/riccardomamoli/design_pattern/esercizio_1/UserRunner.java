package riccardomamoli.design_pattern.esercizio_1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Info info = new Info();
        info.setNome("Riccardo");
        info.setCognome("Mamoli");
        info.setDataDiNascita(LocalDate.of(1996, 01,19));

        DataSource ds = new InfoAdapter(info);

        UserData userData = new UserData();
        userData.getData(ds);

        System.out.println("Nome: " + userData.getNomeCompleto());
        System.out.println("Eta: " + userData.getEta());
        
    }
}
