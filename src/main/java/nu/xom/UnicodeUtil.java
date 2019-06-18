package nu.xom;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

final class UnicodeUtil {
    private static final int CANONICAL_COMBINING_CLASS_NOT_REORDERED = 0;
    private static final int CANONICAL_COMBINING_CLASS_OVERLAY = 1;
    private static final int CANONICAL_COMBINING_CLASS_NUKTA = 7;
    private static final int CANONICAL_COMBINING_CLASS_KANA_VOICING = 8;
    private static final int CANONICAL_COMBINING_CLASS_VIRAMA = 9;
    private static final int CANONICAL_COMBINING_CLASS_10 = 10;
    private static final int CANONICAL_COMBINING_CLASS_11 = 11;
    private static final int CANONICAL_COMBINING_CLASS_12 = 12;
    private static final int CANONICAL_COMBINING_CLASS_13 = 13;
    private static final int CANONICAL_COMBINING_CLASS_14 = 14;
    private static final int CANONICAL_COMBINING_CLASS_15 = 15;
    private static final int CANONICAL_COMBINING_CLASS_16 = 16;
    private static final int CANONICAL_COMBINING_CLASS_17 = 17;
    private static final int CANONICAL_COMBINING_CLASS_18 = 18;
    private static final int CANONICAL_COMBINING_CLASS_19 = 19;
    private static final int CANONICAL_COMBINING_CLASS_20 = 20;
    private static final int CANONICAL_COMBINING_CLASS_21 = 21;
    private static final int CANONICAL_COMBINING_CLASS_22 = 22;
    private static final int CANONICAL_COMBINING_CLASS_23 = 23;
    private static final int CANONICAL_COMBINING_CLASS_24 = 24;
    private static final int CANONICAL_COMBINING_CLASS_25 = 25;
    private static final int CANONICAL_COMBINING_CLASS_26 = 26;
    private static final int CANONICAL_COMBINING_CLASS_27 = 27;
    private static final int CANONICAL_COMBINING_CLASS_28 = 28;
    private static final int CANONICAL_COMBINING_CLASS_29 = 29;
    private static final int CANONICAL_COMBINING_CLASS_30 = 30;
    private static final int CANONICAL_COMBINING_CLASS_31 = 31;
    private static final int CANONICAL_COMBINING_CLASS_32 = 32;
    private static final int CANONICAL_COMBINING_CLASS_33 = 33;
    private static final int CANONICAL_COMBINING_CLASS_34 = 34;
    private static final int CANONICAL_COMBINING_CLASS_35 = 35;
    private static final int CANONICAL_COMBINING_CLASS_36 = 36;
    private static final int CANONICAL_COMBINING_CLASS_84 = 84;
    private static final int CANONICAL_COMBINING_CLASS_91 = 91;
    private static final int CANONICAL_COMBINING_CLASS_103 = 103;
    private static final int CANONICAL_COMBINING_CLASS_107 = 107;
    private static final int CANONICAL_COMBINING_CLASS_118 = 118;
    private static final int CANONICAL_COMBINING_CLASS_122 = 122;
    private static final int CANONICAL_COMBINING_CLASS_129 = 129;
    private static final int CANONICAL_COMBINING_CLASS_130 = 130;
    private static final int CANONICAL_COMBINING_CLASS_132 = 132;
    private static final int CANONICAL_COMBINING_CLASS_ATTACHED_BELOW = 202;
    private static final int CANONICAL_COMBINING_CLASS_214 = 214;
    private static final int CANONICAL_COMBINING_CLASS_ATTACHED_ABOVE_RIGHT = 216;
    private static final int CANONICAL_COMBINING_CLASS_BELOW_LEFT = 218;
    private static final int CANONICAL_COMBINING_CLASS_BELOW = 220;
    private static final int CANONICAL_COMBINING_CLASS_BELOW_RIGHT = 222;
    private static final int CANONICAL_COMBINING_CLASS_LEFT = 224;
    private static final int CANONICAL_COMBINING_CLASS_RIGHT = 226;
    private static final int CANONICAL_COMBINING_CLASS_ABOVE_LEFT = 228;
    private static final int CANONICAL_COMBINING_CLASS_ABOVE = 230;
    private static final int CANONICAL_COMBINING_CLASS_ABOVE_RIGHT = 232;
    private static final int CANONICAL_COMBINING_CLASS_DOUBLE_BELOW = 233;
    private static final int CANONICAL_COMBINING_CLASS_DOUBLE_ABOVE = 234;
    private static final int CANONICAL_COMBINING_CLASS_IOTA_SUBSCRIPT = 240;
    private static int HI_SURROGATE_START = 55296;
    private static int HI_SURROGATE_END = 56319;
    private static int LOW_SURROGATE_START = 56320;
    private static Map compositions;
    private static final int FIRST_HANGUL_SYLLABLE = 44032;
    private static final int LAST_HANGUL_SYLLABLE = 55203;

    UnicodeUtil() {
    }

    private static boolean isHighSurrogate(char var0) {
        return var0 >= HI_SURROGATE_START && var0 <= HI_SURROGATE_END;
    }

    private static void loadCompositions() {
        //ClassLoader var0 = (class$nu$xom$Verifier == null ? (class$nu$xom$Verifier = class$("nu.Verifier")) : class$nu$xom$Verifier).getClassLoader();
        ClassLoader var0 = nu.xom.Verifier.class.getClassLoader();
        if (var0 != null) {
            loadCompositions(var0);
        }

        if (compositions == null) {
            var0 = Thread.currentThread().getContextClassLoader();
            loadCompositions(var0);
        }

        if (compositions == null) {
            throw new RuntimeException("Broken XOM installation: could not load nu/xom/compositions.dat");
        }
    }

    private static void loadCompositions(ClassLoader var0) {
        DataInputStream var1 = null;

        try {
            InputStream var2 = var0.getResourceAsStream("nu/xom/compositions.dat");
            var1 = new DataInputStream(var2);
            compositions = new HashMap();

            try {
                while(true) {
                    String var3 = var1.readUTF();
                    String var4 = var1.readUTF();
                    compositions.put(var4, var3);
                }
            } catch (EOFException var14) {
                return;
            }
        } catch (IOException var15) {
        } finally {
            try {
                if (var1 != null) {
                    var1.close();
                }
            } catch (IOException var13) {
            }

        }

    }

    private static boolean isStarter(int var0) {
        return getCombiningClass(var0) == 0;
    }

