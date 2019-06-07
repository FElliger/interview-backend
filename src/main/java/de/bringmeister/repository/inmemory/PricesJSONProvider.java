package de.bringmeister.repository.inmemory;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PricesJSONProvider implements DataProvider<RawPriceData> {

    private List<JSONPrice> prices;

    public PricesJSONProvider(InputStream inputStream) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JavaType priceListType = mapper.getTypeFactory().constructCollectionType(List.class, JSONPrice.class);
            prices = mapper.readValue(inputStream, priceListType);
        } catch(IOException ex) {
            throw new IllegalArgumentException("Reading JSON failed!", ex);
        }
    }

    @Override
    public Stream<RawPriceData> getAllData() {
        return prices.stream().map(JSONPrice::toRawPriceData);
    }
}
