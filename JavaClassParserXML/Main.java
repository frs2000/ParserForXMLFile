package JavaClassParserXML;

import org.hibernate.HibernateException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception  {
        HashSet hs = new HashSet<>();
        clearTables();
        readingFileAndFillingTables(hs);
    }



    static void readingFileAndFillingTables(HashSet hs) {
        try {
            File file = new File("C:\\................\\test.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            NodeList nList = document.getElementsByTagName("transaction");

            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    String place = document.getElementsByTagName("place").item(i).getTextContent();
                    String amount = document.getElementsByTagName("amount").item(i).getTextContent();
                    String currency = document.getElementsByTagName("currency").item(i).getTextContent();
                    String card = document.getElementsByTagName("card").item(i).getTextContent();
                    String firstName = document.getElementsByTagName("firstName").item(i).getTextContent();
                    String lastName = document.getElementsByTagName("lastName").item(i).getTextContent();
                    String middleName = document.getElementsByTagName("middleName").item(i).getTextContent();
                    String inn = document.getElementsByTagName("inn").item(i).getTextContent();


                    EntityManagerFactory emf2 = null ;
                    EntityManager em2 = null;
                    try{
                        emf2 = Persistence.createEntityManagerFactory("JPA1");
                        em2 = emf2.createEntityManager();

                        em2.getTransaction().begin();

                        //TableAll tableAll = new TableAll(place, amount, currency, card, firstName , lastName , middleName , inn );
                        Client client = new Client( firstName , lastName , middleName , inn);


                        if (hs.contains(inn) == false){
                            hs.add(inn);
                            em2.persist(client);
                        }

                        Transaction transaction = null ;
                        if (hs.contains(inn) == false) {
                            Client client2 = em2.find(Client.class, inn);
                            transaction = new Transaction(place, amount, currency, card, client2);
                        }
                        else {transaction = new Transaction(place, amount, currency, card, client);}
                        em2.persist(transaction);




                        em2.getTransaction().commit();
                    } catch (
                            HibernateException ex) {
                        ex.printStackTrace();
                    } finally    {
                        em2.close();
                        emf2.close();
                    }



                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    static void clearTables() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("JPA1");
            em = emf.createEntityManager();

            em.getTransaction().begin();
/*
             Query query = em.createQuery("DELETE FROM TableAll u ");
            query.executeUpdate();
*/
            Query query1 = em.createQuery("DELETE FROM Transaction u ");
            query1.executeUpdate();

            Query query2 = em.createQuery("DELETE FROM Client u ");
            query2.executeUpdate();

            em.getTransaction().commit();
        } catch (
                HibernateException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

}










