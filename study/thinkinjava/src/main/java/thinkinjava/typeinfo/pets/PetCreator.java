//: typeinfo/pets/PetCreator.java
// Creates random sequences of Pets.
package thinkinjava.typeinfo.pets;
import java.util.*;

/**
 *  ͨ������������ش�����ͬ��ĳ������Ϊ���������
 *  �����Դ������������List��
 */
public abstract class PetCreator {
  private Random rand = new Random(47);
  // The List of the different types of Pet to create:
  public abstract List<Class<? extends Pet>> types();


  /**
   * �����������
   * @return
   */
  public Pet randomPet() { // Create one random Pet
    int n = rand.nextInt(types().size());
    try {
      return types().get(n).newInstance();
    } catch(InstantiationException e) {
      throw new RuntimeException(e);
    } catch(IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }


  /**
   * ������������
   * @param size
   * @return
     */
  public Pet[] createArray(int size) {
    Pet[] result = new Pet[size];
    for(int i = 0; i < size; i++)
      result[i] = randomPet();
    return result;
  }

  /**
   * ��������list
   * @param size
   * @return
     */
  public ArrayList<Pet> arrayList(int size) {
    ArrayList<Pet> result = new ArrayList<Pet>();
    Collections.addAll(result, createArray(size));
    return result;
  }
} ///:~
