package de.bringmeister.repository.inmemory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

class XMLProduct {
    @JacksonXmlProperty(isAttribute = true)
    private String id;
    @JacksonXmlProperty(localName = "Name")
    private String name;
    @JacksonXmlProperty(localName = "Description")
    private String description;
    @JacksonXmlProperty(localName = "sku")
    private String sku;

    XMLProduct() {}

    RawProductData toRawProductData() {
        return new RawProductData(id, name, description, sku);
    }
}
