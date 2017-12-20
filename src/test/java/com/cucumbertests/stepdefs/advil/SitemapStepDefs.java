package com.cucumbertests.stepdefs.advil;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import cucumber.api.java8.En;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SitemapStepDefs implements En {

    public SitemapStepDefs() {

        Given("^I have URLs from sitemap$", () -> {
            try {

                URL url = new URL("https://www.advil.com/sitemap.xml");
                URLConnection connection = url.openConnection();

                Document doc = parseXML(connection.getInputStream());

                NodeList nList = doc.getElementsByTagName("url");

                System.out.println("----------------------------");
                System.out.println(nList.getLength());

                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        System.out.println("url loc : " + eElement.getElementsByTagName("loc").item(0).getTextContent());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        When("^I visit individual URL in sitemap$", () ->{

        });

        Then("^I see the page is available$", () -> {

        });
    }

    private Document parseXML(InputStream stream)
            throws Exception {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        } catch (Exception ex) {
            throw ex;
        }

        return doc;
    }
}
