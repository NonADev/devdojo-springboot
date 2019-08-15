package br.com.devdojo.awesome.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component //usado para avisar ao @componentScan encontrala e mapeala
//@Repository //especialização do component, usado quando usado com daw, exeções sao traduzidas
//@Service //especialização do component, usado com meor ntuido de usar classes como serviço, tendo em sua prática apenas as normas formais, sem diferenciação final dos components
public class DateUtil {
    public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }
}
