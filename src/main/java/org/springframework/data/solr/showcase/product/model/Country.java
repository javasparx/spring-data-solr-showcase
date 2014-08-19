package org.springframework.data.solr.showcase.product.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.data.solr.showcase.product.SearchableCountryDefinition;

/**
 * @author Java
 *         Date 8/19/2014.
 */
@SolrDocument(solrCoreName = "collectionCountry")
public class Country implements SearchableCountryDefinition {

    public Country(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private
    @Id
    @Indexed
    @Field
    String id;

    private
    @Indexed(NAME_FIELD_NAME)
    @Field(NAME_FIELD_NAME)
    String name;

    private
    @Indexed(SHORT_NAME_FIELD_NAME)
    @Field("shortName")
    String shortName;

    private
    @Indexed(ISO_CODE_FIELD_NAME)
    @Field(ISO_CODE_FIELD_NAME)
    String isoCode;

    private
    @Indexed
    @Field
    Integer popularity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", isoCode='" + isoCode + '\'' +
                ", popularity=" + popularity +
                '}';
    }
}