    private static int getCombiningClass(int var0) {
        if (var0 <= 767) {
            return 0;
        } else if (var0 <= 788) {
            return 230;
        } else if (var0 <= 789) {
            return 232;
        } else if (var0 <= 793) {
            return 220;
        } else if (var0 <= 794) {
            return 232;
        } else if (var0 <= 795) {
            return 216;
        } else if (var0 <= 800) {
            return 220;
        } else if (var0 <= 802) {
            return 202;
        } else if (var0 <= 806) {
            return 220;
        } else if (var0 <= 808) {
            return 202;
        } else if (var0 <= 819) {
            return 220;
        } else if (var0 <= 824) {
            return 1;
        } else if (var0 <= 828) {
            return 220;
        } else if (var0 <= 836) {
            return 230;
        } else if (var0 <= 837) {
            return 240;
        } else if (var0 <= 838) {
            return 230;
        } else if (var0 <= 841) {
            return 220;
        } else if (var0 <= 844) {
            return 230;
        } else if (var0 <= 846) {
            return 220;
        } else if (var0 <= 847) {
            return 0;
        } else if (var0 <= 850) {
            return 230;
        } else if (var0 <= 854) {
            return 220;
        } else if (var0 <= 855) {
            return 230;
        } else if (var0 <= 856) {
            return 232;
        } else if (var0 <= 858) {
            return 220;
        } else if (var0 <= 859) {
            return 230;
        } else if (var0 <= 860) {
            return 233;
        } else if (var0 <= 862) {
            return 234;
        } else if (var0 <= 863) {
            return 233;
        } else if (var0 <= 865) {
            return 234;
        } else if (var0 <= 866) {
            return 233;
        } else if (var0 <= 879) {
            return 230;
        } else if (var0 <= 1154) {
            return 0;
        } else if (var0 <= 1159) {
            return 230;
        } else if (var0 <= 1424) {
            return 0;
        } else if (var0 <= 1425) {
            return 220;
        } else if (var0 <= 1429) {
            return 230;
        } else if (var0 <= 1430) {
            return 220;
        } else if (var0 <= 1433) {
            return 230;
        } else if (var0 <= 1434) {
            return 222;
        } else if (var0 <= 1435) {
            return 220;
        } else if (var0 <= 1441) {
            return 230;
        } else if (var0 <= 1447) {
            return 220;
        } else if (var0 <= 1449) {
            return 230;
        } else if (var0 <= 1450) {
            return 220;
        } else if (var0 <= 1452) {
            return 230;
        } else if (var0 <= 1453) {
            return 222;
        } else if (var0 <= 1454) {
            return 228;
        } else if (var0 <= 1455) {
            return 230;
        } else if (var0 <= 1456) {
            return 10;
        } else if (var0 <= 1457) {
            return 11;
        } else if (var0 <= 1458) {
            return 12;
        } else if (var0 <= 1459) {
            return 13;
        } else if (var0 <= 1460) {
            return 14;
        } else if (var0 <= 1461) {
            return 15;
        } else if (var0 <= 1462) {
            return 16;
        } else if (var0 <= 1463) {
            return 17;
        } else if (var0 <= 1464) {
            return 18;
        } else if (var0 <= 1466) {
            return 19;
        } else if (var0 <= 1467) {
            return 20;
        } else if (var0 <= 1468) {
            return 21;
        } else if (var0 <= 1469) {
            return 22;
        } else if (var0 <= 1470) {
            return 0;
        } else if (var0 <= 1471) {
            return 23;
        } else if (var0 <= 1472) {
            return 0;
        } else if (var0 <= 1473) {
            return 24;
        } else if (var0 <= 1474) {
            return 25;
        } else if (var0 <= 1475) {
            return 0;
        } else if (var0 <= 1476) {
            return 230;
        } else if (var0 <= 1477) {
            return 220;
        } else if (var0 <= 1478) {
            return 0;
        } else if (var0 <= 1479) {
            return 18;
        } else if (var0 <= 1551) {
            return 0;
        } else if (var0 <= 1559) {
            return 230;
        } else if (var0 <= 1560) {
            return 30;
        } else if (var0 <= 1561) {
            return 31;
        } else if (var0 <= 1562) {
            return 32;
        } else if (var0 <= 1610) {
            return 0;
        } else if (var0 <= 1611) {
            return 27;
        } else if (var0 <= 1612) {
            return 28;
        } else if (var0 <= 1613) {
            return 29;
        } else if (var0 <= 1614) {
            return 30;
        } else if (var0 <= 1615) {
            return 31;
        } else if (var0 <= 1616) {
            return 32;
        } else if (var0 <= 1617) {
            return 33;
        } else if (var0 <= 1618) {
            return 34;
        } else if (var0 <= 1620) {
            return 230;
        } else if (var0 <= 1622) {
            return 220;
        } else if (var0 <= 1627) {
            return 230;
        } else if (var0 <= 1628) {
            return 220;
        } else if (var0 <= 1630) {
            return 230;
        } else if (var0 <= 1647) {
            return 0;
        } else if (var0 <= 1648) {
            return 35;
        } else if (var0 <= 1749) {
            return 0;
        } else if (var0 <= 1756) {
            return 230;
        } else if (var0 <= 1758) {
            return 0;
        } else if (var0 <= 1762) {
            return 230;
        } else if (var0 <= 1763) {
            return 220;
        } else if (var0 <= 1764) {
            return 230;
        } else if (var0 <= 1766) {
            return 0;
        } else if (var0 <= 1768) {
            return 230;
        } else if (var0 <= 1769) {
            return 0;
        } else if (var0 <= 1770) {
            return 220;
        } else if (var0 <= 1772) {
            return 230;
        } else if (var0 <= 1773) {
            return 220;
        } else if (var0 <= 1808) {
            return 0;
        } else if (var0 <= 1809) {
            return 36;
        } else if (var0 <= 1839) {
            return 0;
        } else if (var0 <= 1840) {
            return 230;
        } else if (var0 <= 1841) {
            return 220;
        } else if (var0 <= 1843) {
            return 230;
        } else if (var0 <= 1844) {
            return 220;
        } else if (var0 <= 1846) {
            return 230;
        } else if (var0 <= 1849) {
            return 220;
        } else if (var0 <= 1850) {
            return 230;
        } else if (var0 <= 1852) {
            return 220;
        } else if (var0 <= 1853) {
            return 230;
        } else if (var0 <= 1854) {
            return 220;
        } else if (var0 <= 1857) {
            return 230;
        } else if (var0 <= 1858) {
            return 220;
        } else if (var0 <= 1859) {
            return 230;
        } else if (var0 <= 1860) {
            return 220;
        } else if (var0 <= 1861) {
            return 230;
        } else if (var0 <= 1862) {
            return 220;
        } else if (var0 <= 1863) {
            return 230;
        } else if (var0 <= 1864) {
            return 220;
        } else if (var0 <= 1866) {
            return 230;
        } else if (var0 <= 2026) {
            return 0;
        } else if (var0 <= 2033) {
            return 230;
        } else if (var0 <= 2034) {
            return 220;
        } else if (var0 <= 2035) {
            return 230;
        } else if (var0 <= 2363) {
            return 0;
        } else if (var0 <= 2364) {
            return 7;
        } else if (var0 <= 2380) {
            return 0;
        } else if (var0 <= 2381) {
            return 9;
        } else if (var0 <= 2384) {
            return 0;
        } else if (var0 <= 2385) {
            return 230;
        } else if (var0 <= 2386) {
            return 220;
        } else if (var0 <= 2388) {
            return 230;
        } else if (var0 <= 2491) {
            return 0;
        } else if (var0 <= 2492) {
            return 7;
        } else if (var0 <= 2508) {
            return 0;
        } else if (var0 <= 2509) {
            return 9;
        } else if (var0 <= 2619) {
            return 0;
        } else if (var0 <= 2620) {
            return 7;
        } else if (var0 <= 2636) {
            return 0;
        } else if (var0 <= 2637) {
            return 9;
        } else if (var0 <= 2747) {
            return 0;
        } else if (var0 <= 2748) {
            return 7;
        } else if (var0 <= 2764) {
            return 0;
        } else if (var0 <= 2765) {
            return 9;
        } else if (var0 <= 2875) {
            return 0;
        } else if (var0 <= 2876) {
            return 7;
        } else if (var0 <= 2892) {
            return 0;
        } else if (var0 <= 2893) {
            return 9;
        } else if (var0 <= 3020) {
            return 0;
        } else if (var0 <= 3021) {
            return 9;
        } else if (var0 <= 3148) {
            return 0;
        } else if (var0 <= 3149) {
            return 9;
        } else if (var0 <= 3156) {
            return 0;
        } else if (var0 <= 3157) {
            return 84;
        } else if (var0 <= 3158) {
            return 91;
        } else if (var0 <= 3259) {
            return 0;
        } else if (var0 <= 3260) {
            return 7;
        } else if (var0 <= 3276) {
            return 0;
        } else if (var0 <= 3277) {
            return 9;
        } else if (var0 <= 3404) {
            return 0;
        } else if (var0 <= 3405) {
            return 9;
        } else if (var0 <= 3529) {
            return 0;
        } else if (var0 <= 3530) {
            return 9;
        } else if (var0 <= 3639) {
            return 0;
        } else if (var0 <= 3641) {
            return 103;
        } else if (var0 <= 3642) {
            return 9;
        } else if (var0 <= 3655) {
            return 0;
        } else if (var0 <= 3659) {
            return 107;
        } else if (var0 <= 3767) {
            return 0;
        } else if (var0 <= 3769) {
            return 118;
        } else if (var0 <= 3783) {
            return 0;
        } else if (var0 <= 3787) {
            return 122;
        } else if (var0 <= 3863) {
            return 0;
        } else if (var0 <= 3865) {
            return 220;
        } else if (var0 <= 3892) {
            return 0;
        } else if (var0 <= 3893) {
            return 220;
        } else if (var0 <= 3894) {
            return 0;
        } else if (var0 <= 3895) {
            return 220;
        } else if (var0 <= 3896) {
            return 0;
        } else if (var0 <= 3897) {
            return 216;
        } else if (var0 <= 3952) {
            return 0;
        } else if (var0 <= 3953) {
            return 129;
        } else if (var0 <= 3954) {
            return 130;
        } else if (var0 <= 3955) {
            return 0;
        } else if (var0 <= 3956) {
            return 132;
        } else if (var0 <= 3961) {
            return 0;
        } else if (var0 <= 3965) {
            return 130;
        } else if (var0 <= 3967) {
            return 0;
        } else if (var0 <= 3968) {
            return 130;
        } else if (var0 <= 3969) {
            return 0;
        } else if (var0 <= 3971) {
            return 230;
        } else if (var0 <= 3972) {
            return 9;
        } else if (var0 <= 3973) {
            return 0;
        } else if (var0 <= 3975) {
            return 230;
        } else if (var0 <= 4037) {
            return 0;
        } else if (var0 <= 4038) {
            return 220;
        } else if (var0 <= 4150) {
            return 0;
        } else if (var0 <= 4151) {
            return 7;
        } else if (var0 <= 4152) {
            return 0;
        } else if (var0 <= 4154) {
            return 9;
        } else if (var0 <= 4236) {
            return 0;
        } else if (var0 <= 4237) {
            return 220;
        } else if (var0 <= 4958) {
            return 0;
        } else if (var0 <= 4959) {
            return 230;
        } else if (var0 <= 5907) {
            return 0;
        } else if (var0 <= 5908) {
            return 9;
        } else if (var0 <= 5939) {
            return 0;
        } else if (var0 <= 5940) {
            return 9;
        } else if (var0 <= 6097) {
            return 0;
        } else if (var0 <= 6098) {
            return 9;
        } else if (var0 <= 6108) {
            return 0;
        } else if (var0 <= 6109) {
            return 230;
        } else if (var0 <= 6312) {
            return 0;
        } else if (var0 <= 6313) {
            return 228;
        } else if (var0 <= 6456) {
            return 0;
        } else if (var0 <= 6457) {
            return 222;
        } else if (var0 <= 6458) {
            return 230;
        } else if (var0 <= 6459) {
            return 220;
        } else if (var0 <= 6678) {
            return 0;
        } else if (var0 <= 6679) {
            return 230;
        } else if (var0 <= 6680) {
            return 220;
        } else if (var0 <= 6963) {
            return 0;
        } else if (var0 <= 6964) {
            return 7;
        } else if (var0 <= 6979) {
            return 0;
        } else if (var0 <= 6980) {
            return 9;
        } else if (var0 <= 7018) {
            return 0;
        } else if (var0 <= 7019) {
            return 230;
        } else if (var0 <= 7020) {
            return 220;
        } else if (var0 <= 7027) {
            return 230;
        } else if (var0 <= 7081) {
            return 0;
        } else if (var0 <= 7082) {
            return 9;
        } else if (var0 <= 7222) {
            return 0;
        } else if (var0 <= 7223) {
            return 7;
        } else if (var0 <= 7615) {
            return 0;
        } else if (var0 <= 7617) {
            return 230;
        } else if (var0 <= 7618) {
            return 220;
        } else if (var0 <= 7625) {
            return 230;
        } else if (var0 <= 7626) {
            return 220;
        } else if (var0 <= 7628) {
            return 230;
        } else if (var0 <= 7629) {
            return 234;
        } else if (var0 <= 7630) {
            return 214;
        } else if (var0 <= 7631) {
            return 220;
        } else if (var0 <= 7632) {
            return 202;
        } else if (var0 <= 7654) {
            return 230;
        } else if (var0 <= 7677) {
            return 0;
        } else if (var0 <= 7678) {
            return 230;
        } else if (var0 <= 7679) {
            return 220;
        } else if (var0 <= 8399) {
            return 0;
        } else if (var0 <= 8401) {
            return 230;
        } else if (var0 <= 8403) {
            return 1;
        } else if (var0 <= 8407) {
            return 230;
        } else if (var0 <= 8410) {
            return 1;
        } else if (var0 <= 8412) {
            return 230;
        } else if (var0 <= 8416) {
            return 0;
        } else if (var0 <= 8417) {
            return 230;
        } else if (var0 <= 8420) {
            return 0;
        } else if (var0 <= 8422) {
            return 1;
        } else if (var0 <= 8423) {
            return 230;
        } else if (var0 <= 8424) {
            return 220;
        } else if (var0 <= 8425) {
            return 230;
        } else if (var0 <= 8427) {
            return 1;
        } else if (var0 <= 8431) {
            return 220;
        } else if (var0 <= 8432) {
            return 230;
        } else if (var0 <= 11743) {
            return 0;
        } else if (var0 <= 11775) {
            return 230;
        } else if (var0 <= 12329) {
            return 0;
        } else if (var0 <= 12330) {
            return 218;
        } else if (var0 <= 12331) {
            return 228;
        } else if (var0 <= 12332) {
            return 232;
        } else if (var0 <= 12333) {
            return 222;
        } else if (var0 <= 12335) {
            return 224;
        } else if (var0 <= 12440) {
            return 0;
        } else if (var0 <= 12442) {
            return 8;
        } else if (var0 <= 42606) {
            return 0;
        } else if (var0 <= 42607) {
            return 230;
        } else if (var0 <= 42619) {
            return 0;
        } else if (var0 <= 42621) {
            return 230;
        } else if (var0 <= 43013) {
            return 0;
        } else if (var0 <= 43014) {
            return 9;
        } else if (var0 <= 43203) {
            return 0;
        } else if (var0 <= 43204) {
            return 9;
        } else if (var0 <= 43306) {
            return 0;
        } else if (var0 <= 43309) {
            return 220;
        } else if (var0 <= 43346) {
            return 0;
        } else if (var0 <= 43347) {
            return 9;
        } else if (var0 <= 64285) {
            return 0;
        } else if (var0 <= 64286) {
            return 26;
        } else if (var0 <= 65055) {
            return 0;
        } else if (var0 <= 65062) {
            return 230;
        } else if (var0 <= 66044) {
            return 0;
        } else if (var0 <= 66045) {
            return 220;
        } else if (var0 <= 68108) {
            return 0;
        } else if (var0 <= 68109) {
            return 220;
        } else if (var0 <= 68110) {
            return 0;
        } else if (var0 <= 68111) {
            return 230;
        } else if (var0 <= 68151) {
            return 0;
        } else if (var0 <= 68152) {
            return 230;
        } else if (var0 <= 68153) {
            return 1;
        } else if (var0 <= 68154) {
            return 220;
        } else if (var0 <= 68158) {
            return 0;
        } else if (var0 <= 68159) {
            return 9;
        } else if (var0 <= 119140) {
            return 0;
        } else if (var0 <= 119142) {
            return 216;
        } else if (var0 <= 119145) {
            return 1;
        } else if (var0 <= 119148) {
            return 0;
        } else if (var0 <= 119149) {
            return 226;
        } else if (var0 <= 119154) {
            return 216;
        } else if (var0 <= 119162) {
            return 0;
        } else if (var0 <= 119170) {
            return 220;
        } else if (var0 <= 119172) {
            return 0;
        } else if (var0 <= 119177) {
            return 230;
        } else if (var0 <= 119179) {
            return 220;
        } else if (var0 <= 119209) {
            return 0;
        } else if (var0 <= 119213) {
            return 230;
        } else if (var0 <= 119361) {
            return 0;
        } else {
            return var0 <= 119364 ? 230 : 0;
        }
    }

    static int combineSurrogatePair(char var0, char var1) {
        int var2 = var0 & 2047;
        int var3 = var1 - '\udc00';
        int var4 = var2 << 10;
        int var5 = var4 | var3;
        int var6 = var5 + 65536;
        return var6;
    }

    private static String makeSurrogatePair(int var0) {
        StringBuffer var1 = new StringBuffer(2);
        if (var0 <= 65535) {
            var1.append((char)var0);
        } else {
            char var2 = (char)('ퟀ' + (var0 >> 10));
            char var3 = (char)('\udc00' + (var0 & 1023));
            var1.append(var2);
            var1.append(var3);
        }

        return var1.toString();
    }

    private static char getHighSurrogate(int var0) {
        char var1 = (char)var0;
        int var2 = var0 >> 16 & 31;
        char var3 = (char)(var2 - 1);
        return (char)(HI_SURROGATE_START | var3 << 6 | var1 >> 10);
    }

    private static char getLowSurrogate(int var0) {
        char var1 = (char)var0;
        return (char)(LOW_SURROGATE_START | var1 & 1023);
    }

    static String normalize(String var0) {
        boolean var1 = false;
        int var2 = var0.length();

        for(int var3 = 0; var3 < var2; ++var3) {
            char var4 = var0.charAt(var3);
            if (var4 > 255) {
                var1 = true;
                break;
            }
        }

        if (var1) {
            var0 = decomposeHangul(var0);
            nu.xom.UnicodeUtil.UnicodeString var7 = new nu.xom.UnicodeUtil.UnicodeString(var0);
            nu.xom.UnicodeUtil.UnicodeString var8 = var7.decompose();
            nu.xom.UnicodeUtil.UnicodeString var5 = var8.compose();
            String var6 = var5.toString();
            var6 = composeHangul(var6);
            return var6;
        } else {
            return var0;
        }
    }

    private static String decomposeHangul(String var0) {
        int var1 = var0.length();
        StringBuffer var2 = new StringBuffer(var0.length());

        for(int var3 = 0; var3 < var1; ++var3) {
            char var4 = var0.charAt(var3);
            if (var4 >= '가' && var4 <= '힣') {
                var2.append(decomposeHangul(var4));
            } else {
                var2.append(var4);
            }
        }

        return var2.toString();
    }

    private static int composeCharacter(int var0, int var1) {
        StringBuffer var2 = new StringBuffer(4);
        if (var0 > 65535) {
            var2.append(getHighSurrogate(var0));
            var2.append(getLowSurrogate(var0));
        } else {
            var2.append((char)var0);
        }

        if (var1 > 65535) {
            var2.append(getHighSurrogate(var1));
            var2.append(getLowSurrogate(var1));
        } else {
            var2.append((char)var1);
        }

        String var3 = (String)compositions.get(var2.toString());
        if (var3 == null) {
            return -1;
        } else {
            return var3.length() == 1 ? var3.charAt(0) : combineSurrogatePair(var3.charAt(0), var3.charAt(1));
        }
    }

