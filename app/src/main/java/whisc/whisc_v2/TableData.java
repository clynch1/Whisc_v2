package whisc.whisc_v2;

/**
 * Created by clynch on 11/8/17.
 */
import android.provider.BaseColumns;

public class TableData {

    public TableData()
    {

    }

    public static abstract class TableInfo implements BaseColumns
    {

        public static final String DATABASE_NAME = "user_meals";

        public static final String TABLE_1_NAME = "meal";
        public static final String MEAL_ID = "id";
        public static final String MEAL_NAME = "meal_name";
        public static final String MEAL_DESCRIPTION = "meal_description";
        public static final String PREP_TIME = "prep_time";
        public static final String COOK_TIME = "cook_time";
        public static final String SERVING_SIZE = "serving_size";
        public static final String MEAL_DIRECTIONS = "meal_directions";

        public static final String TABLE_2_NAME = "ingredients";
        public static final String IN_AMOUNT = "in_amount";
        public static final String IN_ITEM = "in_item";
    }

}//end of table data
