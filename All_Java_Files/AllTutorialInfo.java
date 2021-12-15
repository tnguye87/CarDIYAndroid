package edu.gmu.cs477.fall2020.finalproject_tnguye87;

public final class AllTutorialInfo {

    // constructor of the class
    AllTutorialInfo() {};

    /*******************
     * All information for the oil change
     *******************/
    private int [] OIL_CHANGE_IMAGES = {
        R.drawable.oil_change_tools,
        R.drawable.hood_release,
        R.drawable.open_hood,
        R.drawable.locate_oil_cap,
        R.drawable.jack_car,
        R.drawable.car_jack_stand,
        R.drawable.remove_plug_let_oil_drain,
        R.drawable.put_new_oil_filter,
        R.drawable.add_new_oil,
        R.drawable.reset_maintenance_light};
    public int [] getOilChangeImages () { return OIL_CHANGE_IMAGES; }

    private String [] OIL_CHANGE_INSTRUCTION = {
            "Gather all necessary tools and equipments",
            "The hood release is often inside the vehicle, somewhere near the steering column or on the floor next to the driver’s seat",
            "Locate the exterior latch. You only be able to lift the hood a few inches until you move the exterior lever under the hood to completely unlock it",
            "Locate the oil fill cap. The cap may even say the recommended oil viscosity for your engine (0w20 or 5w20)",
            "Locate the suitable jack points and Position the floor jack under the appropriate spot\n",
            "After raising your car, place the pair of jack stand for extra safety ",
            "Loosen drain plug. Remove the oil drain plug which is located in the bottom of the oil pan. It will take some effort to loosen the drain plug ",
            "Locate the oil filter and Remove it. After that, add some new oil to the new oil filter and install it",
            "Add your new oil. Slowly pour it into the funnel in the oil fill hole",
            "Reset your Maintenance Reminder light (if your vehicle is equipped with one).Use a dry erase marker to write your next oil change reminder mileage and date on the upper left driver's side of the windshield",
    };
    public String [] getOilChangeInstruction() { return OIL_CHANGE_INSTRUCTION;}

    /*******************
     * All information for the Engine Air Filter Replacement
     *******************/
    private int [] ENGINE_AIR_FILTER_IMAGES = {
        R.drawable.engine_air_filter_tools,
        R.drawable.hood_release,
        R.drawable.open_hood,
        R.drawable.locate_engine_air_filter_box,
        R.drawable.remove_engine_air_filter,
        R.drawable.clean_engine_air_filter_box,
        R.drawable.replace_new_engine_air_filter,
        R.drawable.place_engine_filter_box_back};
    public int [] getENGINE_AIR_FILTER_IMAGES () {return ENGINE_AIR_FILTER_IMAGES;}

    private String [] ENGINE_AIR_FILTER_REPLACEMENT_INSTRUCTION = {
            "Gather all necessary tools and equipments",
            "The hood release is often inside the vehicle, somewhere near the steering column or on the floor next to the driver’s seat",
            "Locate the exterior latch. You only be able to lift the hood a few inches until you move the exterior lever under the hood to completely unlock it",
            "Usually, the air filter box housing is placed next to the engine. A filter box is pretty easy to spot because of the giant hose that sticks out of the side.",
            "Remove the cover of the air filter. After the lid is off, to remove the filter, you just need to lift it out of the housing",
            "Connect an air hose to a compressor and then blow out the dust with the compressed air. Alternatively, use a vacuum cleaner and suck up all the dirt.",
            "Once the housing is clean, place the new filter where the old one used to be. You only need to insert it in the housing such that the rubber rim faces upwards. Do ensure that the rubber rim seals the edges",
            "Place the cover of the air filter back on. Tighten the clamps or the screws and double check to make sure that everything is put back firmly together"
    };
    public String [] getENGINE_AIR_FILTER_REPLACEMENT_INSTRUCTION () {return ENGINE_AIR_FILTER_REPLACEMENT_INSTRUCTION;}

    /*******************
     * All information for the Cabin Air Filter Replacement
     *******************/
    private int [] CABIN_AIR_FILTER_REPLACEMENT_IMAGES = {
        R.drawable.carbin_air_filter_tool,
        R.drawable.open_glove_box,
        R.drawable.release_glove_box,
        R.drawable.remove_cabin_air_filter_tray,
        R.drawable.remove_replace_cabin_air_filter,
        R.drawable.put_glove_box_back};
    public int [] getCABIN_AIR_FILTER_REPLACEMENT_IMAGES () {return CABIN_AIR_FILTER_REPLACEMENT_IMAGES;}