    private static String decompose(int var0) {
        if (var0 < 192) {
            return String.valueOf((char)var0);
        } else if (var0 >= 44032 && var0 <= 55203) {
            return decomposeHangul((char)var0);
        } else {
            switch(var0) {
                case 192:
                    return "À";
                case 193:
                    return "Á";
                case 194:
                    return "Â";
                case 195:
                    return "Ã";
                case 196:
                    return "Ä";
                case 197:
                    return "Å";
                case 199:
                    return "Ç";
                case 200:
                    return "È";
                case 201:
                    return "É";
                case 202:
                    return "Ê";
                case 203:
                    return "Ë";
                case 204:
                    return "Ì";
                case 205:
                    return "Í";
                case 206:
                    return "Î";
                case 207:
                    return "Ï";
                case 209:
                    return "Ñ";
                case 210:
                    return "Ò";
                case 211:
                    return "Ó";
                case 212:
                    return "Ô";
                case 213:
                    return "Õ";
                case 214:
                    return "Ö";
                case 217:
                    return "Ù";
                case 218:
                    return "Ú";
                case 219:
                    return "Û";
                case 220:
                    return "Ü";
                case 221:
                    return "Ý";
                case 224:
                    return "à";
                case 225:
                    return "á";
                case 226:
                    return "â";
                case 227:
                    return "ã";
                case 228:
                    return "ä";
                case 229:
                    return "å";
                case 231:
                    return "ç";
                case 232:
                    return "è";
                case 233:
                    return "é";
                case 234:
                    return "ê";
                case 235:
                    return "ë";
                case 236:
                    return "ì";
                case 237:
                    return "í";
                case 238:
                    return "î";
                case 239:
                    return "ï";
                case 241:
                    return "ñ";
                case 242:
                    return "ò";
                case 243:
                    return "ó";
                case 244:
                    return "ô";
                case 245:
                    return "õ";
                case 246:
                    return "ö";
                case 249:
                    return "ù";
                case 250:
                    return "ú";
                case 251:
                    return "û";
                case 252:
                    return "ü";
                case 253:
                    return "ý";
                case 255:
                    return "ÿ";
                case 256:
                    return "Ā";
                case 257:
                    return "ā";
                case 258:
                    return "Ă";
                case 259:
                    return "ă";
                case 260:
                    return "Ą";
                case 261:
                    return "ą";
                case 262:
                    return "Ć";
                case 263:
                    return "ć";
                case 264:
                    return "Ĉ";
                case 265:
                    return "ĉ";
                case 266:
                    return "Ċ";
                case 267:
                    return "ċ";
                case 268:
                    return "Č";
                case 269:
                    return "č";
                case 270:
                    return "Ď";
                case 271:
                    return "ď";
                case 274:
                    return "Ē";
                case 275:
                    return "ē";
                case 276:
                    return "Ĕ";
                case 277:
                    return "ĕ";
                case 278:
                    return "Ė";
                case 279:
                    return "ė";
                case 280:
                    return "Ę";
                case 281:
                    return "ę";
                case 282:
                    return "Ě";
                case 283:
                    return "ě";
                case 284:
                    return "Ĝ";
                case 285:
                    return "ĝ";
                case 286:
                    return "Ğ";
                case 287:
                    return "ğ";
                case 288:
                    return "Ġ";
                case 289:
                    return "ġ";
                case 290:
                    return "Ģ";
                case 291:
                    return "ģ";
                case 292:
                    return "Ĥ";
                case 293:
                    return "ĥ";
                case 296:
                    return "Ĩ";
                case 297:
                    return "ĩ";
                case 298:
                    return "Ī";
                case 299:
                    return "ī";
                case 300:
                    return "Ĭ";
                case 301:
                    return "ĭ";
                case 302:
                    return "Į";
                case 303:
                    return "į";
                case 304:
                    return "İ";
                case 308:
                    return "Ĵ";
                case 309:
                    return "ĵ";
                case 310:
                    return "Ķ";
                case 311:
                    return "ķ";
                case 313:
                    return "Ĺ";
                case 314:
                    return "ĺ";
                case 315:
                    return "Ļ";
                case 316:
                    return "ļ";
                case 317:
                    return "Ľ";
                case 318:
                    return "ľ";
                case 323:
                    return "Ń";
                case 324:
                    return "ń";
                case 325:
                    return "Ņ";
                case 326:
                    return "ņ";
                case 327:
                    return "Ň";
                case 328:
                    return "ň";
                case 332:
                    return "Ō";
                case 333:
                    return "ō";
                case 334:
                    return "Ŏ";
                case 335:
                    return "ŏ";
                case 336:
                    return "Ő";
                case 337:
                    return "ő";
                case 340:
                    return "Ŕ";
                case 341:
                    return "ŕ";
                case 342:
                    return "Ŗ";
                case 343:
                    return "ŗ";
                case 344:
                    return "Ř";
                case 345:
                    return "ř";
                case 346:
                    return "Ś";
                case 347:
                    return "ś";
                case 348:
                    return "Ŝ";
                case 349:
                    return "ŝ";
                case 350:
                    return "Ş";
                case 351:
                    return "ş";
                case 352:
                    return "Š";
                case 353:
                    return "š";
                case 354:
                    return "Ţ";
                case 355:
                    return "ţ";
                case 356:
                    return "Ť";
                case 357:
                    return "ť";
                case 360:
                    return "Ũ";
                case 361:
                    return "ũ";
                case 362:
                    return "Ū";
                case 363:
                    return "ū";
                case 364:
                    return "Ŭ";
                case 365:
                    return "ŭ";
                case 366:
                    return "Ů";
                case 367:
                    return "ů";
                case 368:
                    return "Ű";
                case 369:
                    return "ű";
                case 370:
                    return "Ų";
                case 371:
                    return "ų";
                case 372:
                    return "Ŵ";
                case 373:
                    return "ŵ";
                case 374:
                    return "Ŷ";
                case 375:
                    return "ŷ";
                case 376:
                    return "Ÿ";
                case 377:
                    return "Ź";
                case 378:
                    return "ź";
                case 379:
                    return "Ż";
                case 380:
                    return "ż";
                case 381:
                    return "Ž";
                case 382:
                    return "ž";
                case 416:
                    return "Ơ";
                case 417:
                    return "ơ";
                case 431:
                    return "Ư";
                case 432:
                    return "ư";
                case 461:
                    return "Ǎ";
                case 462:
                    return "ǎ";
                case 463:
                    return "Ǐ";
                case 464:
                    return "ǐ";
                case 465:
                    return "Ǒ";
                case 466:
                    return "ǒ";
                case 467:
                    return "Ǔ";
                case 468:
                    return "ǔ";
                case 469:
                    return "Ǖ";
                case 470:
                    return "ǖ";
                case 471:
                    return "Ǘ";
                case 472:
                    return "ǘ";
                case 473:
                    return "Ǚ";
                case 474:
                    return "ǚ";
                case 475:
                    return "Ǜ";
                case 476:
                    return "ǜ";
                case 478:
                    return "Ǟ";
                case 479:
                    return "ǟ";
                case 480:
                    return "Ǡ";
                case 481:
                    return "ǡ";
                case 482:
                    return "Ǣ";
                case 483:
                    return "ǣ";
                case 486:
                    return "Ǧ";
                case 487:
                    return "ǧ";
                case 488:
                    return "Ǩ";
                case 489:
                    return "ǩ";
                case 490:
                    return "Ǫ";
                case 491:
                    return "ǫ";
                case 492:
                    return "Ǭ";
                case 493:
                    return "ǭ";
                case 494:
                    return "Ǯ";
                case 495:
                    return "ǯ";
                case 496:
                    return "ǰ";
                case 500:
                    return "Ǵ";
                case 501:
                    return "ǵ";
                case 504:
                    return "Ǹ";
                case 505:
                    return "ǹ";
                case 506:
                    return "Ǻ";
                case 507:
                    return "ǻ";
                case 508:
                    return "Ǽ";
                case 509:
                    return "ǽ";
                case 510:
                    return "Ǿ";
                case 511:
                    return "ǿ";
                case 512:
                    return "Ȁ";
                case 513:
                    return "ȁ";
                case 514:
                    return "Ȃ";
                case 515:
                    return "ȃ";
                case 516:
                    return "Ȅ";
                case 517:
                    return "ȅ";
                case 518:
                    return "Ȇ";
                case 519:
                    return "ȇ";
                case 520:
                    return "Ȉ";
                case 521:
                    return "ȉ";
                case 522:
                    return "Ȋ";
                case 523:
                    return "ȋ";
                case 524:
                    return "Ȍ";
                case 525:
                    return "ȍ";
                case 526:
                    return "Ȏ";
                case 527:
                    return "ȏ";
                case 528:
                    return "Ȑ";
                case 529:
                    return "ȑ";
                case 530:
                    return "Ȓ";
                case 531:
                    return "ȓ";
                case 532:
                    return "Ȕ";
                case 533:
                    return "ȕ";
                case 534:
                    return "Ȗ";
                case 535:
                    return "ȗ";
                case 536:
                    return "Ș";
                case 537:
                    return "ș";
                case 538:
                    return "Ț";
                case 539:
                    return "ț";
                case 542:
                    return "Ȟ";
                case 543:
                    return "ȟ";
                case 550:
                    return "Ȧ";
                case 551:
                    return "ȧ";
                case 552:
                    return "Ȩ";
                case 553:
                    return "ȩ";
                case 554:
                    return "Ȫ";
                case 555:
                    return "ȫ";
                case 556:
                    return "Ȭ";
                case 557:
                    return "ȭ";
                case 558:
                    return "Ȯ";
                case 559:
                    return "ȯ";
                case 560:
                    return "Ȱ";
                case 561:
                    return "ȱ";
                case 562:
                    return "Ȳ";
                case 563:
                    return "ȳ";
                case 832:
                    return "̀";
                case 833:
                    return "́";
                case 835:
                    return "̓";
                case 836:
                    return "̈́";
                case 884:
                    return "ʹ";
                case 894:
                    return ";";
                case 901:
                    return "΅";
                case 902:
                    return "Ά";
                case 903:
                    return "·";
                case 904:
                    return "Έ";
                case 905:
                    return "Ή";
                case 906:
                    return "Ί";
                case 908:
                    return "Ό";
                case 910:
                    return "Ύ";
                case 911:
                    return "Ώ";
                case 912:
                    return "ΐ";
                case 938:
                    return "Ϊ";
                case 939:
                    return "Ϋ";
                case 940:
                    return "ά";
                case 941:
                    return "έ";
                case 942:
                    return "ή";
                case 943:
                    return "ί";
                case 944:
                    return "ΰ";
                case 970:
                    return "ϊ";
                case 971:
                    return "ϋ";
                case 972:
                    return "ό";
                case 973:
                    return "ύ";
                case 974:
                    return "ώ";
                case 979:
                    return "ϓ";
                case 980:
                    return "ϔ";
                case 1024:
                    return "Ѐ";
                case 1025:
                    return "Ё";
                case 1027:
                    return "Ѓ";
                case 1031:
                    return "Ї";
                case 1036:
                    return "Ќ";
                case 1037:
                    return "Ѝ";
                case 1038:
                    return "Ў";
                case 1049:
                    return "Й";
                case 1081:
                    return "й";
                case 1104:
                    return "ѐ";
                case 1105:
                    return "ё";
                case 1107:
                    return "ѓ";
                case 1111:
                    return "ї";
                case 1116:
                    return "ќ";
                case 1117:
                    return "ѝ";
                case 1118:
                    return "ў";
                case 1142:
                    return "Ѷ";
                case 1143:
                    return "ѷ";
                case 1217:
                    return "Ӂ";
                case 1218:
                    return "ӂ";
                case 1232:
                    return "Ӑ";
                case 1233:
                    return "ӑ";
                case 1234:
                    return "Ӓ";
                case 1235:
                    return "ӓ";
                case 1238:
                    return "Ӗ";
                case 1239:
                    return "ӗ";
                case 1242:
                    return "Ӛ";
                case 1243:
                    return "ӛ";
                case 1244:
                    return "Ӝ";
                case 1245:
                    return "ӝ";
                case 1246:
                    return "Ӟ";
                case 1247:
                    return "ӟ";
                case 1250:
                    return "Ӣ";
                case 1251:
                    return "ӣ";
                case 1252:
                    return "Ӥ";
                case 1253:
                    return "ӥ";
                case 1254:
                    return "Ӧ";
                case 1255:
                    return "ӧ";
                case 1258:
                    return "Ӫ";
                case 1259:
                    return "ӫ";
                case 1260:
                    return "Ӭ";
                case 1261:
                    return "ӭ";
                case 1262:
                    return "Ӯ";
                case 1263:
                    return "ӯ";
                case 1264:
                    return "Ӱ";
                case 1265:
                    return "ӱ";
                case 1266:
                    return "Ӳ";
                case 1267:
                    return "ӳ";
                case 1268:
                    return "Ӵ";
                case 1269:
                    return "ӵ";
                case 1272:
                    return "Ӹ";
                case 1273:
                    return "ӹ";
                case 1570:
                    return "آ";
                case 1571:
                    return "أ";
                case 1572:
                    return "ؤ";
                case 1573:
                    return "إ";
                case 1574:
                    return "ئ";
                case 1728:
                    return "ۀ";
                case 1730:
                    return "ۂ";
                case 1747:
                    return "ۓ";
                case 2345:
                    return "ऩ";
                case 2353:
                    return "ऱ";
                case 2356:
                    return "ऴ";
                case 2392:
                    return "क़";
                case 2393:
                    return "ख़";
                case 2394:
                    return "ग़";
                case 2395:
                    return "ज़";
                case 2396:
                    return "ड़";
                case 2397:
                    return "ढ़";
                case 2398:
                    return "फ़";
                case 2399:
                    return "य़";
                case 2507:
                    return "ো";
                case 2508:
                    return "ৌ";
                case 2524:
                    return "ড়";
                case 2525:
                    return "ঢ়";
                case 2527:
                    return "য়";
                case 2611:
                    return "ਲ਼";
                case 2614:
                    return "ਸ਼";
                case 2649:
                    return "ਖ਼";
                case 2650:
                    return "ਗ਼";
                case 2651:
                    return "ਜ਼";
                case 2654:
                    return "ਫ਼";
                case 2888:
                    return "ୈ";
                case 2891:
                    return "ୋ";
                case 2892:
                    return "ୌ";
                case 2908:
                    return "ଡ଼";
                case 2909:
                    return "ଢ଼";
                case 2964:
                    return "ஔ";
                case 3018:
                    return "ொ";
                case 3019:
                    return "ோ";
                case 3020:
                    return "ௌ";
                case 3144:
                    return "ై";
                case 3264:
                    return "ೀ";
                case 3271:
                    return "ೇ";
                case 3272:
                    return "ೈ";
                case 3274:
                    return "ೊ";
                case 3275:
                    return "ೋ";
                case 3402:
                    return "ൊ";
                case 3403:
                    return "ോ";
                case 3404:
                    return "ൌ";
                case 3546:
                    return "ේ";
                case 3548:
                    return "ො";
                case 3549:
                    return "ෝ";
                case 3550:
                    return "ෞ";
                case 3907:
                    return "གྷ";
                case 3917:
                    return "ཌྷ";
                case 3922:
                    return "དྷ";
                case 3927:
                    return "བྷ";
                case 3932:
                    return "ཛྷ";
                case 3945:
                    return "ཀྵ";
                case 3955:
                    return "ཱི";
                case 3957:
                    return "ཱུ";
                case 3958:
                    return "ྲྀ";
                case 3960:
                    return "ླྀ";
                case 3969:
                    return "ཱྀ";
                case 3987:
                    return "ྒྷ";
                case 3997:
                    return "ྜྷ";
                case 4002:
                    return "ྡྷ";
                case 4007:
                    return "ྦྷ";
                case 4012:
                    return "ྫྷ";
                case 4025:
                    return "ྐྵ";
                case 4134:
                    return "ဦ";
                case 6918:
                    return "ᬆ";
                case 6920:
                    return "ᬈ";
                case 6922:
                    return "ᬊ";
                case 6924:
                    return "ᬌ";
                case 6926:
                    return "ᬎ";
                case 6930:
                    return "ᬒ";
                case 6971:
                    return "ᬻ";
                case 6973:
                    return "ᬽ";
                case 6976:
                    return "ᭀ";
                case 6977:
                    return "ᭁ";
                case 6979:
                    return "ᭃ";
                case 7680:
                    return "Ḁ";
                case 7681:
                    return "ḁ";
                case 7682:
                    return "Ḃ";
                case 7683:
                    return "ḃ";
                case 7684:
                    return "Ḅ";
                case 7685:
                    return "ḅ";
                case 7686:
                    return "Ḇ";
                case 7687:
                    return "ḇ";
                case 7688:
                    return "Ḉ";
                case 7689:
                    return "ḉ";
                case 7690:
                    return "Ḋ";
                case 7691:
                    return "ḋ";
                case 7692:
                    return "Ḍ";
                case 7693:
                    return "ḍ";
                case 7694:
                    return "Ḏ";
                case 7695:
                    return "ḏ";
                case 7696:
                    return "Ḑ";
                case 7697:
                    return "ḑ";
                case 7698:
                    return "Ḓ";
                case 7699:
                    return "ḓ";
                case 7700:
                    return "Ḕ";
                case 7701:
                    return "ḕ";
                case 7702:
                    return "Ḗ";
                case 7703:
                    return "ḗ";
                case 7704:
                    return "Ḙ";
                case 7705:
                    return "ḙ";
                case 7706:
                    return "Ḛ";
                case 7707:
                    return "ḛ";
                case 7708:
                    return "Ḝ";
                case 7709:
                    return "ḝ";
                case 7710:
                    return "Ḟ";
                case 7711:
                    return "ḟ";
                case 7712:
                    return "Ḡ";
                case 7713:
                    return "ḡ";
                case 7714:
                    return "Ḣ";
                case 7715:
                    return "ḣ";
                case 7716:
                    return "Ḥ";
                case 7717:
                    return "ḥ";
                case 7718:
                    return "Ḧ";
                case 7719:
                    return "ḧ";
                case 7720:
                    return "Ḩ";
                case 7721:
                    return "ḩ";
                case 7722:
                    return "Ḫ";
                case 7723:
                    return "ḫ";
                case 7724:
                    return "Ḭ";
                case 7725:
                    return "ḭ";
                case 7726:
                    return "Ḯ";
                case 7727:
                    return "ḯ";
                case 7728:
                    return "Ḱ";
                case 7729:
                    return "ḱ";
                case 7730:
                    return "Ḳ";
                case 7731:
                    return "ḳ";
                case 7732:
                    return "Ḵ";
                case 7733:
                    return "ḵ";
                case 7734:
                    return "Ḷ";
                case 7735:
                    return "ḷ";
                case 7736:
                    return "Ḹ";
                case 7737:
                    return "ḹ";
                case 7738:
                    return "Ḻ";
                case 7739:
                    return "ḻ";
                case 7740:
                    return "Ḽ";
                case 7741:
                    return "ḽ";
                case 7742:
                    return "Ḿ";
                case 7743:
                    return "ḿ";
                case 7744:
                    return "Ṁ";
                case 7745:
                    return "ṁ";
                case 7746:
                    return "Ṃ";
                case 7747:
                    return "ṃ";
                case 7748:
                    return "Ṅ";
                case 7749:
                    return "ṅ";
                case 7750:
                    return "Ṇ";
                case 7751:
                    return "ṇ";
                case 7752:
                    return "Ṉ";
                case 7753:
                    return "ṉ";
                case 7754:
                    return "Ṋ";
                case 7755:
                    return "ṋ";
                case 7756:
                    return "Ṍ";
                case 7757:
                    return "ṍ";
                case 7758:
                    return "Ṏ";
                case 7759:
                    return "ṏ";
                case 7760:
                    return "Ṑ";
                case 7761:
                    return "ṑ";
                case 7762:
                    return "Ṓ";
                case 7763:
                    return "ṓ";
                case 7764:
                    return "Ṕ";
                case 7765:
                    return "ṕ";
                case 7766:
                    return "Ṗ";
                case 7767:
                    return "ṗ";
                case 7768:
                    return "Ṙ";
                case 7769:
                    return "ṙ";
                case 7770:
                    return "Ṛ";
                case 7771:
                    return "ṛ";
                case 7772:
                    return "Ṝ";
                case 7773:
                    return "ṝ";
                case 7774:
                    return "Ṟ";
                case 7775:
                    return "ṟ";
                case 7776:
                    return "Ṡ";
                case 7777:
                    return "ṡ";
                case 7778:
                    return "Ṣ";
                case 7779:
                    return "ṣ";
                case 7780:
                    return "Ṥ";
                case 7781:
                    return "ṥ";
                case 7782:
                    return "Ṧ";
                case 7783:
                    return "ṧ";
                case 7784:
                    return "Ṩ";
                case 7785:
                    return "ṩ";
                case 7786:
                    return "Ṫ";
                case 7787:
                    return "ṫ";
                case 7788:
                    return "Ṭ";
                case 7789:
                    return "ṭ";
                case 7790:
                    return "Ṯ";
                case 7791:
                    return "ṯ";
                case 7792:
                    return "Ṱ";
                case 7793:
                    return "ṱ";
                case 7794:
                    return "Ṳ";
                case 7795:
                    return "ṳ";
                case 7796:
                    return "Ṵ";
                case 7797:
                    return "ṵ";
                case 7798:
                    return "Ṷ";
                case 7799:
                    return "ṷ";
                case 7800:
                    return "Ṹ";
                case 7801:
                    return "ṹ";
                case 7802:
                    return "Ṻ";
                case 7803:
                    return "ṻ";
                case 7804:
                    return "Ṽ";
                case 7805:
                    return "ṽ";
                case 7806:
                    return "Ṿ";
                case 7807:
                    return "ṿ";
                case 7808:
                    return "Ẁ";
                case 7809:
                    return "ẁ";
                case 7810:
                    return "Ẃ";
                case 7811:
                    return "ẃ";
                case 7812:
                    return "Ẅ";
                case 7813:
                    return "ẅ";
                case 7814:
                    return "Ẇ";
                case 7815:
                    return "ẇ";
                case 7816:
                    return "Ẉ";
                case 7817:
                    return "ẉ";
                case 7818:
                    return "Ẋ";
                case 7819:
                    return "ẋ";
                case 7820:
                    return "Ẍ";
                case 7821:
                    return "ẍ";
                case 7822:
                    return "Ẏ";
                case 7823:
                    return "ẏ";
                case 7824:
                    return "Ẑ";
                case 7825:
                    return "ẑ";
                case 7826:
                    return "Ẓ";
                case 7827:
                    return "ẓ";
                case 7828:
                    return "Ẕ";
                case 7829:
                    return "ẕ";
                case 7830:
                    return "ẖ";
                case 7831:
                    return "ẗ";
                case 7832:
                    return "ẘ";
                case 7833:
                    return "ẙ";
                case 7835:
                    return "ẛ";
                case 7840:
                    return "Ạ";
                case 7841:
                    return "ạ";
                case 7842:
                    return "Ả";
                case 7843:
                    return "ả";
                case 7844:
                    return "Ấ";
                case 7845:
                    return "ấ";
                case 7846:
                    return "Ầ";
                case 7847:
                    return "ầ";
                case 7848:
                    return "Ẩ";
                case 7849:
                    return "ẩ";
                case 7850:
                    return "Ẫ";
                case 7851:
                    return "ẫ";
                case 7852:
                    return "Ậ";
                case 7853:
                    return "ậ";
                case 7854:
                    return "Ắ";
                case 7855:
                    return "ắ";
                case 7856:
                    return "Ằ";
                case 7857:
                    return "ằ";
                case 7858:
                    return "Ẳ";
                case 7859:
                    return "ẳ";
                case 7860:
                    return "Ẵ";
                case 7861:
                    return "ẵ";
                case 7862:
                    return "Ặ";
                case 7863:
                    return "ặ";
                case 7864:
                    return "Ẹ";
                case 7865:
                    return "ẹ";
                case 7866:
                    return "Ẻ";
                case 7867:
                    return "ẻ";
                case 7868:
                    return "Ẽ";
                case 7869:
                    return "ẽ";
                case 7870:
                    return "Ế";
                case 7871:
                    return "ế";
                case 7872:
                    return "Ề";
                case 7873:
                    return "ề";
                case 7874:
                    return "Ể";
                case 7875:
                    return "ể";
                case 7876:
                    return "Ễ";
                case 7877:
                    return "ễ";
                case 7878:
                    return "Ệ";
                case 7879:
                    return "ệ";
                case 7880:
                    return "Ỉ";
                case 7881:
                    return "ỉ";
                case 7882:
                    return "Ị";
                case 7883:
                    return "ị";
                case 7884:
                    return "Ọ";
                case 7885:
                    return "ọ";
                case 7886:
                    return "Ỏ";
                case 7887:
                    return "ỏ";
                case 7888:
                    return "Ố";
                case 7889:
                    return "ố";
                case 7890:
                    return "Ồ";
                case 7891:
                    return "ồ";
                case 7892:
                    return "Ổ";
                case 7893:
                    return "ổ";
                case 7894:
                    return "Ỗ";
                case 7895:
                    return "ỗ";
                case 7896:
                    return "Ộ";
                case 7897:
                    return "ộ";
                case 7898:
                    return "Ớ";
                case 7899:
                    return "ớ";
                case 7900:
                    return "Ờ";
                case 7901:
                    return "ờ";
                case 7902:
                    return "Ở";
                case 7903:
                    return "ở";
                case 7904:
                    return "Ỡ";
                case 7905:
                    return "ỡ";
                case 7906:
                    return "Ợ";
                case 7907:
                    return "ợ";
                case 7908:
                    return "Ụ";
                case 7909:
                    return "ụ";
                case 7910:
                    return "Ủ";
                case 7911:
                    return "ủ";
                case 7912:
                    return "Ứ";
                case 7913:
                    return "ứ";
                case 7914:
                    return "Ừ";
                case 7915:
                    return "ừ";
                case 7916:
                    return "Ử";
                case 7917:
                    return "ử";
                case 7918:
                    return "Ữ";
                case 7919:
                    return "ữ";
                case 7920:
                    return "Ự";
                case 7921:
                    return "ự";
                case 7922:
                    return "Ỳ";
                case 7923:
                    return "ỳ";
                case 7924:
                    return "Ỵ";
                case 7925:
                    return "ỵ";
                case 7926:
                    return "Ỷ";
                case 7927:
                    return "ỷ";
                case 7928:
                    return "Ỹ";
                case 7929:
                    return "ỹ";
                case 7936:
                    return "ἀ";
                case 7937:
                    return "ἁ";
                case 7938:
                    return "ἂ";
                case 7939:
                    return "ἃ";
                case 7940:
                    return "ἄ";
                case 7941:
                    return "ἅ";
                case 7942:
                    return "ἆ";
                case 7943:
                    return "ἇ";
                case 7944:
                    return "Ἀ";
                case 7945:
                    return "Ἁ";
                case 7946:
                    return "Ἂ";
                case 7947:
                    return "Ἃ";
                case 7948:
                    return "Ἄ";
                case 7949:
                    return "Ἅ";
                case 7950:
                    return "Ἆ";
                case 7951:
                    return "Ἇ";
                case 7952:
                    return "ἐ";
                case 7953:
                    return "ἑ";
                case 7954:
                    return "ἒ";
                case 7955:
                    return "ἓ";
                case 7956:
                    return "ἔ";
                case 7957:
                    return "ἕ";
                case 7960:
                    return "Ἐ";
                case 7961:
                    return "Ἑ";
                case 7962:
                    return "Ἒ";
                case 7963:
                    return "Ἓ";
                case 7964:
                    return "Ἔ";
                case 7965:
                    return "Ἕ";
                case 7968:
                    return "ἠ";
                case 7969:
                    return "ἡ";
                case 7970:
                    return "ἢ";
                case 7971:
                    return "ἣ";
                case 7972:
                    return "ἤ";
                case 7973:
                    return "ἥ";
                case 7974:
                    return "ἦ";
                case 7975:
                    return "ἧ";
                case 7976:
                    return "Ἠ";
                case 7977:
                    return "Ἡ";
                case 7978:
                    return "Ἢ";
                case 7979:
                    return "Ἣ";
                case 7980:
                    return "Ἤ";
                case 7981:
                    return "Ἥ";
                case 7982:
                    return "Ἦ";
                case 7983:
                    return "Ἧ";
                case 7984:
                    return "ἰ";
                case 7985:
                    return "ἱ";
                case 7986:
                    return "ἲ";
                case 7987:
                    return "ἳ";
                case 7988:
                    return "ἴ";
                case 7989:
                    return "ἵ";
                case 7990:
                    return "ἶ";
                case 7991:
                    return "ἷ";
                case 7992:
                    return "Ἰ";
                case 7993:
                    return "Ἱ";
                case 7994:
                    return "Ἲ";
                case 7995:
                    return "Ἳ";
                case 7996:
                    return "Ἴ";
                case 7997:
                    return "Ἵ";
                case 7998:
                    return "Ἶ";
                case 7999:
                    return "Ἷ";
                case 8000:
                    return "ὀ";
                case 8001:
                    return "ὁ";
                case 8002:
                    return "ὂ";
                case 8003:
                    return "ὃ";
                case 8004:
                    return "ὄ";
                case 8005:
                    return "ὅ";
                case 8008:
                    return "Ὀ";
                case 8009:
                    return "Ὁ";
                case 8010:
                    return "Ὂ";
                case 8011:
                    return "Ὃ";
                case 8012:
                    return "Ὄ";
                case 8013:
                    return "Ὅ";
                case 8016:
                    return "ὐ";
                case 8017:
                    return "ὑ";
                case 8018:
                    return "ὒ";
                case 8019:
                    return "ὓ";
                case 8020:
                    return "ὔ";
                case 8021:
                    return "ὕ";
                case 8022:
                    return "ὖ";
                case 8023:
                    return "ὗ";
                case 8025:
                    return "Ὑ";
                case 8027:
                    return "Ὓ";
                case 8029:
                    return "Ὕ";
                case 8031:
                    return "Ὗ";
                case 8032:
                    return "ὠ";
                case 8033:
                    return "ὡ";
                case 8034:
                    return "ὢ";
                case 8035:
                    return "ὣ";
                case 8036:
                    return "ὤ";
                case 8037:
                    return "ὥ";
                case 8038:
                    return "ὦ";
                case 8039:
                    return "ὧ";
                case 8040:
                    return "Ὠ";
                case 8041:
                    return "Ὡ";
                case 8042:
                    return "Ὢ";
                case 8043:
                    return "Ὣ";
                case 8044:
                    return "Ὤ";
                case 8045:
                    return "Ὥ";
                case 8046:
                    return "Ὦ";
                case 8047:
                    return "Ὧ";
                case 8048:
                    return "ὰ";
                case 8049:
                    return "ά";
                case 8050:
                    return "ὲ";
                case 8051:
                    return "έ";
                case 8052:
                    return "ὴ";
                case 8053:
                    return "ή";
                case 8054:
                    return "ὶ";
                case 8055:
                    return "ί";
                case 8056:
                    return "ὸ";
                case 8057:
                    return "ό";
                case 8058:
                    return "ὺ";
                case 8059:
                    return "ύ";
                case 8060:
                    return "ὼ";
                case 8061:
                    return "ώ";
                case 8064:
                    return "ᾀ";
                case 8065:
                    return "ᾁ";
                case 8066:
                    return "ᾂ";
                case 8067:
                    return "ᾃ";
                case 8068:
                    return "ᾄ";
                case 8069:
                    return "ᾅ";
                case 8070:
                    return "ᾆ";
                case 8071:
                    return "ᾇ";
                case 8072:
                    return "ᾈ";
                case 8073:
                    return "ᾉ";
                case 8074:
                    return "ᾊ";
                case 8075:
                    return "ᾋ";
                case 8076:
                    return "ᾌ";
                case 8077:
                    return "ᾍ";
                case 8078:
                    return "ᾎ";
                case 8079:
                    return "ᾏ";
                case 8080:
                    return "ᾐ";
                case 8081:
                    return "ᾑ";
                case 8082:
                    return "ᾒ";
                case 8083:
                    return "ᾓ";
                case 8084:
                    return "ᾔ";
                case 8085:
                    return "ᾕ";
                case 8086:
                    return "ᾖ";
                case 8087:
                    return "ᾗ";
                case 8088:
                    return "ᾘ";
                case 8089:
                    return "ᾙ";
                case 8090:
                    return "ᾚ";
                case 8091:
                    return "ᾛ";
                case 8092:
                    return "ᾜ";
                case 8093:
                    return "ᾝ";
                case 8094:
                    return "ᾞ";
                case 8095:
                    return "ᾟ";
                case 8096:
                    return "ᾠ";
                case 8097:
                    return "ᾡ";
                case 8098:
                    return "ᾢ";
                case 8099:
                    return "ᾣ";
                case 8100:
                    return "ᾤ";
                case 8101:
                    return "ᾥ";
                case 8102:
                    return "ᾦ";
                case 8103:
                    return "ᾧ";
                case 8104:
                    return "ᾨ";
                case 8105:
                    return "ᾩ";
                case 8106:
                    return "ᾪ";
                case 8107:
                    return "ᾫ";
                case 8108:
                    return "ᾬ";
                case 8109:
                    return "ᾭ";
                case 8110:
                    return "ᾮ";
                case 8111:
                    return "ᾯ";
                case 8112:
                    return "ᾰ";
                case 8113:
                    return "ᾱ";
                case 8114:
                    return "ᾲ";
                case 8115:
                    return "ᾳ";
                case 8116:
                    return "ᾴ";
                case 8118:
                    return "ᾶ";
                case 8119:
                    return "ᾷ";
                case 8120:
                    return "Ᾰ";
                case 8121:
                    return "Ᾱ";
                case 8122:
                    return "Ὰ";
                case 8123:
                    return "Ά";
                case 8124:
                    return "ᾼ";
                case 8126:
                    return "ι";
                case 8129:
                    return "῁";
                case 8130:
                    return "ῂ";
                case 8131:
                    return "ῃ";
                case 8132:
                    return "ῄ";
                case 8134:
                    return "ῆ";
                case 8135:
                    return "ῇ";
                case 8136:
                    return "Ὲ";
                case 8137:
                    return "Έ";
                case 8138:
                    return "Ὴ";
                case 8139:
                    return "Ή";
                case 8140:
                    return "ῌ";
                case 8141:
                    return "῍";
                case 8142:
                    return "῎";
                case 8143:
                    return "῏";
                case 8144:
                    return "ῐ";
                case 8145:
                    return "ῑ";
                case 8146:
                    return "ῒ";
                case 8147:
                    return "ΐ";
                case 8150:
                    return "ῖ";
                case 8151:
                    return "ῗ";
                case 8152:
                    return "Ῐ";
                case 8153:
                    return "Ῑ";
                case 8154:
                    return "Ὶ";
                case 8155:
                    return "Ί";
                case 8157:
                    return "῝";
                case 8158:
                    return "῞";
                case 8159:
                    return "῟";
                case 8160:
                    return "ῠ";
                case 8161:
                    return "ῡ";
                case 8162:
                    return "ῢ";
                case 8163:
                    return "ΰ";
                case 8164:
                    return "ῤ";
                case 8165:
                    return "ῥ";
                case 8166:
                    return "ῦ";
                case 8167:
                    return "ῧ";
                case 8168:
                    return "Ῠ";
                case 8169:
                    return "Ῡ";
                case 8170:
                    return "Ὺ";
                case 8171:
                    return "Ύ";
                case 8172:
                    return "Ῥ";
                case 8173:
                    return "῭";
                case 8174:
                    return "΅";
                case 8175:
                    return "`";
                case 8178:
                    return "ῲ";
                case 8179:
                    return "ῳ";
                case 8180:
                    return "ῴ";
                case 8182:
                    return "ῶ";
                case 8183:
                    return "ῷ";
                case 8184:
                    return "Ὸ";
                case 8185:
                    return "Ό";
                case 8186:
                    return "Ὼ";
                case 8187:
                    return "Ώ";
                case 8188:
                    return "ῼ";
                case 8189:
                    return "´";
                case 8192:
                    return " ";
                case 8193:
                    return " ";
                case 8486:
                    return "Ω";
                case 8490:
                    return "K";
                case 8491:
                    return "Å";
                case 8602:
                    return "↚";
                case 8603:
                    return "↛";
                case 8622:
                    return "↮";
                case 8653:
                    return "⇍";
                case 8654:
                    return "⇎";
                case 8655:
                    return "⇏";
                case 8708:
                    return "∄";
                case 8713:
                    return "∉";
                case 8716:
                    return "∌";
                case 8740:
                    return "∤";
                case 8742:
                    return "∦";
                case 8769:
                    return "≁";
                case 8772:
                    return "≄";
                case 8775:
                    return "≇";
                case 8777:
                    return "≉";
                case 8800:
                    return "≠";
                case 8802:
                    return "≢";
                case 8813:
                    return "≭";
                case 8814:
                    return "≮";
                case 8815:
                    return "≯";
                case 8816:
                    return "≰";
                case 8817:
                    return "≱";
                case 8820:
                    return "≴";
                case 8821:
                    return "≵";
                case 8824:
                    return "≸";
                case 8825:
                    return "≹";
                case 8832:
                    return "⊀";
                case 8833:
                    return "⊁";
                case 8836:
                    return "⊄";
                case 8837:
                    return "⊅";
                case 8840:
                    return "⊈";
                case 8841:
                    return "⊉";
                case 8876:
                    return "⊬";
                case 8877:
                    return "⊭";
                case 8878:
                    return "⊮";
                case 8879:
                    return "⊯";
                case 8928:
                    return "⋠";
                case 8929:
                    return "⋡";
                case 8930:
                    return "⋢";
                case 8931:
                    return "⋣";
                case 8938:
                    return "⋪";
                case 8939:
                    return "⋫";
                case 8940:
                    return "⋬";
                case 8941:
                    return "⋭";
                case 9001:
                    return "〈";
                case 9002:
                    return "〉";
                case 10972:
                    return "⫝̸";
                case 12364:
                    return "が";
                case 12366:
                    return "ぎ";
                case 12368:
                    return "ぐ";
                case 12370:
                    return "げ";
                case 12372:
                    return "ご";
                case 12374:
                    return "ざ";
                case 12376:
                    return "じ";
                case 12378:
                    return "ず";
                case 12380:
                    return "ぜ";
                case 12382:
                    return "ぞ";
                case 12384:
                    return "だ";
                case 12386:
                    return "ぢ";
                case 12389:
                    return "づ";
                case 12391:
                    return "で";
                case 12393:
                    return "ど";
                case 12400:
                    return "ば";
                case 12401:
                    return "ぱ";
                case 12403:
                    return "び";
                case 12404:
                    return "ぴ";
                case 12406:
                    return "ぶ";
                case 12407:
                    return "ぷ";
                case 12409:
                    return "べ";
                case 12410:
                    return "ぺ";
                case 12412:
                    return "ぼ";
                case 12413:
                    return "ぽ";
                case 12436:
                    return "ゔ";
                case 12446:
                    return "ゞ";
                case 12460:
                    return "ガ";
                case 12462:
                    return "ギ";
                case 12464:
                    return "グ";
                case 12466:
                    return "ゲ";
                case 12468:
                    return "ゴ";
                case 12470:
                    return "ザ";
                case 12472:
                    return "ジ";
                case 12474:
                    return "ズ";
                case 12476:
                    return "ゼ";
                case 12478:
                    return "ゾ";
                case 12480:
                    return "ダ";
                case 12482:
                    return "ヂ";
                case 12485:
                    return "ヅ";
                case 12487:
                    return "デ";
                case 12489:
                    return "ド";
                case 12496:
                    return "バ";
                case 12497:
                    return "パ";
                case 12499:
                    return "ビ";
                case 12500:
                    return "ピ";
                case 12502:
                    return "ブ";
                case 12503:
                    return "プ";
                case 12505:
                    return "ベ";
                case 12506:
                    return "ペ";
                case 12508:
                    return "ボ";
                case 12509:
                    return "ポ";
                case 12532:
                    return "ヴ";
                case 12535:
                    return "ヷ";
                case 12536:
                    return "ヸ";
                case 12537:
                    return "ヹ";
                case 12538:
                    return "ヺ";
                case 12542:
                    return "ヾ";
                case 63744:
                    return "豈";
                case 63745:
                    return "更";
                case 63746:
                    return "車";
                case 63747:
                    return "賈";
                case 63748:
                    return "滑";
                case 63749:
                    return "串";
                case 63750:
                    return "句";
                case 63751:
                    return "龜";
                case 63752:
                    return "龜";
                case 63753:
                    return "契";
                case 63754:
                    return "金";
                case 63755:
                    return "喇";
                case 63756:
                    return "奈";
                case 63757:
                    return "懶";
                case 63758:
                    return "癩";
                case 63759:
                    return "羅";
                case 63760:
                    return "蘿";
                case 63761:
                    return "螺";
                case 63762:
                    return "裸";
                case 63763:
                    return "邏";
                case 63764:
                    return "樂";
                case 63765:
                    return "洛";
                case 63766:
                    return "烙";
                case 63767:
                    return "珞";
                case 63768:
                    return "落";
                case 63769:
                    return "酪";
                case 63770:
                    return "駱";
                case 63771:
                    return "亂";
                case 63772:
                    return "卵";
                case 63773:
                    return "欄";
                case 63774:
                    return "爛";
                case 63775:
                    return "蘭";
                case 63776:
                    return "鸞";
                case 63777:
                    return "嵐";
                case 63778:
                    return "濫";
                case 63779:
                    return "藍";
                case 63780:
                    return "襤";
                case 63781:
                    return "拉";
                case 63782:
                    return "臘";
                case 63783:
                    return "蠟";
                case 63784:
                    return "廊";
                case 63785:
                    return "朗";
                case 63786:
                    return "浪";
                case 63787:
                    return "狼";
                case 63788:
                    return "郎";
                case 63789:
                    return "來";
                case 63790:
                    return "冷";
                case 63791:
                    return "勞";
                case 63792:
                    return "擄";
                case 63793:
                    return "櫓";
                case 63794:
                    return "爐";
                case 63795:
                    return "盧";
                case 63796:
                    return "老";
                case 63797:
                    return "蘆";
                case 63798:
                    return "虜";
                case 63799:
                    return "路";
                case 63800:
                    return "露";
                case 63801:
                    return "魯";
                case 63802:
                    return "鷺";
                case 63803:
                    return "碌";
                case 63804:
                    return "祿";
                case 63805:
                    return "綠";
                case 63806:
                    return "菉";
                case 63807:
                    return "錄";
                case 63808:
                    return "鹿";
                case 63809:
                    return "論";
                case 63810:
                    return "壟";
                case 63811:
                    return "弄";
                case 63812:
                    return "籠";
                case 63813:
                    return "聾";
                case 63814:
                    return "牢";
                case 63815:
                    return "磊";
                case 63816:
                    return "賂";
                case 63817:
                    return "雷";
                case 63818:
                    return "壘";
                case 63819:
                    return "屢";
                case 63820:
                    return "樓";
                case 63821:
                    return "淚";
                case 63822:
                    return "漏";
                case 63823:
                    return "累";
                case 63824:
                    return "縷";
                case 63825:
                    return "陋";
                case 63826:
                    return "勒";
                case 63827:
                    return "肋";
                case 63828:
                    return "凜";
                case 63829:
                    return "凌";
                case 63830:
                    return "稜";
                case 63831:
                    return "綾";
                case 63832:
                    return "菱";
                case 63833:
                    return "陵";
                case 63834:
                    return "讀";
                case 63835:
                    return "拏";
                case 63836:
                    return "樂";
                case 63837:
                    return "諾";
                case 63838:
                    return "丹";
                case 63839:
                    return "寧";
                case 63840:
                    return "怒";
                case 63841:
                    return "率";
                case 63842:
                    return "異";
                case 63843:
                    return "北";
                case 63844:
                    return "磻";
                case 63845:
                    return "便";
                case 63846:
                    return "復";
                case 63847:
                    return "不";
                case 63848:
                    return "泌";
                case 63849:
                    return "數";
                case 63850:
                    return "索";
                case 63851:
                    return "參";
                case 63852:
                    return "塞";
                case 63853:
                    return "省";
                case 63854:
                    return "葉";
                case 63855:
                    return "說";
                case 63856:
                    return "殺";
                case 63857:
                    return "辰";
                case 63858:
                    return "沈";
                case 63859:
                    return "拾";
                case 63860:
                    return "若";
                case 63861:
                    return "掠";
                case 63862:
                    return "略";
                case 63863:
                    return "亮";
                case 63864:
                    return "兩";
                case 63865:
                    return "凉";
                case 63866:
                    return "梁";
                case 63867:
                    return "糧";
                case 63868:
                    return "良";
                case 63869:
                    return "諒";
                case 63870:
                    return "量";
                case 63871:
                    return "勵";
                case 63872:
                    return "呂";
                case 63873:
                    return "女";
                case 63874:
                    return "廬";
                case 63875:
                    return "旅";
                case 63876:
                    return "濾";
                case 63877:
                    return "礪";
                case 63878:
                    return "閭";
                case 63879:
                    return "驪";
                case 63880:
                    return "麗";
                case 63881:
                    return "黎";
                case 63882:
                    return "力";
                case 63883:
                    return "曆";
                case 63884:
                    return "歷";
                case 63885:
                    return "轢";
                case 63886:
                    return "年";
                case 63887:
                    return "憐";
                case 63888:
                    return "戀";
                case 63889:
                    return "撚";
                case 63890:
                    return "漣";
                case 63891:
                    return "煉";
                case 63892:
                    return "璉";
                case 63893:
                    return "秊";
                case 63894:
                    return "練";
                case 63895:
                    return "聯";
                case 63896:
                    return "輦";
                case 63897:
                    return "蓮";
                case 63898:
                    return "連";
                case 63899:
                    return "鍊";
                case 63900:
                    return "列";
                case 63901:
                    return "劣";
                case 63902:
                    return "咽";
                case 63903:
                    return "烈";
                case 63904:
                    return "裂";
                case 63905:
                    return "說";
                case 63906:
                    return "廉";
                case 63907:
                    return "念";
                case 63908:
                    return "捻";
                case 63909:
                    return "殮";
                case 63910:
                    return "簾";
                case 63911:
                    return "獵";
                case 63912:
                    return "令";
                case 63913:
                    return "囹";
                case 63914:
                    return "寧";
                case 63915:
                    return "嶺";
                case 63916:
                    return "怜";
                case 63917:
                    return "玲";
                case 63918:
                    return "瑩";
                case 63919:
                    return "羚";
                case 63920:
                    return "聆";
                case 63921:
                    return "鈴";
                case 63922:
                    return "零";
                case 63923:
                    return "靈";
                case 63924:
                    return "領";
                case 63925:
                    return "例";
                case 63926:
                    return "禮";
                case 63927:
                    return "醴";
                case 63928:
                    return "隸";
                case 63929:
                    return "惡";
                case 63930:
                    return "了";
                case 63931:
                    return "僚";
                case 63932:
                    return "寮";
                case 63933:
                    return "尿";
                case 63934:
                    return "料";
                case 63935:
                    return "樂";
                case 63936:
                    return "燎";
                case 63937:
                    return "療";
                case 63938:
                    return "蓼";
                case 63939:
                    return "遼";
                case 63940:
                    return "龍";
                case 63941:
                    return "暈";
                case 63942:
                    return "阮";
                case 63943:
                    return "劉";
                case 63944:
                    return "杻";
                case 63945:
                    return "柳";
                case 63946:
                    return "流";
                case 63947:
                    return "溜";
                case 63948:
                    return "琉";
                case 63949:
                    return "留";
                case 63950:
                    return "硫";
                case 63951:
                    return "紐";
                case 63952:
                    return "類";
                case 63953:
                    return "六";
                case 63954:
                    return "戮";
                case 63955:
                    return "陸";
                case 63956:
                    return "倫";
                case 63957:
                    return "崙";
                case 63958:
                    return "淪";
                case 63959:
                    return "輪";
                case 63960:
                    return "律";
                case 63961:
                    return "慄";
                case 63962:
                    return "栗";
                case 63963:
                    return "率";
                case 63964:
                    return "隆";
                case 63965:
                    return "利";
                case 63966:
                    return "吏";
                case 63967:
                    return "履";
                case 63968:
                    return "易";
                case 63969:
                    return "李";
                case 63970:
                    return "梨";
                case 63971:
                    return "泥";
                case 63972:
                    return "理";
                case 63973:
                    return "痢";
                case 63974:
                    return "罹";
                case 63975:
                    return "裏";
                case 63976:
                    return "裡";
                case 63977:
                    return "里";
                case 63978:
                    return "離";
                case 63979:
                    return "匿";
                case 63980:
                    return "溺";
                case 63981:
                    return "吝";
                case 63982:
                    return "燐";
                case 63983:
                    return "璘";
                case 63984:
                    return "藺";
                case 63985:
                    return "隣";
                case 63986:
                    return "鱗";
                case 63987:
                    return "麟";
                case 63988:
                    return "林";
                case 63989:
                    return "淋";
                case 63990:
                    return "臨";
                case 63991:
                    return "立";
                case 63992:
                    return "笠";
                case 63993:
                    return "粒";
                case 63994:
                    return "狀";
                case 63995:
                    return "炙";
                case 63996:
                    return "識";
                case 63997:
                    return "什";
                case 63998:
                    return "茶";
                case 63999:
                    return "刺";
                case 64000:
                    return "切";
                case 64001:
                    return "度";
                case 64002:
                    return "拓";
                case 64003:
                    return "糖";
                case 64004:
                    return "宅";
                case 64005:
                    return "洞";
                case 64006:
                    return "暴";
                case 64007:
                    return "輻";
                case 64008:
                    return "行";
                case 64009:
                    return "降";
                case 64010:
                    return "見";
                case 64011:
                    return "廓";
                case 64012:
                    return "兀";
                case 64013:
                    return "嗀";
                case 64016:
                    return "塚";
                case 64018:
                    return "晴";
                case 64021:
                    return "凞";
                case 64022:
                    return "猪";
                case 64023:
                    return "益";
                case 64024:
                    return "礼";
                case 64025:
                    return "神";
                case 64026:
                    return "祥";
                case 64027:
                    return "福";
                case 64028:
                    return "靖";
                case 64029:
                    return "精";
                case 64030:
                    return "羽";
                case 64032:
                    return "蘒";
                case 64034:
                    return "諸";
                case 64037:
                    return "逸";
                case 64038:
                    return "都";
                case 64042:
                    return "飯";
                case 64043:
                    return "飼";
                case 64044:
                    return "館";
                case 64045:
                    return "鶴";
                case 64048:
                    return "侮";
                case 64049:
                    return "僧";
                case 64050:
                    return "免";
                case 64051:
                    return "勉";
                case 64052:
                    return "勤";
                case 64053:
                    return "卑";
                case 64054:
                    return "喝";
                case 64055:
                    return "嘆";
                case 64056:
                    return "器";
                case 64057:
                    return "塀";
                case 64058:
                    return "墨";
                case 64059:
                    return "層";
                case 64060:
                    return "屮";
                case 64061:
                    return "悔";
                case 64062:
                    return "慨";
                case 64063:
                    return "憎";
                case 64064:
                    return "懲";
                case 64065:
                    return "敏";
                case 64066:
                    return "既";
                case 64067:
                    return "暑";
                case 64068:
                    return "梅";
                case 64069:
                    return "海";
                case 64070:
                    return "渚";
                case 64071:
                    return "漢";
                case 64072:
                    return "煮";
                case 64073:
                    return "爫";
                case 64074:
                    return "琢";
                case 64075:
                    return "碑";
                case 64076:
                    return "社";
                case 64077:
                    return "祉";
                case 64078:
                    return "祈";
                case 64079:
                    return "祐";
                case 64080:
                    return "祖";
                case 64081:
                    return "祝";
                case 64082:
                    return "禍";
                case 64083:
                    return "禎";
                case 64084:
                    return "穀";
                case 64085:
                    return "突";
                case 64086:
                    return "節";
                case 64087:
                    return "練";
                case 64088:
                    return "縉";
                case 64089:
                    return "繁";
                case 64090:
                    return "署";
                case 64091:
                    return "者";
                case 64092:
                    return "臭";
                case 64093:
                    return "艹";
                case 64094:
                    return "艹";
                case 64095:
                    return "著";
                case 64096:
                    return "褐";
                case 64097:
                    return "視";
                case 64098:
                    return "謁";
                case 64099:
                    return "謹";
                case 64100:
                    return "賓";
                case 64101:
                    return "贈";
                case 64102:
                    return "辶";
                case 64103:
                    return "逸";
                case 64104:
                    return "難";
                case 64105:
                    return "響";
                case 64106:
                    return "頻";
                case 64112:
                    return "並";
                case 64113:
                    return "况";
                case 64114:
                    return "全";
                case 64115:
                    return "侀";
                case 64116:
                    return "充";
                case 64117:
                    return "冀";
                case 64118:
                    return "勇";
                case 64119:
                    return "勺";
                case 64120:
                    return "喝";
                case 64121:
                    return "啕";
                case 64122:
                    return "喙";
                case 64123:
                    return "嗢";
                case 64124:
                    return "塚";
                case 64125:
                    return "墳";
                case 64126:
                    return "奄";
                case 64127:
                    return "奔";
                case 64128:
                    return "婢";
                case 64129:
                    return "嬨";
                case 64130:
                    return "廒";
                case 64131:
                    return "廙";
                case 64132:
                    return "彩";
                case 64133:
                    return "徭";
                case 64134:
                    return "惘";
                case 64135:
                    return "慎";
                case 64136:
                    return "愈";
                case 64137:
                    return "憎";
                case 64138:
                    return "慠";
                case 64139:
                    return "懲";
                case 64140:
                    return "戴";
                case 64141:
                    return "揄";
                case 64142:
                    return "搜";
                case 64143:
                    return "摒";
                case 64144:
                    return "敖";
                case 64145:
                    return "晴";
                case 64146:
                    return "朗";
                case 64147:
                    return "望";
                case 64148:
                    return "杖";
                case 64149:
                    return "歹";
                case 64150:
                    return "殺";
                case 64151:
                    return "流";
                case 64152:
                    return "滛";
                case 64153:
                    return "滋";
                case 64154:
                    return "漢";
                case 64155:
                    return "瀞";
                case 64156:
                    return "煮";
                case 64157:
                    return "瞧";
                case 64158:
                    return "爵";
                case 64159:
                    return "犯";
                case 64160:
                    return "猪";
                case 64161:
                    return "瑱";
                case 64162:
                    return "甆";
                case 64163:
                    return "画";
                case 64164:
                    return "瘝";
                case 64165:
                    return "瘟";
                case 64166:
                    return "益";
                case 64167:
                    return "盛";
                case 64168:
                    return "直";
                case 64169:
                    return "睊";
                case 64170:
                    return "着";
                case 64171:
                    return "磌";
                case 64172:
                    return "窱";
                case 64173:
                    return "節";
                case 64174:
                    return "类";
                case 64175:
                    return "絛";
                case 64176:
                    return "練";
                case 64177:
                    return "缾";
                case 64178:
                    return "者";
                case 64179:
                    return "荒";
                case 64180:
                    return "華";
                case 64181:
                    return "蝹";
                case 64182:
                    return "襁";
                case 64183:
                    return "覆";
                case 64184:
                    return "視";
                case 64185:
                    return "調";
                case 64186:
                    return "諸";
                case 64187:
                    return "請";
                case 64188:
                    return "謁";
                case 64189:
                    return "諾";
                case 64190:
                    return "諭";
                case 64191:
                    return "謹";
                case 64192:
                    return "變";
                case 64193:
                    return "贈";
                case 64194:
                    return "輸";
                case 64195:
                    return "遲";
                case 64196:
                    return "醙";
                case 64197:
                    return "鉶";
                case 64198:
                    return "陼";
                case 64199:
                    return "難";
                case 64200:
                    return "靖";
                case 64201:
                    return "韛";
                case 64202:
                    return "響";
                case 64203:
                    return "頋";
                case 64204:
                    return "頻";
                case 64205:
                    return "鬒";
                case 64206:
                    return "龜";
                case 64207:
                    return "\ud84a\udc4a";
                case 64208:
                    return "\ud84a\udc44";
                case 64209:
                    return "\ud84c\udfd5";
                case 64210:
                    return "㮝";
                case 64211:
                    return "䀘";
                case 64212:
                    return "䀹";
                case 64213:
                    return "\ud854\ude49";
                case 64214:
                    return "\ud857\udcd0";
                case 64215:
                    return "\ud85f\uded3";
                case 64216:
                    return "齃";
                case 64217:
                    return "龎";
                case 64285:
                    return "יִ";
                case 64287:
                    return "ײַ";
                case 64298:
                    return "שׁ";
                case 64299:
                    return "שׂ";
                case 64300:
                    return "שּׁ";
                case 64301:
                    return "שּׂ";
                case 64302:
                    return "אַ";
                case 64303:
                    return "אָ";
                case 64304:
                    return "אּ";
                case 64305:
                    return "בּ";
                case 64306:
                    return "גּ";
                case 64307:
                    return "דּ";
                case 64308:
                    return "הּ";
                case 64309:
                    return "וּ";
                case 64310:
                    return "זּ";
                case 64312:
                    return "טּ";
                case 64313:
                    return "יּ";
                case 64314:
                    return "ךּ";
                case 64315:
                    return "כּ";
                case 64316:
                    return "לּ";
                case 64318:
                    return "מּ";
                case 64320:
                    return "נּ";
                case 64321:
                    return "סּ";
                case 64323:
                    return "ףּ";
                case 64324:
                    return "פּ";
                case 64326:
                    return "צּ";
                case 64327:
                    return "קּ";
                case 64328:
                    return "רּ";
                case 64329:
                    return "שּ";
                case 64330:
                    return "תּ";
                case 64331:
                    return "וֹ";
                case 64332:
                    return "בֿ";
                case 64333:
                    return "כֿ";
                case 64334:
                    return "פֿ";
                case 119134:
                    return "\ud834\udd57\ud834\udd65";
                case 119135:
                    return "\ud834\udd58\ud834\udd65";
                case 119136:
                    return "\ud834\udd5f\ud834\udd6e";
                case 119137:
                    return "\ud834\udd5f\ud834\udd6f";
                case 119138:
                    return "\ud834\udd5f\ud834\udd70";
                case 119139:
                    return "\ud834\udd5f\ud834\udd71";
                case 119140:
                    return "\ud834\udd5f\ud834\udd72";
                case 119227:
                    return "\ud834\uddb9\ud834\udd65";
                case 119228:
                    return "\ud834\uddba\ud834\udd65";
                case 119229:
                    return "\ud834\uddbb\ud834\udd6e";
                case 119230:
                    return "\ud834\uddbc\ud834\udd6e";
                case 119231:
                    return "\ud834\uddbb\ud834\udd6f";
                case 119232:
                    return "\ud834\uddbc\ud834\udd6f";
                case 194560:
                    return "丽";
                case 194561:
                    return "丸";
                case 194562:
                    return "乁";
                case 194563:
                    return "\ud840\udd22";
                case 194564:
                    return "你";
                case 194565:
                    return "侮";
                case 194566:
                    return "侻";
                case 194567:
                    return "倂";
                case 194568:
                    return "偺";
                case 194569:
                    return "備";
                case 194570:
                    return "僧";
                case 194571:
                    return "像";
                case 194572:
                    return "㒞";
                case 194573:
                    return "\ud841\ude3a";
                case 194574:
                    return "免";
                case 194575:
                    return "兔";
                case 194576:
                    return "兤";
                case 194577:
                    return "具";
                case 194578:
                    return "\ud841\udd1c";
                case 194579:
                    return "㒹";
                case 194580:
                    return "內";
                case 194581:
                    return "再";
                case 194582:
                    return "\ud841\udd4b";
                case 194583:
                    return "冗";
                case 194584:
                    return "冤";
                case 194585:
                    return "仌";
                case 194586:
                    return "冬";
                case 194587:
                    return "况";
                case 194588:
                    return "\ud864\udddf";
                case 194589:
                    return "凵";
                case 194590:
                    return "刃";
                case 194591:
                    return "㓟";
                case 194592:
                    return "刻";
                case 194593:
                    return "剆";
                case 194594:
                    return "割";
                case 194595:
                    return "剷";
                case 194596:
                    return "㔕";
                case 194597:
                    return "勇";
                case 194598:
                    return "勉";
                case 194599:
                    return "勤";
                case 194600:
                    return "勺";
                case 194601:
                    return "包";
                case 194602:
                    return "匆";
                case 194603:
                    return "北";
                case 194604:
                    return "卉";
                case 194605:
                    return "卑";
                case 194606:
                    return "博";
                case 194607:
                    return "即";
                case 194608:
                    return "卽";
                case 194609:
                    return "卿";
                case 194610:
                    return "卿";
                case 194611:
                    return "卿";
                case 194612:
                    return "\ud842\ude2c";
                case 194613:
                    return "灰";
                case 194614:
                    return "及";
                case 194615:
                    return "叟";
                case 194616:
                    return "\ud842\udf63";
                case 194617:
                    return "叫";
                case 194618:
                    return "叱";
                case 194619:
                    return "吆";
                case 194620:
                    return "咞";
                case 194621:
                    return "吸";
                case 194622:
                    return "呈";
                case 194623:
                    return "周";
                case 194624:
                    return "咢";
                case 194625:
                    return "哶";
                case 194626:
                    return "唐";
                case 194627:
                    return "啓";
                case 194628:
                    return "啣";
                case 194629:
                    return "善";
                case 194630:
                    return "善";
                case 194631:
                    return "喙";
                case 194632:
                    return "喫";
                case 194633:
                    return "喳";
                case 194634:
                    return "嗂";
                case 194635:
                    return "圖";
                case 194636:
                    return "嘆";
                case 194637:
                    return "圗";
                case 194638:
                    return "噑";
                case 194639:
                    return "噴";
                case 194640:
                    return "切";
                case 194641:
                    return "壮";
                case 194642:
                    return "城";
                case 194643:
                    return "埴";
                case 194644:
                    return "堍";
                case 194645:
                    return "型";
                case 194646:
                    return "堲";
                case 194647:
                    return "報";
                case 194648:
                    return "墬";
                case 194649:
                    return "\ud845\udce4";
                case 194650:
                    return "売";
                case 194651:
                    return "壷";
                case 194652:
                    return "夆";
                case 194653:
                    return "多";
                case 194654:
                    return "夢";
                case 194655:
                    return "奢";
                case 194656:
                    return "\ud845\udea8";
                case 194657:
                    return "\ud845\udeea";
                case 194658:
                    return "姬";
                case 194659:
                    return "娛";
                case 194660:
                    return "娧";
                case 194661:
                    return "姘";
                case 194662:
                    return "婦";
                case 194663:
                    return "㛮";
                case 194664:
                    return "㛼";
                case 194665:
                    return "嬈";
                case 194666:
                    return "嬾";
                case 194667:
                    return "嬾";
                case 194668:
                    return "\ud846\uddc8";
                case 194669:
                    return "寃";
                case 194670:
                    return "寘";
                case 194671:
                    return "寧";
                case 194672:
                    return "寳";
                case 194673:
                    return "\ud846\udf18";
                case 194674:
                    return "寿";
                case 194675:
                    return "将";
                case 194676:
                    return "当";
                case 194677:
                    return "尢";
                case 194678:
                    return "㞁";
                case 194679:
                    return "屠";
                case 194680:
                    return "屮";
                case 194681:
                    return "峀";
                case 194682:
                    return "岍";
                case 194683:
                    return "\ud847\udde4";
                case 194684:
                    return "嵃";
                case 194685:
                    return "\ud847\udde6";
                case 194686:
                    return "嵮";
                case 194687:
                    return "嵫";
                case 194688:
                    return "嵼";
                case 194689:
                    return "巡";
                case 194690:
                    return "巢";
                case 194691:
                    return "㠯";
                case 194692:
                    return "巽";
                case 194693:
                    return "帨";
                case 194694:
                    return "帽";
                case 194695:
                    return "幩";
                case 194696:
                    return "㡢";
                case 194697:
                    return "\ud848\udd83";
                case 194698:
                    return "㡼";
                case 194699:
                    return "庰";
                case 194700:
                    return "庳";
                case 194701:
                    return "庶";
                case 194702:
                    return "廊";
                case 194703:
                    return "\ud868\udf92";
                case 194704:
                    return "廾";
                case 194705:
                    return "\ud848\udf31";
                case 194706:
                    return "\ud848\udf31";
                case 194707:
                    return "舁";
                case 194708:
                    return "弢";
                case 194709:
                    return "弢";
                case 194710:
                    return "㣇";
                case 194711:
                    return "\ud84c\udeb8";
                case 194712:
                    return "\ud858\uddda";
                case 194713:
                    return "形";
                case 194714:
                    return "彫";
                case 194715:
                    return "㣣";
                case 194716:
                    return "徚";
                case 194717:
                    return "忍";
                case 194718:
                    return "志";
                case 194719:
                    return "忹";
                case 194720:
                    return "悁";
                case 194721:
                    return "㤺";
                case 194722:
                    return "㤜";
                case 194723:
                    return "悔";
                case 194724:
                    return "\ud849\uded4";
                case 194725:
                    return "惇";
                case 194726:
                    return "慈";
                case 194727:
                    return "慌";
                case 194728:
                    return "慎";
                case 194729:
                    return "慌";
                case 194730:
                    return "慺";
                case 194731:
                    return "憎";
                case 194732:
                    return "憲";
                case 194733:
                    return "憤";
                case 194734:
                    return "憯";
                case 194735:
                    return "懞";
                case 194736:
                    return "懲";
                case 194737:
                    return "懶";
                case 194738:
                    return "成";
                case 194739:
                    return "戛";
                case 194740:
                    return "扝";
                case 194741:
                    return "抱";
                case 194742:
                    return "拔";
                case 194743:
                    return "捐";
                case 194744:
                    return "\ud84a\udf0c";
                case 194745:
                    return "挽";
                case 194746:
                    return "拼";
                case 194747:
                    return "捨";
                case 194748:
                    return "掃";
                case 194749:
                    return "揤";
                case 194750:
                    return "\ud84a\udff1";
                case 194751:
                    return "搢";
                case 194752:
                    return "揅";
                case 194753:
                    return "掩";
                case 194754:
                    return "㨮";
                case 194755:
                    return "摩";
                case 194756:
                    return "摾";
                case 194757:
                    return "撝";
                case 194758:
                    return "摷";
                case 194759:
                    return "㩬";
                case 194760:
                    return "敏";
                case 194761:
                    return "敬";
                case 194762:
                    return "\ud84c\udc0a";
                case 194763:
                    return "旣";
                case 194764:
                    return "書";
                case 194765:
                    return "晉";
                case 194766:
                    return "㬙";
                case 194767:
                    return "暑";
                case 194768:
                    return "㬈";
                case 194769:
                    return "㫤";
                case 194770:
                    return "冒";
                case 194771:
                    return "冕";
                case 194772:
                    return "最";
                case 194773:
                    return "暜";
                case 194774:
                    return "肭";
                case 194775:
                    return "䏙";
                case 194776:
                    return "朗";
                case 194777:
                    return "望";
                case 194778:
                    return "朡";
                case 194779:
                    return "杞";
                case 194780:
                    return "杓";
                case 194781:
                    return "\ud84c\udfc3";
                case 194782:
                    return "㭉";
                case 194783:
                    return "柺";
                case 194784:
                    return "枅";
                case 194785:
                    return "桒";
                case 194786:
                    return "梅";
                case 194787:
                    return "\ud84d\udc6d";
                case 194788:
                    return "梎";
                case 194789:
                    return "栟";
                case 194790:
                    return "椔";
                case 194791:
                    return "㮝";
                case 194792:
                    return "楂";
                case 194793:
                    return "榣";
                case 194794:
                    return "槪";
                case 194795:
                    return "檨";
                case 194796:
                    return "\ud84d\udea3";
                case 194797:
                    return "櫛";
                case 194798:
                    return "㰘";
                case 194799:
                    return "次";
                case 194800:
                    return "\ud84e\udca7";
                case 194801:
                    return "歔";
                case 194802:
                    return "㱎";
                case 194803:
                    return "歲";
                case 194804:
                    return "殟";
                case 194805:
                    return "殺";
                case 194806:
                    return "殻";
                case 194807:
                    return "\ud84e\ude8d";
                case 194808:
                    return "\ud847\udd0b";
                case 194809:
                    return "\ud84e\udefa";
                case 194810:
                    return "汎";
                case 194811:
                    return "\ud84f\udcbc";
                case 194812:
                    return "沿";
                case 194813:
                    return "泍";
                case 194814:
                    return "汧";
                case 194815:
                    return "洖";
                case 194816:
                    return "派";
                case 194817:
                    return "海";
                case 194818:
                    return "流";
                case 194819:
                    return "浩";
                case 194820:
                    return "浸";
                case 194821:
                    return "涅";
                case 194822:
                    return "\ud84f\udd1e";
                case 194823:
                    return "洴";
                case 194824:
                    return "港";
                case 194825:
                    return "湮";
                case 194826:
                    return "㴳";
                case 194827:
                    return "滋";
                case 194828:
                    return "滇";
                case 194829:
                    return "\ud84f\uded1";
                case 194830:
                    return "淹";
                case 194831:
                    return "潮";
                case 194832:
                    return "\ud84f\udf5e";
                case 194833:
                    return "\ud84f\udf8e";
                case 194834:
                    return "濆";
                case 194835:
                    return "瀹";
                case 194836:
                    return "瀞";
                case 194837:
                    return "瀛";
                case 194838:
                    return "㶖";
                case 194839:
                    return "灊";
                case 194840:
                    return "災";
                case 194841:
                    return "灷";
                case 194842:
                    return "炭";
                case 194843:
                    return "\ud841\udd25";
                case 194844:
                    return "煅";
                case 194845:
                    return "\ud850\ude63";
                case 194846:
                    return "熜";
                case 194847:
                    return "\ud850\udfab";
                case 194848:
                    return "爨";
                case 194849:
                    return "爵";
                case 194850:
                    return "牐";
                case 194851:
                    return "\ud851\ude08";
                case 194852:
                    return "犀";
                case 194853:
                    return "犕";
                case 194854:
                    return "\ud851\udf35";
                case 194855:
                    return "\ud852\udc14";
                case 194856:
                    return "獺";
                case 194857:
                    return "王";
                case 194858:
                    return "㺬";
                case 194859:
                    return "玥";
                case 194860:
                    return "㺸";
                case 194861:
                    return "㺸";
                case 194862:
                    return "瑇";
                case 194863:
                    return "瑜";
                case 194864:
                    return "瑱";
                case 194865:
                    return "璅";
                case 194866:
                    return "瓊";
                case 194867:
                    return "㼛";
                case 194868:
                    return "甤";
                case 194869:
                    return "\ud853\udc36";
                case 194870:
                    return "甾";
                case 194871:
                    return "\ud853\udc92";
                case 194872:
                    return "異";
                case 194873:
                    return "\ud848\udd9f";
                case 194874:
                    return "瘐";
                case 194875:
                    return "\ud853\udfa1";
                case 194876:
                    return "\ud853\udfb8";
                case 194877:
                    return "\ud854\udc44";
                case 194878:
                    return "㿼";
                case 194879:
                    return "䀈";
                case 194880:
                    return "直";
                case 194881:
                    return "\ud854\udcf3";
                case 194882:
                    return "\ud854\udcf2";
                case 194883:
                    return "\ud854\udd19";
                case 194884:
                    return "\ud854\udd33";
                case 194885:
                    return "眞";
                case 194886:
                    return "真";
                case 194887:
                    return "真";
                case 194888:
                    return "睊";
                case 194889:
                    return "䀹";
                case 194890:
                    return "瞋";
                case 194891:
                    return "䁆";
                case 194892:
                    return "䂖";
                case 194893:
                    return "\ud855\udc1d";
                case 194894:
                    return "硎";
                case 194895:
                    return "碌";
                case 194896:
                    return "磌";
                case 194897:
                    return "䃣";
                case 194898:
                    return "\ud855\ude26";
                case 194899:
                    return "祖";
                case 194900:
                    return "\ud855\ude9a";
                case 194901:
                    return "\ud855\udec5";
                case 194902:
                    return "福";
                case 194903:
                    return "秫";
                case 194904:
                    return "䄯";
                case 194905:
                    return "穀";
                case 194906:
                    return "穊";
                case 194907:
                    return "穏";
                case 194908:
                    return "\ud856\udd7c";
                case 194909:
                    return "\ud856\udea7";
                case 194910:
                    return "\ud856\udea7";
                case 194911:
                    return "竮";
                case 194912:
                    return "䈂";
                case 194913:
                    return "\ud856\udfab";
                case 194914:
                    return "篆";
                case 194915:
                    return "築";
                case 194916:
                    return "䈧";
                case 194917:
                    return "\ud857\udc80";
                case 194918:
                    return "糒";
                case 194919:
                    return "䊠";
                case 194920:
                    return "糨";
                case 194921:
                    return "糣";
                case 194922:
                    return "紀";
                case 194923:
                    return "\ud857\udf86";
                case 194924:
                    return "絣";
                case 194925:
                    return "䌁";
                case 194926:
                    return "緇";
                case 194927:
                    return "縂";
                case 194928:
                    return "繅";
                case 194929:
                    return "䌴";
                case 194930:
                    return "\ud858\ude28";
                case 194931:
                    return "\ud858\ude47";
                case 194932:
                    return "䍙";
                case 194933:
                    return "\ud858\uded9";
                case 194934:
                    return "罺";
                case 194935:
                    return "\ud858\udf3e";
                case 194936:
                    return "羕";
                case 194937:
                    return "翺";
                case 194938:
                    return "者";
                case 194939:
                    return "\ud859\udcda";
                case 194940:
                    return "\ud859\udd23";
                case 194941:
                    return "聠";
                case 194942:
                    return "\ud859\udda8";
                case 194943:
                    return "聰";
                case 194944:
                    return "\ud84c\udf5f";
                case 194945:
                    return "䏕";
                case 194946:
                    return "育";
                case 194947:
                    return "脃";
                case 194948:
                    return "䐋";
                case 194949:
                    return "脾";
                case 194950:
                    return "媵";
                case 194951:
                    return "\ud859\udfa7";
                case 194952:
                    return "\ud859\udfb5";
                case 194953:
                    return "\ud84c\udf93";
                case 194954:
                    return "\ud84c\udf9c";
                case 194955:
                    return "舁";
                case 194956:
                    return "舄";
                case 194957:
                    return "辞";
                case 194958:
                    return "䑫";
                case 194959:
                    return "芑";
                case 194960:
                    return "芋";
                case 194961:
                    return "芝";
                case 194962:
                    return "劳";
                case 194963:
                    return "花";
                case 194964:
                    return "芳";
                case 194965:
                    return "芽";
                case 194966:
                    return "苦";
                case 194967:
                    return "\ud85a\udf3c";
                case 194968:
                    return "若";
                case 194969:
                    return "茝";
                case 194970:
                    return "荣";
                case 194971:
                    return "莭";
                case 194972:
                    return "茣";
                case 194973:
                    return "莽";
                case 194974:
                    return "菧";
                case 194975:
                    return "著";
                case 194976:
                    return "荓";
                case 194977:
                    return "菊";
                case 194978:
                    return "菌";
                case 194979:
                    return "菜";
                case 194980:
                    return "\ud85b\udc36";
                case 194981:
                    return "\ud85b\udd6b";
                case 194982:
                    return "\ud85b\udcd5";
                case 194983:
                    return "䔫";
                case 194984:
                    return "蓱";
                case 194985:
                    return "蓳";
                case 194986:
                    return "蔖";
                case 194987:
                    return "\ud85c\udfca";
                case 194988:
                    return "蕤";
                case 194989:
                    return "\ud85b\udf2c";
                case 194990:
                    return "䕝";
                case 194991:
                    return "䕡";
                case 194992:
                    return "\ud85b\udfb1";
                case 194993:
                    return "\ud85c\udcd2";
                case 194994:
                    return "䕫";
                case 194995:
                    return "虐";
                case 194996:
                    return "虜";
                case 194997:
                    return "虧";
                case 194998:
                    return "虩";
                case 194999:
                    return "蚩";
                case 195000:
                    return "蚈";
                case 195001:
                    return "蜎";
                case 195002:
                    return "蛢";
                case 195003:
                    return "蝹";
                case 195004:
                    return "蜨";
                case 195005:
                    return "蝫";
                case 195006:
                    return "螆";
                case 195007:
                    return "䗗";
                case 195008:
                    return "蟡";
                case 195009:
                    return "蠁";
                case 195010:
                    return "䗹";
                case 195011:
                    return "衠";
                case 195012:
                    return "衣";
                case 195013:
                    return "\ud85d\ude67";
                case 195014:
                    return "裗";
                case 195015:
                    return "裞";
                case 195016:
                    return "䘵";
                case 195017:
                    return "裺";
                case 195018:
                    return "㒻";
                case 195019:
                    return "\ud85e\udcae";
                case 195020:
                    return "\ud85e\udd66";
                case 195021:
                    return "䚾";
                case 195022:
                    return "䛇";
                case 195023:
                    return "誠";
                case 195024:
                    return "諭";
                case 195025:
                    return "變";
                case 195026:
                    return "豕";
                case 195027:
                    return "\ud85f\udca8";
                case 195028:
                    return "貫";
                case 195029:
                    return "賁";
                case 195030:
                    return "贛";
                case 195031:
                    return "起";
                case 195032:
                    return "\ud85f\udf2f";
                case 195033:
                    return "\ud842\udc04";
                case 195034:
                    return "跋";
                case 195035:
                    return "趼";
                case 195036:
                    return "跰";
                case 195037:
                    return "\ud842\udcde";
                case 195038:
                    return "軔";
                case 195039:
                    return "輸";
                case 195040:
                    return "\ud861\uddd2";
                case 195041:
                    return "\ud861\udded";
                case 195042:
                    return "邔";
                case 195043:
                    return "郱";
                case 195044:
                    return "鄑";
                case 195045:
                    return "\ud861\udf2e";
                case 195046:
                    return "鄛";
                case 195047:
                    return "鈸";
                case 195048:
                    return "鋗";
                case 195049:
                    return "鋘";
                case 195050:
                    return "鉼";
                case 195051:
                    return "鏹";
                case 195052:
                    return "鐕";
                case 195053:
                    return "\ud862\udffa";
                case 195054:
                    return "開";
                case 195055:
                    return "䦕";
                case 195056:
                    return "閷";
                case 195057:
                    return "\ud863\udd77";
                case 195058:
                    return "䧦";
                case 195059:
                    return "雃";
                case 195060:
                    return "嶲";
                case 195061:
                    return "霣";
                case 195062:
                    return "\ud864\udd45";
                case 195063:
                    return "\ud864\ude1a";
                case 195064:
                    return "䩮";
                case 195065:
                    return "䩶";
                case 195066:
                    return "韠";
                case 195067:
                    return "\ud865\udc0a";
                case 195068:
                    return "䪲";
                case 195069:
                    return "\ud865\udc96";
                case 195070:
                    return "頋";
                case 195071:
                    return "頋";
                case 195072:
                    return "頩";
                case 195073:
                    return "\ud865\uddb6";
                case 195074:
                    return "飢";
                case 195075:
                    return "䬳";
                case 195076:
                    return "餩";
                case 195077:
                    return "馧";
                case 195078:
                    return "駂";
                case 195079:
                    return "駾";
                case 195080:
                    return "䯎";
                case 195081:
                    return "\ud866\udf30";
                case 195082:
                    return "鬒";
                case 195083:
                    return "鱀";
                case 195084:
                    return "鳽";
                case 195085:
                    return "䳎";
                case 195086:
                    return "䳭";
                case 195087:
                    return "鵧";
                case 195088:
                    return "\ud868\udcce";
                case 195089:
                    return "䳸";
                case 195090:
                    return "\ud868\udd05";
                case 195091:
                    return "\ud868\ude0e";
                case 195092:
                    return "\ud868\ude91";
                case 195093:
                    return "麻";
                case 195094:
                    return "䵖";
                case 195095:
                    return "黹";
                case 195096:
                    return "黾";
                case 195097:
                    return "鼅";
                case 195098:
                    return "鼏";
                case 195099:
                    return "鼖";
                case 195100:
                    return "鼻";
                case 195101:
                    return "\ud869\ude00";
                default:
                    if (var0 <= 65535) {
                        return String.valueOf((char)var0);
                    } else {
                        StringBuffer var1 = new StringBuffer(2);
                        var1.append(getHighSurrogate(var0));
                        var1.append(getLowSurrogate(var0));
                        return var1.toString();
                    }
            }
        }
    }

