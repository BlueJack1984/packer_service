//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nu.xom;

import java.io.UnsupportedEncodingException;

class URIUtil {
    URIUtil() {
    }

    static boolean isOpaque(String var0) {
        int var1 = var0.indexOf(58);
        if (var0.substring(var1 + 1).startsWith("/")) {
            return false;
        } else {
            return Verifier.isAlpha(var0.charAt(0));
        }
    }

    static boolean isAbsolute(String var0) {
        int var1 = var0.indexOf(58);
        return var1 >= 1;
    }

    static String absolutize(String var0, String var1) {
        if (!"".equals(var0) && var0 != null) {
            nu.xom.URIUtil.ParsedURI var2 = new nu.xom.URIUtil.ParsedURI(var0);
            if (var2.path.endsWith("/..")) {
                var2.path = var2.path + '/';
            }

            nu.xom.URIUtil.ParsedURI var3 = new nu.xom.URIUtil.ParsedURI(var1);
            nu.xom.URIUtil.ParsedURI var4 = new nu.xom.URIUtil.ParsedURI();
            if (var3.authority != null) {
                var4.authority = var3.authority;
                var4.query = var3.query;
                var4.path = removeDotSegments(var3.path);
            } else {
                if ("".equals(var3.path)) {
                    var4.path = var2.path;
                    if (var3.query != null) {
                        var4.query = var3.query;
                    } else {
                        var4.query = var2.query;
                    }
                } else {
                    if (var3.path.startsWith("/")) {
                        var4.path = removeDotSegments(var3.path);
                    } else {
                        var4.path = merge(var2, var3.path);
                        var4.path = removeDotSegments(var4.path);
                    }

                    var4.query = var3.query;
                }

                var4.authority = var2.authority;
            }

            var4.scheme = var2.scheme;
            var4.fragment = var3.fragment;
            return var4.toString();
        } else {
            return var1;
        }
    }

    private static String merge(nu.xom.URIUtil.ParsedURI var0, String var1) {
        if (var0.authority != null && "".equals(var0.path) && !"".equals(var0.authority)) {
            return "/" + var1;
        } else {
            int var2 = var0.path.lastIndexOf(47);
            if (var2 == -1) {
                return var1;
            } else {
                String var3 = var0.path.substring(0, var2 + 1);
                return var3 + var1;
            }
        }
    }

    static String removeDotSegments(String var0) {
        StringBuffer var1 = new StringBuffer();

        while(var0.length() > 0) {
            if (var0.startsWith("/./")) {
                var0 = '/' + var0.substring(3);
            } else if (var0.equals("/.")) {
                var0 = "/";
            } else {
                int var2;
                if (var0.startsWith("/../")) {
                    var0 = '/' + var0.substring(4);
                    var2 = var1.toString().lastIndexOf(47);
                    if (var2 != -1) {
                        var1.setLength(var2);
                    }
                } else if (var0.equals("/..")) {
                    var0 = "/";
                    var2 = var1.toString().lastIndexOf(47);
                    if (var2 != -1) {
                        var1.setLength(var2);
                    }
                } else {
                    var2 = var0.indexOf(47);
                    if (var2 == 0) {
                        var2 = var0.indexOf(47, 1);
                    }

                    if (var2 == -1) {
                        var1.append(var0);
                        var0 = "";
                    } else {
                        var1.append(var0.substring(0, var2));
                        var0 = var0.substring(var2);
                    }
                }
            }
        }

        return var1.toString();
    }

