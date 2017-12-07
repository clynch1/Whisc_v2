package whisc.whisc_v2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static whisc.whisc_v2.R.mipmap.meal17;
import static whisc.whisc_v2.R.mipmap.meal_holder;

public class AccountSettingsHolder extends AppCompatActivity {
    SQLiteHelper mSQLiteHelper;

    private Button addMeals;
    private TextView numberOfMeals;
    private ImageView mealImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings_holder);
        addMeals = (Button) findViewById(R.id.accountSettingsHolder_add_demo_meals);
        numberOfMeals = (TextView) findViewById(R.id.accountSettingsHolder_number_of_meals);
        mealImage = (ImageView) findViewById(R.id.accountSettingsHolder_imageView);
        mSQLiteHelper = new SQLiteHelper(this);

        addMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeals(Integer.parseInt(numberOfMeals.getText().toString()));
                Intent intent = new Intent(AccountSettingsHolder.this, MainActivity.class);
                startActivity(intent);
                toastMessage("Demo Meals Added");
            }
        });
    }//end of onCreate

    public void addMeals(int numberOfMeals){
        for(int i = 0; i < numberOfMeals; i++){
            switch (i){
                case 0:{
                    String mealName = "Pork chops with celeriac mash and apple and ale gravy";
                    String mealDescription = "Fresh and creamy celeriac mash is the perfect partner to rich pork chops in this delicious recipe.";
                    String mealPrep = "30 mins to 1 hour";
                    String mealCook = "30 mins to 1 hour";
                    String mealServing = "Serves 4";
                    String meatType = "pork";
                    String mealDirections = "Peel the celeriac chop into 2.5cm/1-inch chunks and place in a pan of cold water. Place on the hob bring to the boil and simmer until tender about 20 minutes. Mash the celeriac with 100g/3Â½oz of the butter and season with salt and freshly ground black pepper. Keep covered and warm. Meanwhile peel halve and thinly slice the onion lengthways into semicircles. Heat the olive oil in a heavy-bottomed frying pan and cook the onions on a medium high heat until soft and lightly coloured. Cut the apples into quarters and cut out the cores out then cut them again into eighths. Remove the onions from the pan add the remaining 50g/1Â½oz butter and place the apples in. Cook over a medium heat. When they are golden brown turn them over so that they are beautifully coloured on both sides. Return the onions to the pan with the apples raise the heat and add the ale to the pan. Adjust the seasoning and continue cooking until the apples begin to break down a little and the liquid has reduced by at least half and has thickened somewhat. With a sharp knife score the skin of the chops and season them. This will help the chop to crisp when it is cooked. Heat a griddle pan until smoking hot. Rub the pork chops with some vegetable oil (this will help prevent sticking) and place the chops on the hot griddle. Cook turning as necessary until the meat is cooked through (cooking time will depend on the thickness of the chops it will be cooked when the juices run clear when pierced with a sharp knife near the bone). For the salad make the vinaigrette. Place the mustard vinegar and olive oil in a bowl and whisk. Season to taste. When the chops are cooked remove from the griddle and put covered on a plate in a warm place for five minutes. Meanwhile whisk a knob of butter into the sauce to thicken it slightly. To serve divide the mash among four plates place a chop on each dollop of mash and pour the mustard and ale sauce over it. Dress the rocket with the vinaigrette and serve on the side.";
                    String imageID = "meal56";
                    mealImage.setImageResource(R.mipmap.meal56);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "1", "large celeriac");
                    mSQLiteHelper.addIngredientsData(mealID, "150g/5oz", "butter");
                    mSQLiteHelper.addIngredientsData(mealID, "pinch", "salt");
                    mSQLiteHelper.addIngredientsData(mealID, "pinch", "freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "large onion");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "extra virgin olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "3", "Cox's apples peeled");
                    mSQLiteHelper.addIngredientsData(mealID, "425ml/1 Pint", "London Pride ale (or similar)");
                    mSQLiteHelper.addIngredientsData(mealID, "4", "pork chops");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "vegetable oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1½ tsp", "Dijon mustard");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "white wine vinegar");
                    mSQLiteHelper.addIngredientsData(mealID, "3 tbsp", "extra virgin olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "250g/9oz", "rocket leaves");
                    break;
                }//end of case 0
                case 1:{
                    String mealName = "Turkey meatballs with spaghetti and tomato sauce";
                    String mealDescription = "These turkey meatballs are low-fat but still full of flavour. Leftovers freeze brilliantly too.";
                    String mealPrep = "30 mins to 1 hour";
                    String mealCook = "10 to 30 mins";
                    String mealServing = "Serves 4";
                    String meatType = "turkey";
                    String mealDirections = "For the meatballs heat the oil in a pan and fry the onion for about 10 minutes until softened. Meanwhile soak the breadcrumbs in the milk for 10 minutes in a large bowl. For the sauce heat the oil in a pan and fry the onion for about 10 minutes until softened. Add the crushed garlic and sautÃ© for one minute. Add all the remaining ingredients and simmer covered for seven minutes. For the meatballs add the turkey mince grated apple thyme sautÃ©ed onions salt and freshly ground black pepper to the soaked breadcrumbs and mix together. Using floured hands form teaspoons of the turkey mixture into small balls. Heat the sunflower oil in a frying pan and brown the meatballs. Transfer the meatballs to the pan of tomato sauce and simmer uncovered for 8-10 minutes. Cook the spaghetti according to the instructions on the packet. Drain and toss with the sauce. Sprinkle with a little chopped parsley or basil and serve.";
                    String imageID = "61";
                    mealImage.setImageResource(R.mipmap.meal61);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "medium onion chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "40g/1½oz", "white breadcrumbs");
                    mSQLiteHelper.addIngredientsData(mealID, "50ml/2fl oz", "milk");
                    mSQLiteHelper.addIngredientsData(mealID, "250g/9oz", "turkey mince");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "small apple peeled and grated");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "fresh thyme leaves");
                    mSQLiteHelper.addIngredientsData(mealID, "1½ tsp", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "flour for dusting");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "sunflower oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "medium onion chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "clove garlic crushed");
                    mSQLiteHelper.addIngredientsData(mealID, "400g/14oz", "thin chopped tomatoes");
                    mSQLiteHelper.addIngredientsData(mealID, "100ml/3½fl oz", "water");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "tomato pure");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "sugar");
                    mSQLiteHelper.addIngredientsData(mealID, "1¼ tsp", "dried oregano");
                    mSQLiteHelper.addIngredientsData(mealID, "pinch", "dried chilli flakes");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "250g/9oz", "dried spaghetti");
                    mSQLiteHelper.addIngredientsData(mealID, "", "chopped fresh parsley or basil");
                    break;
                }//end of case 1
                case 2:{
                    String mealName = "Perfect roast pork belly with cabbage and bacon";
                    String mealDescription = "James Martin's twice-cooked pork belly comes with bacon-flecked greens and a fluffy apple sauce.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "over 2 hours";
                    String mealServing = "Serves 4";
                    String meatType = "pork";
                    String mealDirections = "For the pork belly place the pork belly onion carrot celery parsley thyme star anise and cinnamon sticks into a large saucepan. Cover with cold water until the pork is just covered. Bring the mixture to the boil then reduce the heat and simmer for two hours. Remove the pork belly from the mixture and place between two heavy baking trays. Chill in the fridge for at least two hours. Preheat the oven to 225C/450F/Gas 8. Remove the pork from the fridge and cut into four squares. Heat an ovenproof frying pan until hot add half of the butter and the pork belly pieces skin-side down. Fry for 2-3 minutes. Carefully turn the pork belly pieces over and pour over the honey. Roast the pork belly in the oven for 10-12 minutes or until the pork skin is crisp. Meanwhile bring the red wine and beef stock to the boil in a saucepan and cook until the volume of the liquid has reduced by half. Whisk in the remaining butter and season with salt and freshly ground black pepper. Meanwhile for the sautÃ©ed cabbage and bacon heat the butter in a frying pan until foaming and fry the bacon until crisp and golden-brown. Add the cabbage and Brussels sprouts and stir-fry for a further minute. Add the water and cook for a further 4-5 minutes or until the cabbage is just tender. Stir in the chervil and season with salt and freshly ground black pepper. For the apple sauce heat the butter and Bramley apples in a lidded saucepan over a gentle heat. Cover and cook for 2-4 minutes or until the apples have collapsed and are soft and fluffy. Stir the mixture to form a smooth purÃ©e. To serve spoon the sautÃ©ed cabbage onto serving plates and top with a piece of pork belly. Spoon over the red wine sauce and serve the apple sauce alongside.";
                    String imageID = "67";
                    mealImage.setImageResource(R.mipmap.meal67);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "2kg/4lb 4oz", "boneless pork belly skin scored");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "onion cut into chunks");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "carrot cut into chunks");
                    mSQLiteHelper.addIngredientsData(mealID, "50ml/2fl oz", "milk");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "celery stick cut into four pieces");
                    mSQLiteHelper.addIngredientsData(mealID, "4 sprigs", "flatleaf parsley");
                    mSQLiteHelper.addIngredientsData(mealID, "2 sprigs", "thyme");
                    mSQLiteHelper.addIngredientsData(mealID, "3", "star anise");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "cinnamon stick broken in half");
                    mSQLiteHelper.addIngredientsData(mealID, "110g/4oz", "butter");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "honey");
                    mSQLiteHelper.addIngredientsData(mealID, "110ml/4fl oz", "red wine");
                    mSQLiteHelper.addIngredientsData(mealID, "250ml/9fl oz", "beef stock");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "100ml/3½fl oz", "water");
                    mSQLiteHelper.addIngredientsData(mealID, "50g/2oz", "butter");
                    mSQLiteHelper.addIngredientsData(mealID, "4 slices", "smoked streaky bacon finely sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "green cabbage preferably Hispie finely shredded");
                    mSQLiteHelper.addIngredientsData(mealID, "150g/5oz", "Brussels sprouts finely sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "75ml/3fl oz", "water");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "chopped fresh chervil");
                    mSQLiteHelper.addIngredientsData(mealID, "25g/1oz", "butter");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "large Bramley apple peeled roughly chopped");
                    break;
                }//end of case 2
                case 3:{
                    String mealName = "Barbecue baby back ribs with celeriac slaw";
                    String mealDescription = "Good things come to those who wait and these fall-apart slow-cooked ribs are no exception.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "over 2 hours";
                    String mealServing = "Serves 2-4";
                    String meatType = "pork";
                    String mealDirections = "For the ribs place the rib racks into a large wide heavy-based saucepan with the celery onion chillies garlic and apple juice. Pour over just enough water to cover the pan contents. Bring the mixture to the boil then reduce the heat until the mixture is just simmering. Simmer very gently for 1-1Â½ hours or until the meat is tender. From time to time skim off any scum that rises to the surface during cooking. Meanwhile for the barbecue sauce heat the ketchup and muscovado sugar in a frying pan over a medium heat for 2-3 minutes or until the sugar has dissolved into the ketchup. Add the Worcestershire sauce smoked paprikas and vinegar and bring the mixture to the boil. Reduce the heat until the mixture is simmering and cook for 4-5 minutes or until thickened. Stir in the bourbon then remove from the heat and set aside. Refrigerate when cool. For the slaw whisk together the egg yolks mustard and vinegar in a large bowl until well combined. Gradually add the oil in a very thin stream whisking continuously until the mixture has thickened to a light mayonnaise and all of the oil has been fully incorporated into it. Drain the celeriac thoroughly and remove any excess moisture using kitchen paper. Stir the celeriac and parsley into the mayonnaise and season to taste with salt and freshly ground black pepper. Chill in the fridge until needed. When the ribs are poached preheat the oven to 240C/220C Fan/Gas 9 or as hot as it will go. Remove the ribs from the poaching liquor using a slotted spoon and transfer to a large roasting tray. Brush the ribs all over with the barbecue sauce then roast in the oven for 10-15 minutes turning every five minutes until the ribs are sticky and charred around the edges. To serve pile the ribs on a serving platter and serve the celeriac slaw and the remaining barbecue sauce alongside.";
                    String imageID = "76";
                    mealImage.setImageResource(R.mipmap.meal76);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "2kg/2lb 4oz", "baby back pork ribs in racks");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "celery stalks trimmed halved");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "onion thickly sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "red chillies cut in half lengthways");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "garlic bulb cut in half horizontally");
                    mSQLiteHelper.addIngredientsData(mealID, "250ml/9fl oz", "apple juice");
                    mSQLiteHelper.addIngredientsData(mealID, "125ml/4½fl oz", "tomato ketchup");
                    mSQLiteHelper.addIngredientsData(mealID, "150g/5oz", "dark muscovado sugar");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "Worcestershire sauce");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "smoked hot paprika");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "smoked sweet paprika");
                    mSQLiteHelper.addIngredientsData(mealID, "75ml/2½fl oz", "white wine vinegar");
                    mSQLiteHelper.addIngredientsData(mealID, "50ml/2fl oz", "bourbon whiskey");
                    mSQLiteHelper.addIngredientsData(mealID, "3", "free-range egg yolks");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "Dijon mustard");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "cider vinegar");
                    mSQLiteHelper.addIngredientsData(mealID, "300ml/10½fl oz", "rapeseed oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "celeriac julienned and placed in acidulated water");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "roughly chopped fresh flatleaf parsley");
                    mSQLiteHelper.addIngredientsData(mealID, "", "sea salt and freshly ground black pepper");
                    break;
                }//end of case 3
                case 4:{
                    String mealName = "Curried chicken and rice salad";
                    String mealDescription = "Strips of chicken breast are tossed with spiced rice flaked almonds sliced apple cherry tomatoes and long stem broccoli to make a colourful filling and portable lunch. Simply pack into a plastic container pop into a cool bag with a couple of ice blocks and keep well chilled.With a GI of 51 this meal is high protein low GI and provides 354 kcal per portion.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "30 mins to 1 hour";
                    String mealServing = "Serves 2";
                    String meatType = "chicken";
                    String mealDirections = "Half-fill a medium saucepan with water and bring to the boil. Add the rice and one teaspoon of curry powder. Cook for 25 minutes or until tender. Add the broccoli and cook for a further two minutes. Drain in a colander and rinse under cold running water until cold. Meanwhile half-fill a small saucepan with water add the stock cube and bring to the boil stirring until the stock dissolves. Add the chicken the sliced onion and bay leaf and reduce the heat. Simmer for 15 minutes or until the chicken is cooked through. Drain in a sieve reserving the cooking liquor. Set the chicken aside to cool. Return the pan to the heat spray with oil and add the chopped onion. Cook over a low heat for three minutes stirring. Add the remaining curry powder and stir for a further few seconds. Pour 200ml/7fl oz of reserved cooking liquor into the pan and bring to a simmer. Cook the onion for 5-6 minutes or until most of the liquid has evaporated stirring occasionally. Remove from the heat spread across a plate and leave to cool for five minutes before transferring to the fridge until cold. Tear the chicken into pieces and place in a bowl. Tip the rice and broccoli on top add the cooled spiced onions almonds apple and tomatoes. Scatter with the coriander and sprinkle with lemon juice. Season to taste and serve.";
                    String imageID = "77";
                    mealImage.setImageResource(R.mipmap.meal77);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "75g/2½oz", "wholegrain long-grain rice");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "medium curry powder");
                    mSQLiteHelper.addIngredientsData(mealID, "100g/3½oz", "long-stem broccoli trimmed and cut into short lengths");
                    mSQLiteHelper.addIngredientsData(mealID, "½ ", "chicken stock cube");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "chicken breast boned and skinned");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "small onion half thinly sliced half finely chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "bay leaf");
                    mSQLiteHelper.addIngredientsData(mealID, "", "low-calorie cooking spray");
                    mSQLiteHelper.addIngredientsData(mealID, "15g/½oz", "flaked almonds toasted");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "small eating apple cored halved and thinly sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "100g/3½oz", "cherry tomatoes halved");
                    mSQLiteHelper.addIngredientsData(mealID, "3", "heaped tbsp roughly chopped fresh coriander");
                    mSQLiteHelper.addIngredientsData(mealID, "½", "lemon juice only");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    break;
                }//end of case 4
                case 5:{
                    String mealName = "Bake-in-a-bag fish";
                    String mealDescription = "Baking fish wrapped in foil is very quick and easy. Use whatever fish you want and vary the other ingredients when you want something new.This meal is low calorie and provides 370 kcal 34g protein 2.5g carbohydrate (of which 1.8g sugars) 23g fat (of which 2.5g saturates) 1.2g fibre and 0.3g salt per portion.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "10 to 30 mins";
                    String mealServing = "Serves 2";
                    String meatType = "fish";
                    String mealDirections = "Preheat the oven to 200C/180C Fan/Gas 6. Cut out two squares baking paper about 40cm/16in square and two squares of tin foil the same size. Lay a square of baking paper on top of each piece of foil. Heat the oil in a frying pan and fry the fennel and onion with some salt and pepper for 2-3 minutes until softened. Divide the fennel and onions between the two squares of baking paper and layer on the sliced tomatoes then put a fish fillet on top of each heap. Drizzle over some olive oil a squeeze of lemon juice and season with salt and freshly ground black pepper. Sprinkle over the chopped dill and a few capers if using. Fold the foil and paper over the fish and double-fold each edge to make a sealed parcel leaving a gap at the top to pour in a few tablespoons of white wine (if using). Pour in the wine then fully seal the parcel but not be too tight as it needs to expand in the oven as it cooks. Place the parcels on a baking tray and bake for 12-14 minutes or until the fish is just opaque and cooked through. Serve the parcels on each plate so they can be opened at the table.";
                    String imageID = "227";
                    mealImage.setImageResource(R.mipmap.meal227);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "¼", "fennel bulb finely sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "½", "onion finely sliced");
                    mSQLiteHelper.addIngredientsData(mealID, " ", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "medium tomato sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "2 175g/6oz", "fish fillets as cod sea bass or trout");
                    mSQLiteHelper.addIngredientsData(mealID, "", "olive oil drizzle");
                    mSQLiteHelper.addIngredientsData(mealID, "", "squeeze lemon juice");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "chopped dill (optional)");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "capers (optional)");
                    mSQLiteHelper.addIngredientsData(mealID, "2-3 tbsp", "white wine (optional)");
                    break;
                }//end of case 5
                case 6:{
                    String mealName = "Citrus spiced salmon";
                    String mealDescription = "Sumac and dried lime powder pack a citrus punch that will brighten any piece of fish. Serve with a fattoush salad or Persian herb rice - or both.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "10 to 30 mins";
                    String mealServing = "Serves 4";
                    String meatType = "fish";
                    String mealDirections = "Preheat the oven to 200C/180C Fan/Gas 6. Line a baking tray with baking paper. Combine the ground rose petals spices orange zest and lime zest in a small bowl. Add the olive oil and stir well to form a wet marinade for the salmon. Rub each salmon fillet well with the marinade and place on the tray. (At this stage you can cover with clingfilm and leave overnight in the refrigerator to marinate if you wish). Once all the fillets are well coated in the marinade season generously with sea salt and black pepper. Bake for 10-12 minutes or until the fish is cooked through. Serve with lemon wedges.";
                    String imageID = "418";
                    mealImage.setImageResource(R.mipmap.meal418);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "4 tbsp", "dried edible rose petals finely ground");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "sumac");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "dried lime powder");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "ground cumin");
                    mSQLiteHelper.addIngredientsData(mealID, "½ tsp", "ground cinnamon");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "orange zest only");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "lime zest only");
                    mSQLiteHelper.addIngredientsData(mealID, "6 tbsp", "olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "4 x 150g/5oz", "skinless salmon fillets");
                    mSQLiteHelper.addIngredientsData(mealID, "", "sea salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "lemon cut into 6 wedges to serve");
                    break;
                }//end of case 6
                case 7:{
                    String mealName = "Pork and apple burgers";
                    String mealDescription = "This recipe makes enough mince mixture for two meals. Try it as burgers and then as meatballs.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "10 to 30 mins";
                    String mealServing = "Serves 8";
                    String meatType = "pork";
                    String mealDirections = "Preheat the oven to 190C/170C Fan/Gas 5 and line a baking tray with foil.  Place all the meat mixture ingredients into a bowl with a good pinch of salt and black pepper. Start mixing with a spoon then finish with your hands to get it totally mixed together.   Divide the mixture in half and place one half into a bowl or box cover and place in the fridge until needed – it should be used within 2 days. Divide the rest into 4 and form into burger shaped patties.  Heat the olive oil in a frying pan until medium hot then add the burgers and fry on each side for 45-60 seconds until just browned. Lift out and place onto the lined baking tray. Bake in the preheated oven for 15 minutes until cooked through and hot.   Served the burgers in the buns with mustard lettuce tomato and onion.  To cook meatballs divide the mixture into 12 even pieces and roll into them into balls.  Heat a small frying pan until medium hot add half the olive oil and the onion and cook for 4-5 minutes until just softened. You can cover the pan with a lid turn the heat down low and let the onions steam until they’re softened.  Meanwhile heat a large frying pan until hot add the remaining oil and brown the meatballs on each side until browned all over.  Remove the lid from the onion pan add the tinned tomatoes and when they are simmering add the browned meatballs. Cook over a gentle heat for 15 minutes until the meatballs are cooked through and the sauce has thickened slightly. Season with salt and black pepper.  While the sauce cooks bring a pan of salted water to the boil. Add the pasta and cook according to packet instructions. Drain the pasta reserving about 3-4 tablespoons of the cooking water then add the pasta to the pan of sauce. Add the pasta water if the sauce needs thinning down. Stir well to coat the spaghetti in the sauce then cook for another couple of minutes. Serve immediately with grated Parmesan.";
                    String imageID = "44";
                    mealImage.setImageResource(R.mipmap.meal44);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "750g/1lb 10oz", "pork mince");
                    mSQLiteHelper.addIngredientsData(mealID, "75g/3oz", "fresh breadcrumbs");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "finely chopped fresh sage");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "dessert apple coarsely grated");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "spring onions finely chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "free-range egg lightly beaten");
                    mSQLiteHelper.addIngredientsData(mealID, "", "sea salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "4", "burger buns");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "head Little Gem lettuce leaves separated");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "tomatoes thickly sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "½", "small red onion cut into rings");
                    mSQLiteHelper.addIngredientsData(mealID, "", "English mustard to taste");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "onion roughly chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1 x 400g/14oz", "thin chopped tomatoes");
                    mSQLiteHelper.addIngredientsData(mealID, "300g/10½oz", "dried spaghetti");
                    mSQLiteHelper.addIngredientsData(mealID, "", "grated Parmesan to serve (optional)");
                    break;
                }//end of case 7
                case 8:{
                    String mealName = "Chilli and coriander fish parcel";
                    String mealDescription = "Baking fish is a great way to reduce calories. Give the fish extra oomph with chilli and coriander. For this recipe you will need a blender or a food processor. As part of an Intermittent diet plan 1 serving provides 1 of your 6 daily vegetable portions and 148 calories.";
                    String mealPrep = "1-2 hours";
                    String mealCook = "10 to 30 mins";
                    String mealServing = "Serves 1";
                    String meatType = "fish";
                    String mealDirections = "Preheat the oven to 200C/180C Fan/Gas 6.   Place the fish in a non-metallic dish and sprinkle with the lemon juice. Cover and leave in the fridge to marinate for 15-20 minutes.  Put the coriander garlic and chilli in a food processor or blender and process until the mixture forms a paste. Add the sugar and yoghurt and briefly process to blend.  Lay the fish on a sheet of foil. Coat the fish on both sides with the paste. Gather up the foil loosely and turn over at the top to seal. Return to the fridge for at least 1 hour.  Place the parcel on a baking tray and bake for about 15 minutes or until the fish is just cooked. Serve with the mangetout.";
                    String imageID = "366";
                    mealImage.setImageResource(R.mipmap.meal366);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "125g/4˝oz", "cod coley or haddock fillet");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "lemon juice");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "fresh coriander leaves");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "garlic clove roughly chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "green chilli deseeded and chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "sugar");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "natural yoghurt");
                    mSQLiteHelper.addIngredientsData(mealID, "80g/3oz", "mangetout steamed to serve");
                    break;
                }//end of case 8
