package lolstormSDK;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameConstants {
    public static final Map<String, String> GAME_TYPES;
    public static final Map<String, String> CHAMPION_KEY_MAP;
    public static final Map<String, String> CHAMPION_NAME_MAP;
    public static final Map<Integer, String> SPELL_ID_MAP;

    public final static String DIV_1 = "I";
    public final static String DIV_2 = "II";
    public final static String DIV_3 = "III";
    public final static String DIV_4 = "IV";
    public final static String DIV_5 = "V";
    private final static String DIV_UNKNOWN = "";

    public static final String divisionFromInt(int division) {
        switch (division) {
            case 1:
                return DIV_1;
            case 2:
                return DIV_2;
            case 3:
                return DIV_3;
            case 4:
                return DIV_4;
            case 5:
                return DIV_5;
            default:
                return DIV_UNKNOWN;
        }
    }

    static {
        Map<String, String> tempMap = new HashMap<>();
        tempMap.put("NONE", "Custom");
        tempMap.put("NORMAL", "Normal");
        tempMap.put("NORMAL_3x3", "Normal 3x3");
        tempMap.put("ODIN_UNRANKED", "Dominion");
        tempMap.put("ARAM_UNRANKED_5x5", "ARAM");
        tempMap.put("BOT", "Bot");
        tempMap.put("BOT_3x3", "Bot 3x3");
        tempMap.put("RANKED_SOLO_5x5", "Ranked Solo 5x5");
        tempMap.put("RANKED_TEAM_3x3", "Ranked Team 3x3");
        tempMap.put("RANKED_TEAM_5x5", "Ranked Team 5x5");
        tempMap.put("ONEFORALL_5x5", "One For All");
        tempMap.put("FIRSTBLOOD_1x1", "Showdown 1x1");
        tempMap.put("FIRSTBLOOD_2x2", "Showdown 2x2");
        tempMap.put("SR_6x6", "Hexakill");
        tempMap.put("CAP_5x5", "Team Builder");
        tempMap.put("URF", "URF");
        tempMap.put("URF_BOT", "URF Bot");
        tempMap.put("NIGHTMARE_BOT", "Doom Bots");
        tempMap.put("ASCENSION_5x5", "Ascension");
        tempMap.put("HEXAKILL", "Hexakill");
        tempMap.put("KING_PORO", "King Poro");
        tempMap.put("COUNTER_PICK", "Nemesis");

        GAME_TYPES = Collections.unmodifiableMap(tempMap);
    }

    static {
        Map<String, String> championMap = new HashMap<>();

        championMap.put("266","Aatrox");
        championMap.put("103","Ahri");
        championMap.put("84","Akali");
        championMap.put("12","Alistar");
        championMap.put("32","Amumu");
        championMap.put("34","Anivia");
        championMap.put("1","Annie");
        championMap.put("22","Ashe");
        championMap.put("268","Azir");
        championMap.put("432", "Bard");
        championMap.put("53","Blitzcrank");
        championMap.put("63","Brand");
        championMap.put("201","Braum");
        championMap.put("51","Caitlyn");
        championMap.put("69","Cassiopeia");
        championMap.put("31","Chogath");
        championMap.put("42","Corki");
        championMap.put("122","Darius");
        championMap.put("131","Diana");
        championMap.put("36","DrMundo");
        championMap.put("119","Draven");
        championMap.put("60","Elise");
        championMap.put("28","Evelynn");
        championMap.put("81","Ezreal");
        championMap.put("245","Ekko");
        championMap.put("9","FiddleSticks");
        championMap.put("114","Fiora");
        championMap.put("105","Fizz");
        championMap.put("3","Galio");
        championMap.put("41","Gangplank");
        championMap.put("86","Garen");
        championMap.put("150","Gnar");
        championMap.put("79","Gragas");
        championMap.put("104","Graves");
        championMap.put("120","Hecarim");
        championMap.put("74","Heimerdinger");
        championMap.put("39","Irelia");
        championMap.put("40","Janna");
        championMap.put("59","JarvanIV");
        championMap.put("24","Jax");
        championMap.put("126","Jayce");
        championMap.put("222","Jinx");
        championMap.put("429","Kalista");
        championMap.put("43","Karma");
        championMap.put("30","Karthus");
        championMap.put("38","Kassadin");
        championMap.put("55","Katarina");
        championMap.put("10","Kayle");
        championMap.put("85","Kennen");
        championMap.put("121","Khazix");
        championMap.put("96","KogMaw");
        championMap.put("7","Leblanc");
        championMap.put("64","LeeSin");
        championMap.put("89","Leona");
        championMap.put("127","Lissandra");
        championMap.put("236","Lucian");
        championMap.put("117","Lulu");
        championMap.put("99","Lux");
        championMap.put("54","Malphite");
        championMap.put("90","Malzahar");
        championMap.put("57","Maokai");
        championMap.put("11","MasterYi");
        championMap.put("21","MissFortune");
        championMap.put("62","MonkeyKing");
        championMap.put("82","Mordekaiser");
        championMap.put("25","Morgana");
        championMap.put("267","Nami");
        championMap.put("75","Nasus");
        championMap.put("111","Nautilus");
        championMap.put("76","Nidalee");
        championMap.put("56","Nocturne");
        championMap.put("20","Nunu");
        championMap.put("2","Olaf");
        championMap.put("61","Orianna");
        championMap.put("80","Pantheon");
        championMap.put("78","Poppy");
        championMap.put("133","Quinn");
        championMap.put("33","Rammus");
        championMap.put("421","RekSai");
        championMap.put("58","Renekton");
        championMap.put("107","Rengar");
        championMap.put("92","Riven");
        championMap.put("68","Rumble");
        championMap.put("13","Ryze");
        championMap.put("113","Sejuani");
        championMap.put("35","Shaco");
        championMap.put("98","Shen");
        championMap.put("102","Shyvana");
        championMap.put("27","Singed");
        championMap.put("14","Sion");
        championMap.put("15","Sivir");
        championMap.put("72","Skarner");
        championMap.put("37","Sona");
        championMap.put("16","Soraka");
        championMap.put("50","Swain");
        championMap.put("134","Syndra");
        championMap.put("91","Talon");
        championMap.put("44","Taric");
        championMap.put("17","Teemo");
        championMap.put("412","Thresh");
        championMap.put("18","Tristana");
        championMap.put("48","Trundle");
        championMap.put("23","Tryndamere");
        championMap.put("4","TwistedFate");
        championMap.put("29","Twitch");
        championMap.put("77","Udyr");
        championMap.put("6","Urgot");
        championMap.put("110","Varus");
        championMap.put("67","Vayne");
        championMap.put("45","Veigar");
        championMap.put("161","Velkoz");
        championMap.put("254","Vi");
        championMap.put("112","Viktor");
        championMap.put("8","Vladimir");
        championMap.put("106","Volibear");
        championMap.put("19","Warwick");
        championMap.put("101","Xerath");
        championMap.put("5","XinZhao");
        championMap.put("157","Yasuo");
        championMap.put("83","Yorick");
        championMap.put("154","Zac");
        championMap.put("238","Zed");
        championMap.put("115","Ziggs");
        championMap.put("26","Zilean");
        championMap.put("143","Zyra");

        CHAMPION_KEY_MAP = Collections.unmodifiableMap(championMap);
    }

    static{
        Map<String, String> championMap = new HashMap<>();

        championMap.put("266","Aatrox");
        championMap.put("103","Ahri");
        championMap.put("84","Akali");
        championMap.put("12","Alistar");
        championMap.put("32","Amumu");
        championMap.put("34","Anivia");
        championMap.put("1","Annie");
        championMap.put("22","Ashe");
        championMap.put("268","Azir");
        championMap.put("53","Blitzcrank");
        championMap.put("63","Brand");
        championMap.put("201","Braum");
        championMap.put("51","Caitlyn");
        championMap.put("69","Cassiopeia");
        championMap.put("31","Cho'Gath");
        championMap.put("42","Corki");
        championMap.put("122","Darius");
        championMap.put("131","Diana");
        championMap.put("36","Dr. Mundo");
        championMap.put("119","Draven");
        championMap.put("245","Ekko");
        championMap.put("60","Elise");
        championMap.put("28","Evelynn");
        championMap.put("81","Ezreal");
        championMap.put("9","Fiddlesticks");
        championMap.put("114","Fiora");
        championMap.put("105","Fizz");
        championMap.put("3","Galio");
        championMap.put("41","Gangplank");
        championMap.put("86","Garen");
        championMap.put("150","Gnar");
        championMap.put("79","Gragas");
        championMap.put("104","Graves");
        championMap.put("120","Hecarim");
        championMap.put("74","Heimerdinger");
        championMap.put("39","Irelia");
        championMap.put("40","Janna");
        championMap.put("59","Jarvan IV");
        championMap.put("24","Jax");
        championMap.put("126","Jayce");
        championMap.put("222","Jinx");
        championMap.put("429","Kalista");
        championMap.put("43","Karma");
        championMap.put("30","Karthus");
        championMap.put("38","Kassadin");
        championMap.put("55","Katarina");
        championMap.put("10","Kayle");
        championMap.put("85","Kennen");
        championMap.put("121","Kha'Zix");
        championMap.put("96","Kog'Maw");
        championMap.put("7","LeBlanc");
        championMap.put("64","Lee Sin");
        championMap.put("89","Leona");
        championMap.put("127","Lissandra");
        championMap.put("236","Lucian");
        championMap.put("117","Lulu");
        championMap.put("99","Lux");
        championMap.put("54","Malphite");
        championMap.put("90","Malzahar");
        championMap.put("57","Maokai");
        championMap.put("11","Master Yi");
        championMap.put("21","Miss Fortune");
        championMap.put("62","Wukong");
        championMap.put("82","Mordekaiser");
        championMap.put("25","Morgana");
        championMap.put("267","Nami");
        championMap.put("75","Nasus");
        championMap.put("111","Nautilus");
        championMap.put("76","Nidalee");
        championMap.put("56","Nocturne");
        championMap.put("20","Nunu");
        championMap.put("2","Olaf");
        championMap.put("61","Orianna");
        championMap.put("80","Pantheon");
        championMap.put("78","Poppy");
        championMap.put("133","Quinn");
        championMap.put("33","Rammus");
        championMap.put("421","Rek'Sai");
        championMap.put("58","Renekton");
        championMap.put("107","Rengar");
        championMap.put("92","Riven");
        championMap.put("68","Rumble");
        championMap.put("13","Ryze");
        championMap.put("113","Sejuani");
        championMap.put("35","Shaco");
        championMap.put("98","Shen");
        championMap.put("102","Shyvana");
        championMap.put("27","Singed");
        championMap.put("14","Sion");
        championMap.put("15","Sivir");
        championMap.put("72","Skarner");
        championMap.put("37","Sona");
        championMap.put("16","Soraka");
        championMap.put("50","Swain");
        championMap.put("134","Syndra");
        championMap.put("91","Talon");
        championMap.put("44","Taric");
        championMap.put("17","Teemo");
        championMap.put("412","Thresh");
        championMap.put("18","Tristana");
        championMap.put("48","Trundle");
        championMap.put("23","Tryndamere");
        championMap.put("4","Twisted Fate");
        championMap.put("29","Twitch");
        championMap.put("77","Udyr");
        championMap.put("6","Urgot");
        championMap.put("110","Varus");
        championMap.put("67","Vayne");
        championMap.put("45","Veigar");
        championMap.put("161","Vel'Koz");
        championMap.put("254","Vi");
        championMap.put("112","Viktor");
        championMap.put("8","Vladimir");
        championMap.put("106","Volibear");
        championMap.put("19","Warwick");
        championMap.put("101","Xerath");
        championMap.put("5","Xin Zhao");
        championMap.put("157","Yasuo");
        championMap.put("83","Yorick");
        championMap.put("154","Zac");
        championMap.put("238","Zed");
        championMap.put("115","Ziggs");
        championMap.put("26","Zilean");
        championMap.put("143","Zyra");

        CHAMPION_NAME_MAP = Collections.unmodifiableMap(championMap);
    }

    static {
        Map<Integer, String> spellIDMap = new HashMap<>();

        spellIDMap.put(1, "summonerboost");
        spellIDMap.put(12, "summonerteleport");
        spellIDMap.put(30, "summonerpororecall");
        spellIDMap.put(14, "summonerdot");
        spellIDMap.put(6, "summonerhaste");
        spellIDMap.put(32, "summonersnowball");
        spellIDMap.put(7, "summonerheal");
        spellIDMap.put(11, "summonersmite");
        spellIDMap.put(3, "summonerexhaust");
        spellIDMap.put(31, "summonerporothrow");
        spellIDMap.put(13, "summonermana");
        spellIDMap.put(2, "summonerclairvoyance");
        spellIDMap.put(21, "summonerbarrier");
        spellIDMap.put(4, "summonerflash");
        spellIDMap.put(17, "summonerOdinGarrison");

        SPELL_ID_MAP = Collections.unmodifiableMap(spellIDMap);
    }
}
