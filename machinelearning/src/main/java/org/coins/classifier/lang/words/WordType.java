package org.coins.classifier.lang.words;

import org.coins.classifier.lang.counting.Countable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by johannes on 11/28/15.
 */
public enum WordType implements Countable {
    PRONOUNS(PronounGroup.FIRST_SINGULAR, PronounGroup.SECOND_SINGULAR, PronounGroup.THIRD_SINGULAR,
            PronounGroup.FIRST_PLURAL, PronounGroup.SECOND_PLURAL, PronounGroup.THIRD_PLURAL),
    ARTICLES(new WordGroup("the", "a", "an")),
    AUXILIARY_VERBS(new WordGroup("do", "does", "did", "has", "have", "had", "is", "am", "are",  "was", "were",  "be",
            "being", "been", "may", "must", "might", "should", "could", "would", "shall", "will", "can")),
    PREPOSITIONS(new WordGroup("aboard", "about", "above", "across", "after", "against", "along", "amid", "among",
            "anti", "around", "as", "at", "before", "behind", "below", "beneath", "beside", "besides", "between",
            "beyond", "by", "despite", "during", "except",  "for", "from", "in", "inside", "into",   "near", "of",
            "off", "on", "onto",  "over", "past", "per", "since", "than", "through", "to", "toward", "towards", "under",
            "underneath", "until", "up", "upon", "versus", "via", "with", "within", "without")),
    CONJUNCTIONS(new WordGroup("for", "and", "but", "or", "yet", "so", "after", "although", "as", "because",
            "before", "if", "once", "since", "though", "unless", "until", "when", "where", "while")),
    NEGATIONS(new WordGroup("no", "not", "neither", "nor", "never")),
    QUANTIFIERS(new WordGroup("all", "any", "both", "each", "enough", "every", "few", "fewer", "little", "less",
            "lots", "many", "more", "none", "several", "some")),
    HAPPY_SMILIES(new WordGroup(":)", ":-)", "=)", ":^)", ":-]", "=]", ":]", "(:", "(=", "[:", "c:", ":))", ";-]", ";o)",
            ":3", ":9 ", ":D", ":-D", ":'D", "xD", "XD", "8)", "8-)", "¦)", "=8)", "=8^)", "=B)", "=B^)", "~8D", ">:)",
            ">:D", ">:>", ">:[]", "^_^", "^-^", "^.^", "^,^ ", "^^ ", "^^'", "^^°", "^////^", "^o^", "^O^", "^0^",
            "<(^.^)>", "-^_^-", "*(^_^)*", "*0*", "*~*", ":>", ":i", "l:" )),
    SAD_SMILIES(new WordGroup(":(", ":[", "=(", "=[", ":'(", ":,(", ";(", ";_;", "T.T", "T_T", "Q_Q", ":S", ":-/", ":-\\",
            ":/", ":-I", ">:(", ">:O", ">:@", "/o\\", "DX", ":-E3", "x_X", "x.X", "°_°", ">.<", ">,<", "-.-", "-,-", "._.",
            "^_°'", "^,°'", "Oo", "oO", "O.o'", "cO", "ô_o", "Ô_ô", "D:", "D8<", "O_O", "Ò_Ó", "U_U", "v_v", ":<",
            "°_°", "m(", "°^°", "(@_@)", ";.;")),
    OTHER_SMILIES(new WordGroup(";)", ";-)", "(;", "(-;", "^.-", ";D", ";-D", ":P", ":p", "c[=", ":-*", ":*", ";*", "( )",
            ":-x", "C:", ":o", ":-o", ":O", "0:-)", "O:-)", "3:)", "3:D", "-.-zZz", "(o)_(o)", "($)_($)", "^_-", "//.o",
            "^w^", "=^_^=", "x3", "*_*", "#-)", "`*,...ò_Ó...,*´", ":-{}", ":ö", "û_û", "Ö_Ö", ":o)", "cB", "BD", "Y_",
            ":-€", ":3", "o/ \\o", "x'DD", "\\m/", "l/l")),
    BAD_WORDS(new WordGroup("anus", "arse", "arsehole", "ass", "ass-hat", "ass-jabber", "ass-pirate", "assbag", "assbandit",
            "assbanger", "assbite", "assclown", "asscock", "asscracker", "asses", "assface", "assfuck-rear", "assfucker",
            "assgoblin", "asshat", "asshead", "asshole", "asshopper", "assjacker", "asslick", "asslicker", "assmonkey",
            "assmunch", "assmuncher", "assnigger", "asspirate", "assshit", "assshole", "asssucker", "asswad", "asswipe",
            "axwound", "bampot", "bastard", "beaner", "bitch", "bitchass", "bitches", "bitchtits", "bitchy", "blow job",
            "blowjob", "bollocks", "bollox", "boner", "brotherfucker", "bullshit", "bumblefuck", "butt plug", "butt-pirate",
            "buttfucka", "buttfucker", "camel toe", "carpetmuncher", "chesticle", "chinc", "chink", "choad", "chode", "clit",
            "clitface", "clitfuck", "clusterfuck", "cock", "cockass", "cockbite", "cockburger", "cockface", "cockfucker",
            "cockhead", "cockjockey", "cockknoker", "cockmaster", "cockmongler", "cockmongruel", "cockmonkey", "cockmuncher",
            "cocknose", "cocknugget", "cockshit", "cocksmith", "cocksmoke", "cocksmoker", "cocksniffer", "cocksucker",
            "cockwaffle", "coochie", "coochy", "coon", "cooter", "cracker", "cum", "cumbubble", "cumdumpster", "cumguzzler",
            "cumjockey", "cumslut", "cumtart", "cunnie", "cunnilingus", "cunt", "cuntass", "cuntface", "cunthole", "cuntlicker",
            "cuntrag", "cuntslut", "dago", "damn", "deggo", "dick", "dick-sneeze", "dickbag", "dickbeaters", "dickface",
            "dickfuck", "dickfucker", "dickhead", "dickhole", "dickjuice", "dickmilk", "dickmonger", "dicks", "dickslap",
            "dicksucker", "dicksucking", "dicktickler", "dickwad", "dickweasel", "dickweed", "dickwod", "dike", "dildo",
            "dipshit", "doochbag", "dookie", "douche", "douche-fag", "douchebag", "douchewaffle", "dumass", "dumb ass",
            "dumbass", "dumbfuck", "dumbshit", "dumshit", "dyke", "fag", "fagbag", "fagfucker", "faggit", "faggot",
            "faggotcock", "fagtard", "fatass", "fellatio", "feltch", "flamer", "fuck", "fuckass", "fuckbag", "fuckboy",
            "fuckbrain", "fuckbutt", "fuckbutter", "fucked", "fucker", "fuckersucker", "fuckface", "fuckhead", "fuckhole",
            "fuckin", "fucking", "fucknut", "fucknutt", "fuckoff", "fucks", "fuckstick", "fucktard", "fucktart", "fuckup",
            "fuckwad", "fuckwit", "fuckwitt", "fudgepacker", "gay", "gayass", "gaybob", "gaydo", "gayfuck", "gayfuckist",
            "gaylord", "gaytard", "gaywad", "goddamn", "goddamnit", "gooch", "gook", "gringo", "guido", "handjob", "hard on",
            "heeb", "hell", "ho", "hoe", "homo", "homodumbshit", "honkey", "humping", "jackass", "jagoff", "jap", "jerk off",
            "jerkass", "jigaboo", "jizz", "jungle bunny", "kike", "kooch", "kootch", "kraut", "kunt", "lameass", "lardass",
            "lesbian", "lesbo", "lezzie", "mcfagget", "mick", "minge", "mothafucka", "mothafuckin'", "motherfucker",
            "motherfucking", "muff", "muffdiver", "munging", "negro", "nigaboo", "nigga", "nigger", "niggers", "niglet",
            "nut sack", "nutsack", "paki", "panooch", "pecker", "peckerhead", "penis", "penisbanger", "penisfucker",
            "penispuffer", "piss", "pissed", "pissed off", "pissflaps", "polesmoker", "pollock", "poon", "poonani",
            "poonany", "poontang", "porch monkey", "porchmonkey", "prick", "punanny", "punta", "pussies", "pussy",
            "pussylicking", "puto", "queef", "queer", "queerbait", "queerhole", "renob", "rimjob", "ruski", "sand nigger",
            "sandnigger", "schlong", "scrote", "shit", "shitass", "shitbag", "shitbagger", "shitbrains", "shitbreath",
            "shitcanned", "shitcunt", "shitdick", "shitface", "shitfaced", "shithead", "shithole", "shithouse", "shitspitter",
            "shitstain", "shitter", "shittiest", "shitting", "shitty", "shiz", "shiznit", "skank", "skeet", "skullfuck",
            "slut", "slutbag", "smeg", "snatch", "spic", "spick", "splooge", "spook", "suckass", "tard", "testicle",
            "thundercunt", "tit", "titfuck", "tits", "tittyfuck", "twat", "twatlips", "twats", "twatwaffle", "unclefucker",
            "va-j-j", "vag", "vagina", "vajayjay", "vjayjay", "wank", "wankjob", "wetback", "whore", "whorebag", "whoreface",
            "wop" )),
    US_TERMS(new WordGroup("US", "States", "America", "USA", "murica")),
    CLINTON_TERMS(new WordGroup("Hillary", "Clinton")),
    SANDERS_TERMS(new WordGroup("Bernie", "Sanders")),
    TRUMP_TERMS(new WordGroup("Donald", "Trump")),
    RUBIO_TERMS(new WordGroup("Marco", "Rubio")),
    CARSON_TERMS(new WordGroup("Ben", "Carson"));
    private final Set<WordGroup> groupSet;

    WordType(WordGroup ... groups) {
        this.groupSet = new HashSet<>();
        this.groupSet.addAll(Arrays.asList(groups));
        for (WordGroup group : groupSet) {
            group.setParentType(this);
        }
    }

    public Set<WordGroup> getGroupSet() {
        return groupSet;
    }

    @Override
    public String getName() {
        return name();
    }
}