    private static String decomposeHangul(char var0) {
        int var1 = var0 - '가';
        if (var1 >= 0 && var1 < 11172) {
            int var2 = 4352 + var1 / 588;
            int var3 = 4449 + var1 % 588 / 28;
            int var4 = 4519 + var1 % 28;
            StringBuffer var5 = new StringBuffer(3);
            var5.append((char)var2);
            var5.append((char)var3);
            if (var4 != 4519) {
                var5.append((char)var4);
            }

            return var5.toString();
        } else {
            return String.valueOf(var0);
        }
    }

    private static String composeHangul(String var0) {
        int var1 = var0.length();
        if (var1 == 0) {
            return "";
        } else {
            StringBuffer var2 = new StringBuffer(var1);
            char var3 = var0.charAt(0);
            var2.append(var3);

            for(int var4 = 1; var4 < var1; ++var4) {
                char var5 = var0.charAt(var4);
                int var6 = var3 - 4352;
                int var7;
                if (0 <= var6 && var6 < 19) {
                    var7 = var5 - 4449;
                    if (var7 >= 0 && var7 < 21) {
                        var3 = (char)('가' + (var6 * 21 + var7) * 28);
                        var2.setCharAt(var2.length() - 1, var3);
                        continue;
                    }
                }

                var7 = var3 - '가';
                if (var7 >= 0 && var7 < 11172 && var7 % 28 == 0) {
                    int var8 = var5 - 4519;
                    if (var8 >= 0 && var8 <= 28) {
                        var3 = (char)(var3 + var8);
                        var2.setCharAt(var2.length() - 1, var3);
                        continue;
                    }
                }

                var3 = var5;
                var2.append(var5);
            }

            return var2.toString();
        }
    }