    static String toURI(String var0) {
        int var1 = var0.length();
        StringBuffer var2 = new StringBuffer(var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            char var4 = var0.charAt(var3);
            switch(var4) {
                case ' ':
                    var2.append("%20");
                    break;
                case '!':
                    var2.append(var4);
                    break;
                case '"':
                    var2.append("%22");
                    break;
                case '#':
                    var2.append(var4);
                    break;
                case '$':
                    var2.append(var4);
                    break;
                case '%':
                    var2.append(var4);
                    break;
                case '&':
                    var2.append(var4);
                    break;
                case '\'':
                    var2.append(var4);
                    break;
                case '(':
                    var2.append(var4);
                    break;
                case ')':
                    var2.append(var4);
                    break;
                case '*':
                    var2.append(var4);
                    break;
                case '+':
                    var2.append(var4);
                    break;
                case ',':
                    var2.append(var4);
                    break;
                case '-':
                    var2.append(var4);
                    break;
                case '.':
                    var2.append(var4);
                    break;
                case '/':
                    var2.append(var4);
                    break;
                case '0':
                    var2.append(var4);
                    break;
                case '1':
                    var2.append(var4);
                    break;
                case '2':
                    var2.append(var4);
                    break;
                case '3':
                    var2.append(var4);
                    break;
                case '4':
                    var2.append(var4);
                    break;
                case '5':
                    var2.append(var4);
                    break;
                case '6':
                    var2.append(var4);
                    break;
                case '7':
                    var2.append(var4);
                    break;
                case '8':
                    var2.append(var4);
                    break;
                case '9':
                    var2.append(var4);
                    break;
                case ':':
                    var2.append(var4);
                    break;
                case ';':
                    var2.append(var4);
                    break;
                case '<':
                    var2.append("%3C");
                    break;
                case '=':
                    var2.append(var4);
                    break;
                case '>':
                    var2.append("%3E");
                    break;
                case '?':
                    var2.append(var4);
                    break;
                case '@':
                    var2.append(var4);
                    break;
                case 'A':
                    var2.append(var4);
                    break;
                case 'B':
                    var2.append(var4);
                    break;
                case 'C':
                    var2.append(var4);
                    break;
                case 'D':
                    var2.append(var4);
                    break;
                case 'E':
                    var2.append(var4);
                    break;
                case 'F':
                    var2.append(var4);
                    break;
                case 'G':
                    var2.append(var4);
                    break;
                case 'H':
                    var2.append(var4);
                    break;
                case 'I':
                    var2.append(var4);
                    break;
                case 'J':
                    var2.append(var4);
                    break;
                case 'K':
                    var2.append(var4);
                    break;
                case 'L':
                    var2.append(var4);
                    break;
                case 'M':
                    var2.append(var4);
                    break;
                case 'N':
                    var2.append(var4);
                    break;
                case 'O':
                    var2.append(var4);
                    break;
                case 'P':
                    var2.append(var4);
                    break;
                case 'Q':
                    var2.append(var4);
                    break;
                case 'R':
                    var2.append(var4);
                    break;
                case 'S':
                    var2.append(var4);
                    break;
                case 'T':
                    var2.append(var4);
                    break;
                case 'U':
                    var2.append(var4);
                    break;
                case 'V':
                    var2.append(var4);
                    break;
                case 'W':
                    var2.append(var4);
                    break;
                case 'X':
                    var2.append(var4);
                    break;
                case 'Y':
                    var2.append(var4);
                    break;
                case 'Z':
                    var2.append(var4);
                    break;
                case '[':
                    var2.append(var4);
                    break;
                case '\\':
                    var2.append("%5C");
                    break;
                case ']':
                    var2.append(var4);
                    break;
                case '^':
                    var2.append("%5E");
                    break;
                case '_':
                    var2.append(var4);
                    break;
                case '`':
                    var2.append("%60");
                    break;
                case 'a':
                    var2.append(var4);
                    break;
                case 'b':
                    var2.append(var4);
                    break;
                case 'c':
                    var2.append(var4);
                    break;
                case 'd':
                    var2.append(var4);
                    break;
                case 'e':
                    var2.append(var4);
                    break;
                case 'f':
                    var2.append(var4);
                    break;
                case 'g':
                    var2.append(var4);
                    break;
                case 'h':
                    var2.append(var4);
                    break;
                case 'i':
                    var2.append(var4);
                    break;
                case 'j':
                    var2.append(var4);
                    break;
                case 'k':
                    var2.append(var4);
                    break;
                case 'l':
                    var2.append(var4);
                    break;
                case 'm':
                    var2.append(var4);
                    break;
                case 'n':
                    var2.append(var4);
                    break;
                case 'o':
                    var2.append(var4);
                    break;
                case 'p':
                    var2.append(var4);
                    break;
                case 'q':
                    var2.append(var4);
                    break;
                case 'r':
                    var2.append(var4);
                    break;
                case 's':
                    var2.append(var4);
                    break;
                case 't':
                    var2.append(var4);
                    break;
                case 'u':
                    var2.append(var4);
                    break;
                case 'v':
                    var2.append(var4);
                    break;
                case 'w':
                    var2.append(var4);
                    break;
                case 'x':
                    var2.append(var4);
                    break;
                case 'y':
                    var2.append(var4);
                    break;
                case 'z':
                    var2.append(var4);
                    break;
                case '{':
                    var2.append("%7B");
                    break;
                case '|':
                    var2.append("%7C");
                    break;
                case '}':
                    var2.append("%7D");
                    break;
                case '~':
                    var2.append(var4);
                    break;
                default:
                    var2.append(percentEscape(var4));
            }
        }

        return var2.toString();
    }

