package com.example.android_project.data;

import java.util.ArrayList;
import java.util.List;

public class Attractions {

    private static List<Attraction> list;

    public Attractions() {

        list = new ArrayList<>();

        // String name, String banner, String image, String details
        Attraction a1 = new Attraction("Dimitrie Gusti National Village Museum",
                "banner_dimitrie_gusti",
                "image_dimitrie_gusti",
                "The whole west side of the Herăstrău Park is given over to a massive outdoor museum, which has more than 270 authentic historic buildings.\n" +
                        "\n" +
                        "Made from stone, wood or cob (clay and straw), these have been carefully dismantled and put back together at this site and come from all corners of the country, from Banat in the West to Moldova in the east and Transylvania in the centre.\n" +
                        "\n" +
                        "Each region has its own style, whether it’s the brightly painted walls from the Danube Delta to the ornately carved portals from Berbeşti in the centre of Romania.\n" +
                        "\n" +
                        "The museum was established by the eminent sociologist Dimitrie Gusti in 1936 and its oldest houses date from the 18th century.\n" +
                        "\n" +
                        "Each house also has a recorded explanation of its style and region, available in English.");

        Attraction a2 = new Attraction("Parliamentary Palace",
                "banner_parliamentary_palace",
                "image_parliamentary_palace",
                "A building of absurd magnitude, the Parliamentary Palace hosts Romania’s Parliament, but also perfectly encapsulates Nicolae Ceaușescu’s megalomania.\n" +
                        "\n" +
                        "At 365,000 square metres, it’s the largest administrative building in the world, intended as a residence, and despite containing reception halls, museums and government offices and the parliament hall, is still almost three quarters empty.\n" +
                        "\n" +
                        "The palace was raised at an enormous cost, in terms of money but also lives, as thousands of people are claimed to have died during its construction in the second half of the 1980s.\n" +
                        "\n" +
                        "The palace was the focal point of Ceaușescu’s pompous redesign of Bucharest following an earthquake in 1977, and had eight subterranean levels, at the bottom of which was a nuclear bunker.\n" +
                        "\n" +
                        "You have to go in to gauge the full, stupefying size of this building, paying a visit to the Museum of the Palace, Museum of Communist Totalitarianism and the National Museum of Contemporary Art.");


        Attraction a4 = new Attraction("Lipscani (Old Town)",
                "banner_lipscani",
                "image_lipscani",
                "A hint of what Bucharest looked like before the Second World War, Lipscani was the place to do business in the city between the Middle Ages and the 1800s.\n" +
                        "\n" +
                        "Some of the street names still recall the guilds that were once based along them: Blănari (Furriers’ Street) or Șelari (Saddlers’ Street). This small pocket was one of the only parts of Bucharest to be retrievable after the Second World War and has been reborn as a stylish pedestrian zone that has boutiques, restaurants and bars in restored buildings.\n" +
                        "\n" +
                        "Look for Pasajul Macca-Vilacrosse on the west side, a fork-shaped shopping passage from 1891 lit by yellow stained glass in its roof.");

        Attraction a5 = new Attraction("Romanian Peasant Museum",
                "banner_peasant_museum",
                "image_peasant_museum",
                "First opened in 1906, the history of this highly-regarded folk museum was interrupted in the 20th century by the Communist regime, but it reopened in 1990 no more than six weeks after Ceaușescu died.\n" +
                        "\n" +
                        "In those intervening years it had been a museum to communism, and you can still view a small exhibition on collectivisation in the basement preserved for posterity.\n" +
                        "\n" +
                        "The remainder is dedicated to the history and culture of the Romanian countryside, summing up 400 years and presenting a jaw-dropping diversity of costume, furniture, religious objects and ceramics.\n" +
                        "\n" +
                        "There’s also an entire wooden house (The House in the House), brought here from Gorj County in the southwest of the country.");

        list.add(a1);
        list.add(a2);
        list.add(a4);
        list.add(a5);

    }

    public static ArrayList<Attraction> getList() {
        return new ArrayList<>(list);
    }

    public static int length() {
        return list.size();
    }

    public static List<String> getBanners() {
        List<String> banners = new ArrayList<>();
        for (Attraction item : list) {
            banners.add(item.getBanner());
        }

        return banners;
    }


}