    private static class UnicodeString {
        private int[] data;
        private int size = 0;

        UnicodeString(String var1) {
            int var2 = var1.length();
            this.data = new int[var2];
            int var3 = 0;

            for(int var4 = 0; var4 < var2; ++var4) {
                char var5 = var1.charAt(var4);
                int var6 = var5;
                if (isHighSurrogate(var5)) {
                    ++var4;
                    var6 = combineSurrogatePair(var5, var1.charAt(var4));
                }

                this.data[var3] = var6;
                ++var3;
            }

            this.size = var3;
        }

        UnicodeString(int var1) {
            this.size = 0;
            this.data = new int[var1];
        }

        nu.xom.UnicodeUtil.UnicodeString decompose() {
            nu.xom.UnicodeUtil.UnicodeString var1 = new nu.xom.UnicodeUtil.UnicodeString(this.size);

            int var2;
            int var3;
            for(var2 = 0; var2 < this.size; ++var2) {
                var3 = this.data[var2];
                String var4 = nu.xom.UnicodeUtil.decompose(var3);
                var1.append(var4);
            }

            for(var2 = 0; var2 < var1.size - 1; ++var2) {
                var3 = var1.data[var2];
                int var7 = var1.data[var2 + 1];
                int var5 = getCombiningClass(var7);
                if (var5 != 0) {
                    int var6 = getCombiningClass(var3);
                    if (var6 > var5) {
                        var1.data[var2] = var7;
                        var1.data[var2 + 1] = var3;
                        var2 -= 2;
                        if (var2 == -2) {
                            var2 = -1;
                        }
                    }
                }
            }

            return var1;
        }

