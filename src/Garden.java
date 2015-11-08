import java.util.List;

public class Garden {
	 protected GardenType _gardenType;
     protected Climate _climate;
     protected List<Field> _fields;
     protected double _size;

     public void setClimate(Climate climate)
     {
         _climate = climate;
     }

     public Climate getClimate()
     {
         return _climate;
     }

     public void setFields(List<Field> fields)
     {
         _fields = fields;
     }

     public List<Field> getFields()
     {
         return _fields;
     }

     public void setGardenType(GardenType gardenType)
     {
         _gardenType = gardenType;
     }

     public GardenType getGardenType()
     {
         return _gardenType;
     }

     public void setSize(double size)
     {
         _size = size;
     }

     public double getSize()
     {
         return _size;
     }
}
