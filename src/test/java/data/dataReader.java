package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class dataReader {

    @DataProvider(name = "getUserData")
    public Object[][] getData() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, Object>> dataList = Arrays.asList(mapper.readValue(new File("src/test/java/data/testData.json"),HashMap[].class));

        Object[][] data = new Object[dataList.size()][1];
        for(int i=0;i<dataList.size();i++)
        {
            data[i][0]= dataList.get(i);
        }
        return data;

    }
}