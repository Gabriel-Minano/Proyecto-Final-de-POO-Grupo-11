//CREATE READ UPDATE DELETE
package DataAccesObject;

import java.util.List;

public interface IBaseDAO<T>/*Representa una variable "T"*/ {

    public boolean Create(T input);

    public T Read(String id);

    public List<T> ReadAll();

    public boolean Update(T input);

    public boolean Delete(String id);

}
