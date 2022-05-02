package com.arup.datonal.validator;

import java.util.Locale;

public class PinOrPassValidator {
    private static final String[] commonPasswords = {"password", "qwerty", "1234", "dragon", "123123", "baseball", "abc123", "football", "monkey", "letmein", "696969", "shadow", "master", "qwertyuiop", "123321", "mustang", "michael", "pussy", "superman", "1qaz2wsx", "fuckyou", "121212", "qazwsx", "123qwe", "killer", "trustno1", "jordan", "jennifer", "zxcvbnm", "asdfgh", "hunter", "buster", "soccer", "harley", "batman", "andrew", "tigger", "sunshine", "iloveyou", "fuckme", "2000", "charlie", "robert", "thomas", "hockey", "ranger", "daniel", "starwars", "klaster", "112233", "george", "asshole", "computer", "michelle", "jessica", "pepper", "1111", "zxcvbn", "131313", "freedom", "pass", "fuck", "maggie", "159753", "ginger", "princess", "joshua", "cheese", "amanda", "summer", "love", "ashley", "6969", "nicole", "chelsea", "biteme", "matthew", "access", "yankees", "dallas", "austin", "thunder", "taylor", "matrix", "william", "corvette", "hello", "martin", "heather", "secret", "fucker", "merlin", "diamond", "1234qwer", "gfhjkm", "hammer", "silver", "anthony", "justin", "test", "bailey", "q1w2e3r4t5", "patrick", "internet", "scooter", "orange", "11111", "golfer", "cookie", "richard", "samantha", "bigdog", "guitar", "jackson", "whatever", "mickey", "chicken", "sparky", "snoopy", "maverick", "phoenix", "camaro", "sexy", "peanut", "morgan", "welcome", "falcon", "cowboy", "ferrari", "samsung", "andrea", "smokey", "steelers", "joseph", "mercedes", "dakota", "arsenal", "eagles", "melissa", "boomer", "booboo", "spider", "nascar", "monster", "tigers", "yellow", "123123123", "gateway", "marina", "diablo", "bulldog", "qwer1234", "compaq", "purple", "hardcore", "banana", "junior", "hannah", "123654", "porsche", "lakers", "iceman", "money", "cowboys", "london", "tennis", "ncc1701", "coffee", "scooby", "0000", "miller", "boston", "q1w2e3r4", "fuckoff", "brandon", "yamaha", "chester", "mother", "forever", "johnny", "edward", "oliver", "redsox", "player", "nikita", "knight", "fender", "barney", "midnight", "please", "brandy", "chicago", "badboy", "iwantu", "slayer", "rangers", "charles", "angel", "flower", "bigdaddy", "rabbit", "wizard", "bigdick", "jasper", "enter", "rachel", "chris", "steven", "winner", "adidas", "victoria", "natasha", "1q2w3e4r", "jasmine", "winter", "prince", "panties", "marine", "ghbdtn", "fishing", "cocacola", "casper", "james", "232323", "raiders", "marlboro", "gandalf", "asdfasdf", "crystal", "12344321", "sexsex", "golden", "blowme", "bigtits", "8675309", "panther", "lauren", "angela", "bitch", "spanky", "thx1138", "angels", "madison", "winston", "shannon", "mike", "toyota", "blowjob", "jordan23", "canada", "sophie", "Password", "apples", "dick", "tiger", "razz", "123abc", "pokemon", "qazxsw", "55555", "qwaszx", "muffin", "johnson", "murphy", "cooper", "jonathan", "liverpoo", "david", "danielle", "159357", "jackie", "1990", "789456", "turtle", "horny", "abcd1234", "scorpion", "qazwsxedc", "101010", "butter", "carlos", "password1", "dennis", "slipknot", "qwerty123", "booger", "asdf", "1991", "black", "startrek", "12341234", "cameron", "newyork", "rainbow", "nathan", "john", "1992", "rocket", "viking", "redskins", "butthead", "asdfghjkl", "1212", "sierra", "peaches", "gemini", "doctor", "wilson", "sandra", "helpme", "qwertyui", "victor", "florida", "dolphin", "pookie", "captain", "tucker", "blue", "liverpool", "theman", "bandit", "dolphins", "maddog", "packers", "jaguar", "lovers", "nicholas", "united", "tiffany", "maxwell", "nirvana", "jeremy", "suckit", "stupid", "porn", "monica", "elephant", "giants", "jackass", "hotdog", "rosebud", "success", "debbie", "mountain", "warrior", "1q2w3e4r5t", "q1w2e3", "albert", "metallic", "lucky", "azerty", "7777", "shithead", "alex", "bond007", "alexis", "samson", "5150", "willie", "scorpio", "bonnie", "gators", "benjamin", "voodoo", "driver", "dexter", "2112", "jason", "calvin", "freddy", "212121", "creative", "12345a", "sydney", "rush2112", "1989", "asdfghjk", "red123", "bubba", "4815162342", "passw0rd", "trouble", "gunner", "happy", "fucking", "gordon", "legend", "jessie", "stella", "qwert", "eminem", "arthur", "apple", "nissan", "bullshit", "bear", "america", "1qazxsw2", "nothing", "parker", "4444", "rebecca", "qweqwe", "garfield", "01012011", "beavis", "69696969", "jack", "asdasd", "december", "2222", "102030", "252525", "11223344", "magic", "apollo", "skippy", "315475", "girls", "kitten", "golf", "copper", "braves", "shelby", "godzilla", "beaver", "fred", "tomcat", "august", "buddy", "airborne", "1993", "1988", "lifehack", "brooklyn", "animal", "platinum", "phantom", "online", "xavier", "darkness", "blink182", "power", "fish", "green", "789456123", "voyager", "police", "travis", "12qwaszx", "heaven", "snowball", "lover", "00000", "pakistan", "007007", "walter", "playboy", "blazer", "cricket", "sniper", "hooters", "donkey", "willow", "loveme", "saturn", "therock", "redwings", "bigboy", "pumpkin", "trinity", "williams", "tits", "nintendo", "digital", "destiny", "topgun", "runner", "marvin", "guinness", "chance", "bubbles", "testing", "fire", "november", "minecraft", "asdf1234", "lasvegas", "sergey", "broncos", "cartman", "private", "celtic", "birdie", "little", "cassie", "babygirl", "donald", "beatles", "1313", "dickhead", "family", "12121212", "school", "louise", "gabriel", "eclipse", "fluffy", "147258369", "lol123", "explorer", "beer", "nelson", "flyers", "spencer", "scott", "lovely", "gibson", "doggie", "cherry", "andrey", "snickers", "buffalo", "pantera", "metallica", "member", "carter", "qwertyu", "peter", "alexande", "steve", "bronco", "paradise", "goober", "5555", "samuel", "montana", "mexico", "dreams", "michigan", "cock", "carolina", "yankee", "friends", "magnum", "surfer", "poopoo", "maximus", "genius", "cool", "vampire", "lacrosse", "asd123", "aaaa", "christin", "kimberly", "speedy", "sharon", "carmen", "111222", "kristina", "sammy", "racing", "ou812", "sabrina", "horses", "qwerty1", "pimpin", "baby", "stalker", "enigma", "147147", "star", "poohbear", "boobies", "147258", "simple", "bollocks", "12345q", "marcus", "brian", "1987", "qweasdzxc", "drowssap", "hahaha", "caroline", "barbara", "dave", "viper", "drummer", "action", "einstein", "bitches", "genesis", "hello1", "scotty", "friend", "forest", "010203", "hotrod", "google", "vanessa", "spitfire", "badger", "maryjane", "friday", "alaska", "1232323q", "tester", "jester", "jake", "champion", "billy", "147852", "rock", "hawaii", "badass", "chevy", "420420", "walker", "stephen", "eagle1", "bill", "1986", "october", "gregory", "svetlana", "pamela", "1984", "music", "shorty", "westside", "stanley", "diesel", "courtney", "242424", "kevin", "porno", "hitman", "boobs", "mark", "12345qwert", "reddog", "frank", "qwe123", "popcorn", "patricia", "1969", "teresa", "mozart", "buddha", "anderson", "paul", "melanie", "security", "lucky1", "lizard", "denise", "3333", "a12345", "123789", "ruslan", "stargate", "simpsons", "scarface", "eagle", "thumper", "olivia", "naruto", "1234554321", "general", "cherokee", "vincent", "Usuckballz1", "spooky", "qweasd", "cumshot", "free", "frankie", "douglas", "death", "1980", "loveyou", "kitty", "kelly", "veronica", "suzuki", "semperfi", "penguin", "mercury", "liberty", "spirit", "scotland", "natalie", "marley", "vikings", "system", "sucker", "king", "allison", "marshall", "1979", "098765", "qwerty12", "hummer", "adrian", "1985", "vfhbyf", "sandman", "rocky", "leslie", "antonio", "4321", "softball", "passion", "mnbvcxz", "bastard", "passport", "horney", "rascal", "howard", "franklin", "bigred", "assman", "alexander", "homer", "redrum", "jupiter", "claudia", "141414", "zaq12wsx", "shit", "patches", "nigger", "cunt", "raider", "infinity", "andre", "54321", "galore", "college", "russia", "kawasaki", "bishop", "vladimir", "money1", "freeuser", "wildcats", "francis", "disney", "budlight", "brittany", "1994", "sweet", "oksana", "honda", "domino", "bulldogs", "brutus", "swordfis", "norman", "monday", "jimmy", "ironman", "ford", "fantasy", "9999", "PASSWORD", "hentai", "duncan", "cougar", "1977", "jeffrey", "house", "dancer", "brooke", "timothy", "super", "marines", "justice", "digger", "connor", "patriots", "karina", "202020", "molly", "everton", "tinker", "alicia", "rasdzv3", "poop", "pearljam", "stinky", "naughty", "colorado", "123123a", "water", "test123", "ncc1701d", "motorola", "ireland", "asdfg", "slut", "matt", "houston", "boogie", "zombie", "accord", "vision", "bradley", "reggie", "kermit", "froggy", "ducati", "avalon", "6666", "9379992", "sarah", "saints", "logitech", "chopper", "852456", "simpson", "madonna", "juventus", "claire", "159951", "zachary", "yfnfif", "wolverin", "warcraft", "hello123", "extreme", "penis", "peekaboo", "fireman", "eugene", "brenda", "123654789", "russell", "panthers", "georgia", "smith", "skyline", "jesus", "elizabet", "spiderma", "smooth", "pirate", "empire", "bullet", "8888", "virginia", "valentin", "psycho", "predator", "arizona", "134679", "mitchell", "alyssa", "vegeta", "titanic", "christ", "goblue", "fylhtq", "wolf", "kirill", "indian", "hiphop", "baxter", "awesome", "people", "danger", "roland", "mookie", "741852963", "dreamer", "bambam", "arnold", "1981", "skipper", "serega", "rolltide", "elvis", "changeme", "simon", "1q2w3e", "lovelove", "fktrcfylh", "denver", "tommy", "mine", "loverboy", "hobbes", "happy1", "alison", "nemesis", "chevelle", "cardinal", "burton", "wanker", "picard", "151515", "tweety", "michael1", "147852369", "12312", "xxxx", "windows", "turkey", "1974", "vfrcbv", "sublime", "1975", "galina", "bobby", "newport", "manutd", "daddy", "american", "alexandr", "1966", "victory", "rooster", "qqq111", "madmax", "electric", "bigcock", "a1b2c3", "wolfpack", "spring", "phpbb", "lalala", "suckme", "spiderman", "eric", "darkside", "classic", "raptor", "hendrix", "1982", "wombat", "avatar", "alpha", "zxc123", "crazy", "hard", "england", "brazil", "1978", "01011980", "wildcat", "polina", "freepass"};
    public static String specialCharacter;
    private static final int maxValidateCommonWordSize = 6;

