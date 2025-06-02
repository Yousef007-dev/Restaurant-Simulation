import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    List <Meal> list;
    String OrderCase,OrderType;
    double Price;

    Order(String Case,String Type, List<Meal> lt)
    {
        list=lt;
        Price=GetPrice(lt);
        this.OrderCase=Case;
        this.OrderType=Type;
    }

    double GetPrice(List <Meal> lt)
    {
        double sum=0;
        for (Meal meal:lt)
            sum+=meal.maelPrise;
        return sum;
    }


}
