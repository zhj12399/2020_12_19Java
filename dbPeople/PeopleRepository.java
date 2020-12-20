package com.ZHJ.dbPeople;
/**
 * @author ZHJ
 * @date 2020/11/27
 */
import com.ZHJ.Things.People;

public interface PeopleRepository {
    boolean NameExist(String name);
    boolean PassWordCorrect(String name,String password);
    boolean AddPeople(People people);
    People FindPeople(String name);
    boolean DeletePeople(String name);
    boolean ModifyMoney(People people,int num,String op);
}
