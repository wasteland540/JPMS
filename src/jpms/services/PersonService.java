/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpms.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpms.model.AdditionalInfo;
import jpms.model.Adress;
import jpms.model.Communication;
import jpms.model.CommunicationTyp;
import jpms.model.Fee;
import jpms.model.Person;
import jpms.model.PersonGroup;
import jpms.model.VoiceTyp;

/**
 *
 * @author m.elz
 */

@Singleton
public class PersonService implements IPersonService {

    @Inject
    private EntityManagerFactory entityManagerFactory;
    
    @Override
    public void test() {
//        Person person = new Person();
//        person.setFirstname("marcel2222");
//        
//        Communication comm = new Communication();
//        comm.setPerson(person);
//        comm.setCommunicationTyp(CommunicationTyp.Email);
//        comm.setCommunicationValue("m_m_elz@web.de");
//        
//        Adress adress = new Adress();
//        adress.setCity("Bonn");
//        adress.setZipcode(53225);
//        adress.getPersons().add(person);
//        
//        Fee fee1 = new Fee();
//        fee1.setPerson(person);
//        fee1.setAmount(123.00);
//        fee1.setSettledAt(new Date());
//        
//        Fee fee2 = new Fee();
//        fee2.setPerson(person);
//        fee2.setAmount(312.00);
//        fee2.setSettledAt(new Date());
//        
//        AdditionalInfo addInfo = new AdditionalInfo();
//        addInfo.setPerson(person);
//        addInfo.setFunctionRole("juip");
//        addInfo.setVoice(VoiceTyp.S1);
//        
//        PersonGroup persGroup = new PersonGroup();
//        persGroup.getPersons().add(person);
//        persGroup.setName("Männerchor");
//        
//        //rel ships
//        person.getCommunications().add(comm);
//        person.getAdresses().add(adress);
//        person.getFees().add(fee1);
//        person.getFees().add(fee2);
//        person.setAdditionalInfo(addInfo);
//        person.getPersonGroups().add(persGroup);
//        
//        EntityManager em = entityManagerFactory.createEntityManager();
//        em.getTransaction().begin();
//
//        em.persist(person);
//        //em.persist(comm);
//        
//        
//        
//        
//        List<Person> persons = em.createQuery("SELECT p FROM Person p").getResultList();
//        
//        int a = 2;
//        
//        
//        
//        em.flush();
//
//        em.getTransaction().commit();
//        em.close();
    }
    
}
