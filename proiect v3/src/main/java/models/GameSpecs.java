package models;

import CSV.CSVSerialize;
import enums.CPU;
import enums.GPU;
import enums.RAM;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameSpecs implements CSVSerialize {
    private RAM ram;
    private GPU gpu;
    private CPU cpu;

    @Override
    public String serialize() {
        return ram + ", " + cpu + ", " + gpu;
    }
}
