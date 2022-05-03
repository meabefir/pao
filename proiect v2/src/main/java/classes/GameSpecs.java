package classes;

import enums.CPU;
import enums.GPU;
import enums.RAM;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameSpecs {
    private RAM ram;
    private GPU gpu;
    private CPU cpu;
}