    private static boolean NextWord(String str) {
        int StringLength = str.length();
        int currLength = 1;
        char current = str.charAt(0);
        char next = str.charAt(1);
        int length = 0;
        for(int i = 2 ; i <= StringLength ; i++) {
            if (current +1 != next) {
                length = Math.max(currLength, length);
                currLength = 1;
            } else {
                currLength++;
            }
            current = next;
            if(i != StringLength) {
                next = str.charAt(i);
            }
        }
        length = Math.max(currLength, length);
        return length >= maxValidateCommonWordSize;
    }

    private static boolean PreviousWord(String str) {
        return NextWord(new StringBuilder(str).reverse().toString());
    }

    private static boolean duplicate(String str) {
        int StringLength = str.length();
        int currLength = 1;
        char current = str.charAt(0);
        char next = str.charAt(1);
        int length = 0;
        for(int i = 2 ; i <= StringLength ; i++) {
            if (current != next) {
                length = Math.max(currLength, length);
                currLength = 1;
            } else {
                currLength++;
            }
            current = next;
            if(i != StringLength) {
                next = str.charAt(i);
            }
        }
        length = Math.max(currLength, length);
        return length < maxValidateCommonWordSize;
    }

    private static boolean CheckCapital(String str) {
        for(int i = 0 ; i < str.length() ; i++) {
            char c = str.charAt(i);
            if(c >= 65 && c <= 90) {
                return true;
            }
        }
        return false;
    }