//                case 9:{
//                    String mealName = "Chilli and coriander fish parcel";
//                    String mealDescription = "Baking fish is a great way to reduce calories. Give the fish extra oomph with chilli and coriander. For this recipe you will need a blender or a food processor. As part of an Intermittent diet plan 1 serving provides 1 of your 6 daily vegetable portions and 148 calories.";
//                    String mealPrep = "1-2 hours";
//                    String mealCook = "10 to 30 mins";
//                    String mealServing = "Serves 1";
//                    String meatType = "fish";
//                    String mealDirections = "Preheat the oven to 200C/180C Fan/Gas 6.   Place the fish in a non-metallic dish and sprinkle with the lemon juice. Cover and leave in the fridge to marinate for 15-20 minutes.  Put the coriander garlic and chilli in a food processor or blender and process until the mixture forms a paste. Add the sugar and yoghurt and briefly process to blend.  Lay the fish on a sheet of foil. Coat the fish on both sides with the paste. Gather up the foil loosely and turn over at the top to seal. Return to the fridge for at least 1 hour.  Place the parcel on a baking tray and bake for about 15 minutes or until the fish is just cooked. Serve with the mangetout.";
//                    String imageID = "366";
//                    mealImage.setImageResource(R.mipmap.meal17);
//
//                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
//                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
//                    String mealID = "";
//                    while(mealIDData.moveToNext()){
//                        mealID = mealIDData.getString(0);
//                    }//end of while
//                    mSQLiteHelper.addIngredientsData(mealID, "125g/4˝oz", "cod coley or haddock fillet");
//                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "lemon juice");
//                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "fresh coriander leaves");
//                    mSQLiteHelper.addIngredientsData(mealID, "1", "garlic clove roughly chopped");
//                    mSQLiteHelper.addIngredientsData(mealID, "1", "green chilli deseeded and chopped");
//                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "sugar");
//                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "natural yoghurt");
//                    mSQLiteHelper.addIngredientsData(mealID, "80g/3oz", "mangetout steamed to serve");
//                    break;
//                }//end of case 9
            }//end of switch
        }//end of for
    }//end of addMeals

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }//end of imageViewToByte

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }//end of toastMessage

}
