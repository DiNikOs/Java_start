/**
 * Java2. Core. HW3
 * Согласно задания минимальный функционал.
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 06, 2019
 */

import java.util.*;
       

public class HW3 {
    public static void main(String[] args) {
        
    System.out.println("Answer 1:");
    String [] array = {"Германия", "Россия", "Франция", "Дания", "Россия", "Дания", "Россия", "Дания", "Россия", "Дания", "Россия", "Германия" };
    getWords(array);
    
    System.out.println("Answer 2:");
   // "Лавцов", "Сидоров", "Степанов", "Просковьев","Смирнов", "Кравцов"
    getPhoneBook("Смирнов");
    getPhoneBook("Степанов");
    getPhoneBook("Лавцов");
    getPhoneBook("Кравцов");
    }

    static void getWords(String []array) {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            words.add(array[i]);
            }
        
        Map<String, Integer> map = new HashMap<>();
        Set<String> hs = new HashSet<>();
           
        for (String w :words) {    
           int count = map.getOrDefault(w, 0);
           map.put(w, count + 1);
        }
        for (int i = 0; i < words.size(); i++) {
            hs.add(words.get(i));
            }        
        System.out.println(words);
        System.out.println(hs); 
        System.out.println(map.toString()); 
    }
    
    static void getPhoneBook(String name) {
        PhoneBook pb = new PhoneBook();
        
        pb.add("Лавцов", "8910123876");
        pb.add("Кравцов", "89101334566");
        pb.add("Сидоров", "8920765432");
        pb.add("Просковьев", "8930134678");
        pb.add("Смирнов", "8940444444");
        pb.add("Степанов", "8950555555");
        pb.add("Сидоров", "8960666666");
        pb.add("Борисов", "8970777777");
        pb.add("Лавцов", "8910123456");
        pb.add("Сидоров", "8920765432");
        pb.add("Просковьев", "8960131234");
        pb.add("Смирнов", "8940999999");
        pb.add("Смирнов", "8940888888");
        pb.add("Смирнов", "8940121212");
        
        System.out.println("   "+name + ":\t" + pb.get(name)); 
    }
}  

class PhoneBook {
    Map<String, String> man = new HashMap<>(); 
          
    void add(String name, String phone) {
        man.put(phone, name );
    }
        
    List<String> get(String name) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : man.entrySet()) {
            if (name.equals(entry.getValue())) //  
                list.add(entry.getKey()); 
        }
        return list;
    }
}
         