    private static boolean CheckCommon(String str) {
        boolean b = true;
        for(String common : commonPasswords) {
            common = common.toLowerCase(Locale.ROOT);
            str = str.toLowerCase(Locale.ROOT);
            if(str.contains(common)) {
                b = false;
                break;
            }
        }
        return b;
    }

    private static boolean CheckSpecialCharacter(String str) {
        boolean bool = false;
        char[] specialCharacters = specialCharacter.toCharArray();
        for(char c : specialCharacters) {
            if (str.contains(String.valueOf(c))) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    public static boolean CheckNumbers(String strNum) {
        try {
            long l = Long.parseLong(strNum);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }


    public static String pin(String pass) {
        if(pass.equals("")) {
            return "Please Enter PIN";
        }
        else if(pass.length() < 8) {
            return "Pin Must Be 8 letters";
        }
        else if(NextWord(pass) || PreviousWord(pass)) {
            return "Pin Cannot be Added in Sequence";
        }
        else if(!CheckNumbers(pass)) {
            return "Please Add Number in Pin";
        }
        else {
            return "";
        }
    }

    public static String password(String pass) {
        if(pass.equals("")) {
            return "Please Enter Password";
        }
        else if(pass.length() < 8) {
            return "Password Must Be 8 letters";
        }
        else if(!duplicate(pass)) {
            return "Password Cannot be Duplicate, Or Multiple Duplicate Words cannot be User";
        }
        else if(NextWord(pass) || PreviousWord(pass)) {
            return "Password Cannot not be Added in Sequence";
        }
        else if(!CheckCommon(pass)) {
            return "Your Password is Too Common, Or Please Change Uncommon Words";
        }
        else if(!CheckCapital(pass)) {
            return "Please add Capital Words";
        }
        else if(CheckNumbers(pass)) {
            return "Numbers Cannot be applied in Password";
        }
        else if(!CheckSpecialCharacter(pass)) {
            return "Please Enter Special Character in Password";
        }
        else {
            return "";
        }
    }
}