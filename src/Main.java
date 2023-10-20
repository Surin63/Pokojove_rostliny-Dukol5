import com.engeto.plant.Plant;
import com.engeto.plant.PlantList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Plant plant1 = new Plant("Karafiat", "Mame ji v pokoji", LocalDate.now(), LocalDate.now().plusDays(6),
                3);
        System.out.println(plant1);

    }




}