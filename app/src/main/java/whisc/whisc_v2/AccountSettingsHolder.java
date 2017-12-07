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
                case 9:{
                    String mealName = "Grilled pork chops with almond croquettes";
                    String mealDescription = "Pork chops cooked with apple wedges and flaked almond croquettes make for a stunning weekend supper.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "10 to 30 mins";
                    String mealServing = "Serves 2";
                    String meatType = "pork";
                    String mealDirections = "Preheat the grill to high. Place the pork chops and apple wedges in an ovenproof frying pan. Season the pork fat with salt and place under the grill for  and grill for 5 minutes on each side or until the pork cooked through.   Meanwhile make the dressing. Put the egg yolk mustard and honey into a bowl. Add the cider vinegar and whisk gradually adding the vegetable oil whisking constantly until thick and creamy. Add the shallot garlic parsley mint basil and thyme and whisk once more. Check the seasoning and set aside.  Heat the oil in a deep-fat fryer or large saucepan to 180C/350F. (CAUTION: hot oil can be dangerous. Never leave the pan unattended.)  To make the croquettes check the seasoning of the mashed potatoes add the remaining flatleaf parsley and mix well. Take a small handful and roll into a small sausage shape repeating with all the potato mixture.   Prepare a plate of flour a bowl of eggs and a plate of flaked almonds. Roll the potato sausages in the flour then the eggs and then the flaked almonds.   Carefully lower the croquettes into the fat fryer for 2–3 minutes or until golden brown crisp and heated through. Remove using a slotted spoon and drain on kitchen paper.   Put the salad leaves into a bowl with enough dressing to just coat the leaves and toss well.  Serve the pork chops with the croquettes apple wedges and salad.";
                    String imageID = "70";
                    mealImage.setImageResource(R.mipmap.meal70);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "2", "pork loin chops about 2cm/¾in thick");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "apple cored and cut into wedges");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "free-range egg yolk");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "green chilli deseeded and chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "English mustard");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "clear honey");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "cider vinegar");
                    mSQLiteHelper.addIngredientsData(mealID, "150ml/5fl oz", "vegetable or sunflower oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "banana shallot finely chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "cider vinegar");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "garlic clove finely chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "finely chopped flatleaf parsley");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "finely chopped mint");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "finely chopped basil");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "finely chopped thyme");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "500g/1lb 2oz", "mashed potatoes");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "finely chopped flatleaf parsley");
                    mSQLiteHelper.addIngredientsData(mealID, "50g/1¾oz", "plain flour");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "free-range egg beaten");
                    mSQLiteHelper.addIngredientsData(mealID, "75g/3oz", "flaked almonds");
                    mSQLiteHelper.addIngredientsData(mealID, "1 head", "Little Gem lettuce roughly chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "2 handfuls", "lamb’s lettuce");
                    break;
                }//end of case 9
                case 10:{
                    String mealName = "Pork tenderloin stuffed with apricots apples and ginger";
                    String mealDescription = "This easy yet stunning roast pork recipe of two flattened tenderloins sandwiched together is great served hot for dinner or cold for picnics and parties.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "1 to 2 hours";
                    String mealServing = "Serves 6";
                    String meatType = "pork";
                    String mealDirections = "To make the stuffing melt the butter with the oil in a large non-stick frying pan and gently fry the shallot and garlic for five minutes stirring regularly until softened but not coloured. Peel the apple and cut into quarters. Remove the core and roughly chop the flesh.  Remove the pan from the heat and stir in the apple apricots sultanas stem ginger breadcrumbs ground ginger lemon zest and juice and the herbs. Season with salt and plenty of freshly ground black pepper and mix well together. Add the measured stem ginger syrup and mix again until the stuffing comes together. Leave to cool.  Put the pork tenderloins on a board and using a sharp knife carefully trim off as much excess fat and sinew as possible. Place one of the tenderloins between two sheets of cling film and bash with a rolling pin until around 1cm/½in thick. Put to one side and repeat the same process with the other tenderloin.  Place a large clean sheet of cling film on the board and arrange the bacon in slightly overlapping lengths on top making a bacon rectangle roughly 32x28cm/13x11in. (A little longer and just over three times as wide as the flattened pork.) If some of the rashers are a little short stretch with the back of a knife.  Place one of the tenderloins in the centre of the bacon and spread the stuffing on top. Cover with the other tenderloin to sandwich the stuffing. Bring the bacon up over the top of the pork to enclose it completely using the cling film to help you. Wrap tightly in more cling film to help hold the shape. Place the pork parcel on its side on a small tray and chill in the fridge for one hour. (At this stage the pork can be kept overnight in the fridge if preparing ahead.)  Preheat the oven to 200C/180C (fan)/Gas 6. Unwrap the pork and place on a lightly greased baking tray with the ends of the bacon underneath. Roast in the oven for about 50 minutes or until the bacon is crisp and the pork is piping hot throughout.   Remove the tray from the oven and transfer the pork to a board or warmed serving platter. Cover with foil and leave to rest. (If serving the meat cold simply allow to cool and then wrap in foil and keep in the fridge.)  Put the baking tray on the hob and add 200ml/7fl oz water. Bring to a simmer over a medium heat and cook for two minutes stirring constantly and lifting the sticky juices from the bottom of the tin. Strain the liquid through a fine sieve into a small saucepan. Add any juices that have collected beneath the pork. Pour the ginger wine and cream into the same saucepan and bring the sauce to a simmer stirring  Mix the cornflour with the one teaspoon of cold water in a small bowl until smooth. Pour into the sauce and bubble for about a couple of minutes until thickened stirring. Remove from the heat and add salt and pepper to taste. (You can even add a dash more ginger wine if the sauce needs it.)  Carve the pork into slices and divide between six warmed dinner plates. Spoon a little of the ginger sauce over each serving of pork. Serve with creamy mashed potatoes and some freshly cooked green vegetables.";
                    String imageID = "78";
                    mealImage.setImageResource(R.mipmap.meal78);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "knob", "butter");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "sunflower oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "large banana (long) shallot finely chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "garlic cloves crushed");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "eating apple such as Gala");
                    mSQLiteHelper.addIngredientsData(mealID, "75g/2½oz", "ready-to-eat dried apricots quartered");
                    mSQLiteHelper.addIngredientsData(mealID, "50g/1¾oz", "sultanas");
                    mSQLiteHelper.addIngredientsData(mealID, "3 balls", "stem ginger in syrup drained roughly chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "75g/2½oz", "fresh white breadcrumbs");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "ground ginger");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "lemon finely grated zest and juice");
                    mSQLiteHelper.addIngredientsData(mealID, "3 tbsp", "finely chopped fresh parsley leaves");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "finely chopped fresh thyme leaves");
                    mSQLiteHelper.addIngredientsData(mealID, "1 heaped tsp", "flaked sea salt plus extra to season");
                    mSQLiteHelper.addIngredientsData(mealID, "", "freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "stem ginger syrup (from the jar)");
                    mSQLiteHelper.addIngredientsData(mealID, "2 x 500g/1lb 2oz", " pork fillets (tenderloin)");
                    mSQLiteHelper.addIngredientsData(mealID, "16", "rindless smoked streaky bacon rashers thinly sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "", "sunflower oil for greasing");
                    mSQLiteHelper.addIngredientsData(mealID, "100ml/3½fl oz", "ginger wine");
                    mSQLiteHelper.addIngredientsData(mealID, "100ml/3½fl oz", "double cream");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "cornflour");
                    mSQLiteHelper.addIngredientsData(mealID, "", "mashed potatoes");
                    mSQLiteHelper.addIngredientsData(mealID, "", "freshly cooked green vegetables");
                    break;
                }//end of case 10
                case 11:{
                    String mealName = "Chicken and leek pie";
                    String mealDescription = "A classic recipe for chicken and leeks in a white sauce topped with shortcrust pastry.";
                    String mealPrep = "1-2 hours";
                    String mealCook = "30 mins to 1 hour";
                    String mealServing = "Serves 6";
                    String meatType = "chicken";
                    String mealDirections = "For the pastry sift the flour and salt into a large mixing bowl.  Add the lard and butter and using your fingertips rub the fat into the flour until the mixture resembles breadcrumbs. Lift the flour into the air as you do so to get some air into the mixture and keep it cool.  Add just enough cold water to form a dough and mix together with a palette knife.  Then using your hands bring the dough together in the bowl. Turn it out onto a clean floured work surface and knead briefly. Shape it into a flattened circle and wrap it in cling film. Chill in the fridge for at least 20 minutes.  While the pastry is chilling make the white sauce. Melt the butter in a heavy-bottomed pan. Add the flour and stir with a wooden spoon over a medium heat for a minute or two being careful not to burn it.  Add the milk all at once. Take off the heat and whisk the mixture with a balloon whisk. Once all the lumps are whisked in place the pan back onto a medium heat and whisk continuously until the sauce comes to the boil and thickens.  Cook for another five minutes stirring frequently with a wooden spoon. Add the double cream mustard and the crumbled stock cube. Stir well then allow to cool.  For the filling top and tail the leeks. Slice them into thick rings and wash thoroughly in a bowl of clean water to remove any grit. Heat a heavy-bottomed pan and place the leeks into the hot pan. Add the butter.  Add the apples to the pan with the leeks and cook for a few minutes. Add the bacon lardons and the chicken pieces to the pan and cook for a few minutes longer (the chicken will be only about two-thirds cooked at this stage).  Preheat the oven to 200C/400F/Gas 6. Place the chicken mixture into a pie dish. Sprinkle the tarragon leaves if using over the top. Season with freshly ground black pepper. Pour over the cooled white sauce.  For the pastry top allow the pastry to get up to room temperature. Lightly flour a clean board and roll out the pastry into a rectangle about one-and-a-half times the size of the pie dish.  With a knife trim long half-inch wide strips from the pastry. Use the trimmings to make an edge around the pie dish by brushing them with a bit of water (to help the pastry adhere) and placing them in a single layer around the outer edge of the pie dish.  Using your rolling pin lift the remaining pastry sheet and place it on top of the dish to form a lid. Trim around the edge of the pie dish with a knife to cut off the excess pastry reserving the trimmings. Use your fingers and thumb to crimp the edges. Make a hole in the centre to allow the air to escape.  Brush the top of the pastry with the beaten egg and salt wash. Use the reserved pastry trimmings to decorate the top.  Place the pie on a baking tray and bake in the oven for 25-30 minutes until bubbling hot and golden. Serve while hot.";
                    String imageID = "101";
                    mealImage.setImageResource(R.mipmap.meal101);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "300g/10½oz", "plain flour plus extra for flouring");
                    mSQLiteHelper.addIngredientsData(mealID, "pinch", "salt");
                    mSQLiteHelper.addIngredientsData(mealID, "30g/1¼oz", "cold lard cut into cubes (optional - you can use all butter if you prefer)");
                    mSQLiteHelper.addIngredientsData(mealID, "85g/3oz", "cold butter cut into cubes");
                    mSQLiteHelper.addIngredientsData(mealID, "2-3 tbsp", "cold water");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "free-range egg beaten with a pinch of salt");
                    mSQLiteHelper.addIngredientsData(mealID, "30g/1¼oz", "butter");
                    mSQLiteHelper.addIngredientsData(mealID, "30g/1¼oz", "flour");
                    mSQLiteHelper.addIngredientsData(mealID, "600ml/1 pint", "milk");
                    mSQLiteHelper.addIngredientsData(mealID, "3 tbsp", "double cream");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "Dijon mustard");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "large leeks");
                    mSQLiteHelper.addIngredientsData(mealID, "30g/1½oz", "butter");
                    mSQLiteHelper.addIngredientsData(mealID, "3", "crisp eating apples cored and cut into large pieces");
                    mSQLiteHelper.addIngredientsData(mealID, "70g/2½ oz", "bacon lardons or streaky bacon diced");
                    mSQLiteHelper.addIngredientsData(mealID, "10", "boneless chicken thighs diced");
                    mSQLiteHelper.addIngredientsData(mealID, "1 bunch", "tarragon leaves only (optional)");
                    mSQLiteHelper.addIngredientsData(mealID, "", "freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "", "sunflower oil for greasing");
                    mSQLiteHelper.addIngredientsData(mealID, "100ml/3½fl oz", "ginger wine");
                    mSQLiteHelper.addIngredientsData(mealID, "100ml/3½fl oz", "double cream");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "cornflour");
                    mSQLiteHelper.addIngredientsData(mealID, "", "mashed potatoes");
                    mSQLiteHelper.addIngredientsData(mealID, "", "freshly cooked green vegetables");
                    break;
                }//end of case 11
                case 12:{
                    String mealName = "Crispy fish fingers";
                    String mealDescription = "These quick and easy fish fingers are baked in the oven rather than fried for a healthier dish.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "10 to 30 mins";
                    String mealServing = "Serves 1-2";
                    String meatType = "fish";
                    String mealDirections = "Preheat the oven to 220C/450F/Gas 7. Line a baking tray with baking parchment and brush the parchment all over with a little vegetable oil.  In a bowl mix together the breadcrumbs and polenta and season well with freshly ground black pepper.  Put the plain flour into another bowl and season with the paprika. Beat the egg in a bowl.  Squeeze the lemon juice over the strips of fish.  Roll each strip of fish first in the flour mixture then dip it into the beaten egg then coat it in the polenta mixture. Place each fish finger onto the prepared baking tray.  Drizzle a little more vegetable oil onto each fish finger then bake in the oven for 10-12 minutes or until the fish is cooked through and the coating is crisp. Serve with salad or steamed vegetables.";
                    String imageID = "229";
                    mealImage.setImageResource(R.mipmap.meal229);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "1-2 tbsp", "vegetable oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "slice day-old bread grated");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "quick-cook dried polenta");
                    mSQLiteHelper.addIngredientsData(mealID, "", "freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "3 tbsp", "plain flour");
                    mSQLiteHelper.addIngredientsData(mealID, "pinch", "paprika");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "free-range egg");
                    mSQLiteHelper.addIngredientsData(mealID, "", "lemon juice only");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "white fish fillet such as cod or plaice skin and bones removed cut into 3cm/1in strips");
                    break;
                }//end of case 12
                case 13:{
                    String mealName = "Red mullet with baked tomatoes";
                    String mealDescription = "Baking fish is an easy way to reduce calories without compromising on flavour. Serve alongside baked  tomatoes for a healthy evening meal.As part of an Intermittent diet plan 1 serving provides 2 of your 6 daily vegetable portions and 248 kcal.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "10 to 30 mins";
                    String mealServing = "Serves 4";
                    String meatType = "fish";
                    String mealDirections = "Preheat the oven to 200C/180C Fan/Gas 6.   Put the tomatoes in an ovenproof dish with the beans garlic lemon juice and spray with the oil. Season with salt and freshly ground black pepper and mix well. Bake for 10 minutes or until the tomatoes and beans are tender.  Meanwhile tear off 4 large sheets of foil and line with non-stick baking paper. Place 2 fish fillets on each piece of baking paper then scatter over the lemon rind capers and spring onions season with salt and freshly ground black pepper. Fold over the paper-lined foil and scrunch the edges together to seal. Place the parcels on a large baking tray.  Place the fish parcels next to the vegetables in the oven and bake for a further 8-10 minutes or until the flesh flakes easily when pressed in the centre with a knife.  Spoon the vegetables on to four serving plates and top each with two fish fillets. Garnish with the parsley and caperberries and serve.";
                    String imageID = "265";
                    mealImage.setImageResource(R.mipmap.meal265);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "375g/13oz", "mixed red and yellow cherry tomatoes");
                    mSQLiteHelper.addIngredientsData(mealID, "320g/11Â˝oz", "fine green beans trimmed");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "garlic cloves finely chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "lemon juice");
                    mSQLiteHelper.addIngredientsData(mealID, "", "low-calorie cooking spray");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "8", "red mullet fillets approximately 100g/3oz each");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "lemon finely grated rind only");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "baby capers drained");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "spring onions finely sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "baby capers drained");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "chopped parsley");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "baby capers drained");
                    mSQLiteHelper.addIngredientsData(mealID, "8", "caperberries");
                    break;
                }//end of case 13
                case 14:{
                    String mealName = "Salmon and watercress tart";
                    String mealDescription = "Buttery pastry rich salmon and peppery watercress make this creamy quiche by Nigel Slater a real treat to come home to. For this recipe you will also need a round loose-bottomed 24cm/10in tart case at least 3.5cm/1½in deep plus baking beans for baking blind.";
                    String mealPrep = "30 mins to 1 hour";
                    String mealCook = "30 mins to 1 hour";
                    String mealServing = "Serves 4-6";
                    String meatType = "fish";
                    String mealDirections = "For the pastry case put the flour and butter into the bowl of a food processor. Add a pinch of salt and blitz until the mixture resembles fine breadcrumbs. If you prefer rub the butter into the flour in a bowl with your fingertips.   Add the egg yolk and enough water to bring the dough to a firm ball. The less water you add the better as too much will cause your pastry case to shrink in the oven.   Pat the pastry into a flat round on a floured surface then roll out to a rough circle large enough to line the tart tin. Lightly butter the tin dust it with a small amount of flour shake out any surplus then lower in the circle of pastry. Push the pastry right into the corner where the rim joins the base without stretching it. Make certain there are no holes or tears. Trim the overhanging pastry and place in the fridge to chill for 20 minutes.   Set the oven at 200C/400F/Gas 6. Put a baking sheet in the oven to warm. Line the pastry case with kitchen foil and baking beans and slide onto the hot baking sheet. Bake for 15 minutes then remove from the oven and carefully lift the beans and foil out.   Return the pastry case to the oven for five minutes or so until the surface is dry to the touch. Remove from the oven and set aside. Turn the oven temperature down to 180C/350F/Gas 4.  For the filling place the salmon in a baking dish brush with oil or butter season lightly with salt and pepper and bake for 15 minutes or until the flakes part easily. Remove from the oven and leave to cool a little then break into large pieces. Place the pieces of fish in the tart case.   Wash the watercress leaves and remove and discard the thickest of the stems. Put the still-wet watercress into a saucepan cover tightly with a lid and place over a high heat. Cook for a couple of minutes or until the leaves wilt a little. They should keep their colour. Turn the watercress over once with tongs let it steam a further few seconds then remove from the heat cool under cold running water and squeeze dry.   Tuck the watercress into the tart case in between the salmon pieces. Gently whisk the eggs and cream pour into the case and bake for 25 minutes or until set. Serve warm.";
                    String imageID = "315";
                    mealImage.setImageResource(R.mipmap.meal315);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "200g/7oz", "plain flour plus extra for dusting");
                    mSQLiteHelper.addIngredientsData(mealID, "100g/3½oz", "butter cut into small pieces plus extra for greasing");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "free-range egg yolk");
                    mSQLiteHelper.addIngredientsData(mealID, "", "a little water");
                    mSQLiteHelper.addIngredientsData(mealID, "400g/14oz", "salmon fillet");
                    mSQLiteHelper.addIngredientsData(mealID, "", "a little butter or groundnut oil");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "large handfuls watercress");
                    mSQLiteHelper.addIngredientsData(mealID, "4", "free-range eggs");
                    mSQLiteHelper.addIngredientsData(mealID, "400ml/14fl oz", "double cream");
                    break;
                }//end of case 14
                case 15:{
                    String mealName = "Chicken nuggets with easy BBQ sauce";
                    String mealDescription = "These gluten-free chicken nuggets have a crisp coating thanks to ground almonds. Try them with throw-together BBQ sauce.Each nugget with a little BBQ sauce provides 77 kcal 6.7g protein 0.8g carbohydrate (of which 0.7g sugars) 5g fat (of which 1g saturates) 0.1g fibre and 0.3g salt.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "10 to 30 mins";
                    String mealServing = "Makes 30";
                    String meatType = "chicken";
                    String mealDirections = "Preheat the oven to 200C/180C Fan/Gas 6. Line a baking tray with baking parchment.  Pour the ground almonds into a bowl and stir in the seasoning. Beat the eggs in a separate bowl.  Season the chicken pieces all over with the salt and pepper. Dip each chicken piece first in the egg then roll in the almonds until completely covered. Arrange the nuggets on the prepared baking tray.  Melt the butter in a small saucepan over a low heat. Drizzle the melted butter over the nuggets. Bake in the oven for 18-20 minutes turning halfway through cooking until cooked through and golden-brown.  Meanwhile for the BBQ sauce whisk together the tomato purée melted butter and balsamic vinegar until well combined. Season with salt pepper and smoked paprika.  Transfer the BBQ sauce to a sterilised airtight jar and chill in the fridge until needed. It will keep in a sealed jar for up to 1 week.";
                    String imageID = "302";
                    mealImage.setImageResource(R.mipmap.meal302);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "180g/6¼oz", "ground almonds");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "free-range eggs");
                    mSQLiteHelper.addIngredientsData(mealID, "4", "chicken breasts cut into nuggets");
                    mSQLiteHelper.addIngredientsData(mealID, "1½ tsp", "salt");
                    mSQLiteHelper.addIngredientsData(mealID, "", "freshly ground black pepper to taste");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "butter");
                    mSQLiteHelper.addIngredientsData(mealID, "¾ tsp", "onion powder");
                    mSQLiteHelper.addIngredientsData(mealID, "¾ tsp", "garlic powder");
                    mSQLiteHelper.addIngredientsData(mealID, "3 tbsp", "tomato purée");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "butter melted");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "balsamic vinegar");
                    mSQLiteHelper.addIngredientsData(mealID, "½ tsp", "smoked paprika to taste");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "butter melted");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "butter melted");
                    break;
                }//end of case 15
                case 16:{
                    String mealName = "Salmon Wellington";
                    String mealDescription = "Wellingtons are fashionable again but they don't have to contain beef. A whole salmon en croûte is a lovely Christmas party centrepiece.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "30 mins to 1 hour";
                    String mealServing = "Serves 12";
                    String meatType = "fish";
                    String mealDirections = "Preheat the oven to 180C/350F/Gas 4. Line a large baking tray with baking parchment.  Slice the cucumbers lengthways scrape out the seeds with a teaspoon and chop into 1cm/½in thick pieces.  Place the cucumbers in a mixing bowl with the mustard honey vinegar and finely chopped dill. Season well with salt and freshly ground black pepper and set aside.  Roll the pastry out to 40cm/16in x 30cm/12in on a lightly floured surface. Lay on the lined baking tray.  Lay one fillet in the centre of the pastry pile half of the filling on top and then cover with the second fillet.  Brush the edges of the pastry with the mixture of milk and beaten egg.  Fold the pastry over the fish sealing the edges by pressing them together leaving a gap in the centre so that you can see inside.  Brush the pastry with more beaten egg and milk and bake for 45 minutes.  Serve with the remaining cucumber mixture.";
                    String imageID = "304";
                    mealImage.setImageResource(R.mipmap.meal304);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "2 medium", "cucumbers peeled");
                    mSQLiteHelper.addIngredientsData(mealID, "5 tbsp", "Dijon mustard");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "clear honey");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "white wine vinegar");
                    mSQLiteHelper.addIngredientsData(mealID, "50g/1¾oz", "fresh dill finely chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "1 x 380g/13oz", "ready-made puff pastry sheet");
                    mSQLiteHelper.addIngredientsData(mealID, "", "plain flour for dusting");
                    mSQLiteHelper.addIngredientsData(mealID, "1 x 2.8kg/6lb", "salmon skin removed and filleted into 2 pieces");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "free-range egg beaten combined with a little milk");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "balsamic vinegar");
                    mSQLiteHelper.addIngredientsData(mealID, "½ tsp", "smoked paprika to taste");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "butter melted");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "butter melted");
                    break;
                }//end of case 16
                case 17:{
                    String mealName = "Chicken and chorizo empanadas";
                    String mealDescription = "Traditional in South America Spain and Portugal empanadas are little pastry pockets with an intensely flavoured savoury filling. They are great eaten any time but make a particularly good tapas-style snack with a cold beer.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "1 to 2 hours";
                    String mealServing = "Makes 10";
                    String meatType = "chicken";
                    String mealDirections = "Heat the oven to 180C/350F/Gas 4.   For the filling put the chicken thighs in a small roasting dish season well all over with salt and pepper and roast for about 45 minutes or until cooked through. Set aside to cool a little.  Meanwhile make the pastry. Melt the butter and leave it to cool slightly. Put the flour in a large bowl and mix in the salt. Pour in the butter and egg. Start mixing adding 3-5 tablespoons of water as you go until you have a soft dough. Turn this out onto a clean work surface and knead it gently for a couple of minutes or until smooth. Return the dough to its bowl cover and set aside to rest while you prepare the filling.  To finish the filling heat the oil in a frying pan over a medium-low heat. Add the onion and cook for 10–12 minutes or until soft.   Add the garlic chorizo cumin seeds and raisins. Cook over a medium heat for 5–8 minutes stirring often until the chorizo is cooked. Remove from the heat.  Once the chicken thighs are cool enough to handle remove the skin. Pull all the meat from the bones and chop it roughly. Add to the chorizo mixture. Taste and add salt and pepper if needed (the chorizo is already quite salty) then leave to cool completely.  Turn the oven to 200C/400F/Gas 6 and line a large baking tray with baking parchment.  Lightly flour the work surface and roll out the dough to a thickness of 3-4mm. Use a 12cm/4½in cutter or a small bowl to cut out 10 discs. You will probably need to re-roll the offcuts once to get this many. Divide the filling between the discs. Dampen the edges of the dough with water then fold over one half of each disc to make a semicircular parcel. Press the edges together firmly then crimp or press the edges with a fork.  Put the empanadas on the baking tray and brush with beaten egg. Bake for 15–20 minutes or until golden-brown. Eat them warm on their own or with a chilli sauce.";
                    String imageID = "531";
                    mealImage.setImageResource(R.mipmap.meal531);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "4", "chicken thighs on the bone and skin on");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "onion finely chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "white wine vinegar");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "garlic clove crushed");
                    mSQLiteHelper.addIngredientsData(mealID, "100g/3½oz", "British-style chorizo finely diced");
                    mSQLiteHelper.addIngredientsData(mealID, "½ tsp", "cumin seeds");
                    mSQLiteHelper.addIngredientsData(mealID, "50g/1¾oz ", "raisins");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "150g/5½oz", "unsalted butter");
                    mSQLiteHelper.addIngredientsData(mealID, "300g/10½oz", "plain flour");
                    mSQLiteHelper.addIngredientsData(mealID, "", "large pinch salt");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "free-range egg lightly beaten");
                    mSQLiteHelper.addIngredientsData(mealID, "", "beaten egg to finish");
                    break;
                }//end of case 17
                case 18:{
                    String mealName = "Pulled chicken with barbecue sauce and baked potatoes";
                    String mealDescription = "No one will ever guess that this American-style barbecue chicken is a budget recipe - and your secret is safe with us.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "1 to 2 hours";
                    String mealServing = "Serves 4";
                    String meatType = "chicken";
                    String mealDirections = "Preheat the oven to 180C/160C Fan/Gas 4. Line the base and sides of a baking tray with aluminium foil.  In a bowl mix together the paprika black pepper and salt until well combined.   Put the chicken legs on a chopping board and carefully score the skin of each leg 5-6 times using a sharp knife.   Roll the scored skin of each chicken leg in the salt and paprika mixture pressing down to coat evenly. Place the spiced chicken legs on the prepared baking tray.  Wash and pat dry the potatoes and prick each 3-4 times with a fork to stop the skins splitting when they are baked. Add the potatoes to the baking tray. Bake the chicken and potatoes in the oven for 1 hour.  Meanwhile for the barbecue sauce in a bowl mix together all of the barbecue sauce ingredients until well combined.   Remove the chicken from the oven and increase the oven temperature to 220C/200C Fan/Gas 7.   Brush the chicken legs all over with the barbecue sauce then return the chicken and potatoes to the oven and continue to cook for a further 12-15 minutes or until the potatoes are tender and the chicken is cooked through with sticky dark-brown skin. (The chicken is cooked through if the juices run clear when the meat is pierced with a skewer in the thickest part and no trace of pink remains.)  Meanwhile for the sweetcorn half-fill a saucepan with water and bring it to the boil. Add the frozen corn-on-the-cobs and return the water to the boil then cook the sweetcorn according to the packet instructions until tender. Drain well then return to the saucepan and stir in the butter until melted. Season well with salt and freshly ground black pepper. (Alternatively cook in the microwave according to the packet instructions then add the butter and seasoning.)  Just before serving shred the chicken meat using two forks to pull the meat apart. Discard the bones but keep the skin (thinly slice it with a sharp knife if you cannot shred it).   Serve the pulled barbecue chicken with the buttered corn-on-the-cobs and the baked potatoes alongside. Sprinkle over the parsley or chives. Serve the soured cream and chive dip (or crème fraîche) in dipping bowls alongside if using.";
                    String imageID = "547";
                    mealImage.setImageResource(R.mipmap.meal547);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "smoked paprika");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "½ tsp", "salt");
                    mSQLiteHelper.addIngredientsData(mealID, "4", "chicken legs");
                    mSQLiteHelper.addIngredientsData(mealID, "4 x 300g/10½oz", "floury potatoes such as King Edward or Maris Piper");
                    mSQLiteHelper.addIngredientsData(mealID, "6 tbsp", "tomato ketchup");
                    mSQLiteHelper.addIngredientsData(mealID, "3 tbsp", "clear honey");
                    mSQLiteHelper.addIngredientsData(mealID, "1½ tbsp", "Worcestershire sauce");
                    mSQLiteHelper.addIngredientsData(mealID, "4", "frozen mini corn-on-the-cobs");
                    mSQLiteHelper.addIngredientsData(mealID, "25g/1oz", "butter");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "", "handful chopped fresh flatleaf parsley or chives (optional)");
                    mSQLiteHelper.addIngredientsData(mealID, "4 tbsp", "soured cream and chive dip or reduced-fat crème fraîche (optional)");
                    break;
                }//end of case 18
                case 19:{
                    String mealName = "Korean fried chicken";
                    String mealDescription = "This fried chicken is crispier than most American versions as it is fried twice. Serve with kimchi and soju for an authentic Korean lunch.";
                    String mealPrep = "1-2 hours";
                    String mealCook = "30 mins to 1 hour";
                    String mealServing = "Serves 4";
                    String meatType = "chicken";
                    String mealDirections = "Start by brining the chicken. Dissolve the sugar and salt in the hot water then mix1 litre/1¾ pint cold water into the brine solution. Submerge the chicken and leave to stand covered for 2 hours. Meanwhile make the sauces.  For the sweet soy sauce put the soy mirin rice wine vinegar ginger garlic and sugar into a saucepan. Stir over a low heat until the sugar is dissolved and simmer for 2 minutes. Taste for seasoning and add a little salt if necessary. Add sesame oil to taste. Whisk the cornflour with a little cold water until you have a smooth but very runny paste then whisk this into the sauce over a low heat until the sauce thickens very slightly. Set aside.  For the chilli sauce whisk all the ingredients together thinning with a little water if too thick. Taste and add salt if necessary.  To cook the chicken drain and rinse the brined chicken thoroughly then pat dry. Make the first coating (this is the coating the batter will stick to otherwise it tends to fall off) by whisking the flour cornflour baking powder and salt together. Dust the chicken in this mixture patting off any excess and leave to stand while you make the batter.  For the batter whisk together the dry ingredients then whisk in 100ml/3½ cold water and the vodka – the texture should be quite thin like double cream.  Add oil to your fryer large saucepan or wok making sure you don’t fill it beyond half way. Heat the oil to 160C. (CAUTION: hot oil can be dangerous. Do not leave unattended.)  Dip the chicken pieces in the batter one at a time. Allow any excess to drip off over the bowl then slowly lower into the hot oil letting go when the chicken piece is almost submerged. Fry for 8–10 minutes then drain and put on kitchen paper – the chicken should be cooked through but have barely taken on any colour. Turn the heat up to 190C or if using a saucepan turn the heat up and leave for around 5 minutes to reach the right temperature. Fry the chicken for another 2 minutes or until it has darkened. Remove and drain thoroughly on kitchen paper. It should be beautifully crisp. You can serve it with the sauces or you can brush some of the soy-based sauce over it and serve with the chilli sauce.";
                    String imageID = "659";
                    mealImage.setImageResource(R.mipmap.meal659);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "8–10", "chicken pieces (thighs work best) boneless skin removed");
                    mSQLiteHelper.addIngredientsData(mealID, "100g/3½oz", "caster sugar");
                    mSQLiteHelper.addIngredientsData(mealID, "75g/2½oz", "sea salt");
                    mSQLiteHelper.addIngredientsData(mealID, "200ml/7fl oz", "hot water");
                    mSQLiteHelper.addIngredientsData(mealID, "2 heaped tbsp", "plain flour");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "cornflour");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "baking powder");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "salt");
                    mSQLiteHelper.addIngredientsData(mealID, "125g/4½oz", "plain flour");
                    mSQLiteHelper.addIngredientsData(mealID, "25g/1oz", "cornflour");
                    mSQLiteHelper.addIngredientsData(mealID, "½ tsp", "salt");
                    mSQLiteHelper.addIngredientsData(mealID, "½ tsp", "baking powder");
                    mSQLiteHelper.addIngredientsData(mealID, "100ml/3½fl oz", "vodka");
                    mSQLiteHelper.addIngredientsData(mealID, "100ml/3½fl oz", "soy sauce");
                    mSQLiteHelper.addIngredientsData(mealID, "25ml/1fl oz", "mirin");
                    mSQLiteHelper.addIngredientsData(mealID, "25ml/1fl oz", "rice wine vinegar");
                    mSQLiteHelper.addIngredientsData(mealID, "2cm/¾in piece", "fresh root ginger grated");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "garlic cloves grated");
                    mSQLiteHelper.addIngredientsData(mealID, "50g/1¾oz", "brown sugar");
                    mSQLiteHelper.addIngredientsData(mealID, "", "few drops of sesame oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "cornflour");
                    mSQLiteHelper.addIngredientsData(mealID, "", "sesame seeds for sprinkling");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "Korean chilli paste (gochujang if you can get it)");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "hot sauce");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "rice wine vinegar");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "honey");
                    mSQLiteHelper.addIngredientsData(mealID, "", "few drops of sesame oil");
                    break;
                }//end of case 19
                case 20:{
                    String mealName = "Turkey meatballs in tomato sauce";
                    String mealDescription = "I have been making meatballs for as long as my children have been eating solid food. I first made them out of turkey mince rather than my usual preferred beef mince when I suddenly and uncharacteristically fell victim to the ethos of the age and it came upon me to pander to the low-fat brigade. Obviously my sub-conscious knew better and directed me thuswards for a reason. It turns out that turkey makes for a light and succulent meatball which my children love. All in all this is quite a favourite in casa Lawson these days and I can be found making a batch – half for tea and half to be frozen for future outings – regularly and pleasurably.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "30 mins to 1 hour";
                    String mealServing = "Serves 4-8";
                    String meatType = "turkey";
                    String mealDirections = "For the sauce put the onion and celery into a food processor and blitz to a mush. (Or you can chop as finely as humanly possible by hand.) Reserve 2 tablespoons of the mixture for the meatballs.  Warm the garlic oil in a large heavy-based saucepan or casserole add the onion and celery mixture along with the thyme and cook at a moderate to low heat stirring every now and again for about 10 minutes.  Add the cans of plum tomatoes filling up each empty can with water to add to the pan. Season with the sugar salt and pepper stir well and let the mixture come to a bubble then turn the heat down and simmer the sauce gently while you get on with the meatballs.  For the meatballs put all the ingredients for the meatballs including the reserved chopped onion and celery and salt according to preference into a large bowl and gently mix together with your hands. Don’t overmix as that will make the meatballs dense-textured and heavy.  When all the meatball ingredients are not too officiously amalgamated start rolling them into balls. The easiest way is to pinch out an amount about the size of a generously heaped teaspoon and roll it into a ball between the palms of your hands. Put each meatball onto a baking tray lined with baking parchment or greaseproof paper. You should get about 50 little meatballs.  Drop the meatballs gently into the simmering sauce I try to let these fall in concentric circles working round the pan from the outside edge inwards in the vaguest of fashions.  Let the meatballs simmer in the sauce for 30 minutes or until cooked through. Serve with rice pasta couscous or however you so please.";
                    String imageID = "717";
                    mealImage.setImageResource(R.mipmap.meal717);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "1", "onion peeled");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "stick celery");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "garlic oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "dried thyme");
                    mSQLiteHelper.addIngredientsData(mealID, "2 x 400g/14oz cans", "chopped plum tomatoes plus approximately 2 full cans of water");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "sugar");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "sea salt flakes or ½ tsp pouring salt");
                    mSQLiteHelper.addIngredientsData(mealID, "", "black pepper to taste");
                    mSQLiteHelper.addIngredientsData(mealID, "500g/1lb 2oz", "turkey mince");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "free-range egg");
                    mSQLiteHelper.addIngredientsData(mealID, "3 tbsp", "breadcrumbs");
                    mSQLiteHelper.addIngredientsData(mealID, "3 tbsp", "grated parmesan");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "Worcestershire sauce");
                    mSQLiteHelper.addIngredientsData(mealID, "½ tsp", "dried thyme");
                    break;
                }//end of case 20
                case 21:{
                    String mealName = "Turkey enchiladas";
                    String mealDescription = "Baking these wraps in a tomato sauce transforms them from mere sandwiches into a proper healthy dinner.Each serving provides 342kcal 25g protein 40g carbohydrate (of which 8g sugars)";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "10 to 30 mins";
                    String mealServing = "Makes 4";
                    String meatType = "turkey";
                    String mealDirections = "Preheat oven to 180C/350F/Gas 4.  To make the sauce heat the olive oil in saucepan over a medium heat. Add the onion and garlic and sauté for 4-5 minutes until softened.  Add the passata oregano and black pepper. Cover  reduce the heat and leave to simmer for ten minutes.  Meanwhile for the turkey heat the olive oil in a separate pan over a medium heat. Add the onion garlic red pepper and courgette. Sauté for five minutes then add the turkey mince. Cook for around 5-7 minutes or until the turkey mince is browned and cooked through.  Add half of the cheese to the turkey mixture and continue to cook until the cheese has melted.  Divide the turkey mixture between four tortillas and roll up into parcels. Place into an ovenproof dish spoon over sauce and sprinkle with remaining cheese. Place into the preheated oven to bake for 15-20 minutes until golden.";
                    String imageID = "945";
                    mealImage.setImageResource(R.mipmap.meal945);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "onion chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "garlic clove crushed");
                    mSQLiteHelper.addIngredientsData(mealID, "200ml/7fl oz", " passata (or 1 x 400g tin chopped tomatoes)");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "chopped fresh oregano (or 1/2 tsp dried oregano)");
                    mSQLiteHelper.addIngredientsData(mealID, "½ tsp", "freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "garlic clove crushed");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "red onion chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "red pepper seeds and core removed finely chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "courgette chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "170g/6oz", "turkey mince");
                    mSQLiteHelper.addIngredientsData(mealID, "85g/3oz", "cheddar grated");
                    mSQLiteHelper.addIngredientsData(mealID, "4", "wholemeal tortillas");
                    break;
                }//end of case 21
                case 22:{
                    String mealName = "Squash and turkey bake";
                    String mealDescription = "Nigel Slater swaps traditional shepherd’s pie ingredients for turkey and butternut squash with delicious results.This meal provides 406 kcal 40g protein 22g carbohydrate (of which 12g sugars) 10g fat (of which 3g saturates) 6g fibre and 0.5g salt per portion.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "1 to 2 hours";
                    String mealServing = "Serves 4";
                    String meatType = "turkey";
                    String mealDirections = "Preheat the oven at 200C/400F/Gas 6. Peel seed and roughly chop the butternut squash. Put the pieces of squash in a steamer basket and place over boiling water. Steam for 20-30 minutes until fully tender. They must be soft enough to mash.   Peel and roughly chop the onion. Peel and finely slice the garlic. Warm a couple of tablespoons of the olive oil in a large pan then add the onion and garlic and cook until pale gold. Cut the mushrooms into thick slices or quarters and add to the pan with the leaves from the thyme sprigs. Fry until softened then transfer to a mixing bowl.  Add a further tablespoon of oil to the pan then as soon as it starts to sizzle add the minced turkey. Season generously with salt and pepper and then stir in a couple of heaped tablespoons of flour. Cook for 2-3 minutes then pour in the red wine and a few shakes of Worcestershire sauce. Simmer at a low temperature for 5 minutes then add the mushrooms and onions. Continue cooking for 5 minutes then tip into a baking dish.  Mash the cooked squash with a potato masher then stir in a generous knob of butter and the grated orange zest to taste. Season with salt and pepper.   Pile the mash on top of the turkey dot with more of the butter then bake for 35-45 minutes or until the mash is lightly browned and slightly crisp on top.";
                    String imageID = "1069";
                    mealImage.setImageResource(R.mipmap.meal1069);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "", "a medium-sized butternut squash");
                    mSQLiteHelper.addIngredientsData(mealID, "", "a little olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "", "a large onion");
                    mSQLiteHelper.addIngredientsData(mealID, "", "a large clove of garlic");
                    mSQLiteHelper.addIngredientsData(mealID, "15", "chestnut mushrooms");
                    mSQLiteHelper.addIngredientsData(mealID, "3", "sprigs thyme");
                    mSQLiteHelper.addIngredientsData(mealID, "500g/1lb 2oz", "minced turkey");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "", "a little flour");
                    mSQLiteHelper.addIngredientsData(mealID, "400ml/14fl oz", "red wine");
                    mSQLiteHelper.addIngredientsData(mealID, "", "a little Worcestershire sauce");
                    mSQLiteHelper.addIngredientsData(mealID, "", "a knob of butter");
                    mSQLiteHelper.addIngredientsData(mealID, "", "a little orange zest");
                    break;
                }//end of case 22
                case 23:{
                    String mealName = "British beef Raj curry";
                    String mealDescription = "This beef curry is a version of a childhood memory of one of Rick Stein’s mum’s curries. The curry is finished with sultanas and desiccated coconut. Serve with chutney salted fish and poppadoms.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "1 to 2 hours";
                    String mealServing = "Serves 4-6";
                    String meatType = "beef";
                    String mealDirections = "Melt the butter in a large sturdy pan over a medium heat. Add the steak in batches and fry for a few minutes until browned and then remove to a plate. Add the onions to the same pan and fry for 10 minutes or until softened and golden-brown.   Add the garlic and fry for one minute then return the meat to the pan along with any juices on the plate. Stir in the chilli powder turmeric one tablespoon of the garam masala and the salt and cook for one minute.  Add the stock followed by the coconut and sultanas. Bring to a simmer cover and cook over a low heat for 45 minutes to an hour or until the beef is tender. Stir in the remaining garam masala and serve.";
                    String imageID = "13";
                    mealImage.setImageResource(R.mipmap.meal13);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "25g/1oz", "butter");
                    mSQLiteHelper.addIngredientsData(mealID, "750g/1lb 10oz", "chuck steak cut into 4cm/1½in cubes");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "medium onions sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "3", "garlic cloves crushed");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "Kashmiri chilli powder");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "ground turmeric");
                    mSQLiteHelper.addIngredientsData(mealID, "1½ tbsp", "garam masala");
                    mSQLiteHelper.addIngredientsData(mealID, "1½ tsp", "salt");
                    mSQLiteHelper.addIngredientsData(mealID, "600ml/20fl oz", "beef stock");
                    mSQLiteHelper.addIngredientsData(mealID, "50g/1¾oz", "desiccated coconut");
                    mSQLiteHelper.addIngredientsData(mealID, "100g/3½oz", "sultanas");
                    mSQLiteHelper.addIngredientsData(mealID, "", "Bombay duck");
                    mSQLiteHelper.addIngredientsData(mealID, "", "a little orange zest");
                    break;
                }//end of case 23
                case 24:{
                    String mealName = "Beef and horseradish sausage rolls";
                    String mealDescription = "This is a great twist on the classic British sausage roll using beef sausages with horseradish and mushrooms. Like a cheat’s Wellington!";
                    String mealPrep = "30 mins to 1 hour";
                    String mealCook = "10 to 30 mins";
                    String mealServing = "Makes 8-14";
                    String meatType = "beef";
                    String mealDirections = "Heat the oil in a medium saucepan over a medium heat. Add the garlic salt and mushrooms and cook until all the moisture from the mushrooms has evaporated. Transfer to a bowl and leave to cool completely.   Stir the horseradish purée into the mushrooms. Add the beef sausage meat and mix until well combined using your hands.   Preheat the oven to 200C/180C Fan/Gas 6. Line a baking tray with baking paper.  Lightly dust a work surface with flour. Roll out the pastry to 25x35cm/10x14in then cut it into three equal rectangles lengthways. Roll out each rectangle a little more to create more width.   Divide the beef mixture into three equal parts. Spread one part down one side of a pastry rectangle in a long thin sausage shape. Brush the long edge with a little beaten egg and roll the pastry up over the sausage meat to seal. Repeat with the remaining sausage meat and pastry.  Cut each long piece into eight to ten sausage rolls and place on the prepared baking tray.  Brush each pastry with a little more beaten egg then bake for 15–20 minutes.   Leave to cool for 10 minutes before eating as they will be very hot.";
                    String imageID = "242";
                    mealImage.setImageResource(R.mipmap.meal242);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "garlic clove crushed");
                    mSQLiteHelper.addIngredientsData(mealID, "½ tsp", "salt");
                    mSQLiteHelper.addIngredientsData(mealID, "300g/10½oz", "chestnut mushrooms thinly sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "3 tbsp", "horseradish purée");
                    mSQLiteHelper.addIngredientsData(mealID, "800g/1lb 12oz", "beef sausages skins removed");
                    mSQLiteHelper.addIngredientsData(mealID, "500g/1lb 2oz", "ready-made puff pastry");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "free-range egg beaten");
                    mSQLiteHelper.addIngredientsData(mealID, "", "little plain flour for dusting");
                    mSQLiteHelper.addIngredientsData(mealID, "50g/1¾oz", "desiccated coconut");
                    mSQLiteHelper.addIngredientsData(mealID, "100g/3½oz", "sultanas");
                    mSQLiteHelper.addIngredientsData(mealID, "", "Bombay duck");
                    mSQLiteHelper.addIngredientsData(mealID, "", "a little orange zest");
                    break;
                }//end of case 24
                case 25:{
                    String mealName = "Minced beef pinwheels";
                    String mealDescription = "Great finger food for a picnic or drinks party these pinwheels are tasty when still warm from the oven.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "30 mins to 1 hour";
                    String mealServing = "Makes 12";
                    String meatType = "beef";
                    String mealDirections = "Preheat the oven to 200C/400F/Gas 6. Line a large baking tray (or two smaller baking trays) with greaseproof paper.  Heat the oil in a small frying pan and gently fry the onion and garlic for 2–3 minutes until soft stirring regularly. Tip the mixture into a large heatproof bowl and leave to cool for few minutes.  Add the minced beef dried herbs tomato purée and the flour to the bowl with the onion and season with salt and freshly ground black pepper. Mix with clean hands until thoroughly combined.   Lightly  flour a clean surface with flour and roll the puff pastry into a 34cm/13½in square.   Spread the mince mixture over the pastry leaving a 2cm/¾in border around the edges. Sprinkle on the grated cheese if using and brush the pastry edges with a little of the beaten egg.   Firmly roll the pastry up like a Swiss roll.   Cut the roll into 12 even slices using a serrated knife – a bread knife is ideal.   Place the pinwheels on the baking tray (or trays) spaced well apart to allow for rising. Flatten each pinwheel slightly with the palm of your hand. Brush the top of each pinwheel with the beaten egg to glaze taking care not to allow the glaze to drip down the sides and onto the tray as this will make the pinwheels stick to the baking paper. Bake for 15 minutes or until well-risen and golden brown and the beef is cooked through.   Allow to cool slightly then remove from the baking paper using a palette knife.  Best served warm.";
                    String imageID = "266";
                    mealImage.setImageResource(R.mipmap.meal266);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "sunflower oil");
                    mSQLiteHelper.addIngredientsData(mealID, "½", "medium onion peeled and finely chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "garlic clove crushed");
                    mSQLiteHelper.addIngredientsData(mealID, "500g", "lean beef mince");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "dried mixed herbs");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "tomato purée");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "plain flour plus extra for rolling");
                    mSQLiteHelper.addIngredientsData(mealID, "500g block", "ready-made puff pastry");
                    mSQLiteHelper.addIngredientsData(mealID, "50g", "mature Cheddar finely grated (optional)");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "medium egg beaten");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    break;
                }//end of case 25
                case 26:{
                    String mealName = "Corned beef hash with poached eggs";
                    String mealDescription = "This retro dish is quick and easy to make - lovely for both brunch and dinner.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "10 to 30 mins";
                    String mealServing = "Serves 4";
                    String meatType = "beef";
                    String mealDirections = "For the corned beef hash place the potatoes into a saucepan and cover with water. Bring to the boil and simmer until just tender. Drain and set aside.  Heat a frying pan until hot. Add one tablespoon of the olive oil the onion and garlic and fry for two to three minutes.  Add the potatoes and fry for a further minute.  Crumble in the corned beef and mix together.  Spoon the mixture into a soup dish then place under the grill for three to four minutes until crusty.  Bring a pan of water to the boil add the vinegar and whirl the water around.  Crack an egg into a ladle. Pour the egg into the pan and simmer for two minutes until ready. Remove from the pan and drain.  Remove the hash from the grill and top with the egg.  Top with a squeeze of ketchup and serve with hot buttered corn bread.  For the cornbread mix together the flour cornmeal icing sugar and baking powder.  Make a well in the centre of the dry ingredients and add the milk and egg. Mix together from the centre outwards incorporating the dry ingredients into the wet gradually to create a dough-like paste. Add the melted butter last.  Place in a hot baking tin. Place in a hot oven for about 15 minutes until golden brown.  Remove from the oven allow to cool slightly. Turn out cut into slices and serve with the corned beef hash.";
                    String imageID = "394";
                    mealImage.setImageResource(R.mipmap.meal394);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "4", "potatoes diced into cubes");
                    mSQLiteHelper.addIngredientsData(mealID, "3 tbsp", "olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "onion finely diced");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "garlic clove crushed");
                    mSQLiteHelper.addIngredientsData(mealID, "340g/12oz", "corned beef tinned");
                    mSQLiteHelper.addIngredientsData(mealID, "", "Salt and freshly ground black pepper to taste");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "vinegar");
                    mSQLiteHelper.addIngredientsData(mealID, "1-2", "free-range eggs (per person)");
                    mSQLiteHelper.addIngredientsData(mealID, "", "Tomato ketchup to serve");
                    mSQLiteHelper.addIngredientsData(mealID, "300g/10Â˝oz", "plain flour");
                    mSQLiteHelper.addIngredientsData(mealID, "315g/11oz", "cornmeal");
                    mSQLiteHelper.addIngredientsData(mealID, "200g/7oz", "icing sugar");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "baking powder");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "free-range egg");
                    mSQLiteHelper.addIngredientsData(mealID, "250ml/9fl oz", "milk");
                    mSQLiteHelper.addIngredientsData(mealID, "200g/7oz", "butter melted");
                    mSQLiteHelper.addIngredientsData(mealID, "315g/11oz", "cornmeal");
                    mSQLiteHelper.addIngredientsData(mealID, "315g/11oz", "cornmeal");
                    mSQLiteHelper.addIngredientsData(mealID, "315g/11oz", "cornmeal");
                    mSQLiteHelper.addIngredientsData(mealID, "315g/11oz", "cornmeal");
                    mSQLiteHelper.addIngredientsData(mealID, "315g/11oz", "cornmeal");
                    mSQLiteHelper.addIngredientsData(mealID, "315g/11oz", "cornmeal");
                    break;
                }//end of case 26
                case 27:{
                    String mealName = "Beef stew with dumplings";
                    String mealDescription = "This hearty one-pot dish boasts melt-in-the-mouth beef chunky vegetables and dumplings to soak up the juices.";
                    String mealPrep = "less than 30 mins";
                    String mealCook = "1 to 2 hours";
                    String mealServing = "Serves 4";
                    String meatType = "beef";
                    String mealDirections = "For the beef stew heat the oil in a large lidded casserole over a medium heat. Fry the chuck steak for 3-4 minutes until browned on all sides. Remove from the pan and set aside.  Add the onions carrot celery and swede and fry for 6-8 minutes or until golden-brown adding a little more olive oil if need be. Season with salt and pepper.  Add the plain flour and stir well. Cook for 1-2 minutes or until it turns a nut-brown colour. Pour in the stock and stir well until the sauce thickens.  Return the beef to the casserole add the red wine and bay leaves and bring the mixture to the boil. Reduce the heat until the mixture is simmering then leave to simmer gently for 1 hour with the lid slightly ajar.  Meanwhile for the suet dumplings combine the flour suet baking powder and salt in a bowl until well combined. Add the water gradually stirring the mixture with your fingers until it comes together as a slightly sloppy dough. Shape the dough into balls roughly the size of a plum.  For the herby oat dumplings combine the oatmeal flour baking powder salt and herbs in a mixing bowl until well combined. Grate the chilled butter into the bowl and mix again. Bring the dough together with your fingers gradually adding enough water as before to make a wet dough. Shape the dough into plum-sized balls.  When the stew has been simmering for an hour season it again with salt and pepper if necessary. Put the dumplings on top of the stew cover the casserole with the lid and cook for a further 15-20 minutes or until the dumplings have proudly puffed up. Serve.";
                    String imageID = "424";
                    mealImage.setImageResource(R.mipmap.meal424);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "4 tbsp", "olive oil");
                    mSQLiteHelper.addIngredientsData(mealID, "500g/1lb 2oz", "chuck steak cubed");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "onions roughly chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "large carrot peeled roughly chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "celery stalk trimmed roughly chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "½", "swede peeled roughly chopped");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tbsp", "plain flour");
                    mSQLiteHelper.addIngredientsData(mealID, "1 litre/1¾ pints", "beef stock");
                    mSQLiteHelper.addIngredientsData(mealID, "400ml/14fl oz", "red wine");
                    mSQLiteHelper.addIngredientsData(mealID, "2", "bay leaves");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "125g/4½oz", "plain flour");
                    mSQLiteHelper.addIngredientsData(mealID, "65g/2¼oz", "shredded suet");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "baking powder");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "salt");
                    mSQLiteHelper.addIngredientsData(mealID, "4-5 tbsp", "cold water to mix");
                    mSQLiteHelper.addIngredientsData(mealID, "70g/2½oz", "fine oatmeal");
                    mSQLiteHelper.addIngredientsData(mealID, "70g/2½oz", " plain flour");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "baking powder");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tsp", "salt");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "chopped fresh parsley");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "chopped fresh rosemary leaves");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "fresh thyme leaves");
                    mSQLiteHelper.addIngredientsData(mealID, "80g/2¾oz", "chilled butter");
                    mSQLiteHelper.addIngredientsData(mealID, "4-5 tbsp", "cold water to mix");
                    break;
                }//end of case 27
                case 28:{
                    String mealName = "Beef Wellington with tarragon sauce";
                    String mealDescription = "Beef Wellington traditionally has pâté spread over the top of the fillet which makes it very rich. This recipe is lighter but with a lovely taste from the tarragon.";
                    String mealPrep = "1-2 hours";
                    String mealCook = "1 to 2 hours";
                    String mealServing = "Serves 6-8";
                    String meatType = "beef";
                    String mealDirections = "Preheat the oven to 220C/200C Fan/Gas 7.  Season the beef with salt and pepper. Place a large frying pan over a high heat add the oil and fry the beef on all sides until browned. Transfer to a baking tray and roast in the oven for 15–18 minutes. Turn the oven off and remove the beef. Leave to cool reserving any cooking juices and then chill in the fridge for at least 1 hour (see tip).  Next make the topping. Melt the butter in the same pan and fry the mushrooms over a high heat for 5–10 minutes – you may need to do this in batches. Tip the mushrooms into a sieve set over a bowl to collect the juices and reserve these for the sauce. Transfer the mushrooms to another bowl and allow to cool before mixing with the Parmesan tarragon mustard and egg yolk. Season with salt and pepper and chill in the fridge for 30 minutes.  Line a baking sheet with baking paper. Sit the single 375g packet of pastry on a floured work surface and roll out to a square about 40cm/16in in size then transfer it to the baking sheet. Place the cooked beef to one side of the pastry and spoon the mushroom mixture on top. Fold over the ends of the pastry and then fold the longest edge over the beef sealing along the side with a little of the egg wash. Chill in the fridge for 30 minutes. While it is chilling preheat the oven again to 220C/200C Fan/Gas 7.  Brush the top of the chilled beef Wellington with egg wash. Roll out the remaining half block of pastry and cut into 8 thin strips: lay 4 strips diagonally and evenly spaced across the top of the beef Wellington and 4 strips across these to create a lattice pattern. Brush the lattice with egg wash and then roast in the oven for 30–35 minutes or until the pastry is golden-brown and crisp.  Meanwhile make the tarragon sauce. Melt the butter in the same frying pan and fry the mushrooms over a high heat for 5–10 minutes or until the juices evaporate. Sprinkle in the flour and add the crème fraîche with the reserved mushroom juices (from the topping) the mustard and tarragon. Season with salt pepper and sugar and bring to the boil stirring. Simmer for 3 minutes until reduced slightly and add any beef juices from the original baking tray. Keep hot.  Allow the beef Wellington to rest covered with foil for 15 minutes before carving. Slice into thick slices and serve with the hot tarragon sauce.";
                    String imageID = "536";
                    mealImage.setImageResource(R.mipmap.meal536);

                    mSQLiteHelper.addMealData(mealName, mealDescription, mealPrep, mealCook, mealServing, mealDirections, imageViewToByte(mealImage), meatType);
                    Cursor mealIDData = mSQLiteHelper.getMealID(mealName);
                    String mealID = "";
                    while(mealIDData.moveToNext()){
                        mealID = mealIDData.getString(0);
                    }//end of while
                    mSQLiteHelper.addIngredientsData(mealID, "1.3kg/2lb 14oz", "middle-cut fillet of beef");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "oil");
                    mSQLiteHelper.addIngredientsData(mealID, "1½ x 375g packets", "all-butter puff pastry");
                    mSQLiteHelper.addIngredientsData(mealID, "", "plain flour for dusting");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "egg beaten with a dash of milk");
                    mSQLiteHelper.addIngredientsData(mealID, "", "salt and freshly ground black pepper");
                    mSQLiteHelper.addIngredientsData(mealID, "", "large knob of butter");
                    mSQLiteHelper.addIngredientsData(mealID, "350g/12oz", "mixed mushrooms (such as button chestnut wild) thinly sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "50g/1¾oz", "Parmesan finely grated");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "finely chopped tarragon");
                    mSQLiteHelper.addIngredientsData(mealID, "2 tsp", "Dijon mustard");
                    mSQLiteHelper.addIngredientsData(mealID, "1", "egg yolk");
                    mSQLiteHelper.addIngredientsData(mealID, "", "small knob of butter");
                    mSQLiteHelper.addIngredientsData(mealID, "100g/3½oz", "button mushrooms finely sliced");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "plain flour");
                    mSQLiteHelper.addIngredientsData(mealID, "400ml/14fl oz", "full-fat crème fraîche");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "Dijon mustard");
                    mSQLiteHelper.addIngredientsData(mealID, "1 tbsp", "freshly chopped tarragon");
                    mSQLiteHelper.addIngredientsData(mealID, "", "a pinch sugar");
                    break;
                }//end of case 28
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
