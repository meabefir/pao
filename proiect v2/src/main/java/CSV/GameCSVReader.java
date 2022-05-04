package CSV;

import classes.Game;
import classes.GameSpecs;
import enums.CPU;
import enums.GPU;
import enums.RAM;

import java.util.ArrayList;
import java.util.List;

public class GameCSVReader extends CSVReader implements CSVParse {

    public GameCSVReader(String path) {
        super(path);
    }

    @Override
    public List<Object> parseData() {
        List<Object> ret = new ArrayList<>();

//        private String title;
//        private Integer rating;
//        private GameSpecs minSpecs;
//        private GameSpecs recommendedSpecs;

        for (int i = 0; i < data.size(); i++) {
            List<String> line = data.get(i);
            Game newGame = Game.builder().title(line.get(0)).rating(Integer.valueOf(line.get(1)))
                    .minSpecs(GameSpecs.builder().ram(RAM.valueOf(line.get(2))).cpu(CPU.valueOf(line.get(3))).gpu(GPU.valueOf(line.get(4))).build())
                    .recommendedSpecs(GameSpecs.builder().ram(RAM.valueOf(line.get(5))).cpu(CPU.valueOf(line.get(6))).gpu(GPU.valueOf(line.get(7))).build())
                    .price(Double.valueOf(line.get(8)))
                    .build();
            ret.add(newGame);
        }

        return  ret;
    }
}