        nu.xom.UnicodeUtil.UnicodeString compose() {
            if (compositions == null) {
                loadCompositions();
            }

            nu.xom.UnicodeUtil.UnicodeString var1 = new nu.xom.UnicodeUtil.UnicodeString(this.size);
            int var2 = -1;
            int var3 = -1;
            int var4 = -1;

            for(int var5 = 0; var5 < this.size; ++var5) {
                int var6 = this.data[var5];
                if (var2 != -1 && !this.isBlocked(var3, var5)) {
                    int var7 = composeCharacter(var2, var6);
                    if (var7 == -1) {
                        var1.append(var6);
                        if (isStarter(var6)) {
                            var2 = var6;
                            var3 = var5;
                            var4 = var1.size - 1;
                        }
                    } else {
                        var2 = var7;
                        this.data[var3] = var7;
                        this.data[var5] = 0;
                        var1.data[var4] = var7;
                    }
                } else {
                    var1.append(var6);
                    if (isStarter(var6)) {
                        var2 = var6;
                        var3 = var5;
                        var4 = var1.size - 1;
                    }
                }
            }

            return var1;
        }

        void append(String var1) {
            for(int var2 = 0; var2 < var1.length(); ++var2) {
                char var3 = var1.charAt(var2);
                if (isHighSurrogate(var3)) {
                    this.append(combineSurrogatePair(var3, var1.charAt(var2 + 1)));
                    ++var2;
                } else {
                    this.append(var3);
                }
            }

        }

        void append(int var1) {
            if (this.size < this.data.length - 1) {
                this.data[this.size] = var1;
                ++this.size;
            } else {
                int[] var2 = new int[this.data.length + 10];
                System.arraycopy(this.data, 0, var2, 0, this.size);
                this.data = var2;
                this.append(var1);
            }

        }

        public String toString() {
            StringBuffer var1 = new StringBuffer();

            for(int var2 = 0; var2 < this.size; ++var2) {
                int var3 = this.data[var2];
                if (var3 <= 65535) {
                    var1.append((char)var3);
                } else {
                    var1.append(makeSurrogatePair(var3));
                }
            }

            return var1.toString();
        }

        private boolean isBlocked(int var1, int var2) {
            int var3 = getCombiningClass(this.data[var2]);

            for(int var4 = var1 + 1; var4 < var2; ++var4) {
                if (this.data[var4] != 0 && var3 == getCombiningClass(this.data[var4])) {
                    return true;
                }
            }

            return false;
        }
    }
}
