import java.util.List;

public class Field {
	 protected double _size;
     protected List<Plant> _plants;

     public void setSize(double size)
     {
         _size = size;
     }

     public double getSize()
     {
         return _size;
     }

     public void setPlants(List<Plant> plants)
     {
         _plants = plants;
     }

     public List<Plant> getPlants()
     {
         return _plants;
     }
}