    static String percentEscape(char var0) {
        StringBuffer var1 = new StringBuffer(3);
        String var2 = String.valueOf(var0);

        try {
            byte[] var3 = var2.getBytes("UTF8");

            for(int var4 = 0; var4 < var3.length; ++var4) {
                var1.append('%');
                String var5 = Integer.toHexString(var3[var4]).toUpperCase();
                if (var0 < 16) {
                    var1.append('0');
                    var1.append(var5);
                } else {
                    var1.append(var5.substring(var5.length() - 2));
                }
            }

            return var1.toString();
        } catch (UnsupportedEncodingException var6) {
            throw new RuntimeException("Broken VM: does not recognize UTF-8 encoding");
        }
    }

    static String relativize(String var0, String var1) {
        nu.xom.URIUtil.ParsedURI var2 = new nu.xom.URIUtil.ParsedURI(var0);
        nu.xom.URIUtil.ParsedURI var3 = new nu.xom.URIUtil.ParsedURI(var1);
        var2.path = removeDotSegments(var2.path);
        if (var2.scheme.equals(var3.scheme) && var2.authority.equals(var3.authority)) {
            String var4 = var2.path;
            String var5 = var3.path;

            do {
                if (var4.length() <= 1) {
                    return var5;
                }

                var4 = var4.substring(0, var4.lastIndexOf(47));
            } while(!var5.startsWith(var4));

            return var5.substring(var4.length() + 1);
        } else {
            return var1;
        }
    }

    static class ParsedURI {
        String scheme;
        String schemeSpecificPart;
        String query;
        String fragment;
        String authority;
        String path = "";

        ParsedURI(String var1) {
            int var2 = var1.indexOf(58);
            int var3 = var1.lastIndexOf(35);
            int var4;
            if (var3 == -1) {
                var4 = var1.indexOf(63);
            } else {
                var4 = var1.substring(0, var3).indexOf(63);
            }

            if (var2 != -1) {
                this.scheme = var1.substring(0, var2);
            }

            if (var4 == -1 && var3 == -1) {
                this.schemeSpecificPart = var1.substring(var2 + 1);
            } else {
                MalformedURIException var5;
                if (var4 != -1) {
                    if (var4 < var2) {
                        var5 = new MalformedURIException("Unparseable URI");
                        var5.setData(var1);
                        throw var5;
                    }

                    this.schemeSpecificPart = var1.substring(var2 + 1, var4);
                } else {
                    if (var3 < var2) {
                        var5 = new MalformedURIException("Unparseable URI");
                        var5.setData(var1);
                        throw var5;
                    }

                    this.schemeSpecificPart = var1.substring(var2 + 1, var3);
                }
            }

            if (var3 != -1) {
                this.fragment = var1.substring(var3 + 1);
            }

            if (var4 != -1) {
                if (var3 == -1) {
                    this.query = var1.substring(var4 + 1);
                } else {
                    this.query = var1.substring(var4 + 1, var3);
                }
            }

            if (this.schemeSpecificPart.startsWith("//")) {
                byte var7 = 2;
                int var6 = this.schemeSpecificPart.indexOf(47, var7);
                if (var6 == -1) {
                    this.authority = this.schemeSpecificPart.substring(2);
                    this.path = "";
                } else {
                    this.authority = this.schemeSpecificPart.substring(var7, var6);
                    this.path = this.schemeSpecificPart.substring(var6);
                }
            } else {
                this.path = this.schemeSpecificPart;
            }

        }

        ParsedURI() {
        }

        public String toString() {
            StringBuffer var1 = new StringBuffer(30);
            if (this.scheme != null) {
                var1.append(this.scheme);
                var1.append(':');
            }

            if (this.schemeSpecificPart != null) {
                var1.append(this.schemeSpecificPart);
            } else {
                var1.append("//");
                if (this.authority != null) {
                    var1.append(this.authority);
                }

                var1.append(this.path);
            }

            if (this.query != null) {
                var1.append('?');
                var1.append(this.query);
            }

            if (this.fragment != null) {
                var1.append('#');
                var1.append(this.fragment);
            }

            return var1.toString();
        }
    }
}
