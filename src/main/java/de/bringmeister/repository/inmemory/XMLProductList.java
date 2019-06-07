package de.bringmeister.repository.inmemory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="Products")
class XMLProductList {

    XMLProductList() {}

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName="Product")
    private XMLProduct[] products;

    public XMLProduct[] getProducts() {
        return products;
    }
}
