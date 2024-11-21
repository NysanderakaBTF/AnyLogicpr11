package simulation.anylogicpr11test3;

import com.anylogic.engine.Engine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pr11.CustomExperiment;
import pr11.Main;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/")
public class Controller {

    @GetMapping(path="/")
    public ResponseEntity<String> findById(@RequestParam int scenario, @RequestParam int rate, @RequestParam int proce, @RequestParam int course) {
        Random rand = new Random(System.currentTimeMillis());
        CustomExperiment s = new CustomExperiment(null);
        Engine d = s.createEngine();
        d.setDefaultRandomGenerator(rand);
        Main m = new Main(d, null, null);
        m.setParametersToDefaultValues();
        m.setDefaultRandomGenerator(rand);
        d.start(m);

        Date date = new Date();
        Calendar date2 = Calendar.getInstance();
        date2.setTime(date);
        date2.add(Calendar.YEAR, 30); // Добавля
        Date stop_date = date2.getTime();

        d.setStartDate(date); //Текущая дата
        d.setStopDate(stop_date); //Текущая дата
        d.setRealTimeMode(false); //Pexum BupyTa

        m.Сценарий = scenario;
        m.Темп_бурения = rate;
        m.Цена_на_нефть = proce;
        m.Курс_доллара = course;

        while (d.getTime() < 30) { // d.date().bej
            d.step(); //Запускаем новый поток (п
        }
        d.stop();
        return null;
    }
}