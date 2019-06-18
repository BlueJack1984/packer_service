package nu.xom;


class CDATASection extends Text {
    CDATASection(Text var1) {
        super(var1);
    }

    CDATASection(String var1) {
        super(var1);
    }

    boolean isCDATASection() {
        return true;
    }

    static Text build(String var0) {
        return new nu.xom.CDATASection(var0);
    }

    String escapeText() {
        String var1 = this.getValue();
        return var1.indexOf("]]>") != -1 ? super.escapeText() : "<![CDATA[" + var1 + "]]>";
    }
}
