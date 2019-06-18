package com.packer.commons.sms.serialize;


import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.Map;
import org.apache.log4j.Logger;

public class TemplateUtil {
    private static Configuration cfg = new Configuration();
    private static Logger log = Logger.getLogger(TemplateUtil.class);

    static {
        cfg.setClassForTemplateLoading(TemplateUtil.class, "");
        cfg.setDefaultEncoding("UTF-8");
    }

    public TemplateUtil() {
    }

    public static String getBody(Map root, String ftl) {
        root.put("typeOfAtom", new TypeOfAtomFieldFunction());
        root.put("propOf", new PropertiesOfExpointer());
        String bodyString = "";

        try {
            Template t = cfg.getTemplate(ftl);
            StringWriter out = new StringWriter();
            t.process(root, out);
            out.flush();
            bodyString = out.toString();
            out.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return bodyString;
    }
}
