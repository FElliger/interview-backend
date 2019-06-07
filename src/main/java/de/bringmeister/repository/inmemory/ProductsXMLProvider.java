package de.bringmeister.repository.inmemory;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class ProductsXMLProvider implements DataProvider<RawProductData> {

    private XMLProductList productList;

    public ProductsXMLProvider(XMLStreamReader xmlStream) {
        try {
            XmlMapper mapper = new XmlMapper();
            productList = mapper.readValue(xmlStream, XMLProductList.class);
        } catch(IOException ex) {
            throw new IllegalArgumentException("Reading XML failed!", ex);
        }
    }

    @Override
    public Stream<RawProductData> getAllData() {
        return Arrays.stream(productList.getProducts()).map(XMLProduct::toRawProductData);
    }
}
