package fr.bts.iris.slam.capabilities;

public interface Enchantable {
    int modifyValue(int baseValue);
    String modifyName(String baseName);
    void setNext(Enchantable next);
}
