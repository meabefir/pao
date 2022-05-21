package CSV;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserCSVReader  extends CSVReader implements CSVParse{
    public UserCSVReader(String path) {
        super(path);
    }

    @Override
    public List<Object> parseData() {
        List<Object> ret = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            List<String> line = data.get(i);
            User newUser = User.builder().username(line.get(0)).password(line.get(1)).build();
            ret.add(newUser);
        }

        return  ret;
    }
}
