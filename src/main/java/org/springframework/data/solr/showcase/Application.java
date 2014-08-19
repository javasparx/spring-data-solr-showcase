/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.solr.showcase;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.solr.showcase.config.SearchContext;
import org.springframework.data.solr.showcase.config.WebContext;
import org.springframework.data.solr.showcase.product.model.Country;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Christoph Strobl
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@Import({WebContext.class, SearchContext.class})
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        SolrServer server = context.getBean("solrServer", SolrServer.class);

        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("0", "Uzbekistan"));
        indexCountry(countries, server);
    }

    public static void indexCountry(Collection<Country> countries, SolrServer solrServer) {
        List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
        for (Country country : countries) {
            for (int i = 0; i < 100; i++) {

                SolrInputDocument sid = new SolrInputDocument();
                sid.addField("id", "country." + country.getId() + i);
                sid.addField("name", country.getName() + i);
                sid.addField("short_name", country.getShortName() + i);
                sid.addField("iso_code", country.getIsoCode() + i);

                documents.add(sid);

            }
        }
        try {
            solrServer.add(documents);
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