    private String [] CABIN_AIR_FILTER_REPLACEMENT_INSTRUCTION = {
            "Gather all necessary tools and equipments",
            "Locate the glove compartment on the passenger side of the vehicle. The glove box will need to be removed in order to access the cabin air filter so take everything out of it first",
            "Remove the glove box. In many vehicles the glove box is held on by one screw, or is simply held on by plastic tabs that can be pushed free. Refer to your vehicle owner's manual to determine the proper method for removing the glove box.",
            "Once the glove box is removed, the cabin air filter cover should be exposed. Remove it by pressing the plastic tabs to release it, and expose the cabin air filter",
            "Remove the cabin air filter, by pulling it straight out, and replace it with your brand new filter.Be sure to line up the air flow arrows on the filter with the arrows on the front of the cover",
            "Once the new cabin filter has been installed, put the plastic cover and glove box back in place"
    };
    public String [] getCABIN_AIR_FILTER_REPLACEMENT_INSTRUCTION () {return CABIN_AIR_FILTER_REPLACEMENT_INSTRUCTION;}

    /*******************
     * All information for the Tire Rotation
     *******************/
    private int [] TIRE_ROTATION_IMAGES = {
        R.drawable.tire_rotation_tool,
        R.drawable.tire_rolation_loosen_lug_nut,
        R.drawable.tire_rotation_raise_car,
        R.drawable.tire_rotation_remove_tires,
        R.drawable.tire_rotation_rotate_tire_pattern,
        R.drawable.tire_rotation_lower_car,
        R.drawable.tire_rotation_tighten_lug_star_pattern
    };
    public int [] getTIRE_ROTATION_IMAGES () {return TIRE_ROTATION_IMAGES;}

    public String [] TIRE_ROTATION_INSTRUCTION = {
            "Gather all necessary tools and equipments",
            "While your car is still on the ground, with the lug wrench, loosen the lug nuts holding the tire to the axle. DO NOT remove the nuts, just loosen them slightly to make removing them easier when the car is in the air",
            "Use your jack to raise each corner of the car and then install the jack stand. Consult your owner's manual to determine the proper placement of the jack",
            "Remove the lug nuts from the first tire you've raised and remove it. Roll the tire to the new location. Keep track of the lug nuts by keeping them close to the axle from which they were removed",
            "Check the rotation pattern of your tires and Rotate the tires in the correct pattern",
            "With your jack, raise each location up off the jack stand until you can safely remove it, then lower the car. Make sure you've tightened each tire on hand-tight before you do this. You should be able to wobble the tire back and forth",
            "Most cars have 4 or 5 lug nuts. When the car is lowered completely, tighten the lug nuts with your lug wrench by tightening one nut, plus a quarter-turn, then the nut directly across from it, then back to the nut next to the first, etc"
    };
    public String [] getTIRE_ROTATION_INSTRUCTION () {return TIRE_ROTATION_INSTRUCTION;}

    /*******************
     * All information for the Windshield Fluid Top Up
     *******************/
    private int [] WINDSHIELD_FLUID_TOP_UP_IMAGES = {
        R.drawable.windshield_washer_top_up_tools,
        R.drawable.hood_release,
        R.drawable.open_hood,
        R.drawable.windshield_washer_top_up_locate_cap,
        R.drawable.windshield_washer_top_up_fill_up,
        R.drawable.windshield_washer_top_up_close_cap };
    public int [] getWINDSHIELD_FLUID_TOP_UP_IMAGES () {return WINDSHIELD_FLUID_TOP_UP_IMAGES;}

    private String [] WINDSHIELD_FLUID_TOP_UP_INSTRUCTION = {
            "Gather all necessary tools and equipments",
            "The hood release is often inside the vehicle, somewhere near the steering column or on the floor next to the driver’s seat",
            "Locate the exterior latch. You only be able to lift the hood a few inches until you move the exterior lever under the hood to completely unlock it",
            "Find the windshield wiper tank cap and remove it. Set the cap in a safe place or, if it is attached to the tank via a leash, move it aside so the opening is not blocked",
            "Fill the wiper fluid tank up to the fill line. Do not fill the tank past the fill line",
            "Put the windshield wiper tank cap back and close it"
    };
    public String [] getWINDSHIELD_FLUID_TOP_UP_INSTRUCTION () {return WINDSHIELD_FLUID_TOP_UP_INSTRUCTION;}
}
